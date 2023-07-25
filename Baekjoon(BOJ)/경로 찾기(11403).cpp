#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int map[101][101];
int visited[101][101];

int N;

void DFS(int sVertex, int rootVertex) {

	for (int i = 1; i <= N; i++) {
		if (map[sVertex][i] == 1 && !visited[rootVertex][i]) {
			visited[rootVertex][i] = 1;
			DFS(i, rootVertex);
		}
	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> map[i][j];

	for (int i = 1; i <= N; i++) {
		DFS(i);
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++)
			cout << visited[i][j] << " ";
		cout << "\n";
	}

	return 0;
}