#include<bits/stdc++.h>
using namespace std;

int D[9];
int W[9];
int N;

int res = 0;

void dfs(int L) {

	if (L == N + 1) {
		int cnt = 0;
		for (int i = 1; i <= N; i++)
			if (D[i] <= 0)
				cnt++;
		res = max(res, cnt);
		return;
	}

	if (D[L] <= 0) {
		dfs(L + 1);
		return;
	}

	bool flag = false;
	for (int i = 1; i <= N; i++) {
		if (i == L)
			continue;

		if (D[i] > 0) {
			D[L] -= W[i];
			D[i] -= W[L];

			dfs(L + 1);
			flag = true;
			D[L] += W[i];
			D[i] += W[L];
		}
	}

	if (!flag)
		dfs(N + 1);

}



int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	for (int i = 1; i <= N; i++)
		cin >> D[i] >> W[i];

	dfs(1);

	cout << res;

	return 0;
}