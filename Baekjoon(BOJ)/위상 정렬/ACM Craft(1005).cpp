#include<bits/stdc++.h>
using namespace std;


void solve() {
	int N, M, target;
	int dis[1001];
	vector<int> graph[1001];

	cin >> N >> M;

	for (int i = 1; i <= N; i++) 
		cin >> dis[i];	

	int deg[1001] = { 0, };
	
	for (int i = 1; i <= M; i++) {
		int a, b;
		cin >> a >> b;
		graph[a].push_back(b);
		deg[b]++;
	}
	cin >> target;

	queue<int> q;
	int res[1001] = { 0 };
	for (int i = 1; i <= N; i++) {
		if (deg[i] == 0) {
			q.push(i);
			res[i] = dis[i];
		}
	}
	
	while (!q.empty()) {
		int top = q.front();
		q.pop();
		if (top == target) {
			cout << res[top] << "\n";			
			return;
		}
		for (auto cur : graph[top]) {
			res[cur] = max(res[cur], dis[cur] + res[top]);
			deg[cur]--;
			if (deg[cur] == 0) {
				q.push(cur);
			}
		}
	}

}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int T;
	cin >> T;
	while (T--) {
		solve();
	}


	return 0;
}