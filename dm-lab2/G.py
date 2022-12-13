def get(r, L, H):
    l = 0;
    q = r;
    while (l < r):
        c = (l + r) // 2
        if (c * h < L * q):
            l = c + 1
        else:
            r = c
    return l;


if __name__ == '__main__':
    m = int(input());
    s = input();
    print(m)
    n = len(s);
    p = [0]*30
    for i in range(n):
        p[ord(s[i]) - ord('a')] += 1;
    pref = [0]*30
    anser = ""
    for i in range(m):
        pref[i] = p[i];
        if (i != 0):
            pref[i] += pref[i - 1]
        anser += str(p[i])
        anser+=' '
    print(anser)
    x = 1
    sum1 = sum2 = s1 = s2 = 0
    for i in range(n):
        c = ord(s[i]) - ord('a')
        y = x * n;
        t = y // x
        x = y // n
        l = 0
        if (c != 0):
            l = pref[c - 1]
        r = pref[c]
        s1 = l * x
        s2 = (n - r) * x
        sum1 *= t
        sum2 *= t
        sum1 += s1
        sum2 += s2
        x = (r - l) * x
    l = sum1
    r = sum1 + x
    h = sum1 + sum2 + x
    MaxQ = 1002
    x = 1
    q = 1;
    for i in range(MaxQ):
        p = get(x, l, h)
        if (p * h <= r * x):
            ans = p
            break
        x *= 2
        q = i + 1;
    s = ""
    for i in range(q):
        s += str(ans%2)
        ans //= 2
    s=s[::-1]
    print(s)