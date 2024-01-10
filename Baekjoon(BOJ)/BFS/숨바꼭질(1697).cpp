#include<bits/stdc++.h>
using namespace std;

typedef pair<int, int> p;

int N, K;
bool visited[100001];

void solve() {

	queue<p> q;
	visited[N] = true;
	q.push({ 0,N });

	while (!q.empty()) {
		int cur = q.front().second;
		int dis = q.front().first;
		q.pop();

		if (cur == K) {
			cout << dis;
			return;
		}

		if (cur - 1 >= 0 && !visited[cur - 1]) {
			visited[cur - 1] = true;
			q.push({ dis + 1, cur - 1 });
		}

		if (cur + 1 <= 100000 && !visited[cur + 1]) {
			visited[cur + 1] = true;
			q.push({ dis + 1, cur + 1 });
		}

		if (cur * 2 <= 100000 && !visited[cur * 2]) {
			visited[cur * 2] = true;
			q.push({ dis + 1, cur * 2 });
		}
	}

}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> K;

	solve();

	

	return 0;
}