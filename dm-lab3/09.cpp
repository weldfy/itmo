#include <bits/stdc++.h>

using namespace std;
string s;
int n;
void rec () {
    if (s.size() == n) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '(') x++;
            else x--;
            if (x < 0) return;
        }
        if (x != 0) return;
        cout << s << endl;
        return;
    }
    s += '(';
    rec();
    s.pop_back();
    s += ')';
    rec();
    s.pop_back();
}

int main ()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> n;
    n *= 2;
    rec();
    return 0;
}
//1 1 3 1 3
///1 0 1 & 1 0 0 = 1 0 0
///0 1 1 & 1 0 1 = 0 0 1
