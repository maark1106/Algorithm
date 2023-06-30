#include<iostream>
#include<vector>
#include<queue>

using namespace std;

vector<int> map[21];
queue<int> Q;
int N, visited[21], dis[21], front = -1, back = -1;

void BFS() {

	int x;

	while (!Q.empty()) {
		x = Q.front();
		Q.pop();
		for (int i = 0; i < map[x].size(); i++) {
			if (!visited[map[x][i]]) {
				visited[map[x][i]] = 1;
				Q.push(map[x][i]);
				dis[map[x][i]] = dis[x] + 1;
			}
		}
	}
}

int main() {

	int M, start, end;
	cin >> N >> M;

	for (int i = 0; i < M; i++) {
		cin >> start >> end;
		map[start].push_back(end);
	}

	visited[1] = 1;
	Q.push(1);
	BFS();

	for (int i = 2; i <= N; i++) {
		cout << i << " : " << dis[i] << endl;
	}

	return 0;
}