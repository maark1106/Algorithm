#include<iostream>
#include<vector>
#include<queue>
using namespace std;

vector<int> v[51];
int dis[51][51];
int N;

void DFS(int sVertex, int rootVertex, int distance) {

	for (int i = 0; i < v[sVertex].size(); i++) {
		int eVertex = v[sVertex][i];
		if (dis[rootVertex][eVertex] == 0) {
			dis[rootVertex][eVertex] = distance + 1;
			DFS(eVertex, rootVertex, distance + 1);
		}
		else if (dis[rootVertex][eVertex] != 0) {
			if (dis[rootVertex][eVertex] > distance + 1) {
				dis[rootVertex][eVertex] = distance + 1;
				DFS(eVertex, rootVertex, distance + 1);
			}
		}
	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	
	while (1) {
		int s, e;
		cin >> s >> e;
		if (s == -1 && e == -1)
			break;

		v[s].push_back(e);
		v[e].push_back(s);
	}

	for (int i = 1; i <= N; i++) {
		DFS(i, i, 0);
	}


	
	int res[51], min = 51, cnt = 0;

	for (int i = 1; i <= N; i++) {
		int max = 0;
		for (int j = 1; j <= N; j++) {
			if (i == j)
				continue;
			if (max < dis[i][j])
				max = dis[i][j];
		}
		res[i] = max;
		if (min > res[i])
			min = res[i];
	}

	for (int i = 1; i <= N; i++) {
		if (min == res[i])
			cnt++;
	}

	cout << min << " " << cnt << "\n";

	for (int i = 1; i <= N; i++) {
		if (min == res[i])
			cout << i << " ";
	}

	return 0;
}