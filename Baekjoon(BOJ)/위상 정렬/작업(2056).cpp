#include<bits/stdc++.h>
#define MAX 10000000
using namespace std;

vector<int>graph[10001];
int workTime[10001];
int totalTime[10001];
int deg[10001];
int N;

void solve() {

	fill(totalTime, totalTime + N, 0);

	queue<int> q;
	for (int i = 1; i <= N; i++) {
		if (deg[i] == 0) {
			q.push(i);
			totalTime[i] = workTime[i];
		}
	}

	while (!q.empty()) {
		int cur = q.front();
		int curTime = totalTime[cur];
		q.pop();

		for (int incident : graph[cur]) {
			deg[incident]--;
			totalTime[incident] = max(totalTime[incident], curTime + workTime[incident]);

			if (deg[incident] == 0) {
				q.push(incident);
			}
		}		
	}

	int res = 0;
	for (int i = 1; i <= N; i++) {
		res = max(res, totalTime[i]);
	}

	cout << res;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;

	for (int i = 1; i <= N; i++) {
		cin >> workTime[i];
		int cnt;
		cin >> cnt;
		for (int j = 0; j < cnt; j++) {
			int num;
			cin >> num;
			graph[i].push_back(num);
			deg[num]++;
		}
	}

	solve();


	return 0;
}