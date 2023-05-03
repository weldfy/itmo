#include <bits/stdc++.h>

using namespace std;
string s;
int n;
void rec () {
    if (s.size() == n) {
        cout << s << endl;
        return;
    }
    s.push_back('0');
    rec();
    s.pop_back();
    s.push_back('1');
    rec();
    s.pop_back();
}

int main()
{
    cin >> n;
    rec();
    return 0;
}
