#include <bits/stdc++.h>

using namespace std;
string s;
vector <string> v;
int main()
{
    cin >> s;
    for (int i = 0; i < s.size(); i++) {
        v.push_back("");
    }
    for (int i = 0; i < s.size(); i++) {
        for (int i = 0; i < s.size(); i++) {
            v[i] = s[i] + v[i];
        }
        sort (v.begin(), v.end());
    }
    cout << v[0] << endl;
    return 0;
}
