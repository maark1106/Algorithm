#include<bits/stdc++.h>
#define MAX 1001

using namespace std;

int N;
int dp[MAX][3] = { 0 };

int main() {
	
	cin >> N;

	cin >> dp[1][0] >> dp[1][1] >> dp[1][2];

	int r, g, b;
	for (int i = 2; i <= N; i++) {
		cin >> r >> g >> b;
		dp[i][0] = min(dp[i - 1][1] + r, dp[i - 1][2] + r);
		dp[i][1] = min(dp[i - 1][0] + g, dp[i - 1][2] + g);
		dp[i][2] = min(dp[i - 1][0] + b, dp[i - 1][1] + b);
	}
	
	cout << min({ dp[N][0],dp[N][1], dp[N][2] });

	return 0;
}
	