#include<bits/stdc++.h>
#define MAX 100000000
using namespace std;

int board[402][402];
int N, M;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	for (int i = 1; i <= N; i++)
		fill(board[i], board[i] + N + 1, MAX);

	for (int i = 0; i < M; i++) {
		int a, b, dis;
		cin >> a >> b >> dis;
		board[a][b] = min(board[a][b], dis);
	}	

	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (board[i][k] + board[k][j] < board[i][j])
					board[i][j] = board[i][k] + board[k][j];
			}
		}
	}

	int res = MAX;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (board[i][j] != MAX && board[j][i] != MAX) {
				if (board[i][j] + board[j][i] < res)
					res = board[i][j] + board[j][i];
			}
		}
	}

	if (res == MAX)
		cout << -1;
	else
		cout << res;

	return 0;
}