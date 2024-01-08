#include<bits/stdc++.h>
#define MAX 1000000
using namespace std;

typedef pair<int, int> p;

int N, M, V1, V2;
vector<p> adj[802];
int res[3][802];

void solve(int idx, int start) {

	fill(res[idx], res[idx] + N + 1, MAX);
	res[idx][start] = 0;
	priority_queue<p, vector<p>, greater<p>> q;
	q.push({ 0, start });

	while (!q.empty()) {
		int dis = q.top().first;
		int cur = q.top().second;
		q.pop();

		if (dis > res[idx][cur])
			continue;

		for (auto nxt : adj[cur]) {
			if (res[idx][cur] + nxt.first < res[idx][nxt.second]) {
				res[idx][nxt.second] = res[idx][cur] + nxt.first;
				q.push({ res[idx][nxt.second] , nxt.second });
			}
		}
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 1; i <= N; i++) {
		int u, v, w;
		cin >> u >> v >> w;
		adj[u].push_back({ w,v });
		adj[v].push_back({ w,u });
	}
	cin >> V1 >> V2;

	solve(0, 1);
	solve(1, V1);
	solve(2, V2);
	
	int m = MAX;
	
	m = min(m, res[0][V1] + res[1][V2] + res[2][N]);
	m = min(m, res[0][V2] + res[2][V1] + res[1][N]);

	m = min(m, res[0][V1] + res[1][V2] * 2 + res[1][N]);
	m = min(m, res[0][V2] + res[2][V1] * 2 + res[2][N]);

	m = min(m, res[0][V1] + res[1][V2] + res[2][0] + res[0][N]);
	m = min(m, res[0][V2] + res[2][V1] + res[1][0] + res[0][N]);


	if (m == MAX)
		cout << -1;
	else
		cout << m;

	return 0;
}