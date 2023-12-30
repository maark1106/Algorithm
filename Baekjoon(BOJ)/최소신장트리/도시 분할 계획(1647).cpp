#include<bits/stdc++.h>
using namespace std;


tuple<int, int, int> edges[1000001];
int N, M;
int parent[100001];

int find(int u) {
	if (u == parent[u])
		return parent[u];

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

	sort(edges, edges + M);
	for (int i = 1; i <= N; i++)
		parent[i] = i;

	int edgeCnt = 0;
	int sum = 0;
	int edgeIdx = 0;
	while (edgeCnt < N - 2) {
		int dis, v1, v2;
		tie(dis, v1, v2) = edges[edgeIdx++];

		if (!Union(v1, v2))
			continue;

		sum += dis;
		edgeCnt++;
	}

	cout << sum;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 0; i < M; i++) {
		int v1, v2, dis;
		cin >> v1 >> v2 >> dis;
		edges[i] = { dis,v1,v2 };
	}

	solve();

	return 0;
}