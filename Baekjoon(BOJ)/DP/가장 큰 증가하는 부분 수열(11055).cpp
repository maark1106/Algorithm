#include<bits/stdc++.h>
#define MAX 1001

using namespace std;

int arr[MAX];
int dp[MAX] = { 0 };
int N;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	int res = 0;
	for (int i = 1; i <= N; i++) {	
		cin >> arr[i];

		dp[i] = arr[i];

		for (int j = i; j >= 0; j--) {
			if (arr[i] > arr[j] && dp[i] < dp[j] + arr[i]) {
				dp[i] = dp[j] + arr[i];
			}
		}		
		
		res = res > dp[i] ? res : dp[i];
	}

	cout << res;
	
	
	return 0;
}