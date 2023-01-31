#include<iostream>
using namespace std;

int N, M;
bool visit[9];
int arr[9];

void dfs(int cnt) {
	
	if (cnt == M) {
		for (int i = 0; i < M; i++)
			cout << arr[i] << " ";
		cout << "\n";
	}

	for (int i = 1; i <= N; i++) {

		if (cnt && arr[cnt - 1] > i) {
			continue;
		}
		
		if (!visit[i]) {
			visit[i] = true;
			arr[cnt] = i;
			dfs(cnt + 1);
			visit[i] = false;
		}
	}
}

int main() {

	cin >> N >> M;

	dfs(0);


	return 0;
}