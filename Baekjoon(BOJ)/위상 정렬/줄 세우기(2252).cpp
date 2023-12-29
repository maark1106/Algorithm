#include<bits/stdc++.h>
using namespace std;

vector<int> stu[32001];
int deg[32001];
int N, M;

void topologicalSort() {

	vector<int> res;	

	for (int i = 1; i <= N; i++) {
		if (deg[i] == 0) {
			res.push_back(i);
		}
	}

	int idx = 0;

	while (res.size() < N) {
		int topNum = res[idx++];
		for (int cur : stu[topNum]) {
			deg[cur]--;
			if (deg[cur] == 0) {
				res.push_back(cur);
			}
		}
	}

	for (int num : res) {
		cout << num << " ";
	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> M;
	for (int i = 1; i <= M; i++) {
		int st1, st2;
		cin >> st1 >> st2;
		stu[st1].push_back(st2);
		deg[st2]++;
	}

	topologicalSort();


	return 0;
}