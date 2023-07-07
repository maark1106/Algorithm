#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

typedef pair<int, int> p;

int N, res = 0;
pair<int, int>arr[8];

void DFS(int nowDay, int sum) {
	if (nowDay == N + 1) {
		if (res < sum)
			res = sum;
		return;
	}

	int completeDay = nowDay + arr[nowDay].first;	
	int nextSum = sum + arr[nowDay].second;

	if (completeDay <= N + 1)
		DFS(completeDay, nextSum);
	
	DFS(nowDay + 1, sum);
	
}

int main() {

	ios_base::sync_with_stdio(false);
	int t, p;
	cin >> N;

	for (int i = 1; i <= N; i++) {
		cin >> t >> p;
		arr[i] = make_pair(t, p);
	}

	DFS(1, 0);

	cout << res;

	return 0;
}