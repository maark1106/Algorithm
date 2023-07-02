#include<iostream>
#include<vector>

using namespace std;

int unf[1001];

int Find(int v) {
	if (v == unf[v])
		return v;

	unf[v] = Find(unf[v]);
	return unf[v];
}

void Union(int a, int b) {
	a = Find(a);
	b = Find(b);

	if (a != b)
		unf[a] = b;
}

int main() {

	int N, M, a, b;

	cin >> N >> M;

	for (int i = 1; i <= N; i++)
		unf[i] = i;

	for (int i = 1; i <= M; i++) {
		cin >> a >> b;
		Union(a, b);
	}

	cin >> a >> b;

	int f1 = Find(a);
	int f2 = Find(b);

	if (f1 == f2)
		cout << "YES";
	else
		cout << "NO";

	return 0;
}