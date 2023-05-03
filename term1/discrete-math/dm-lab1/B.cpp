#include <bits/stdc++.h>

#define endl "\n"

using namespace std;

int n, ans1, ans2, ans3, ans4, ans5;

int f1(string s) {
    return s[0] == '0';
}

int f2(string s)
{
    return s.back() == '1';
}

int f3(string s) {
    int ans = 1;
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == s[s.size() - i - 1]) {
            ans = 0;
        }
    }
    return ans;
}

int f4(string s, int sz)
{
    int ans = 1;
    for (int i = 0; i < s.size(); i++)
    {
        string ss = "";
        int x = i;
        for (int i = 0; i < sz; i++) {
            ss += char(x%2 + '0');
            x /= 2;
        }
        reverse(ss.begin(), ss.end());
        for (int j = i + 1; j < s.size(); j++) {
            string ss1 = "";
            int x = j;
            for (int j = 0; j < sz; j++) {
                ss1 += char(x%2 + '0');
                x /= 2;
            }
            reverse(ss1.begin(), ss1.end());
            int fl = 0;
            for (int k = 0; k < ss1.size(); k++)
            {
                if (ss[k] > ss1[k])
                {
                    fl = 1;
                    break;
                }
            }
            if (fl == 0 and s[i] > s[j]) {
                ans = 0;
            }
        }
    }
    return ans;
}

int f5(string s, int sz) {
    int a[100];
    for (int i = 0; i < s.size(); i++) {
        a[i] = 0;
    }
    for (int i = 0; i < s.size(); i++)
    {
        //cout << a[i] << endl;
        if (a[i] != int(s[i] - '0'))
        {
            a[i] = 1;
        }
        else
        {
            a[i] = 0;
        }
        string ss = "";
        int x = i;
        for (int j = 0; j < sz; j++) {
            ss += char(x%2 + '0');
            x /= 2;
        }
        reverse(ss.begin(), ss.end());


        for (int j = i + 1; j < s.size(); j++) {
            string ss1 = "";
            int x = j;
            for (int j = 0; j < sz; j++) {
                ss1 += char(x%2 + '0');
                x /= 2;
            }
            reverse(ss1.begin(), ss1.end());
            int fl = 0;
            for (int k = 0; k < ss1.size(); k++)
            {
                if (ss[k] > ss1[k])
                {
                    fl = 1;
                    break;
                }
            }
            if (fl == 0)
            {
                a[j] ^= a[i];
                //cout << j << " " << i << endl;
            }
        }
    }
    int ans = 1;
    int x = 4;
    for (int i = 3; i < s.size(); i++)
    {

        if (x == i)
        {
            x *= 2;
        }
        else
        {
            if (a[i] == 1) ans = 0;
        }
    }
    return ans;
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    ans1 = 1;
    ans2 = 1;
    ans3 = 1;
    ans4 = 1;
    ans5 = 1;
    for (int i = 0; i < n; i++)
    {
        int x;
        string s;
        cin >> x >> s;
        ans1 = min (ans1, f1(s));
        ans2 = min (ans2, f2(s));
        ans3 = min (ans3, f3(s));
        ans4 = min (ans4, f4(s, x));
        ans5 = min (ans5, f5(s, x));
    }
    if (!ans1 and !ans2 and !ans3 and !ans4 and !ans5) {cout << "YES" << endl;}
    else cout << "NO" << endl;

    return 0;
}
