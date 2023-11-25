#include<bits/stdc++.h>
using namespace std;

int arr[100001][2];
int dp[100001][2];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	int T;
	cin >> T;

	while (T--) {
		int N;
		cin >> N;

		for (int i = 1; i <= N; i++)
			cin >> arr[i][0];
		for (int i = 1; i <= N; i++)
			cin >> arr[i][1];

		dp[1][0] = arr[1][0];
		dp[1][1] = arr[1][1];				

		for (int i = 2; i <= N; i++) {			

			dp[i][0] = max(dp[i - 1][1], dp[i - 2][1]) + arr[i][0];
			dp[i][1] = max(dp[i - 1][0], dp[i - 2][0]) + arr[i][1];
		}		

		cout << max(dp[N][0], dp[N][1]) << "\n";
	}


	return 0;
}