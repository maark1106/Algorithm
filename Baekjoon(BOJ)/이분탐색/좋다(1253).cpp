#include<bits/stdc++.h>
#define MAX 2001

using namespace std;

int N;
int arr[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	sort(arr, arr + N);

	int res = 0;
	for (int i = 0; i < N; i++) {
		int lt = 0, rt = N - 1;	

		while (lt < rt) {
			if (lt == i) {
				lt++;
				continue;
			}

			if (rt == i) {				
				rt--;
				continue;
			}

			int sum = arr[lt] + arr[rt];

			if (sum == arr[i]) {
				res++;
				break;
			}
			else if (sum > arr[i]) 
				rt--;
			else 
				lt++;			
		}
	}

	cout << res;

	return 0;
}