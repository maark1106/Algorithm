#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N, checked[21], res = 0;
vector<pair <int, int> > v[21];

void DFS(int vector, int sum) {
	if (vector == N) {
		if (sum < res)
			res = sum;
		return;
	}

	for (int i = 0;i < v[vector].size(); i++) {
		if (checked[v[vector][i].first] == 0) {
			checked[v[vector][i].first] = 1;
			DFS(v[vector][i].first, sum + v[vector][i].second);
			checked[v[vector][i].first] = 0;
		}
	}
}


int main() {

	int M, start, end, dis;

	cin >> N >> M;

	for (int i = 0; i < M; i++) {
		cin >> start >> end >> dis;
		v[start].push_back(make_pair(end, dis));
		res += dis;
	}

	checked[1] = 1;
	DFS(1, 0);

	cout << res;

	return 0;
}