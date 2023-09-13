#include<bits/stdc++.h>
using namespace std;

int N, res = 0;
bool visited1[40];
bool visited2[40];
bool visited3[40];

void putQueen(int cur) { 
	if (cur == N + 1) {
		res++;
		return;
	}

	for (int i = 1; i <= N; i++) { 
		if (!visited1[i] && !visited2[i + cur] && !visited3[i - cur + N - 1]) {
			visited1[i] = true;
			visited2[i + cur] = true;
			visited3[i - cur + N - 1] = true;

			putQueen(cur + 1);

			visited1[i] = false;
			visited2[i + cur] = false;
			visited3[i - cur + N - 1] = false;
		}

	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	putQueen(1);

	cout << res;

	return 0;
}