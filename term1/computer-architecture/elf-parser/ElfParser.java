import java.io.*;
import java.nio.charset.*;
import java.util.*;
import static java.lang.Integer.*;

public class ElfParser {
    BufferedWriter writer;
    HashMap<Integer, SymtabLine> dictionary = new HashMap<>();
    List<SymtabLine> symtab = new ArrayList<>();
    private int[] elf;
    private int osS, osT, arT, offsT, szT, offsS, szS;
    public ElfParser(String[] args) throws Exception {
        String exc = "Incorrect arguments";
        if (args.length != 2) {
            throw new Exception(exc);
        }
        if (args[0] == null) {
            throw new Exception(exc);
        }
        if (args[1] == null) {
            throw new Exception(exc);
        }
        try {
            byte[] temp;
            FileInputStream input = new FileInputStream(args[0]);
            temp = input.readAllBytes();
            elf = new int[temp.length];
            for (int i = 0; i < elf.length; i++) {
                elf[i] = temp[i];
                if (elf[i] < 0) {
                    elf[i] += 256;
                }
            }
            int e_shoff = get(32, 4);
            int e_shentsize = get(46, 2);
            int e_shnum = get(48, 2);
            int e_shstrndx = get(50, 2);
            osS = get(e_shoff + e_shentsize * e_shstrndx + 16, 4);
            for (int i = 0; i < e_shnum; i++) {
                final int sh_name = get(e_shoff + e_shentsize * i, 4);
                final int sh_addr = get(e_shoff + e_shentsize * i + 12, 4);
                final int sh_offset = get(e_shoff + e_shentsize * i + 16, 4);
                final int sh_size = get(e_shoff + e_shentsize * i + 20, 4);
                String name;
                if (osS + sh_name == osS) {
                    name = "";
                } else {
                    StringBuilder s = new StringBuilder();
                    int x = 0;
                    while (elf[osS + sh_name + x] != 0) {
                        s.append((char) elf[osS + sh_name + x]);
                        x++;
                    }
                    name = s.toString();
                }
                if (".text".equals(name)) {
                    arT = sh_addr;
                    offsT = sh_offset;
                    szT = sh_size;
                }
                if (".symtab".equals(name)) {
                    offsS = sh_offset;
                    szS = sh_size;
                }
                if (".strtab".equals(name)) {
                    osT = sh_offset;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input file exception: " + e.getMessage());
        }
        try {
            writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Output file exception: " + e.getMessage());
        }
    }
    public void parse () throws Exception {
        int offset = offsS;
        for (int i = 0; i < szS / 16; i++) {
            int sym_offset = offset + 16 * i;
            String name;
            if (osT + get(sym_offset, 4) == osS) {
                name = "";
            } else {
                StringBuilder s = new StringBuilder();
                int x = 0;
                while (elf[osT + get(sym_offset, 4) + x] != 0) {
                    s.append((char) elf[osT + get(sym_offset, 4) + x]);
                    x++;
                }
                name = s.toString();
            }
            SymtabLine sym = new SymtabLine(i, name, get(sym_offset + 4, 4),
                    get(sym_offset + 8, 4), get(sym_offset + 12, 1),
                    get(sym_offset + 13, 1), get(sym_offset + 14, 2));
            dictionary.put(sym.x, sym);
            symtab.add(sym);
        }
        try{
            writer.write(".text" + System.lineSeparator());
            for (int i = 0, ind = offsT; i < szT; i += 4, ind += 4) {
                var sym = dictionary.get(arT + i);
                if (sym != null && "FUNC".equals(sym.type)) {
                    writer.write(String.format("%08x   <%s>:", arT + i, sym.nm) + System.lineSeparator());
                }
                String com = toBitString(get(ind + 3, 1)) + toBitString(get(ind + 2, 1)) +
                        toBitString(get(ind + 1, 1)) + toBitString(get(ind, 1));
                writer.write(String.format("   %05x: \t%08x\t", arT + i, parseUnsignedInt(com, 2)));
                try {
                    writer.write(parseCommand(com) + System.lineSeparator());
                } catch (RuntimeException e) {
                    writer.write("unknown" + System.lineSeparator());
                }
            }
            writer.newLine();
            writer.write(".symtab" + System.lineSeparator() + "Symbol Value            \t" +
                    "Size Type \tBind  \tVis    \tIndex   Name" + System.lineSeparator());
            for (var sym: symtab) {
                writer.write(sym.line + System.lineSeparator());
            }
        } catch(final IOException e) {
            throw new Exception("Writing exception: " + e.getMessage());
        }
        try{
            writer.close();
        } catch(final IOException e) {
            throw new Exception("Output file closing exception: " + e.getMessage());
        }
    }
    String parseCommand(final String s) throws Exception {
        if ("00000000000000000000000001110011".equals(s)) {
            return String.format("%7s", "ecall");
        } else if ("00000000000100000000000001110011".equals(s)){
            return String.format("%7s", "ebreak");
        }
        String repeat = String.valueOf(s.charAt(0)).repeat(20);
        final String s1 = repeat + s.substring(0, 12);
        return switch (s.substring(25, 32)) {
            case "0110111", "0010111" -> String.format("%7s\t%s, %s", switch (s.substring(25, 32)) {
                case "0010111" -> "auipc";
                case "0110111" -> "lui";
                default -> throw new Exception("Unknown instruction");
            },  getRegister(s.substring(20, 25)), (parseUnsignedInt(s.substring(0, 20), 2) << 12) + "");
            case "1101111" -> String.format("%7s\t%s, %s", "jal", getRegister(s.substring(20, 25)),
                    switch (dictionary.containsKey(arT +
                            (parseUnsignedInt(String.valueOf(s.charAt(0)).repeat(12) +
                                    s.substring(12, 20) + s.charAt(11) + s.substring(1, 11), 2) << 1)) ? 1 : 0) {
                case 0 -> {
                    String label = String.format("L%d", dictionary.size());
                    dictionary.put(arT, new SymtabLine(0, label, 0, 0, 0, 0, 0));
                    yield label;
                }
                case 1 -> dictionary.get(arT + (parseUnsignedInt(String.valueOf(s.charAt(0)).repeat(12) +
                        s.substring(12, 20) + s.charAt(11) + s.substring(1, 11), 2) << 1)).nm;
                default -> throw new Exception("Incorrect label");
            });
            case "1100111" -> String.format("%7s %s, %s(%s)", "jalr",getRegister(
                    s.substring(20, 25)), parseUnsignedInt(s1, 2) + "", getRegister(s.substring(12, 17)));
            case "1100011" -> String.format("%7s\t%s, %s, %s", switch (s.substring(17, 20)) {
                case "000" -> "beq";
                case "001" -> "bne";
                case "100" -> "blt";
                case "101" -> "bge";
                case "110" -> "bltu";
                case "111" -> "bgeu";
                default -> throw new Exception("Unknown instruction");
            }, getRegister(s.substring(12, 17)),  getRegister(s.substring(7, 12)), switch (dictionary.containsKey(arT + (parseUnsignedInt(
                    repeat + s.charAt(24) + s.substring(1, 7) + s.substring(20, 24), 2) << 1) + 4) ? 1 : 0) {
                case 0 -> {
                    String label = String.format("L%d", dictionary.size());
                    dictionary.put(arT, new SymtabLine(0, label, 0, 0, 0, 0, 0));
                    yield label;
                }
                case 1 -> dictionary.get(arT + (parseUnsignedInt(
                        repeat + s.charAt(24) + s.substring(1, 7) + s.substring(20, 24), 2) << 1) + 4).nm;
                default -> throw new Exception("Incorrect label");
            });
            case "0000011" -> String.format("%7s %s, %s(%s)", switch (s.substring(17, 20)) {
                case "000" -> "lb";
                case "001" -> "lh";
                case "010" -> "lw";
                case "100" -> "lbu";
                case "101" -> "lhu";
                default -> throw new Exception("Unknown instruction");
            }, getRegister(s.substring(20, 25)), parseUnsignedInt(s1, 2) + "", getRegister(s.substring(12, 17)));
            case "0100011" -> String.format("%7s %s, %s(%s)", switch (s.substring(17, 20)) {
                case "000" -> "sb";
                case "001" -> "sh";
                case "010" -> "sw";
                default -> throw new Exception("Unknown instruction");
            }, getRegister(s.substring(7, 12)), (parseUnsignedInt(repeat
                    + s.substring(0, 7) + s.substring(20, 25), 2)) + "", getRegister(s.substring(12, 17)));
            case "0010011" -> String.format("%7s\t%s, %s, %s", switch (s.substring(17, 20)) {
                case "000" -> "addi";
                case "001" -> {
                    if (("0".repeat(7)).equals(s.substring(0, 7))) {
                        yield "slli";
                    } else {
                        throw new Exception("Unknown instruction");
                    }
                }
                case "010" -> "slti";
                case "011" -> "sltiu";
                case "100" -> "xori";
                case "101" -> switch (s.substring(0, 7)) {
                    case "0000000" -> "srli";
                    case "0100000" -> "srai";
                    default -> throw new Exception("Unknown instruction");
                };
                case "110" -> "ori";
                case "111" -> "andi";
                default -> throw new Exception("Unknown instruction");
            },  getRegister(s.substring(20, 25)), getRegister(s.substring(12, 17)),
                    "101".equals(s.substring(17, 20)) || "001".equals(s.substring(17, 20)) ?
                            String.valueOf(parseUnsignedInt(s.substring(7, 12), 2)) :parseUnsignedInt(s1, 2) + "");
            case "0110011" -> String.format("%7s\t%s, %s, %s", switch (s.substring(0, 7)) {
                case "0000000" -> switch (s.substring(17, 20)) {
                    case "000" -> "add";
                    case "001" -> "sll";
                    case "010" -> "slt";
                    case "011" -> "sltu";
                    case "100" -> "xor";
                    case "101" -> "srl";
                    case "110" -> "or";
                    case "111" -> "and";
                    default -> throw new Exception("Unknown instruction");
                };
                case "0100000" -> switch (s.substring(17, 20)) {
                    case "000" -> "sub";
                    case "101" -> "sra";
                    default -> throw new Exception("Unknown instruction");
                };
                case "0000001" -> switch (s.substring(17, 20)) {
                    case "000" -> "mul";
                    case "001" -> "mulh";
                    case "010" -> "mulhsu";
                    case "011" -> "mulhu";
                    case "100" -> "div";
                    case "101" -> "divu";
                    case "110" -> "rem";
                    case "111" -> "remu";
                    default -> throw new Exception("Unknown instruction");
                };
                default -> throw new Exception("Unknown instruction");
            },  getRegister(s.substring(20, 25)),  getRegister(s.substring(12, 17)), getRegister(s.substring(7, 12)));
            case "0001111" -> String.format("%7s\t", "fence", ("001".equals(s.substring(17, 20))? ".i": ""));
            case "1110011" -> String.format("%7s\t%s, %s, %s", switch (s.substring(17, 20)) {
                case "001" -> "csrrw";
                case "010" -> "csrrs";
                case "011" -> "csrrc";
                case "101" -> "csrrwi";
                case "110" -> "csrrsi";
                case "111" -> "csrrci";
                default -> throw new Exception("Unknown instruction");
            },  (s.substring(20, 25)), getRegister(s.substring(0, 7) + s.substring(7, 12)), getRegister(s.substring(12, 17)));
            default -> throw new Exception("Unknown instruction");
        };
    }
    private String getRegister(String x) {
        return switch (parseInt(x, 2)) {
            case 0 -> "zero";
            case 1 -> "ra";
            case 2 -> "sp";
            case 3 -> "gp";
            case 4 -> "tp";
            case 5 -> "t0";
            case 6 -> "t1";
            case 7 -> "t2";
            case 8 -> "s0";
            case 9 -> "s1";
            case 10 -> "a0";
            case 11 -> "a1";
            case 12 -> "a2";
            case 13 -> "a3";
            case 14 -> "a4";
            case 15 -> "a5";
            case 16 -> "a6";
            case 17 -> "a7";
            case 18 -> "s2";
            case 19 -> "s3";
            case 20 -> "s4";
            case 21 -> "s5";
            case 22 -> "s6";
            case 23 -> "s7";
            case 24 -> "s8";
            case 25 -> "s9";
            case 26 -> "s10";
            case 27 -> "s11";
            case 28 -> "t3";
            case 29 -> "t4";
            case 30 -> "t5";
            case 31 -> "t6";
            default -> null;
        };
    }
    private String toBitString (int b) {
        StringBuilder s = new StringBuilder();
        int sz = 8;
        for (int i = 0; i < sz; i++) {
            s.insert(0, (char) (b % 2 + '0'));
            b /= 2;
        }
        return String.valueOf(s);
    }
    public int get(int start, int cnt) {
        int answer = 0;
        for (int i = cnt - 1; i >= 0; i--) {
            answer = (answer << 8) + elf[start + i];
        }
        return answer;
    }
}
