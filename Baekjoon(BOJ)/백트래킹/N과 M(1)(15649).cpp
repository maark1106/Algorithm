#include<iostream>
using namespace std;

int N, M;
bool check[9];
int list[9];

void dfs(int cnt) {

	if (cnt == M) {
		for (int i = 0; i < M; i++) {
			cout << list[i] << " ";
		}
		cout << "\n";
		return;
	}
	
	for (int i = 1; i <= N; i++) {

		if (!check[i]) {
			check[i] = true;
			list[cnt] = i;
			dfs(cnt + 1);
			check[i] = false;
		}
	}

}

int main() {

	cin >> N >> M;

	dfs(0);

	return 0;
}