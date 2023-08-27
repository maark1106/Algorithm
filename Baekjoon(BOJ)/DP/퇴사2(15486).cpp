#include<bits/stdc++.h>
#define MAX 1500001

using namespace std;

int N;
int T[MAX];
int P[MAX];
int dp[MAX] = { 0 };

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	for (int i = 1; i <= N; i++) 
		cin >> T[i] >> P[i];

	
	for (int day = 1; day <= N; day++) {			
		dp[day + T[day]] = max(dp[day + T[day]], dp[day] + P[day]);		
		dp[day + 1] = max(dp[day], dp[day + 1]);
	}
	
	int res = 0;
	for (int day = 1; day <= N + 1; day++) {
		if (res < dp[day])
			res = dp[day];
	}

	cout << res;

	return 0;
}