#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int unf[101];
vector<pair<pair<int, int>, int>> v;

bool compare(pair<pair<int, int>, int> &p1, pair<pair<int, int>, int> &p2) {
	return p1.second < p2.second;
}

int Find(int v) {
	if (v == unf[v])
		return v;
	
	return unf[v] = Find(unf[v]);
}

void Union(int a, int b) {
	a = Find(a);
	b = Find(b);

	if (a != b)
		unf[a] = b;
}

int main() {

	int N, M, A, B, C, res = 0;

	cin >> N >> M;

	for (int i = 1; i <= N; i++)
		unf[i] = i;

	for (int i = 0; i < M; i++) {
		cin >> A >> B >> C;
		v.push_back(make_pair(make_pair(A, B), C));
	}

	sort(v.begin(), v.end(), compare);

	for (int i = 0; i < M; i++) {
		int a = Find(v[i].first.first);
		int b = Find(v[i].first.second);

		if (a != b) {
			Union(a, b);
			res += v[i].second;
		}
	}
	cout << res;

	return 0;
}