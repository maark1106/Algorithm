#include<bits/stdc++.h>
using namespace std;

int board[101][101];
int visited[101][101];
int N, M;

int dy[4] = { 0,0,1,-1 };
int dx[4] = { 1,-1,0,0 };

void input() {

	cin >> N >> M;
	
	char ch;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> ch;
			board[i][j] = ch - '0';
		}
	}
}

void BFS() {
	
	queue<pair<int, pair<int, int>>> Q;
	Q.push({ 1, { 1,1 } });
	visited[1][1] = 1;

	while (!Q.empty()) {
		int dis = Q.front().first;
		int y = Q.front().second.first;
		int x = Q.front().second.second;
		Q.pop();

		if (y == N && x == M) {
			cout << dis;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int curY = y + dy[i];
			int curX = x + dx[i];
			
			if (curY < 1 || curY > N || curX < 1 || curX > M)
				continue;
			if (!visited[curY][curX] && board[curY][curX]) {
				visited[curY][curX] = 1;
				Q.push({ dis + 1 ,{curY,curX} });
			}
		}
	}

}

int main() {

	input();

	BFS();
	
	return 0;
}
