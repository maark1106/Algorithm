#include<bits/stdc++.h>

using namespace std;

int N;

bool compare(int a, int b) {
	return a > b;
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> N;
	
	vector<int> A(N);
	vector<int> B(N);

	for (int i = 0; i < N; i++)
		cin >> A[i];

	for (int i = 0; i < N; i++)
		cin >> B[i];

	sort(A.begin(), A.end(), compare);
	sort(B.begin(), B.end());
	
	int res = 0;
	for (int i = 0; i < N; i++)
		res += A[i] * B[i];

	cout << res;

	return 0;
}