#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;
typedef pair<int, int> p;

queue <pair<int, p> >Q;
int map[9][9];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void BFS() {
	
	while (!Q.empty()) {
		int x = Q.front().second.first;
		int y = Q.front().second.second;
		int dis = Q.front().first;

		if (x == 7 && y == 7) {
			cout << dis;
			return;
		}
		
		Q.pop();

		for (int i = 0; i < 4; i++) {
			int curX = x + dx[i];
			int curY = y + dy[i];

			if (curX <= 0 || curX > 7 || curY <= 0 || curY > 7)
				continue;

			if (map[curX][curY] == 0) {
				map[curX][curY] = 1;
				Q.push({ dis + 1, {curX,curY } });
			}
		}
	}

	cout << -1;
	return;
}

int main() {

	for (int i = 1; i <= 7; i++)
		for (int j = 1; j <= 7; j++)
			cin >> map[i][j];

	map[1][1] = 0;
	Q.push({ 0,{ 1,1 } });
	BFS();	

	return 0;
}