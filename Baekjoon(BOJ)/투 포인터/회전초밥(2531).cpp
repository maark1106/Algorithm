#include<bits/stdc++.h>
using namespace std;

int arr[30001];
int n, d, k, c;
int res = 0;
int check[3001];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n >> d >> k >> c;

	for (int i = 0; i < n; i++)
		cin >> arr[i];

	int cnt = 1;
	check[c] = 1;
	for (int i = 0; i < k; i++) {
		if (check[arr[i]] == 0)
			cnt++;
		check[arr[i]]++;
	}

	res = cnt;
	for (int i = 0; i < n; i++) {		
		check[arr[i]]--;
		if (check[arr[i]] == 0)
			cnt--;

		if (check[arr[(i + k) % n]] == 0)
			cnt++;
		check[arr[(i + k) % n]]++;

		res = max(res, cnt);
	}
	
	cout << res;

	return 0;
}