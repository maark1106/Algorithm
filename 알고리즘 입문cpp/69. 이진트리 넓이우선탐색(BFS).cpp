#include<iostream>
#include<vector>

using namespace std;

int Q[100], front = -1, back = -1, check[10];
vector<int> map[10];

int main() {

	int a, b, x;

	for (int i = 0; i < 6; i++) {
		cin >> a >> b;
		map[a].push_back(b);
	}

	Q[++back] = 1;
	check[1] = 1;

	while (front < back) {
		x = Q[++front];
		cout << x << " ";
		for (int i = 0; i < map[x].size(); i++) {
			if (!check[map[x][i]]) {
				check[map[x][i]] = 1;
				Q[++back] = map[x][i];
			}
		}
	}

	return 0;
}