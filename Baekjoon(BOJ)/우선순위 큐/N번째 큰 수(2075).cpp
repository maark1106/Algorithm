#include<bits/stdc++.h>
using namespace std;

priority_queue<int, vector<int>,greater<int>> Q;
int N;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;	

	int num;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {			
			cin >> num;

			Q.push(num);
			if (Q.size() > N)
				Q.pop();
		}
	}

	cout << Q.top();
	

	return 0;
}