#include<bits/stdc++.h>
#define MAX 1001

using namespace std;
int dp[MAX], arr[MAX];
int N;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	for (int i = 1; i <= N; i++)
		cin >> arr[i];

	int res = 0;
	for (int i = 1; i <= N; i++) {		
		for (int j = i - 1; j >= 1; j--) {
			if (arr[i] > arr[j]) {			
				dp[i] = max(dp[i], dp[j]);
			}
		}
		dp[i]++;
		res = max(res, dp[i]);
	}	

	cout << res;
	
	return 0;
}