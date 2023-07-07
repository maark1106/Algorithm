#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;

typedef pair<int, int> p;
vector<p> arr[20001];

int V, E, K;

int main() {

	int u, v, w;
	priority_queue<p> pQ; // first->가중치, second->정점

	cin >> V >> E;
	cin >> K;

	vector<int> res(V + 1, 2147000000);	

	for (int i = 0; i < E; i++) {
		cin >> u >> v >> w;
		arr[u].push_back(make_pair(w, v));
	}

	pQ.push(make_pair(0, K));
	res[K] = 0;

	while (!pQ.empty()) {
		int dis = -pQ.top().first;
		int vertex = pQ.top().second;
		pQ.pop();

		if (res[vertex] < dis)
			continue;

		for (int i = 0; i < arr[vertex].size(); i++) {
			int nextDis = res[vertex] + arr[vertex][i].first;
			int nextVertex = arr[vertex][i].second;

			if (nextDis < res[nextVertex]) {
				res[nextVertex] = nextDis;
				pQ.push(make_pair(-nextDis, nextVertex));
			}
		}
	}

	for (int i = 1; i <= V; i++) {
		if (res[i] == 2147000000)
			cout << "INF" << "\n";
		else
			cout << res[i] << "\n";
	}

	return 0;
}