#include <bits/stdc++.h>

using namespace std;

bool fl[30], answer[30];
int b[30], n, m;
vector <int> v[30], ans[30], vv;

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

int rec (int x)
{
    if (fl[x]) return b[x];
    int mx = 0;
    for (int i = 0; i < v[x].size(); i++) mx = max(mx, rec(v[x][i]));
    mx++;
    fl[x] = 1;
    b[x] = mx;
    return b[x];
}

int main()
{
    cin >> n;
    for(int i = 0; i < n; i++)
    {
        cin >> m;
        if (m == 0) vv.push_back(i);
        else
        {
            for(int j = 0; j < m; j++)
            {
                int x;
                cin >> x;
                x--;
                v[i].push_back(x);
            }
            for(int j = 0; j < BinPow(2, m); j++)
            {
                int x;
                cin >> x;
                if (x == 1) ans[i].push_back(1);
                else ans[i].push_back(0);
            }
        }
    }

    for (int i = 0; i < vv.size(); i++)
    {
        fl[vv[i]] = 1;
        b[vv[i]] = 0;
    }
    cout << rec(n - 1) << endl;
    for (int x = 0; x < BinPow(2, vv.size()); x++)
    {
        for (int j = 0; j < n; j++) answer[j] = 0;
        for(int j = 0; j < vv.size(); j++) if ((x & BinPow(2, j)) == BinPow(2, j)) answer[vv[vv.size() - j - 1]] = 1;
        for(int j = 0; j < n; j++)
        {
            if (v[j].size() > 0)
            {
                int y = 0;
                for (int t = 0; t < v[j].size(); t++) if (answer[v[j][t]]) y = (y | BinPow(2, v[j].size() - t - 1));
                answer[j] = ans[j][y];
            }
        }
        cout << answer[n - 1];
    }
    cout << endl;
    return 0;
}
