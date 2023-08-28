#include<bits/stdc++.h>
#define MAX 1001

using namespace std;

int N, M;
int dp[MAX][MAX];


int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;

	char ch;	
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> ch;
			dp[i][j] = ch - '0';			
		}
	}	

	int res = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {

			if (dp[i][j]) {				
				dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
				res = max(res, dp[i][j] * dp[i][j]);			
			}
		}
	}
		
	cout << res;
	
	return 0;
}