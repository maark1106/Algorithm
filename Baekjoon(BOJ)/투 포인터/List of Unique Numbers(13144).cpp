#include<bits/stdc++.h>
using namespace std;

int N;
int arr[100001];
bool visited[100001];
long long res = 0;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	int p1 = 0, p2 = 0;	
	while (p1 < N && p2 < N) {
		if (!visited[arr[p2]]) {
			visited[arr[p2]] = true;
			p2++;
		}
		else {
			res += p2 - p1;
			visited[arr[p1]] = false;
			p1++;
		}
	}

	res += (long long)(p2 - p1) * (p2 - p1 + 1) / 2;

	cout << res;
}