#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int N;
int map[150][150];
int res[2];

void divide(int y, int x, int size) {	

	if (size == 1) {
		res[map[y][x]]++;
		return;
	}

	int checkNum = map[y][x];
	bool flag = true;

	for (int i = y; i < y + size; i++) {
		for (int j = x; j < x + size; j++) {
			if (checkNum != map[i][j]) {
				flag = false;
				break;
			}
		}
		if (!flag)
			break;
	}

	if (!flag) {
		int nextSize = size / 2;
		divide(y, x, nextSize);
		divide(y, x + nextSize, nextSize);
		divide(y + nextSize, x, nextSize);
		divide(y + nextSize, x + nextSize, nextSize);
	}
	else
		res[checkNum]++;

}


int main() {

	cin >> N;

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> map[i][j];
	
	divide(1, 1, N);

	cout << res[0] << "\n" << res[1];
	
	return 0;
}