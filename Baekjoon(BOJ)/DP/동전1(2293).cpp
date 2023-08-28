#include<bits/stdc++.h>

using namespace std;

int n, k;
int dp[10001];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n >> k;

	dp[0] = 1;
	int curCoin;
	for (int i = 1; i <= n; i++) {
		cin >> curCoin;
	
		for (int j = curCoin; j <= k; j++) {
			dp[j] += dp[j - curCoin];
		}
	}

	cout << dp[k];

	return 0;
}