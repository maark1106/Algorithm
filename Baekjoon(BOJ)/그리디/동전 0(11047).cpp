#include<bits/stdc++.h>

using namespace std;

int N, K;
int coin[11];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> K;
	for (int i = 1; i <= N; i++)
		cin >> coin[i];

	int res = 0;

	for (int i = N; i > 0; i--) {
		while (K >= coin[i]) {
			K -= coin[i];
			res++;
		}

		if (K == 0)
			break;
	}

	cout << res;

	return 0;
}