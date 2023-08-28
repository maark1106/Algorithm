#include<bits/stdc++.h>
#define MAX 100001
using namespace std;

int N;
int rope[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> N;

	for (int i = 0; i < N; i++)
		cin >> rope[i];

	sort(rope, rope + N, greater<>());
	
	int res = 0;
	for (int i = 0; i < N; i++) {				
		res = max(res, rope[i] * (i + 1));
	}

	cout << res;

	return 0;
}