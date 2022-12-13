#include <bits/stdc++.h>

using namespace std;

int n, r, f;
vector <int> a;

int BinPow (int a, int b)
{
    int ans = 1;
    while (b)
    {
        if (b%2) ans *= a;
        a *= a;
        b /= 2;
    }
    return ans;
}

int f1(int a, int b)
{
    if ((a & BinPow(2, n - b)) == BinPow(2, n - b)) return b;
    else return n + b;
}

int main()
{
    cin >> n;
    for (int i = 0; i < BinPow(2, n); i++)
    {
        int z;
        string s;
        cin >> s >> z;
        if (z == 1) a.push_back(i);
    }
    int k = a.size();
    if (k == 0)
    {
        cout << n + 2  << endl << 1 << " " << 1 << endl << 2 << " " << 1 << " " << n + 1 << endl;
        return 0;
    }
    if (k < 2) cout << 3 * n - 1 << endl;
    else cout << (2 + k) * n - 1 << endl;
    for (int i = 1; i <= n; i++) cout << 1 << " " << i << endl;
    r = 2 * n + 1;
    for (int i = 0; i < a.size(); i++)
    {
        int y = r;
        cout << 2 << " " << f1(a[i], 1) << " " << f1(a[i], 2) << endl;
        for (int j = 2; j < n; j++)
        {
            cout << 2 << " " << y << " " << f1(a[i], j + 1) << endl;
            y++;
        }
        r += n - 1;
    }
    if (k >= 2)
    {
        cout << 3 << " " << 3 * n - 1 << " " << 4 * n - 2 << endl;
        r++;
        f = 5 * n - 3;
        for (int i = 2; i < k; i++)
        {
            cout << 3 << " " << r - 1 << " " << f << endl;
            r++;
            f += n - 1;
        }
    }
    return 0;
}
