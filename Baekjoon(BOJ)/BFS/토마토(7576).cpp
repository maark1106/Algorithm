#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;
typedef pair<int, int> p;

queue <p> Q;
int map[1002][1002];
int N, M;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void BFS() {

	while (!Q.empty()) {
		int y = Q.front().first;
		int x = Q.front().second;
		Q.pop();

		for (int i = 0; i < 4; i++) {
			int curY = y + dy[i];
			int curX = x + dx[i];

			if (curY <= 0 || curY > N || curX <= 0 || curX > M
				|| map[curY][curX] == -1)
				continue;

			if (map[curY][curX] == 0) {
				map[curY][curX] = map[y][x] + 1;
				Q.push({ curY,curX });
			}			
		}
	}
}

int main() {

	cin >> M >> N;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> map[i][j];
			if (map[i][j] == 1)
				Q.push({ i,j });
		}
	}

	BFS();

	int res = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			if (map[i][j] == 0) {
				cout << -1;
				return 0;
			}
			if (res < map[i][j])
				res = map[i][j];
		}
	}

	cout << res - 1;
	
	return 0;
}