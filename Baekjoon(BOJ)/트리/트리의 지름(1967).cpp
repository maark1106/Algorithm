#include<iostream>
#include<vector>
#include<queue>
#include<string.h>

#define MAX 10001

using namespace std;

int N, endVertex;
vector <pair<int, int>> graph[MAX];
queue <pair<int, int >> Q;
bool visited[MAX];

int BFS() {

	int max = 0;

	while (!Q.empty()) {
		int dis = Q.front().first;
		int vertex = Q.front().second;
		Q.pop();

		if (dis > max) {
			max = dis;
			endVertex = vertex;
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

	return max;
}


int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	for (int i = 1; i < N; i++) {
		int sVertex, eVertex, dis;

		cin >> sVertex >> eVertex >> dis;
		graph[sVertex].push_back({ dis, eVertex });
		graph[eVertex].push_back({ dis, sVertex });
	}

	Q.push({ 0, 1 }); // 거리와 시작정점
	visited[1] = true;
	BFS();	
	memset(visited, 0, size(visited));

	Q.push({ 0, endVertex });
	visited[endVertex] = true;
	cout << BFS();

	return 0;
}