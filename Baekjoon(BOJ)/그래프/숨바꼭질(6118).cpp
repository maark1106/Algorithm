#include<bits/stdc++.h>
#define MAX 20001
using namespace std;


int N, M;
int res[MAX];
vector <int> graph[MAX];
queue<pair<int, int>> Q; // 정점, 거리
bool visited[MAX] = { 0 };

void BFS() {

	Q.push({1,0});
	visited[1] = true;
	res[1] = 0;

	while (!Q.empty()) {
		int curVertex = Q.front().first;
		int dis = Q.front().second;
		Q.pop();

		for (int i = 0; i < graph[curVertex].size(); i++) {
			int nextVertex = graph[curVertex][i];

			if (!visited[nextVertex]) {
				visited[nextVertex] = true;
				res[nextVertex] = dis + 1;

				Q.push({ nextVertex, dis + 1 });
			}
		}
	}
}

int main() {

	cin >> N >> M;

	int nodeA, nodeB;
	for (int i = 0; i < M; i++) {
		cin >> nodeA >> nodeB;
		graph[nodeA].push_back(nodeB);
		graph[nodeB].push_back(nodeA);
	}
	
	BFS();

	int maxDistance = *max_element(begin(res), end(res));			

	int count = 0, firstNum = -1;
	for (int i = 1; i <= N; i++) {
		if (maxDistance == res[i]) {
			count++;
			if (firstNum == -1) {
				firstNum = i;
			}
		}
	}

	cout << firstNum << " " << maxDistance << " " << count;

	return 0;
}