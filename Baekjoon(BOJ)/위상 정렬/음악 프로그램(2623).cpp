#include<bits/stdc++.h>
using namespace std;

vector<int> team[1001];
vector<int> res;
int deg[1001];
int N, M;

void tpSort() {

	queue<int> q;
	for (int i = 1; i <= N; i++) {
		if (deg[i] == 0) {			
			q.push(i);
		}
	}

	while (!q.empty()) {
		int top = q.front();
		q.pop();
		res.push_back(top);

		for (int cur : team[top]) {
			deg[cur]--;
			if (deg[cur] == 0) {			
				q.push(cur);
			}
		}
	}

	if (res.size() < N) 
		cout << "0";	
	else {
		for (auto num : res)
			cout << num << "\n";
	}

}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	for (int i = 1; i <= M; i++) {
		int cnt, team1;
		cin >> cnt;
		
		cin >> team1;
		for (int j = 1; j < cnt; j++) {
			int team2;
			cin >> team2;
			team[team1].push_back(team2);
			deg[team2]++;
			team1 = team2;
		}
	}

	tpSort();

	return 0;
}