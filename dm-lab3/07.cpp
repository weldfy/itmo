#include <bits/stdc++.h>

using namespace std;
int n;
vector <int> v;
int main ()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> n;
    for (int i = 0; i < n; i++) v.push_back(i + 1);
    do {
        for (int i = 0; i < n; i++) cout << v[i] << " ";
        cout << endl;
    } while (next_permutation(v.begin(), v.end()));
    return 0;
}
//1 1 3 1 3
///1 0 1 & 1 0 0 = 1 0 0
///0 1 1 & 1 0 1 = 0 0 1
