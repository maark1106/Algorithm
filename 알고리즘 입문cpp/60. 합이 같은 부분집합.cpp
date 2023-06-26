#include<iostream>
#include<vector>

using namespace std;

int N, arr[11], total;
bool result = false;

void DFS(int level, int sum) {
	if (result == true || sum > total / 2)
		return;

	if (level == N + 1) {					
		if (sum == total - sum)
			result = true;
		return;
	}
	
	DFS(level + 1, sum + arr[level]);
	DFS(level + 1, sum);
}

int main() {
		
	result = 0;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
		total += arr[i];
	}
	DFS(1, 0);

	if (result)
		cout << "YES";
	else
		cout << "NO";

	return 0;
}