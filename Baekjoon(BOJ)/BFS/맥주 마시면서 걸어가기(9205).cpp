#include<bits/stdc++.h>
using namespace std;
typedef struct pair<int, int>p;

vector<int> graph[103];
p board[103];

bool solve() {
	

	int N;

	cin >> N;
	for (int i = 0; i <= N + 1; i++) {
		cin >> board[i].first >> board[i].second;
	}

	// 두 좌표를 검사하여 거리가 1000 이하면 그래프에 추가
	for (int i = 0; i <= N ; i++) { 
		for (int j = i + 1; j <= N + 1; j++) {
			if (abs(board[i].first - board[j].first) + abs(board[i].second - board[j].second) <= 1000) {
				graph[i].push_back(j);
				graph[j].push_back(i);
			}
		}
	}

	bool visited[103];
	fill(visited, visited + 103, false);
	queue<int> q;
	q.push(0);
	visited[0] = true;

	while (!q.empty()) {
		int top = q.front();
		q.pop();
		if (top == N + 1) 
			return true;
		
		for (int cur : graph[top]) {
			if (!visited[cur]) {
				visited[cur] = true;
				q.push(cur);
			}
		}
	}

	return false;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int T;
	cin >> T;
	while (T--) {		
		if (solve())
			cout << "happy\n";
		else
			cout << "sad\n";
	}

	return 0;
}