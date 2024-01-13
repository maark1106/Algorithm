#include<bits/stdc++.h>
#define MAX 10000000
using namespace std;

int N, M;
int adj[101][101];

int calSum(int n1, int n2) {

	int sum = 0;
	for (int i = 1; i <= N; i++) {
		sum += min(adj[n1][i], adj[n2][i]);
	}
	return sum;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	for (int i = 1; i <= N; i++)
		fill(adj[i], adj[i] + N + 1, MAX);

	for (int i = 1; i <= N; i++)
		adj[i][i] = 0;

	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		adj[a][b] = 1;
		adj[b][a] = 1;
	}

	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == k || i == j || j == k)
					continue;
				adj[i][j] = min(adj[i][j], adj[i][k] + adj[k][j]);
			}
		}
	}
	
	int res = MAX;
	int min1, min2;
	for (int i = 1; i < N; i++) {
		for (int j = i + 1; j <= N; j++) {
			int disSum = calSum(i, j);
			if (disSum < res) {
				res = disSum;
				min1 = i;
				min2 = j;
			}
		}
	}

	cout << min1 << " " << min2 << res * 2;

	return 0;
}