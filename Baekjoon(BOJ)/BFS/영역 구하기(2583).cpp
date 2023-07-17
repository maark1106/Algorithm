#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#pragma warning(disable:4996)

using namespace std;

int map[101][101];
int dy[4] = { 1,-1,0,0 };
int dx[4] = { 0,0,1,-1 };

int M, N, K;
vector<int> res;
queue <pair<int, int>> Q;

int BFS() {
	int cnt = 1;

	while (!Q.empty()) {
		int y = Q.front().first;
		int x = Q.front().second;
		Q.pop();

		for (int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];

			if (yy < 0 || yy >= N || xx < 0 || xx >= M)
				continue;

			if (!map[yy][xx]) {					
				map[yy][xx] = 1;
				Q.push({ yy,xx });
				cnt++;
			}
		}
	}
	return cnt;
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M >> K;

	int a, b, c, d;
	for (int i = 0; i < K; i++) {		
		cin >> a >> b >> c >> d;
		for (int j = b; j < d; j++) {
			for (int k = a; k < c; k++)
				map[j][k] = 1;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 0) {
				map[i][j] = 1;
				Q.push({ i,j });
				res.push_back(BFS());				
			}
		}
	}
	
	sort(res.begin(), res.end());
	cout << res.size() << "\n";

	for (int i : res)
		cout << i << " ";

	return 0;
}