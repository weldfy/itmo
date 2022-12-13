#include <iostream>
#include <cstdio>

using namespace std;
long long n, i, o, a, x;
string s, s1;
long long code(long long g)
{
    return g ^ (g >> 1);
}
int main ()
{
    cin >> n;
    for (i = 0; i < 10000000000; i++)
    {
        x = code(i);
        s = "";
        do
        {
            s = char (x%2 + '0') + s;
            x /= 2;
        }
        while (x);
        //cout << s << endl;
        if (s.size() <= n)
        {
            s1 = "";
            for (o = 0; o < n - s.size(); o++)
            {
                s1 += '0';
            }
        }
        else break;
        s1 += s;
        for (o = 0; o < s1.size(); o++)
        {
            if (s1[o] == '1') s1[o] = '1';
            else s1[o] = '0';
        }
        cout << s1 << endl;
    }
    return 0;
}
//1 1 3 1 3
///1 0 1 & 1 0 0 = 1 0 0
///0 1 1 & 1 0 1 = 0 0 1
