#include<bits/stdc++.h>
using namespace std;

int N, M;
int board[53][53];

int dy[4] = { -1,0,1,0 };
int dx[4] = { 0,1,0,-1 };

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);
	
	int r, c, dir;
	cin >> N >> M;
	cin >> r >> c >> dir;
	
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> board[i][j];
		}
	}

	int cnt = 0;
	int curY = r + 1, curX = c + 1;
	
	while (1) {
		bool check = false;
		if (board[curY][curX] == 0) { //청소 안했다면 제자리 청소
			board[curY][curX] = 2;
			cnt++;
		}
		for (int i = 0; i < 4; i++) { //현재 방향부터 사방으로 둘러보기
			dir = (dir + 3) % 4;
			int nextY = curY + dy[dir];
			int nextX = curX + dx[dir];
			if (board[nextY][nextX] == 0) {
				curY = nextY;
				curX = nextX;
				check = true;
				break;
			}		
		}

		if (check) // 다음 청소할 구역이 있으면 빈 칸 탐색 중지
			continue;

		if (board[curY - dy[dir]][curX - dx[dir]] != 1) {
			curY = curY - dy[dir];
			curX = curX - dx[dir];
		}
		else
			break;
	}

	cout << cnt;

	return 0;
}