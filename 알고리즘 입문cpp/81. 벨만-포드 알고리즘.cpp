#include<iostream>
#include<vector>
#include<algorithm>

#define INF 2147000000

using namespace std;

typedef pair<int, int> p;

vector <pair<int, p>> v;

int main() {

	int N, M, s, e, d;
	int sVertex, eVertex;
	cin >> N >> M;
	vector<int>res(101, INF);

	for (int i = 0; i < M; i++) {
		cin >> s >> e >> d;
		v.push_back(make_pair(d, make_pair(s, e)));
	}

	cin >> sVertex >> eVertex;
	res[sVertex] = 0;

	for (int i = 1; i < N; i++) {
		for (int i = 0; i < v.size(); i++) {
			int nextDis = v[i].first;
			int from = v[i].second.first;
			int to = v[i].second.second;

			if (res[from] != INF && res[from] + nextDis < res[to])
				res[to] = res[from] + nextDis;
		}
	}

	for (int i = 0; i < v.size(); i++) {
		int nextDis = v[i].first;
		int sv = v[i].second.first;
		int ev = v[i].second.second;

		if (res[sv] != INF && res[sv] + nextDis < res[ev]) {
			cout << "-1";
			return 0;
		}
	}
	cout << res[eVertex];

	return 0;
}