#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
#define MAX 100001

using namespace std;
typedef pair<int, int> p;

//priority_queue <p> pQ;
priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > >pQ;
int visited[100001];

int K, X;

void BFS() {
	

	while (!pQ.empty()) {
		int time = pQ.top().first;
		int curPos = pQ.top().second;

		pQ.pop();

		if (curPos == K) {
			cout << time;
			return;
		}

		if (!visited[curPos]) {
			visited[curPos] = 1;

			if (curPos*2 < MAX && !visited[curPos*2])
				pQ.push({ time, curPos * 2 });

			if (curPos + 1 < MAX && !visited[curPos + 1])
				pQ.push({ time + 1, curPos + 1 });

			if (curPos -1 >=0  && !visited[curPos - 1])
				pQ.push({ time + 1, curPos - 1 });
		}
	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> X >> K;
	pQ.push({ 0,X });

	BFS();
	
	return 0;
}