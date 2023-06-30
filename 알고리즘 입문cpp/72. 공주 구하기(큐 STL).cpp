#include<iostream>
#include<queue>
using namespace std;


int main() {
	
	int N, K;
	queue<int> Q;

	cin >> N >> K;

	for (int i = 1; i <= N; i++)
		Q.push(i);

	while (!Q.empty()) {
		for (int i = 0; i < K; i++) {
			Q.push(Q.front());
			Q.pop();
		}
		Q.pop();
		if (Q.size() == 1) {
			cout << Q.front();
			Q.pop();
		}
	}
		
	
	return 0;
}