#include <bits/stdc++.h>

using namespace std;

int m, n, a[110][110], x[110], q;
string answer;

int main()
{
    cin >> m >> n;
    for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) cin >> a[i][j];
    q = 1000;
    while (q--) {

    for (int i = 0; i < n; i++)
    {
        int fl = 0, y = -1;
        for (int j = 0; j < m; j++)
        {
            if ((a[i][j] == 0 and x[j] == 0) or (a[i][j] == 1 and x[j] == 1)) fl = 1;
            if (a[i][j] == 1) y = j;
        }
        if (fl == 0 and y != -1) x[y] = 1;
    }

    }
    answer = "NO";
    for (int i = 0; i < n; i++)
    {
        int fl = 0;
        for (int j = 0; j < m; j++) if ((a[i][j] == 0 and x[j] == 0) or (a[i][j] == 1 and x[j] == 1)) fl = 1;
        if (fl == 0) answer = "YES";
    }
    cout << answer << endl;
    return 0;
}
