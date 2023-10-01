#include<bits/stdc++.h>
using namespace std;

pair<int, int> arr[500001];
multiset<int> group;
int N;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);	

	cin >> N;
	int h, k;
	for (int i = 0; i < N; i++) {
		cin >> arr[i].first >> arr[i].second;
	}

	sort(arr, arr + N, greater<>());

	for (int i = 0; i < N; i++) {
		auto lowerG = group.lower_bound(arr[i].second);
		if (lowerG == group.begin()) {
			group.insert(1);
		}
		else {
			int cnt = *prev(lowerG);
			group.erase(prev(lowerG));
			group.insert(cnt + 1);
		}
	}

	cout << group.size();

	return 0;
}