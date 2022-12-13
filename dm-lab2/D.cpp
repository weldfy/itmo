#include <bits/stdc++.h>

using namespace std;
string s;
vector <int> pos;
int main()
{
    cin >> s;
    for (int i = 0; i < 26; i++) {
        pos.push_back(i + 1);
    }
    for (int i = 0; i < s.size(); i++) {
        int c = int(s[i] - 'a');
        cout << pos[int(s[i] - 'a')] << " ";
        for (int j = 0; j < 26; j++) {
            if (pos[j] < pos[c]) pos[j]++;
        }
        pos[c] = 1;
    }
    return 0;
}
