#include<bits/stdc++.h>
using namespace std;

typedef pair<int, int> p;

int N, M;
int board[9][9];
int boardCopy[9][9];
vector<p> v;
int res = 0;
int dy[4] = { 0,0,1,-1 };
int dx[4] = { 1,-1,0,0 };

bool visited[9][9];

void BFS(int y, int x) {

	visited[y][x] = true;
	queue<p> q;
	q.push({ y,x });

	while (!q.empty()) {
		y = q.front().first;
		x = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int curY = y + dy[i];
			int curX = x + dx[i];
			if (curY <= 0 || curY > N || curX <= 0 || curX > M)
				continue;
			if (boardCopy[curY][curX] == 0 && !visited[curY][curX]) {
				visited[curY][curX] = true;
				boardCopy[curY][curX] = 2;
				q.push({ curY,curX });
			}
		}
	}

}

void BFSInit() {
	
	memcpy(boardCopy, board, sizeof(board));
	for (int i = 0; i < 9; i++)
		memset(visited[i], 0, sizeof(visited[i]));

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			if (boardCopy[i][j] == 2 && !visited[i][j]) {
				BFS(i, j);
			}
		}
	}

	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			if (boardCopy[i][j] == 0)
				cnt++;
		}
	}
	res = max(res, cnt);
}

void DFS(int idx, int level) {
	if (level == 3) {
		BFSInit();
		return;
	}

	for (int i = idx; i < v.size(); i++) {
		if (board[v[i].first][v[i].second] == 0) {
			board[v[i].first][v[i].second] = 1;
			DFS(i + 1, level + 1);
			board[v[i].first][v[i].second] = 0;
		}
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> board[i][j];
			if (board[i][j] == 0)
				v.push_back({ i,j });
		}
	}

	DFS(0, 0);

	cout << res;

	return 0;
}