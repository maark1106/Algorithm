#include<bits/stdc++.h>
#define MAX 1000001

using namespace std;

int N;
int dp[MAX];


int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	
	for (int i = 2; i <= N; i++) {

		dp[i] = dp[i - 1] + 1;		

		if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) 
			dp[i] = dp[i / 3] + 1;					
		if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) 
			dp[i] = dp[i / 2] + 1;				
	}

	cout << dp[N]<<"\n";
	
	while (1) {

		cout << N <<" ";
		if (N == 1)
			break;

		if (N % 2 == 0 && dp[N] == dp[N / 2] + 1)
			N = N / 2;
		else if (N % 3 == 0 && dp[N] == dp[N / 3] + 1)
			N = N / 3;
		else
			N--;
	}			
	
	return 0;
}