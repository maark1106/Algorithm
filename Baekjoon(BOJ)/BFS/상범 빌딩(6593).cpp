#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;
typedef pair<int, int > p;

int df[6] = { 0,0,0,0,1,-1 };
int dy[6] = { 1,-1,0,0,0,0 };
int dx[6] = { 0,0,-1,1,0,0 };

int L, R, C;
int S, E;

int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);

	while (1) {
		cin >> L >> R >> C;
		if (!L && !R && !C)
			break;

		char map[31][31][31];
		bool flag = false;
		queue <pair<p, p>> Q;

		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					cin >> map[i][j][k];
					if (map[i][j][k] == 'S') {
						Q.push({ {0,i},{j,k } });						
					}
				}
			}
		}
		
		while (!Q.empty()) {
			int dis = Q.front().first.first;
			int f = Q.front().first.second;
			int y = Q.front().second.first;
			int x = Q.front().second.second;
			Q.pop();

			if (map[f][y][x] == 'E') {
				cout << "Escaped in " <<  dis << " minute(s).\n";
				flag = true;
				break;
			}

			for (int i = 0; i < 6; i++) {
				int ff = f + df[i];
				int yy = y + dy[i];
				int xx = x + dx[i];				

				if (ff < 0 || ff >= L || yy < 0 || yy >= R || xx < 0 || xx >= C)
					continue;

		
				if (map[ff][yy][xx] != '#') {					
					if(map[ff][yy][xx] != 'E')
						map[ff][yy][xx] = '#';
					Q.push({ {dis + 1,ff},{yy,xx} });
				}
			}
		}
		if (flag == false) {
			cout << "Trapped!\n";
		}
	}
	
	
	return 0;
}