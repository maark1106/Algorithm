#include<iostream>
#include<algorithm>
using namespace std;

int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

int N, M;
bool visited[500][500];
int map[500][500];

void input() {

	cin >> N >> M;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> map[i][j];
}

int ex_DFS(int x, int y) {
	
	int sum = 0;

	if (x > 0 && y > 0 && y < M - 1) {
		sum = max(sum, map[x - 1][y] + map[x][y] + map[x][y - 1] + map[x][y + 1]);
	}
	if (x > 0 && x < N - 1 && y < M - 1) {
		sum = max(sum, map[x - 1][y] + map[x][y] + map[x + 1][y] + map[x][y + 1]);
	}
	if (x >= 0 && y > 0 && x < N - 1 && y < M - 1) {
		sum = max(sum, map[x][y - 1] + map[x][y] + map[x][y + 1] + map[x + 1][y]);
	}
	if (x > 0 && y > 0 && x < N - 1) {
		sum = max(sum, map[x - 1][y] + map[x][y] + map[x + 1][y] + map[x][y - 1]);
	}
	return sum;
}

int DFS(int x, int y, int cnt) {

	if (cnt == 4) {
		return map[x][y];
	}

	int sum = 0;

	for (int dir = 0; dir < 4; dir++) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			continue;
		if (visited[nx][ny])
			continue;

		visited[nx][ny] = true;
		sum = max(sum,map[x][y] + DFS(nx, ny, cnt + 1));
		visited[nx][ny] = false;
	}

	return sum;
}

int main() {

	int result = 0;

	input();

	for (int i = 0; i < N; i++ ) {
		for (int j = 0; j < M; j++) {
			visited[i][j] = true;
			result = max(DFS(i,j,1), result);
			result = max(ex_DFS(i, j), result);
			visited[i][j] = false;
		}
	}

	cout << result;

	return 0;
}