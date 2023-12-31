#include<bits/stdc++.h>
using namespace std;

pair<int, int> god[1001];
int parent[1001];
int N, M, K;
int cnt = 0;
tuple<long double, int, int> edges[500001];

int find(int u) {
	if (parent[u] == u)
		return u;

	parent[u] = find(parent[u]);

	return parent[u];
}

int Union(int u, int v) {
	u = find(u);
	v = find(v);

	if (u != v) {
		parent[u] = v;
		return 1;
	}
	return 0;
}

void solve() {

	int edgeIdx = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = i + 1; j <= N; j++) {
			long double n1 = pow(god[i].first - god[j].first, 2);
			long double n2 = pow(god[i].second - god[j].second, 2);
			edges[edgeIdx++] = { sqrt(n1 + n2), i,j };
		}
	}

	sort(edges, edges + edgeIdx);

	int idx = 0;	
	long double sum = 0;
	while (cnt < N - 1) {
		int u, v;
		long double dis;
		tie(dis, u, v) = edges[idx++];

		if (!Union(u, v))
			continue;

		cnt++;
		sum += dis;
	}
	
	cout << fixed;
	cout.precision(2);
	cout << sum;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 1; i <= N; i++) {
		int x, y;
		cin >> x >> y;
		god[i].first = x;
		god[i].second = y;
	}

	for (int i = 1; i <= N; i++) {
		parent[i] = i;
	}

	for (int i = 0; i < M; i++) {
		int v1, v2;
		cin >> v1 >> v2;
		if (Union(v1, v2))
			cnt++;
	}

	solve();


	return 0;
}