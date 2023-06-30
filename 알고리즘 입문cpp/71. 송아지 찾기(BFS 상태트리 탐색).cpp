#include<iostream>
#include<vector>
#include<queue>
using namespace std;

queue<int> q;
int s, e;
int visited[10001], d[3] = { -1,1, 5 };

void BFS() {
	
	while (!q.empty()) {		
		int storge = q.front();
		if (storge == e)
			break;
	
		q.pop();
		for (int i = 0; i < 3; i++) {
			int pos = storge + d[i];

			if (pos >= 0 && !visited[pos]) {				
				visited[pos] = visited[storge] + 1;
				q.push(pos);
			}			
		}
	}	
}

int main() {
	
	cin >> s >> e;

	q.push(s);	
	visited[s] = 1;
	BFS();

	cout << visited[e] - 1;

	return 0;
}