#include <bits/stdc++.h>

using namespace std;
vector <long long> v;
vector <vector <long long> > ans;
int n;
void rec (int x) {
    if (x > n) {
        ans.push_back(v);
        return;
    }
    v.push_back(x);
    rec(x + 1);
    v.pop_back();
    rec(x + 1);
}

int main ()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> n;
    rec(1);
    sort(ans.begin(), ans.end());
    for (int i = 0; i < ans.size(); i++) {
        for (int j = 0; j < ans[i].size(); j++) cout << ans[i][j] << " ";
        cout << endl;
    }
    return 0;
}
//1 1 3 1 3
///1 0 1 & 1 0 0 = 1 0 0
///0 1 1 & 1 0 1 = 0 0 1
