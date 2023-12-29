#include<bits/stdc++.h>
using namespace std;

vector<int> graph[32001];
int deg[32001];
int N, M;

int main() {

	cin >> N >> M;

	int src, des;
	for (int i = 1; i <= M; i++) {
		cin >> src >> des;
		graph[src].push_back(des);
		deg[des]++;
	}

	priority_queue<int, vector<int>, greater<int>> q;
	for (int i = 1; i <= N; i++) {
		if (deg[i] == 0)
			q.push(i);
	}

	while (!q.empty()) {
		int top = q.top();
		q.pop();
		cout << top << " ";

		for (int cur : graph[top]) {
			deg[cur]--;
			if (deg[cur] == 0) {
				q.push(cur);
			}
		}
	}

	return 0;
}