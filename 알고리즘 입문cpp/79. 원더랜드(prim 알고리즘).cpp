#include<iostream>
#include<vector>
#include<algorithm>
#include <queue>

using namespace std;

typedef pair<int, int> p;

int visited[101];
vector<p> map[30];


int main() {

	int N, M, a, b, c, res = 0;
	priority_queue <p> pQ;

	cin >> N >> M;

	for (int i = 1; i <= M; i++) {
		cin >> a >> b >> c;
		map[a].push_back(make_pair(b, c));
		map[b].push_back(make_pair(a, c));
	}

	pQ.push(make_pair(0, 1));
	while (!pQ.empty()) {
		p temp = pQ.top();
		int cost = temp.first * -1;
		int v = temp.second;

		pQ.pop();
		if (!visited[v]) {
			visited[v] = 1;
			res += cost;

			for (int i = 0; i < map[v].size(); i++) {
				pQ.push(make_pair(map[v][i].second * -1, map[v][i].first));
			}
		}
	}

	cout << res;

	return 0;
}