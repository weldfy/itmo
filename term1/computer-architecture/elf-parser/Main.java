public class Main {
    public static void main(String[] args) throws Exception {
        ElfParser parser = new ElfParser(args);
        parser.parse();
    }
}
