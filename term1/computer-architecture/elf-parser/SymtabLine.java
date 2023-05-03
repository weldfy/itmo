public class SymtabLine {
    public final String nm;
    public final int x;
    public final String line;
    public final String type;
    public SymtabLine(int symbol, String nm, int x, int sz, int inf, int access, int i) {
        this.nm = nm;
        this.x = x;
        line = String.format("[%4d] 0x%-15X %5d %-8s %-8s %-8s %6s %s", symbol, x, sz,
                switch (inf & 15) {
                    case 0 -> "NOTYPE";
                    case 1 -> "OBJECT";
                    case 2 -> "FUNC";
                    case 3 -> "SECTION";
                    case 4 -> "FILE";
                    case 5 -> "COMMON";
                    case 6 -> "TLS";
                    case 10 -> "LOOS";
                    case 12 -> "HIOS";
                    case 13 -> "LOPROC";
                    case 15 -> "HIPROC";
                    default -> "UNKNOWN";
                }, switch (inf >> 4) {
                    case 0 -> "LOCAL";
                    case 1 -> "GLOBAL";
                    case 2 -> "WEAK";
                    case 10 -> "LOOS";
                    case 12 -> "HIOS";
                    case 13 -> "LOPROC";
                    case 15 -> "HIPROC";
                    default -> "UNKNOWN";
                }, switch(access & 3) {
                    case 0 -> "DEFAULT";
                    case 1 -> "INTERNAL";
                    case 2 -> "HIDDEN";
                    case 3 -> "PROTECTED";
                    default -> "UNKNOWN";
                }, switch (i) {
                    case 0 -> "UNDEF";
                    case 65280 -> "LOPROC";
                    case 65281 -> "AFTER";
                    case 65282 -> "AMD64_LCOMMON";
                    case 65311 -> "HIPROC";
                    case 65312 -> "LOOS";
                    case 65343 -> "HIOS";
                    case 65521 -> "ABS";
                    case 65522 -> "COMMON";
                    case 65535 -> "XINDEX";
                    default -> String.valueOf(i);
                }, nm);
        type = switch (inf & 15) {
            case 0 -> "NOTYPE";
            case 1 -> "OBJECT";
            case 2 -> "FUNC";
            case 3 -> "SECTION";
            case 4 -> "FILE";
            case 5 -> "COMMON";
            case 6 -> "TLS";
            case 10 -> "LOOS";
            case 12 -> "HIOS";
            case 13 -> "LOPROC";
            case 15 -> "HIPROC";
            default -> "UNKNOWN";
        };
    }
}
