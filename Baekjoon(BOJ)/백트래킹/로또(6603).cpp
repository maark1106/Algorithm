#include<iostream>
#include<stack>
using namespace std;

int N;
int lotto[14];
int ans[6];

void dfs(int start, int depth) {

	if (depth == 6) {
		for (int i = 0; i < 6; i++)
			cout << ans[i] << " ";
		cout << endl;
		return;
	}

	for (int i = start; i < N; i++) {
		ans[depth] = lotto[i];
		dfs(i + 1, depth + 1);
	}
}

int main() {

	while (true) {
		
		cin >> N;
		for (int i = 0; i < N; i++)
			cin >> lotto[i];
		if (!N)
			break;

		dfs(0, 0);
		cout << endl;
	}

	return 0;
}