#include <bits/stdc++.h>

using namespace std;

int n, a[1010], mn;

string p[1010], ans;

int main()
{
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < 26; i++) {
        string s = "";
        s += char(i + 'a');
        p[i] = s;
    }
    ans += p[a[0]];
    mn = 26;
    for (int i = 1; i < n; i++) {
        if (a[i] == mn) {
            p[a[i]] = p[a[i - 1]] + p[a[i - 1]][0];
            mn++;
        }
        else {
            p[mn++] = p[a[i - 1]] + p[a[i]][0];
        }
        ans += p[a[i]];
    }
    cout << ans << endl;
    return 0;
}
