#include<iostream>
#include<algorithm>

using namespace std;

int res[10], visited[10];
int N;

void DFS(int level) {
	if (level == N) {
		for (int i = 0; i < N; i++)
			if (visited[i])
				cout << res[i] << " ";
		cout << "\n";
		return;
	}

	for (int i = 0; i < N; i++) {
		if (!visited[i]) {
			res[level] = i + 1;
			visited[i] = 1;
			DFS(level + 1);
			visited[i] = 0;
		}
	}
}

int main() {	

	ios::sync_with_stdio(0); 
	cin.tie(0);
	
	cin >> N;
	DFS(0);


	return 0;
}