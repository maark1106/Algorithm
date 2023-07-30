#include<iostream>
#include<vector>
#include<queue>
#include<string.h>

using namespace std;

int N, M;
int sVertex, eVertex, dis;
bool visited[1001] = { 0 };

vector <pair<int, int>> graph[1001];
queue<pair<int, int>> Q;

void BFS() {

	while (!Q.empty()) {
		int dis = Q.front().first;
		int vertex = Q.front().second;
		Q.pop();

		if (vertex == eVertex) {
			cout << dis << "\n";
			return;
		}

		for (int i = 0; i < graph[vertex].size(); i++) {

			int nextDis = dis + graph[vertex][i].first;
			int nextVertex = graph[vertex][i].second;

			if (!visited[nextVertex]) {
				visited[nextVertex] = true;
				Q.push({ nextDis, nextVertex });
			}
		}
	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;
		
	for (int i = 0; i < N - 1; i++) {
		cin >> sVertex >> eVertex >> dis;
		graph[sVertex].push_back({ dis, eVertex });
		graph[eVertex].push_back({ dis, sVertex });
	}

	for (int i = 0; i < M; i++) {
		cin >> sVertex >> eVertex;
		Q.push({ 0,sVertex });
		visited[sVertex] = true;
		BFS();		
		
		memset(visited, 0, sizeof(visited));
		while (!Q.empty())
			Q.pop();
	}

	return 0;
}