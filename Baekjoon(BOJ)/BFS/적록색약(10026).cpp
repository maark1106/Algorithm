#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<queue>

using namespace std;

typedef pair<int, int> p;
int dy[4] = { 1,-1,0,0 };
int dx[4] = { 0,0,1,-1 };
char map[101][101];
bool visited[101][101];
queue < pair<char, p >> Q;
int N, res = 0;

void visitInit() {
	for (int i = 0; i < 101; i++)
		for (int j = 0; j < 101; j++)
			visited[i][j] = false;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 'R')
				map[i][j] = 'G';
		}
	}

	res = 0;
}

void BFS() {

	while (!Q.empty()) {
		int y = Q.front().second.first;
		int x = Q.front().second.second;
		char ch = Q.front().first;

		Q.pop();

		for (int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];

			if (xx < 0 || xx >= N || yy < 0 || yy >= N)
				continue;

			if (!visited[yy][xx] && map[yy][xx] == ch) {
				Q.push({ ch,{yy,xx} });
				visited[yy][xx] = true;
			}
		}
	}
}


int main() {

	cin >> N;

	for (int i = 0; i < N; i++) 
		for (int j = 0; j < N; j++)
			cin >> map[i][j];			
		
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visited[i][j]) {
				Q.push({ map[i][j],{i,j} });
				visited[i][j] = true;
				BFS();
				res++;
			}
		}
	}
	cout << res << " ";
	visitInit();
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visited[i][j]) {
				Q.push({ map[i][j],{i,j} });
				visited[i][j] = true;
				BFS();
				res++;
			}
		}
	}
	cout << res << " ";
	
	
	return 0;
}