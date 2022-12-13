#include <bits/stdc++.h>

using namespace std;
string s;
map <string, int> mp;

int main()
{
    cin >> s;
    for (int i = 0; i < 26; i++) {
        string s = "";
        s += char(i + 'a');
        mp[s] = i + 1;
    }
    int mn = 27;
    string t = "";
    for (int i = 0; i < s.size(); i++) {
        char c = s[i];
        //cout << t << " " << c << endl;
        if (mp[t + c] != 0) {
            t = t + c;
        } else {
            cout << mp[t] - 1 << " ";
            mp[t + c] = mn++;
            t = "";
            t += c;
        }
        //cout << t << endl;
    }
    cout << mp[t] - 1 << endl;
    return 0;
}
