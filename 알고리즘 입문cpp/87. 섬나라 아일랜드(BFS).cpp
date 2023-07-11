#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;
typedef pair<int, int> p;

int N, res = 0;
int map[22][22];
int dx[8] = { 1,1,1,0,0,-1,-1,-1 };
int dy[8] = { 1,-1,0,1,-1,-1,0,1 };

queue <p> Q;

void BFS() {

	while (!Q.empty()) {
		int x = Q.front().first;
		int y = Q.front().second;		
		Q.pop();

		for (int i = 0; i < 8; i++) {
			int curX = dx[i] + x;
			int curY = dy[i] + y;
			if (curX <= 0 || curX > N || curY <= 0 || curY > N)
				continue;			
						
			if (map[curX][curY] == 1) {
				map[curX][curY] = 0;
				Q.push({ curX,curY });
			}
		}
	}
	return;
}

int main() {

	cin >> N;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> map[i][j];

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++)
			if (map[i][j] == 1) {
				Q.push({ i,j });
				map[i][j] = 0;
				BFS();
				res++;
			}
	}

	cout << res;

	return 0;
}