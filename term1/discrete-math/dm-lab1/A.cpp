#include <bits/stdc++.h>

using namespace std;

int n;
vector <vector <bool>> a, b;

bool f1(vector<vector <bool>> c)
{
    bool ans = 1;
    for (int i = 0; i < n; i++) if (c[i][i] == 0) ans = 0;
    return ans;
}

bool f2(vector<vector <bool>> c)
{
    bool ans = 1;
    for (int i = 0; i < n; i++) if (c[i][i] == 1) ans = 0;
    return ans;
}

bool f3(vector<vector <bool>> c)
{
    bool ans = 1;
    for (int i = 0; i < n; i++) for (int j = 0; j < i; j++) if (c[i][j] == 1 and c[j][i] == 0 or c[j][i] == 1 and c[i][j] == 0) ans = 0;
    return ans;
}

bool f4(vector<vector <bool>> c)
{
    bool ans = 1;
    for (int i = 0; i < n; i++) for (int j = 0; j < i; j++) if (c[i][j] == 1 and c[j][i] == 1) ans = 0;
    return ans;
}
bool f5(vector<vector <bool>> c)
{
    bool ans = 1;
    for (int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if (c[i][j] == 0) continue;
            for(int o = 0; o < n; o++) if (c[j][o] == 1 and c[i][o] == 0) ans = 0;
        }
    }
    return ans;
}

int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        vector <bool> v;
        for (int j = 0; j < n; j++)
        {
            bool x;
            cin >> x;
            v.push_back(x);
        }
        a.push_back(v);
    }
    for (int i = 0; i < n; i++)
    {
        vector <bool> v;
        for (int j = 0; j < n; j++)
        {
            bool x;
            cin >> x;
            v.push_back(x);
        }
        b.push_back(v);
    }
    cout << f1(a) << " " << f2(a) << " " << f3(a) << " " << f4(a) << " " << f5(a) << endl;
    cout << f1(b) << " " << f2(b) << " " << f3(b) << " " << f4(b) << " " << f5(b) << endl;
    for (int i = 0; i < n; i++)
    {
        for (int o = 0; o < n; o++)
        {
            bool fl = 0;
            for (int j = 0; j < n; j++) if (a[i][j] == 1 and b[j][o] == 1) fl = 1;
            cout << fl << " ";
        }
        cout << endl;
    }
    return 0;
}
