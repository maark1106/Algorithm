#include<bits/stdc++.h>
#define MAX 1000000000
using namespace std;

int city[101][101];
int nxt[101][101];
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
		nxt[a][b] = b;
	}

	for (int i = 1; i <= N; i++)
		city[i][i] = 0;

	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (k == i || k == j || i == j)
					continue;

				if (city[i][k] + city[k][j] < city[i][j]) {
					city[i][j] = city[i][k] + city[k][j];
					nxt[i][j] = nxt[i][k];
				}
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

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (city[i][j] == MAX || city[i][j] == 0) {
				cout << 0 << "\n";
				continue;
			}

			vector<int> v;
			int start = i;			
			while (start != j) {
				v.push_back(start);
				start = nxt[start][j];
			}
			v.push_back(j);

			cout << v.size() << " ";
			for (auto a : v)
				cout << a << " ";
			cout << "\n";
		}
	}

	return 0;
}