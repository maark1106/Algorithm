#include<bits/stdc++.h>

using namespace std;

long long dp[501][501] = { 0 };
int N;

int main() {
	
	cin >> N;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= i; j++) {
			int n;
			cin >> n;
			dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + n;
		}
	}

	int res = 0;
	for (int i = 1; i <= N; i++) {
		if (res < dp[N][i])
			res = dp[N][i];
	}

	cout << res;

	return 0;
}