#include <bits/stdc++.h>

using namespace std;

int d, a[1100], ans[1100][1100], n;
string x[1100];

int BinPow(int a, int b) {
    int ans = 1;
    while (b) {
        if (b%2) {
            ans *= a;
        }
        a *= a;
        b /= 2;
    }
    return ans;
}

int main()
{
    cin >> d;
    n = BinPow(2, d);
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> a[i];
        ans[i][0] = a[i];
    }
    for(int j = 1; j < n; j++) {
        for(int i = 0; i < n - 1; i++) {
            ans[i][j]= (ans[i][j - 1] + ans[i + 1][j - 1])%2;
        }
    }
    for(int i = 0; i < n; i++) {
        cout << x[i] << " " << ans[0][i] << endl;
    }
    return 0;
}
