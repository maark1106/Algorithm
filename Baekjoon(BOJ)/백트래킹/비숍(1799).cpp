#include<bits/stdc++.h>
using namespace std;

bool visitedR[25];
bool visitedL[25];

int N, resBlack = 0, resWhite = 0;
int mapBlack[11][11], mapWhite[11][11];

void dfsBlack(int idx, int cnt) {

	if (idx >= N * N) {
		resBlack = max(resBlack, cnt);
		return;
	}

	int i = idx / N;
	int j = idx % N;


	if ((i + j) % 2 == 1 || mapBlack[i][j] == 0 || visitedR[i + j] == true || visitedL[j - i + N - 1] == true)
		dfsBlack(idx + 1, cnt);
	else {
		dfsBlack(idx + 1, cnt);

		visitedR[i + j] = true;
		visitedL[j - i + N - 1] = true;
		dfsBlack(idx + 1, cnt + 1);
		visitedR[i + j] = false;
		visitedL[j - i + N - 1] = false;
	}

}

void dfsWhite(int idx, int cnt) {

	if (idx >= N * N) {
		resWhite = max(resWhite, cnt);
		return;
	}

	int i = idx / N;
	int j = idx % N;


	if ((i + j) % 2 == 0 || mapWhite[i][j] == 0 || visitedR[i + j] == true || visitedL[j - i + N - 1] == true)
		dfsWhite(idx + 1, cnt);
	else {
		dfsWhite(idx + 1, cnt);

		visitedR[i + j] = true;
		visitedL[j - i + N - 1] = true;
		dfsWhite(idx + 1, cnt + 1);
		visitedR[i + j] = false;
		visitedL[j - i + N - 1] = false;
	}

}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	int pos;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> pos;
			if (pos == 1) {
				if ((i + j) % 2 == 0)
					mapBlack[i][j] = 1;
				else
					mapWhite[i][j] = 1;
			}
		}
	}

	dfsBlack(0, 0);
	dfsWhite(1, 0);

	cout << resBlack + resWhite;

	return 0;
}