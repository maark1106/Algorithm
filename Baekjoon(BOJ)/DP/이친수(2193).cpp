#include<bits/stdc++.h>

using namespace std;

long long dp[100][2];
int N;

int main() {
		
	dp[1][0] = 0;
	dp[1][1] = 1;

	for (int i = 2; i <= 90; i++) {
		dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
		dp[i][1] = dp[i - 1][0];
	}
	cin >> N;

	cout << dp[N][0] + dp[N][1];

	return 0;
}