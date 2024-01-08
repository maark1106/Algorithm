#include<bits/stdc++.h>
#define MAX 1000000000
using namespace std;

typedef pair<int, int> p;

int N, M, start;
vector<p> adj[2][1001];

int d[2][1001];


void solve(int i) {

	fill(d[i], d[i] + N + 1, MAX);
	d[i][start] = 0;
	priority_queue<p, vector<p>, greater<p>> q;

	q.push({ 0, start });
	while (!q.empty()) {
		int cur = q.top().second;
		int dis = q.top().first;
		q.pop();

		if (dis > d[i][cur])
			continue;

		for (auto nxt : adj[i][cur]) {
			if (d[i][cur] + nxt.first < d[i][nxt.second]) {
				d[i][nxt.second] = d[i][cur] + nxt.first;
				q.push({ d[i][nxt.second], nxt.second });
			}
		}
	}
}


int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);
	
	cin >> N >> M >> start;
	while (M--) {
		int u, v, w;
		cin >> u >> v >> w;
		adj[0][u].push_back({ w,v });
		adj[1][v].push_back({ w,u });
	}

	solve(0);
	solve(1);

	int res = 0;
	for (int i = 1; i <= N; i++) {
		if (res < d[0][i] + d[1][i])
			res = d[0][i] + d[1][i];
	}

	cout << res;

	return 0;
}