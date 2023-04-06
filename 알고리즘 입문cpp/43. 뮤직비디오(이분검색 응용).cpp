#include<iostream>
#include<cstring>
#include<vector>
#include<algorithm>
using namespace std;

int main() {

	ios::sync_with_stdio(0); cin.tie(0);
	int N,M, sum_arr = 0, res, max = -1;
	
	cin >> N >> M;
	vector<int> arr(N);

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		if (max < arr[i])
			max = arr[i];
	}

	int left = 1;
	int right = max;

	while (left <= right) {
		int cnt = 0;
		int mid = (left + right) / 2;
		cout << "left = " << left << " mid = " << mid << " right = " << right<<endl;
		for (int i = 0; i < N; i++) {
			cnt += arr[i] / mid;
		}
		if (M <= cnt) {
			res = mid;
			left = mid + 1;
		}
		else
			right = mid - 1;
	}

	cout << res;

	return 0;
}