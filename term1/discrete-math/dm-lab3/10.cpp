#include <bits/stdc++.h>

using namespace std;
vector <long long> v;
int n;
void rec (int x, int y) {
    if (x == 0) {
        for (int i = 0; i < v.size() - 1; i++) {
            cout << v[i] << "+";
        }
        cout << v.back() << endl;
    }
    if (x <= 0) return;
    for (int i = y; i <= x; i++) {
        v.push_back(i);
        rec(x - i, i);
        v.pop_back();
    }
}

int main ()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> n;
    rec(n, 1);
    return 0;
}
//1 1 3 1 3
///1 0 1 & 1 0 0 = 1 0 0
///0 1 1 & 1 0 1 = 0 0 1
