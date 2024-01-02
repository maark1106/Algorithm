#include<bits/stdc++.h>
using namespace std;

pair<int, int> work[1001];
int N;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int duringTime, maxTime;
		cin >> duringTime >> maxTime;
		work[i] = { maxTime, duringTime };
	}

	sort(work, work + N, greater<>());

	int res = work[0].first - work[0].second;
	cout << "res = " << res << "\n";
	for (int i = 1; i < N; i++) {
		res = min(res, work[i].first) - work[i].second;
		if (res < 0) {
			cout << -1;
			return 0;
		}
	}

	cout << res;

	return 0;
}