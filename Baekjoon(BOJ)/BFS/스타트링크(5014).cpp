#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

#pragma warning(disable:4996)

using namespace std;

int visited[1000001];
int F, S, G, U, D, res = 0;
queue <pair<int, int>> Q;

int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> F >> S >> G >> U >> D;

	Q.push({ 0,S });
	visited[S] = 1;

	while (!Q.empty()) {
		int dis = Q.front().first;
		int pos = Q.front().second;
		Q.pop();

		if (pos == G) {
			cout << dis;
			return 0;
		}

		int pUp = pos + U;
		int pDown = pos - D;

		if (pUp > 0 && pUp <= F && visited[pUp] == 0) {
			visited[pUp] = 1;
			Q.push({ dis + 1, pUp });
		}

		if (pDown > 0 && pDown <= F && visited[pDown] == 0) {
			visited[pDown] = 1;
			Q.push({ dis + 1, pDown });
		}
	}

	cout << "use the stairs";

	return 0;
}