#include <bits/stdc++.h>

using namespace std;

long long a[100][10], n, fl, b[100], s, x;
string ans;

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> x;
        for (int j = 0; j < 35; j++) a[j][i] = ((x >> j) & 1);
    }
    cin >> s;
    for (int j = 0; j < 35; j++) b[j] = ((s >> j) & 1);
    for (int i = 0; i < 35; i++)
    {
        for (int j = 0; j < 35; j++)
        {
            fl = 1;
            for (int o = 0; o < n; o++) if (a[i][o] != a[j][o]) fl = 0;
            if (fl == 1 and b[i] != b[j])
            {
                cout << "Impossible" << endl;
                return 0;
            }
        }
    }
    fl = 0;
    ans += "(";
    ans += char(n + '0');
    ans += "&~";
    ans += char(n + '0');
    ans += ")";
    for (int i = 0; i < 35; i++)
    {
        if (b[i] == 1)
        {
            ans += "|(";
            for (int j = 0; j < n; j++)
            {
                if (fl == 1) ans += "&";
                if (a[i][j] == 0) ans += "~";
                ans += char(j + 1 + '0');
                fl = 1;
            }
            ans += ")";
        }
        fl = 0;
    }
    cout << ans << endl;
    return 0;
}
