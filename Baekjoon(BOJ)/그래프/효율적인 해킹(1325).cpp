#include<bits/stdc++.h>
#define MAX 20001
using namespace std;


int N, M;
int res[MAX];
vector <int> graph[MAX];

void BFS(int i) {
	
	queue<int> Q; // 정점, 거리
	bool visited[MAX] = { 0 };

	Q.push(i);
	visited[i] = true;

	int cnt = 0;
	while (!Q.empty()) {
		int curVertex = Q.front();
		Q.pop();

		for (int i = 0; i < graph[curVertex].size(); i++) {
			int nextVertex = graph[curVertex][i];

			if (!visited[nextVertex]) {
				visited[nextVertex] = true;
				cnt++;							
				Q.push(nextVertex);
			}
		}
	}

	res[i] = cnt;
}

int main() {

	cin >> N >> M;

	int nodeA, nodeB;
	for (int i = 0; i < M; i++) {
		cin >> nodeA >> nodeB;
		graph[nodeB].push_back(nodeA);
	}

	for (int i = 1; i <= N; i++)
		BFS(i);

	int maxDistance = *max_element(begin(res), end(res));

	int count = 0, firstNum = -1;
	for (int i = 1; i <= N; i++) {
		if (maxDistance == res[i]) {
			cout << i << " ";
		}
	}	

	return 0;
}