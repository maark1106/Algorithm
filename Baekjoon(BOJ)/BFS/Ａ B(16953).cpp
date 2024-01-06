#include<bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef pair<ll, int> p;

int A, B;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> A >> B;

	queue<p> q;
	q.push({ A,0 });

	while (!q.empty()) {
		ll curNum = q.front().first;
		int level = q.front().second;
		q.pop();
		if (curNum == B) {
			cout << level + 1;
			return 0;
		}

		if (curNum * 2 <= B)
			q.push({ curNum * 2, level + 1 });
		if (curNum * 10 + 1 <= B)
			q.push({ curNum * 10 + 1, level + 1 });

	}

	cout << -1;

	return 0;
}