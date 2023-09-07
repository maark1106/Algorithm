#include<bits/stdc++.h>
#define MAX 1000001

using namespace std;

int N, M;
int tree[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;
	for (int i = 0; i < N; i++)
		cin >> tree[i];

	sort(tree, tree + N);

	int lt = 0, rt = tree[N - 1];

	int res;
	while (lt <= rt) {
		int mid = (lt + rt) / 2;
		long long sum = 0;

		for (int i = N - 1; i >= 0; i--) {
			long long cutLength = tree[i] - mid;
			if (cutLength <= 0)
				break;
			sum += cutLength;
		}

		if (sum >= M) {
			res = mid;
			lt = mid + 1;
		}
		else
			rt = mid - 1;
	}

	cout << res;

	return 0;
}