#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;
typedef pair<int, int > p;

int dy[4] = { 0,0,1,-1 };
int dx[4] = { 1,-1,0,0 };

int N, M;
int map[1001][1001];
int visited[1001][1001][2];

queue<pair<int, p>> Q;

int BFS() {

	Q.push({ 0,{0,0} });
	visited[0][0][0] = 1;

	while (!Q.empty()) {
		int y = Q.front().second.first;
		int x = Q.front().second.second;
		int wall = Q.front().first; // wall == 0 깬적 x
		Q.pop();

		if (y == N - 1 && x == M - 1)
			return visited[y][x][wall];

		for (int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];


			if (xx < 0 || xx >= M || yy < 0 || yy >= N)
				continue;		

			//벽 x , 방문한적 x
			if (map[yy][xx] == 0 && visited[yy][xx][wall] == 0) {
				visited[yy][xx][wall] = visited[y][x][wall] + 1;
				Q.push({ wall,{yy,xx} });
			}
			//벽 o, 뚫은 적 x
			if (map[yy][xx] == 1 && wall == 0) {
				visited[yy][xx][1] = visited[y][x][0] + 1;
				Q.push({ 1,{yy,xx} });
			}
		}
	}
	return -1;
}

int main() {

	cin >> N >> M;

	ios_base::sync_with_stdio(0);
	cin.tie(0);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			char ch;
			cin >> ch;
			map[i][j] = ch - '0';
		}
	}

	int res = BFS();

	cout << res;

	return 0;
}