#include<bits/stdc++.h>
#include<unordered_map>

using namespace std;

unordered_map<string, long long> um;
char board[11][11];

int dy[8] = { 1,1,1,-1,-1,-1,0,0 };
int dx[8] = { -1,0,1,-1,0,1,-1,1 };

int N, M, K;

void countArray(int L, string str, int y, int x) {

	um[str]++;
	if (L == 4)
		return;

	for (int i = 0; i < 8; i++) {
		int ny = (y + dy[i] + N) % N;
		int nx = (x + dx[i] + M) % M;

		countArray(L + 1, str + board[ny][nx], ny, nx);
	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M >> K;

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> board[i][j];

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			string str = "";
			str = str + board[i][j];
			countArray(0, str, i, j);
		}
	}

	string res;
	while (K--) {
		cin >> res;
		cout << um[res] << "\n";
	}

	return 0;
}