#include<bits/stdc++.h>
using namespace std;

int N, S, res = 2147000000;
int arr[100001];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> S;

	for (int i = 0; i < N; i++)
		cin >> arr[i];

	int p1 = 0, p2 = 0;
	int sum = arr[p1];

	while (p1 < N && p2 < N) {
		
		if (sum < S) {
			p2++;
			sum += arr[p2];
		}
		else{
			res = min(res, p2 - p1 + 1);
			sum -= arr[p1];
			p1++;
		}
		
	}

	if (res == 2147000000)
		cout << 0;
	else
		cout << res;
}