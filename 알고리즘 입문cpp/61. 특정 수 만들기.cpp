#include<iostream>
using namespace std;

int target, arr[101], res = 0, N, storge[11];

void DFS(int level, int sum) {

	if (level == N + 1) {
		if (sum == target) {
			for (int i = 1; i < level; i++)
				cout << storge[i] << " ";
			puts("");
			res++;
		}
		return;
	}
	storge[level] = arr[level];
	DFS(level + 1, sum + arr[level]);
	storge[level] = -arr[level];
	DFS(level + 1, sum - arr[level]);
	storge[level] = 0;
	DFS(level + 1, sum);
}

int main() {
	
	cin >> N >> target;

	for (int i = 1; i <= N; i++)
		cin >> arr[i];
	DFS(1, 0);

	if (res)
		cout << res;
	else
		cout << -1;

	return 0;
}