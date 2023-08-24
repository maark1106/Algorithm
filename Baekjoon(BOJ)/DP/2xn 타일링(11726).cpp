#include<bits/stdc++.h>
using namespace std;

long long dp[1001];
int N;

int main() {

	dp[1] = 1;
	dp[2] = 2;

	for (int i = 3; i <= 1000; i++)
		dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;

	cin >> N;

	cout << dp[N];

	return 0;
}