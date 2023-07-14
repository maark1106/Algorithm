#include<iostream>
#include<vector>
#include<string>
#include<queue>

using namespace std;

int dy[4] = { 1,-1,0,0 };
int dx[4] = { 0,0,1,-1 };

int N, M, res = 0, Max = 0;
int map[501][501];
queue <pair<int, int>> Q;

void BFS() {

	int cnt = 1;

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
				cnt++;
			}
		}
	}
	
	if (Max < cnt)
		Max = cnt;
	return;
}

int main() {

	cin >> N >> M;

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> map[i][j];

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j]) {
				map[i][j] = 0;
				Q.push({ i,j });				
				BFS();		
				res++;
			}
		}
	}
	
	cout << res << "\n" << Max;

	return 0;
}