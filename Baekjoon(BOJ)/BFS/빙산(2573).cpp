#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;
typedef pair<int, int> p;

int map[301][301];
bool visited[301][301];

int dy[4] = { 0,0,1,-1 };
int dx[4] = { 1,-1,0,0 };
int N, M;

int resYear = 0;

queue <p> Q;

void visitInit() {
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			visited[i][j] = false;
}

void BFS() {

	while (!Q.empty()) {	
		int y = Q.front().first;
		int x = Q.front().second;
		Q.pop();

		for (int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];

			if (yy < 0 || yy >= N || xx < 0 || xx >= M)
				continue;
			if (visited[yy][xx])
				continue;

			if (map[yy][xx] == 0) {
				map[y][x] = (map[y][x] == 0) ? 0 : map[y][x] - 1;
			}
			else {
				visited[yy][xx] = true;
				Q.push({ yy,xx });
			}
		}		
	}
}

int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
		}
	}

	while (1) {
		int ice = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] && visited[i][j] == false) {
					Q.push({ i,j });
					visited[i][j] = true;
					BFS();
					ice++;					
				}
			}
		}

		if (ice >= 2) {
			cout << resYear;
			return 0;
		}
		if (ice == 0)		
			break;

		visitInit();
		resYear++;
	}
	
	cout << 0;

	return 0;
}