#include<bits/stdc++.h>
using namespace std;

typedef long long ll;
int N;
int dp[1000001];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	dp[0] = 1;
	dp[1] = 1;
	for (int i = 2; i <= N; i++) {
		dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
	}
	
	cout << dp[N];

	return 0;
}