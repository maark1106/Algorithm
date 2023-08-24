#include<bits/stdc++.h>
#define MAX 100001
using namespace std;

int dp[MAX];
int N, M, i, j;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;

	dp[0] = 0;
	for (int i = 1; i <= N; i++) {
		int num;
		cin >> num;
		dp[i] = dp[i - 1] + num;
	}

	while (M--) {
		cin >> i >> j;
		cout << dp[j] - dp[i - 1] << "\n";
	}				

	return 0;
}