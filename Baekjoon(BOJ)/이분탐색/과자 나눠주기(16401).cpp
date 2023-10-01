#include<bits/stdc++.h>
using namespace std;

int person[1000001];
int snack[1000001];
int N, M;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);	

	cin >> M >> N;

	for (int i = 0; i < N; i++)
		cin >> snack[i];

	sort(snack, snack + N);
	
	int lt = 1, rt = snack[N - 1];

	int res = 0;
	while (lt <= rt) {
		int cnt = 0;
		int mid = (lt + rt) / 2;

		for (int i = 0; i < N; i++) {			
			cnt += (int)snack[i] / mid;
		}
		
		if (cnt >= M) {
			res = mid;
			lt = mid + 1;			
		}
		else
			rt = mid - 1;		
	}

	cout << res;
	
	return 0;
}