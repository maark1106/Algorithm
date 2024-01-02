#include<bits/stdc++.h>
using namespace std;

int N, M;
tuple<int, int, int> edges[500003];

int find(int*parent, int u) {
	if (parent[u] == u)
		return u;

	parent[u] = find(parent, parent[u]);

	return parent[u];
}

int Union(int* parent, int u, int v) {
	u = find(parent, u);
	v = find(parent, v);

	if (u != v) {
		parent[u] = v;
		return 1;
	}
	return 0;
}

int solve() {

	int parent[1002];
	for (int i = 0; i <= N; i++)
		parent[i] = i;

	int cnt = 0;
	int edgeIdx = 0;
	int res = 0;
	while (cnt < N) {
		int u, v, road;
		tie(road, u, v) = edges[edgeIdx++];

		if (!Union(parent, u, v))
			continue;

		cnt++;		
		if (road == 0)
			res++;
	}
	
	return res;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 0; i <= M; i++) {
		int v1, v2, road;
		cin >> v1 >> v2 >> road;		
		edges[i] = { road,v1,v2 };		
	}

	sort(edges, edges + M + 1, greater<>());
	int min = solve();

	sort(edges, edges + M + 1);	
	int max = solve();

	cout << max * max - min * min;

	return 0;
}