#include <bits/stdc++.h>

using namespace std;
string s;
vector <string> v;
int main()
{
    cin >> s;
    for (int i = 0; i < s.size(); i++) {
        v.push_back(s);
        s = s.back() + s;
        s = s.substr(0, s.size() - 1);
    }
    sort (v.begin(), v.end());
    for (int i = 0; i < v.size(); i++) {
        cout << v[i].back();
    }
    cout << endl;
    return 0;
}
