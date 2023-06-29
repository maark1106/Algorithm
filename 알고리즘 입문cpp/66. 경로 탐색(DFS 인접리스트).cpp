#include<iostream>
#include<vector>
using namespace std;

int check[30], res = 0, n;
vector<int> map[30];

void DFS(int vertex) {

	if (vertex == n) {
		res++;
		return;
	}	

	for (int i = 0; i < map[vertex].size();i++) {
		if (!check[map[vertex][i]]) {
			check[map[vertex][i]] = 1;
			DFS(map[vertex][i]);
			check[map[vertex][i]] = 0;
		}
	}
}

int main() {	
	
	int m, start, end;

	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		cin >> start >> end;
		map[start].push_back(end);
	}

	check[1] = 1;
	DFS(1);
	cout << res;

	return 0;
}