#include <bits/stdc++.h>

using namespace std;
int n, k;
vector <int> v;

void rec (int x) {
    if (v.size() == k) {
        for (int i = 0; i < v.size(); i++) {
            cout << v[i] << " ";
        }
        cout << endl;
        return;
    }
    for (int i = x; i <= n; i++) {
            v.push_back(i);
        rec (i + 1);
        v.pop_back();
    }
}

int main ()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> k;
    rec (1);
    return 0;
}
//1 1 3 1 3
///1 0 1 & 1 0 0 = 1 0 0
///0 1 1 & 1 0 1 = 0 0 1
