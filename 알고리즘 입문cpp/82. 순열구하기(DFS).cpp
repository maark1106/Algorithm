#include<iostream>
#include<vector>

using namespace std;

int arr[17], res[17], visited[17];
int N, R, cnt = 0;

void DFS(int level) {
	if (level == R) {
		for (int i = 0; i < R; i++)
			cout << res[i] << " ";
		cout << "\n";
		cnt++;
		return;
	}

	for (int i = 0; i < N; i++) {
		if (!visited[i]) {
			visited[i] = 1;
			res[level] = arr[i];
			DFS(level + 1);
			visited[i] = 0;
		}
	}

	return;
}

int main() {

	ios::sync_with_stdio(0); cin.tie(0);


	cin >> N >> R;

	for (int i = 0; i < N; i++)
		cin >> arr[i];

	DFS(0);

	cout << cnt;

}