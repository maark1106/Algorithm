#include<iostream>
#include<cstring>
#include<vector>
#include<algorithm>
using namespace std;

int count_horse(vector<int> x, int N, int distant) {

	int cnt = 1;
	int pos = 0;

	for (int i = 1; i < N; i++) {
		if (x[i] - x[pos] >= distant) {
			cnt++;
			pos = i;
		}
	}
	return cnt;
}

int main() {

	ios::sync_with_stdio(0); cin.tie(0);
	int N, C;

	cin >> N >> C;

	vector<int> x(N);
	for (int i = 0; i < N; i++)
		cin >> x[i];

	sort(x.begin(), x.end());

	int left = x[0];
	int right = x[N - 1];
	int res;

	while (left <= right) {
		int mid = (left + right) / 2;
		int cnt = count_horse(x, N, mid);		
		if (cnt >= C) {
			left = mid + 1;
			res = mid;
		}
		else
			right = mid - 1;

	}
	
	cout << res;

	return 0;
}