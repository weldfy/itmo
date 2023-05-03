public class Cache {
    static final int CACHE_LINE_SIZE = 128;
    static final int CACHE_WAY = 2;
    static final int CACHE_SETS_COUNT = 64;
    static final int CACHE_TAG_SIZE = 8;
    /*
         _____  _____        _   _   _______   _________
        |     ||     | ---> |_| |_| |_______| |_________|
        |     ||     |       V   D     tag        line
        |     ||     |
          ...    ...
        |_____||_____|
          64      64
                                 offset - number in line
                                 set - number of line
*/
    static int[][] CACHE_VALID = new int[CACHE_WAY][CACHE_SETS_COUNT];
    static int[][] CACHE_DIRTY = new int[CACHE_WAY][CACHE_SETS_COUNT];
    static int[][][] CACHE_TAG = new int[CACHE_WAY][CACHE_SETS_COUNT][CACHE_TAG_SIZE];
    static int[][][] CACHE_LINE = new int[CACHE_WAY][CACHE_SETS_COUNT][CACHE_LINE_SIZE];
    static int[] CACHE_LINE_USABILITY = new int[CACHE_SETS_COUNT];
    static int cntP = 0;
    static int cntA = 0;
    private final Mem mem;
    final Counter counter;
    public Cache(Mem mem, Counter counter) {
        this.mem = mem;
        this.counter = counter;
    }


    public void WRITE(String tag, String set, String offset, int bits, String line) {
        counter.add(2);
        //counter.add(bits / 16);
        cntA++;
        int intSet = parseInt(set);
        int intOffset = parseInt(offset);
        if (check(0, intSet, tag)) {
            cntP++;
            counter.add(6);
            set(0, intSet, intOffset, bits, line);
            return;
        }
        if (check(1, intSet, tag)) {
            cntP++;
            counter.add(6);
            set(1, intSet, intOffset, bits, line);
            return;
        }
        counter.add(4);
        int way = getNewLine(tag, set);
        set(way, intSet, intOffset, bits, line);
    }
    public int READ(String tag, String set, String offset, int bits) {
        counter.add(2);
        cntA++;
        int intSet = parseInt(set);
        int intOffset = parseInt(offset);
        if (check(0, intSet, tag)) {
            cntP++;
            counter.add(6);
            return get(0, intSet, intOffset, bits);
        }
        if (check(1, intSet, tag)) {
            cntP++;
            counter.add(6);
            return get(1, intSet, intOffset, bits);
        }
        counter.add(4);
        int way = getNewLine(tag, set);
        counter.add(bits/16.);
        return get(way, intSet, intOffset, bits);
    }

    private int getNewLine(String tag, String setString) {
        String line = mem.READ_LINE(parseInt(tag), parseInt(setString));
        int set = parseInt(setString);
        int way = CACHE_LINE_USABILITY[set];
        if (CACHE_DIRTY[way][set] == 1) {
            mem.WRITE_LINE(getTag(way, set), set, getLine(way, set));
        }
        CACHE_LINE_USABILITY[set] = 1 - way;
        CACHE_VALID[way][set] = 1;
        CACHE_DIRTY[way][set] = 0;
        for (int i = 0; i < line.length(); i++) {
            CACHE_LINE[way][set][i] = (int)(line.charAt(i) - '0');
        }
        for (int i = 0; i < tag.length(); i++) {
            CACHE_TAG[way][set][i] = (int)(tag.charAt(i) - '0');
        }
        return way;
    }
    private int getTag(int way, int set) {
        int x = 0;
        for (int i = 0; i < CACHE_TAG[way][set].length; i++) {
            x = x * 2 + CACHE_TAG[way][set][i];
        }
        return x;
    }
    private String getLine(int way, int set) {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < CACHE_LINE[way][set].length; i++) {
            x.append((char)(CACHE_LINE[way][set][i] + '0'));
        }
        return String.valueOf(x);
    }
    private int get(int way, int set, int offset, int bits) {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < bits; i++) {
            x.append((char)(CACHE_LINE[way][set][i + offset]));
        }
        return parseInt(String.valueOf(x));
    }
    private void set(int way, int set, int offset, int bits, String line) {
        CACHE_DIRTY[way][set] = 1;
        for (int i = 0; i < bits; i++) {
            CACHE_LINE[way][set][i + offset] = (int)(line.charAt(i));
        }
    }
    private boolean check(int way, int set, String tag) {
        if (CACHE_VALID[way][set] == 0) {
            return false;
        }
        for (int i = 0; i < tag.length(); i++) {
            if ((int)(tag.charAt(i) - '0') != CACHE_TAG[way][set][i]) {
                return false;
            }
        }
        return true;
    }
    private int parseInt(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            x = x * 2 + (int)(s.charAt(i) - '0');
        }
        return x;
    }
    public double answer() {
        double x = cntP;
        double y = cntA;
        return x / y;
    }
}
