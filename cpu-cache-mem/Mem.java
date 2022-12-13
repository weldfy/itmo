public class Mem {
    static final int CACHE_TAG_SIZE = 8;
    static final int CACHE_SET_SIZE = 6;
    static final int CACHE_OFFSET_SIZE = 7;
    static int[][][] MEM_ARRAY = new int[(int)Math.pow(2, CACHE_TAG_SIZE)]
            [(int)Math.pow(2, CACHE_SET_SIZE)][(int)Math.pow(2, CACHE_OFFSET_SIZE)];
    final Counter counter;
    public Mem(Counter counter) {
        this.counter = counter;
    }
    public String READ_LINE(int tag, int set) {
        counter.add(1);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < MEM_ARRAY[tag][set].length; i++) {
            s.append((char)(MEM_ARRAY[tag][set][i] + '0'));
        }
        counter.add(100);
        counter.add(128/16);
        return String.valueOf(s);
    }
    public void WRITE_LINE(int tag, int set, String line) {
        //counter.add(1);
        counter.add(128/16);
        for (int i = 0; i < line.length(); i++) {
            MEM_ARRAY[tag][set][i] = (int)(line.charAt(i) - '0');
        }
        counter.add(100);
    }
}