#include<iostream>
#include<algorithm>
using namespace std;

int N, M;
int storge[9], arr[9];
bool visit[9];

void dfs(int cnt) {

	if (cnt == M) {
		for (int i = 0; i < M; i++)
			cout << storge[i] << " ";
		cout << "\n";
		return;
	}

	for (int i = 0; i < N; i++) {

		if (!visit[i]) {

			if (storge[cnt] == arr[i])
				continue;
			visit[i] = true;
			storge[cnt] = arr[i];
			dfs(cnt + 1);
			visit[i] = false;
		}
	}
}

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	sort(arr, arr + N);

	dfs(0);

	return 0;
}