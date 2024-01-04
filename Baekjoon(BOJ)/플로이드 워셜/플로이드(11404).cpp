#include<bits/stdc++.h>
#define MAX 1000000000
using namespace std;

int city[101][101];
int N, M;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 1; i <= N; i++)
		fill(city[i], city[i] + N + 1, MAX);

	for (int i = 0; i < M; i++) {
		int a, b, dis;
		cin >> a >> b >> dis;
		city[a][b] = min(city[a][b], dis);
	}

	for (int i = 1; i <= N; i++)
		city[i][i] = 0;
	
	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == k || j == k || i == j)
					continue;
				city[i][j] = min(city[i][j], city[i][k] + city[k][j]);
			}
		}
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (city[i][j] == MAX)
				cout << 0 << " ";
			else
				cout << city[i][j] << " ";
		}
		cout << "\n";
	}

	return 0;
}