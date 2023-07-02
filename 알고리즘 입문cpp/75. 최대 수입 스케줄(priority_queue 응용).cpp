#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;

bool compare(const pair<int, int>& a, const pair<int, int>& b) {	
	return a.second > b.second;	
}

int main() {
	

	vector<pair<int, int> > v;
	priority_queue<int> pQ;

	int N, m, d;

	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> m >> d;
		v.push_back(make_pair(m, d));
	}
	
	
	sort(v.begin(), v.end(), compare);

	int pos = v.front().second, res = 0;
	
	int p = 0;
	for (int i = pos; i >= 1; i--) {
		while (p < N && v[p].second == i)
			pQ.push(v[p++].first);

		if (!pQ.empty()) {
			res += pQ.top();
			pQ.pop();
		}
	}
	
	cout << res;

	return 0;
}