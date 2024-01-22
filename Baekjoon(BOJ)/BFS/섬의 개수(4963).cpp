#include<bits/stdc++.h>
using namespace std;

bool visited[53][53];
int board[53][53];
int N, M;

int dy[8] = { -1,-1,-1,0,0,1,1,1 };
int dx[8] = { -1,0,1,1,-1,-1,1,0 };

void DFS(int y, int x) {

	visited[y][x] = true;

	for (int i = 0; i < 8; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];

		if (ny < 1 || ny > N || nx < 1 || nx > M)
			continue;

		if (board[ny][nx] && !visited[ny][nx]) {
			DFS(ny, nx);
		}
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	while (1) {		
		cin >> M >> N;
		if (!N && !M)
			break;

		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				cin >> board[i][j];

		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (board[i][j] && !visited[i][j]) {
					DFS(i, j);
					sum++;
				}
			}
		}

		cout << sum << "\n";
		for (int i = 1; i <= 51; i++)
			fill(visited[i], visited[i] + 51, false);
	}


	return 0;
}