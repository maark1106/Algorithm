#include<iostream>
#include<vector>
#include<queue>

#define MAX	100001

using namespace std;

int parent[MAX];
vector <int> graph[MAX];
bool visited[MAX];
int N;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	queue<int> Q;

	cin >> N;
	int node1, node2;
	for (int i = 0; i < N - 1; i++) {
		cin >> node1 >> node2;
		graph[node1].push_back(node2);
		graph[node2].push_back(node1);
	}
	Q.push(1);
	visited[1] = true;

	while (!Q.empty()) {
		int parentNum = Q.front();
		Q.pop();

		for (int i = 0; i < graph[parentNum].size(); i++) {
			int nextNum = graph[parentNum][i];
			if (!visited[nextNum]) {
				visited[nextNum] = true;
				parent[nextNum] = parentNum;
				Q.push(nextNum);
			}
		}
	}
	
	for (int i = 2; i <= N; i++)
		cout << parent[i] << "\n";

	return 0;
}