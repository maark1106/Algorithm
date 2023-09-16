#include<bits/stdc++.h>
using namespace std;

int N, M;
int arr[100001];
int res = 2147000000;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
		cin >> arr[i];

	sort(arr, arr + N);

	int lt = 0, rt = 1;
	while (rt < N) {		

		if (arr[rt] - arr[lt] < M)
			rt++;
		else {
			res = min(res, arr[rt] - arr[lt]);
			lt++;
		}
	}

	cout << res;
}