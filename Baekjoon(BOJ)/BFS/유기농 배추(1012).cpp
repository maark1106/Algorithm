#include<iostream>
#include<vector>
#include<string>
#include<queue>

using namespace std;

int map[52][52];
int T, N, M, K;
int dy[4] = {1,-1,0,0};
int dx[4] = { 0,0,1,-1 };

queue<pair<int, int>> Q;

void mapInit() {
	for (int i = 0; i < 52; i++)
		for (int j = 0; j < 52; j++)
			map[i][j] = 0;
}

void BFS() {

	while (!Q.empty()) {
		int y = Q.front().first;
		int x = Q.front().second;
		Q.pop();

		for (int i = 0; i < 4; i++) {
			int curY = y + dy[i];
			int curX = x + dx[i];

			if (curY < 0 || curY >= N || curX < 0 || curX >= M)
				continue;
			
			if (map[curY][curX]) {
				Q.push({ curY,curX });
				map[curY][curX] = 0;
			}
		}
	}
}

int main() {

	cin >> T;

	while (T--) {
		int x, y, res = 0;
		cin >> M >> N >> K; //MÀº °¡·Î
		while (K--) {
			cin >> x >> y;
			map[y][x] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					Q.push({ i,j });
					map[i][j] = 0;
					BFS();					
					res++;
				}
			}
		}
		cout << res<<"\n";
		mapInit();
	}
	

	return 0;
}