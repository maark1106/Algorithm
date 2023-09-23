#include<bits/stdc++.h>
#include<unordered_map>

using namespace std;

unordered_map<long long, long long> um;
long long N, P, Q;

long long dfs(long long N) {

	if (um.find(N) != um.end())
		return um[N];

	um[N] = dfs(N / P) + dfs(N / Q);

	return um[N];
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> P >> Q;

	um[0] = 1;
	long long res = dfs(N);

	cout << res;

	return 0;
}