#include<bits/stdc++.h>
using namespace std;

int dp[1000001];
int N;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;

	int maxCnt = 0;
	for (int i = 0; i < N; i++) {
		int num;
		cin >> num;
		dp[num] = dp[num - 1] + 1;
		maxCnt = max(maxCnt, dp[num]);
	}
	
	cout << N - maxCnt;

	return 0;
}