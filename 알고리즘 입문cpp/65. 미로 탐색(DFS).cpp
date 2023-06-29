#include<iostream>
using namespace std;

int N, map[10][10], visited[10][10], res = 0;
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };

void DFS(int x, int y) {

	int xx,yy;

	if (x == 7 && y == 7) {
		res++;
		return;
	}
	
	for (int i = 0; i < 4; i++) {
		xx = x + dx[i];
		yy = y + dy[i];
		if (xx < 1 || xx > 7 || yy < 1 || yy >7)
			continue;
		if (!map[x][y] && !visited[x][y]) {
			visited[x][y] = 1;
			DFS(xx, yy);
			visited[x][y] = 0;
		}
	}
}

int main() {	
	
	for (int i = 1; i <= 7; i++)
		for (int j = 1; j <= 7; j++)
			cin >> map[i][j];

	DFS(1, 1);

	cout << res;

	return 0;
}