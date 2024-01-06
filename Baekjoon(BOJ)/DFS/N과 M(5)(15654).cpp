#include<bits/stdc++.h>
using namespace std;

int N, M;
int arr[10];
int res[10];
bool check[10];

void DFS(int level) {
	
	if (level == M) {
		for (int i = 0; i < M; i++)
			cout << res[i] << " ";
		cout << "\n";
		return;
	}

	for (int i = 0; i < N; i++) {
		if (!check[i]) {
			check[i] = true;
			res[level] = arr[i];
			DFS(level + 1);
			check[i] = false;
		}
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	sort(arr, arr + N);
	DFS(0);

	return 0;
}