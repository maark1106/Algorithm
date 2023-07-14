#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<queue>

using namespace std;
typedef pair<int, int> p;

int dy[8] = { -2,-2,-1,-1,1,1,2,2 };
int dx[8] = { -1,1,-2,2,-2,2,-1,1 };

int T, I;
pair<int, int > s, e; 
queue <pair<int, p>> Q;

void BFS() {

	int visited[301][301] = { 0 };
	visited[Q.front().second.first][Q.front().second.second] = 1;

	while (!Q.empty()) {
		int y = Q.front().second.first;
		int x = Q.front().second.second;
		int dis = Q.front().first;
		

		if (y == e.first && x == e.second) {
			cout << dis << "\n";
			return;
		}

		Q.pop();

		for (int i = 0; i < 8; i++) {
			int curY = y + dy[i];
			int curX = x + dx[i];			

			if (curY < 0 || curY >= I || curX < 0 || curX >= I)
				continue;
			
			if (!visited[curY][curX]) {
				visited[curY][curX] = 1;
				Q.push({ dis + 1, {curY, curX} });
			}
		}
	}
}

int main() {

	cin >> T;

	while (T--) {
		cin >> I;

		cin >> s.first >> s.second;

		Q.push({ 0,{s.first,s.second} });

		cin >> e.first >> e.second;

		BFS();
		while (!Q.empty())
			Q.pop();
	}
	
	return 0;
}