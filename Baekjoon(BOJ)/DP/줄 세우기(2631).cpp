#include<bits/stdc++.h>
#define MAX 201

using namespace std;
int N;
int arr[MAX];
int dp[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	for (int i = 0; i < N; i++)
		cin >> arr[i];

	int res = 0;
	dp[0] = 1;
	for (int i = 1; i < N; i++) {
		dp[i] = 1;
		for (int j = 0; j < i; j++) {
			if (arr[j] < arr[i] && dp[i] <= dp[j])
				dp[i]++;
		}

		res = max(res, dp[i]);
	}
	
	cout << N - res;

	return 0;
}