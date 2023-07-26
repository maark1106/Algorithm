#include<iostream>
#include<vector>
#include<queue>

using namespace std;
int N, delN, res = 0, root;

vector <int> graph[51];

void DFS(int parentNum) {

	if (parentNum == delN)
		return;

	bool flag = true;

	for (int i = 0; i < graph[parentNum].size(); i++) {
		int childNum = graph[parentNum][i];
		
		if (childNum != delN) {
			flag = false;
			DFS(childNum);
		}
	}

	if (flag)
		res++;
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int p;
		cin >> p;		

		if (p == -1)
			root = i;
		else
			graph[p].push_back(i);
	}

	cin >> delN;
	
	DFS(root);

	cout << res;

	return 0;
}