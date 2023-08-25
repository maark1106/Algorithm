#include<bits/stdc++.h>
#define MAX 100001

using namespace std;
int arr[MAX] = {0};
int dp[MAX];
int N, res = -1001;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);		

	cin >> N;
	
	for (int i = 1; i <= N; i++)
		cin >> arr[i];

	for (int i = 1; i <= N; i++) {
		dp[i] = max(dp[i - 1] + arr[i], arr[i]);

		if (dp[i] > res)
			res = dp[i];
	}

	cout << res;

	return 0;
}