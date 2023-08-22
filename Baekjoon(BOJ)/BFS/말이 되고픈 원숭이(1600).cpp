#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;

int N;
int dyHorse[8] = { -2,-2,-1,-1,1,1,2,2 };
int dxHorse[8] = { -1,1,-2,2,-2,2,-1,1 };

int dy[4] = { 1,-1,0,0 };
int dx[4] = { 0,0,1,-1 };

int H, W, K;
int map[201][201];
bool visited[201][201][31];

typedef struct Info {
	int y;
	int x;
	int count;
	int remainK;

	Info(int a,  int b, int c, int d) {
		y = a;
		x = b;
		count = c;
		remainK = d;
	}
}Info;

queue<Info> Q;

void BFS() {

	bool flag = false;

	while (!Q.empty()) {
		Info info = Q.front();
		int y = info.y;
		int x = info.x;
		int count = info.count;
		int remainK = info.remainK;

		if (y == H && x == W) {
			cout << count;
			flag = true;
			break;
		}

		Q.pop();

		for (int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];

			if (yy <= 0 || yy > H || xx <= 0 || xx > W || visited[yy][xx][remainK]
				|| map[yy][xx])
				continue;
			
			visited[yy][xx][remainK] = true;
			Q.push(Info(yy, xx, count + 1, remainK));
		}

		if (remainK > 0) {
			for (int i = 0; i < 8; i++) {
				int yy = y + dyHorse[i];
				int xx = x + dxHorse[i];

				if (yy <= 0 || yy > H || xx <= 0 || xx > W || visited[yy][xx][remainK - 1]
					|| map[yy][xx])
					continue;

				visited[yy][xx][remainK - 1] = true;
				Q.push(Info(yy, xx, count + 1, remainK - 1));
			}
		}
	}
	
	if(!flag)
		cout << -1;
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> K;
	cin >> W >> H;

	for (int i = 1; i <= H; i++)
		for (int j = 1; j <= W; j++)
			cin >> map[i][j];

	visited[1][1][K] = true;
	Q.push(Info(1, 1, 0, K));
	BFS();

	return 0;
}