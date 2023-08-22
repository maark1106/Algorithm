#include<bits/stdc++.h>
#define SIZE 20001

using namespace std;

int K, V, E;
vector<int> visited[SIZE];
int num[SIZE];

void BFS(int start) {

	num[start] = 1;
	queue<int> Q;
	Q.push(start);

	while (!Q.empty()) {
		int curVertex = Q.front();
		Q.pop();

		for (int i = 0; i < visited[curVertex].size(); i++) {
			int nextVertex = visited[curVertex][i];
			if (num[nextVertex] == 0) {
				Q.push(nextVertex);

				num[nextVertex] = num[curVertex] == 1 ? -1 : 1;				
			}
		}
	}
}

bool check() {
	for (int i = 1; i <= V; i++) {
		for (int j = 0; j < visited[i].size(); j++) {
			if (num[i] == num[visited[i][j]])
				return false;
		}
	}
	return true;
}

int main() {

	cin >> K;

	while (K--) {
		cin >> V >> E;

		int s, e;
		for (int i = 0; i < E; i++) {
			cin >> s >> e;

			visited[s].push_back(e);
			visited[e].push_back(s);
		}

		for (int i = 1; i <= V; i++) {
			if (num[i] == 0)
				BFS(i);
		}

		if (check())
			cout << "YES\n";
		else
			cout << "NO\n";

		memset(visited, 0, sizeof(visited));
		memset(num, 0, sizeof(num));
	}

	return 0;
}