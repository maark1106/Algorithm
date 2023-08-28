#include<bits/stdc++.h>
#define MAX 1001

using namespace std;

int T, W;
int dp[1001][33][3]; // 초, 이동 횟수, 나무 위치

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> T >> W;	

	int treeNum;
	for (int i = 1; i <= T; i++) {
		cin >> treeNum;

		for (int j = 0; j <= W; j++) {
			for (int k = 1; k <= 2; k++) {
				if (i == 1 && k == 2) // 시작할 때 2번 위치에 서 있기 반칙
					continue;

				if (k == treeNum) {					
					dp[i][j][k] = max(dp[i - 1][j][k] + 1, dp[i][j][k]);					
				}
				else {
					dp[i][j + 1][treeNum] = max(dp[i][j + 1][treeNum], dp[i - 1][j][k] + 1);
					dp[i][j][k] = max(dp[i - 1][j][k], dp[i][j][k]);
				}
			}
		}
	}

	int res = 0;
	
	for (int j = 0; j <= W; j++)
		for (int k = 1; k <= 2; k++)
				res = max(res, dp[T][j][k]);				
	
	cout << res;
	
	return 0;
}