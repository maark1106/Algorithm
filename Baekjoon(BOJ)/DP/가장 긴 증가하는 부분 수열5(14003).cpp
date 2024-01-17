#include<bits/stdc++.h>
using namespace std;

int N;
int arr[1000001];
int dp[1000001];
vector<int> v;
vector<int> res;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	cin >> arr[0];

	v.push_back(arr[0]);
	dp[0] = 0;
	int vIdx = 0;
	for (int i = 1; i < N; i++) {
		cin >> arr[i];

		if (v[vIdx] < arr[i]) {
			v.push_back(arr[i]);
			vIdx++;
			dp[i] = vIdx;
		}
		else {
			int idx = lower_bound(v.begin(), v.end(), arr[i]) - v.begin();
			v[idx] = arr[i];
			dp[i] = idx;
		}
	}
	
	for (int i = N - 1; i >= 0; i--) {
		if (dp[i] == vIdx) {
			res.push_back(arr[i]);
			vIdx--;
		}
	}

	cout << res.size() << "\n";
	for (int i = res.size() - 1; i >= 0; i--) {
		cout << res[i] << " ";
	}

	return 0;
}