#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int N, M, res = 0;
vector<int> v[1001];
bool visited[1001];

queue <int> Q;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;

	int s, e;
	for (int i = 1; i <= M; i++) {
		cin >> s >> e;
		v[s].push_back(e);
		v[e].push_back(s);
	}

	for (int i = 1; i <= N; i++) {
		if (visited[i] == false) {
			res++;
			visited[i] = true;
			Q.push(i);

			while (!Q.empty()) {
				int sVertex = Q.front();
				Q.pop();

				for (int j = 0; j < v[sVertex].size(); j++) {
					int eVertex = v[sVertex][j];
					if (visited[eVertex] == false) {
						visited[eVertex] = true;
						Q.push(eVertex);
					}
				}
			}
		}
	}

	cout << res;

	return 0;
}