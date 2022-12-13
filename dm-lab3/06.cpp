#include <bits/stdc++.h>

using namespace std;
int n;
string s;
vector <string> ans;
void rec () {
    if (s.size() == n) {
        ans.push_back(s);
        return;
    }
    s += "0";
    rec();
    s.pop_back();
    if (s.back() == '1') return;
    s += "1";
    rec();
    s.pop_back();
}

int main ()
{
    cin >> n;
    rec();
    cout << ans.size() << endl;
    for (int i = 0; i < ans.size(); i++) cout << ans[i] << endl;
    return 0;
}
//1 1 3 1 3
///1 0 1 & 1 0 0 = 1 0 0
///0 1 1 & 1 0 1 = 0 0 1
