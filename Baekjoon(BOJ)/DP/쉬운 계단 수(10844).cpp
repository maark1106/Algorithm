#include<bits/stdc++.h>
#define DIV 1000000000
using namespace std;
typedef long long ll;

ll dp[101][10];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	
	for (int i = 1; i <= 9; i++)
		dp[1][i] = 1;

	for (int i = 2; i <= N; i++) {
		dp[i][0] = dp[i - 1][1];
		dp[i][9] = dp[i - 1][8];
		for (int j = 1; j <= 8; j++) {
			dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % DIV;
		}
	}
	
	ll res = 0;
	for (int i = 0; i <= 9; i++)
		res += dp[N][i];
	cout << res % DIV;


	return 0;
}