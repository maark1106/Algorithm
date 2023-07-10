#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int arr[101], oper[5], N;
int Max = -2147000000, Min = 2147000000;
int visited[101];

void DFS(int level, int sum) {
	if (level == N) {
		if (sum > Max)
			Max = sum;
		if (sum < Min)
			Min = sum;
		return;
	}

	if (oper[1] > 0) {
		oper[1]--;
		DFS(level + 1, sum + arr[level + 1]);
		oper[1]++;
	}
	if (oper[2] > 0) {
		oper[2]--;
		DFS(level + 1, sum - arr[level + 1]);
		oper[2]++;
	}
	if (oper[3] > 0) {
		oper[3]--;
		DFS(level + 1, sum * arr[level + 1]);
		oper[3]++;
	}
	if (oper[4] > 0) {
		oper[4]--;
		DFS(level + 1, sum / arr[level + 1]);
		oper[4]++;
	}
}

int main() {

	cin >> N;

	for (int i = 1; i <= N; i++)
		cin >> arr[i];

	for (int i = 1; i <= 4; i++)
		cin >> oper[i];

	DFS(1, arr[1]);

	cout << Max << "\n" << Min;

	return 0;
}