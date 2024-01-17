#include<bits/stdc++.h>
using namespace std;

int dp[101][100001];
int v[101];
int w[101];
int N, K;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> K;
	for (int i = 1; i <= N; i++) {
		cin >> w[i] >> v[i];
	}

	
	for (int i = 1; i <= N; i++) {		
		for (int j = 1; j <= K; j++) {
			if (j - w[i] < 0) // 못 넣을 경우
				dp[i][j] = dp[i - 1][j];
			else	//넣을 수 있는 경우(선택 vs 안 선택 비교)
				dp[i][j] = max(dp[i - 1][j - w[i]] + v[i], dp[i - 1][j]);
		}
	}

	cout << dp[N][K];

	return 0;
}