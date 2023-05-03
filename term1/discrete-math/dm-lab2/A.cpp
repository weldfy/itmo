#include <bits/stdc++.h>

using namespace std;

long long ans, n;
vector <long long> v;

int main()
{
    cin >> n;
    for (long long i = 0; i < n; i++) {
        long long x;
        cin >> x;
        //x = 1e9;
        v.push_back(x);
    }
    while (v.size() > 1) {
        sort(v.begin(), v.end());
        ans += v[0] + v[1];
        vector <long long> vv;
        for (long long i = 2; i < v.size(); i++) vv.push_back(v[i]);
        vv.push_back(v[0] + v[1]);
        v = vv;
    }
    cout << ans << endl;
    return 0;
}
