#include<iostream>
#include<vector>

using namespace std;

int dy[21][21];

int DFS(int n, int r) {

	if (dy[n][r])
		return dy[n][r];

	if (n == r || r == 0)
		return 1;

	dy[n][r] = DFS(n - 1, r - 1) + DFS(n - 1, r);

	return dy[n][r];
}

int main() {

	int n, r;

	cin >> n >> r;

	cout << DFS(n, r);
	return 0;
}