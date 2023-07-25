#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int N, M, res = 0;
bool visited[501];
vector<int> v[501];
queue<int> Q;


int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;

	int s, e;
	for (int i = 0; i < M; i++) {
		cin >> s >> e;
		v[s].push_back(e);
		v[e].push_back(s);
		if (s == 1) {
			Q.push(e);
			visited[e] = true;
			res++;
		}
	}
	
	visited[1] = true;
	while (!Q.empty()) {
		int sVertex = Q.front();

		Q.pop();
		for (int i = 0; i < v[sVertex].size(); i++) {
			int eVertex = v[sVertex][i];
			if (!visited[eVertex]) {
				visited[eVertex] = true;
				res++;c
			}
		}
	}

	cout << res;
	
	return 0;
}