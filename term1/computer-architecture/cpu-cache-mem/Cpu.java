public class Cpu {
    static final int M = 64;
    static final int N = 60;
    static final int K = 32;
    static final int A_BIT_SIZE = M * K * 8;
    static final int B_BIT_SIZE = K * N * 16;
    static final Counter counter = new Counter();
    static final Cache cache = new Cache(new Mem(counter), counter);

    public static void main(String[] args) {
        int pa = 0;
        int pc = A_BIT_SIZE + B_BIT_SIZE;
        counter.add(1 + 1);
        for (int y = 0; y < M; y++)
        {
            for (int x = 0; x < N; x++)
            {
                int pb = A_BIT_SIZE;
                int s = 0;
                for (int k = 0; k < K; k++)
                {
                    s += READ(pa + k*8, 8) * READ(pb + x*16, 16);
                    pb += N*16;
                    counter.add(1 + 1 + 1 + 5);
                }
                WRITE(pc + x*32, s, 32);
                counter.add(1 + 1 + 1);
            }
            pa += K*8;
            pc += N*32;
            counter.add(1 + 1 + 1);
        }
        System.out.println(cache.answer() * 100 + "% " + counter.getAnswer() + " tacts");
    }
    static  int READ(int x, int bits) {
        int tag = x / (128 * 64);
        int set = (x / 128)%64;
        int offset = x % 128;
        return cache.READ(parseBits(tag, 8),
                parseBits(set, 6), parseBits(offset, 7), bits);
    }
    static void WRITE(int x, int bits, int y) {
        int tag = x / (128 * 64);
        int set = (x / 128)%64;
        int offset = x % 128;
        cache.WRITE(parseBits(tag, 8), parseBits(set, 6),
                parseBits(offset, 7), bits, parseBits(y, bits));
    }
    static String parseBits(int x, int sz) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < sz; i++) {
            s.insert(0, (char) (x % 2 + '0'));
            x /= 2;
        }
        return s.toString();
    }
}
/*
        MEM_SIZE – 2097152
        CACHE_SIZE – 16384
        CACHE_LINE_SIZE – 128
        CACHE_LINE_COUNT – 128
        CACHE_WAY – 2
        CACHE_SETS_COUNT –  64
        CACHE_TAG_SIZE – 8
        CACHE_SET_SIZE – 6
        CACHE_OFFSET_SIZE – 7
        CACHE_ADDR_SIZE – 22
         _____  _____        _   _   _______   _________
        |     ||     | ---> |_| |_| |_______| |_________|
        |     ||     |       V   D     tag        line
        |     ||     |
          ...    ...
        |_____||_____|
          x      x
*/

