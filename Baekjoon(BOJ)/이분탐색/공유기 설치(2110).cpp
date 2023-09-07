#include<bits/stdc++.h>
#define MAX 200001

using namespace std;

int N, C;
int x[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> C;

	for (int i = 0; i < N; i++)
		cin >> x[i];

	sort(x, x + N);

	int lt = 0, rt = x[N - 1];

	int res = 0;
	while (lt <= rt) {

		int mid = (lt + rt) / 2;
		int pos = x[0];
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			if (x[i] - pos >= mid) {
				pos = x[i];
				cnt++;
			}			
		}
		
		if (cnt >= C) {
			res = mid;
			lt = mid + 1;
		}
		else
			rt = mid - 1;
	}

	cout << res;
	
	return 0;
}