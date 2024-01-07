#include<bits/stdc++.h>
#define MAX 100000000
using namespace std;

typedef pair<int, int> p;

int N, M, start, des;
vector<p> adj[20001];
int res[20001];

void solve() {

	fill(res, res + N + 1, MAX);

	priority_queue<p, vector<p>, greater<p>> q;
	res[start] = 0;
	q.push({ 0,start });

	while (!q.empty()) {
		int dis = q.top().first;
		int top = q.top().second;
		q.pop();

		if (res[top] < dis)
			continue;

		for (auto nxt : adj[top]) {
			if (res[top] + nxt.first < res[nxt.second]) {
				res[nxt.second] = res[top] + nxt.first;
				q.push({ res[nxt.second] , nxt.second });
			}
		}
	}

	cout << res[des];

}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	
	while (M--) {
		int u, v, w;
		cin >> u >> v >> w;
		adj[u].push_back({ w,v });
	}

	cin >> start >> des;

	solve();

	return 0;
}