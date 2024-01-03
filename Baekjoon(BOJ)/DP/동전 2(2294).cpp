#include<bits/stdc++.h>
#define MAX 2147000000
using namespace std;

typedef long long ll;
int n, k;
int dp[100001];
int coin[101];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> k;
	for (int i = 0; i < n; i++) {
		cin >> coin[i];	
	}	

	fill(dp, dp + 10001, 2147000000);
	dp[0] = 0;
	for (int i = 0; i < n; i++) {
		for (int j = coin[i]; j <= k; j++) {
			dp[j] = min(dp[j], dp[j - coin[i]] + 1);
		}
	}

	if (dp[k] == 2147000000)
		cout << -1;
	else
		cout << dp[k];
	

	/*for (int i = 1; i <= k; i++) {
		for (int j = 0; j < n; j++) {
			int dis = i - coin[j];			
			if (dis > 0 && dp[dis] != 0) {
				if (dp[i] == 0)
					dp[i] = dp[dis] + 1;
				else
					dp[i] = min(dp[i], dp[dis] + 1);
			}
		}
	}*/

	/*if (dp[k] == 0)
		cout << -1;
	else
		cout << dp[k];*/

	return 0;
}