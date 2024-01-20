#include<bits/stdc++.h>
using namespace std;
typedef long long ll;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int T;
	cin >> T;

	while (T--) {
		int a, b;
		cin >> a >> b;
		int dis = b - a;
		ll sum = 0;
		int cnt = 0, num = 1;
		bool even = false;
		while (1) {
			if (sum >= dis)
				break;

			sum += num;
			cnt++;

			if (even) {
				num++;
				even = false;
			}
			else {
				even = true;
			}
		}
		cout << cnt << "\n";
	}

	return 0;
}