#include<iostream>
using namespace std;

int N, map[21][21], visited[21], res = 0;

void DFS(int vertex) {

	if (vertex == N) {
		res++;
		return;
	}
	for (int i = 1; i <= N; i++) {
		if (map[vertex][i] && !visited[vertex]) {
			visited[vertex] = 1;
			DFS(i);
			visited[vertex] = 0;
		}
	}
}

int main() {

	int v, start, end;

	cin >> N >> v;
	for (int i = 0; i < v; i++) {
		cin >> start >> end;
		map[start][end] = 1;
	}

	DFS(1);

	cout << res;

	return 0;
}