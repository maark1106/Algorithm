#include<bits/stdc++.h>
using namespace std;

int N, S, res = 0;
int arr[21];
priority_queue()

void recursive(int L, int sum) {

	if (L == N) {
		if (sum == S)
			res++;
		return;
	}
	
	recursive(L + 1, sum);
	recursive(L + 1, sum + arr[L]);
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> S;

	for (int i = 0; i < N; i++)
		cin >> arr[i];	
	
	recursive(0, 0);

	cout << res;

	return 0;
}