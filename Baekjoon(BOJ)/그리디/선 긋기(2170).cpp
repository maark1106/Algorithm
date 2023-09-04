#include<bits/stdc++.h>

using namespace std;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	int N;
	cin >> N;
	vector<pair<int, int>> dot;

	int x, y;
	for (int i = 0; i < N; i++) {
		cin >> x >> y;
		dot.push_back({ x,y });
	}

	sort(dot.begin(), dot.end());

	int start = dot[0].first;
	int end = dot[0].second;
	int res = 0;

	for (int i = 1; i < N; i++) {
		if (end >= dot[i].first) {
			end = max(end, dot[i].second);
		}
		else {
			res += end - start;
			start = dot[i].first;
			end = dot[i].second;
		}
	}

	res += end - start;
	cout << res;

	return 0;
}