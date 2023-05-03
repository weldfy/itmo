#include <bits/stdc++.h>

using namespace std;

int n;
string ans;

//string to_string(int x)
//{
//    string s;
//    stringstream ss;
//    ss << x;
//    ss >> s;
//    return s;
//}

int main()
{
    cin >> n;
    ans = "((A0|B0)|(A0|B0))";
    for (int i = 1; i < n; i++) ans = "((" + ans + "|((A" + to_string(i) + "|A" + to_string(i) + ")|(B" +
        to_string(i) + "|B" + to_string(i) + ")))|(A" + to_string(i) + "|B" + to_string(i) + "))";
    cout << ans << endl;
    return 0;
}
