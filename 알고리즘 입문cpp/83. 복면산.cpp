#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;
int arr[8], visited[10];

int SEND() {
	return 1000 * arr[6] + 100 * arr[1] + 10 * arr[3] + arr[0];
}

int MORE() {
	return 1000 * arr[2] + 100 * arr[4] + 10 * arr[5] + arr[1];
}

int MONEY() {
	return 10000 * arr[2] + 1000 * arr[4] + 100 * arr[3] + 10 * arr[1] + arr[7];
}

void DFS(int level) {
	if (level == 8) {
		int send = SEND();
		int more = MORE();
		int money = MONEY();
		if (send + more == money) {
			if (arr[2] == 0 || arr[6] == 0)
				return;

			cout << " " << arr[6] << arr[1] << arr[3] << arr[0] << "\n";
			cout << "+" << arr[2] << arr[4] << arr[5] << arr[1] << "\n";
			cout << "---------" << "\n";
			cout << arr[2] << arr[4] << arr[3] << arr[1] << arr[7] << "\n";
		}
		return;
	}

	for (int i = 0; i < 10; i++) {
		if (!visited[i]) {
			arr[level] = i;
			visited[i] = 1;
			DFS(level + 1);
			visited[i] = 0;
		}
	}
}

int main() {


	DFS(0);
	
	return 0;
}