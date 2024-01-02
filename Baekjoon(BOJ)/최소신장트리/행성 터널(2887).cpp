#include<bits/stdc++.h>
using namespace std;

tuple<int, int, int>edges[400000];
int parent[400000];
pair<int, int> x[100001];
pair<int, int> y[100001];
pair<int, int> z[100001];

int find(int u) {
	if (parent[u] == u)
		return u;

	parent[u] = find( parent[u]);

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

	int N;

	cin >> N;

	for (int i = 0; i < N; i++) {
		int dx, dy, dz;
		cin >> dx >> dy >> dz;
		x[i] = { dx,i };
		y[i] = { dy,i };
		z[i] = { dz,i };
	}

	sort(x, x + N);
	sort(y, y + N);
	sort(z, z + N);

	int edgeIdx = 0;
	for (int i = 0; i < N - 1; i++) {
		edges[edgeIdx++] = { abs(x[i].first - x[i + 1].first), x[i].second,x[i + 1].second };
		edges[edgeIdx++] = { abs(y[i].first - y[i + 1].first), y[i].second,y[i + 1].second };
		edges[edgeIdx++] = { abs(z[i].first - z[i + 1].first), z[i].second,z[i + 1].second };
	}

	sort(edges, edges + edgeIdx);
	
	for (int i = 0; i < N; i++)
		parent[i] = i;

	int cnt = 0;
	int sum = 0;
	int idx = 0;
	while (cnt < N - 1) {
		int dis, u, v;
		tie(dis, u, v) = edges[idx++];

		if (!Union(u, v))
			continue;

		cnt++;
		sum += dis;
	}

	cout << sum;

	return 0;
}