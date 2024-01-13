#include<bits/stdc++.h>
using namespace std;

typedef long long ll;

set<ll> s;
ll arr[101];
int N;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		s.insert(arr[i]);
	}

	ll start;
	for (int i = 0; i < N; i++) {
		bool flag = true;
		for (int j = 0; j < N; j++) {
			if (i == j)
				continue;
			if (arr[j] % 3 == 0 && arr[j] / 3 == arr[i]) {				
				flag = false;
				break;
			}
			else {
				if (arr[j] * 2 == arr[i]) {
					flag = false;
					break;
				}
			}
		}
		if (flag == true) {
			start = arr[i];
			break;
		}
	}

	cout << start << " ";
	for (int i = 1; i < N; i++) {
		if (start % 3 == 0 && s.find(start / 3) != s.end()) { // ÀÖ´Ù¸é
			start = start / 3;
		}
		else {
			start = start * 2;
		}
		cout << start << " ";
	}

	return 0;
}