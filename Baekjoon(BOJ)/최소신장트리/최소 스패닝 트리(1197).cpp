#include<bits/stdc++.h>
using namespace std;

tuple<int, int, int> edges[100001];
int parent[10001];
int V, E;

int find(int u) {

	if (u == parent[u])
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

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> V >> E;

	for (int i = 0; i < E; i++) {
		int v1, v2, dis;
		cin >> v1 >> v2 >> dis;
		edges[i] = { dis,v1,v2 };
	}

	sort(edges, edges + E);
	
	for (int i = 1; i <= V; i++)
		parent[i] = i;
	
	int cnt = 0;
	int idx = 0;
	int sum = 0;
	while (cnt < V - 1) {
		int u, v, dis;
		tie(dis, u, v) = edges[idx++];

		if (!Union(u, v))
			continue;

		cnt++;
		sum += dis;
	}

	cout << sum;

	return 0;
}