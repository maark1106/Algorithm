#include<bits/stdc++.h>
#define MAX 100001
using namespace std;

int N;
int arr[MAX];
int res[2];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	int lt = 0, rt = N - 1;

	int res = 2147000000;
	int p1, p2;
	while (lt < rt) {
		int sum = arr[lt] + arr[rt];

		if (sum == 0) {
			p1 = arr[lt];
			p2 = arr[rt];
			break;
		}
		else if (abs(sum) < res) {
			res = abs(sum);
			p1 = arr[lt];
			p2 = arr[rt];
		}
		
		if (sum > 0)
			rt--;
		else
			lt++;

	}

	cout << p1 << " " << p2;

	return 0;
}