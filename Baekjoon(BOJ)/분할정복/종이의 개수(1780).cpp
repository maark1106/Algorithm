#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;

int N;
int map[2200][2200];
int cnt[3];// cnt[0] = -1, cnt[1] = 0,cnt[2] = 1

void recursive(int sy, int sx, int size) {	

	if (size == 1) {
		cnt[map[sy][sx] + 1]++;
		return;
	}


	int checkNum = map[sy][sx];

	bool flag = true;

	for (int i = sy; i < sy + size; i++) {
		for (int j = sx; j < sx + size; j++) {
			if (checkNum != map[i][j]) {
				flag = false;
				int nextSize = size / 3;

				recursive(sy, sx, nextSize);
				recursive(sy, sx + nextSize, nextSize);
				recursive(sy, sx + nextSize * 2, nextSize);

				recursive(sy + nextSize, sx, nextSize);
				recursive(sy + nextSize, sx + nextSize, nextSize);
				recursive(sy + nextSize, sx + nextSize * 2, nextSize);

				recursive(sy + nextSize * 2, sx, nextSize);
				recursive(sy + nextSize * 2, sx + nextSize, nextSize);
				recursive(sy + nextSize * 2, sx + nextSize * 2, nextSize);

				break;
			}
		}
		if (!flag)
			break;
	}

	if (flag) {
		cnt[map[sy][sx] + 1]++;
	}
}

int main() {

	cin >> N;

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> map[i][j];

	recursive(1, 1, N);

	for (int i : cnt)
		cout << i << "\n";
	
	return 0;
}