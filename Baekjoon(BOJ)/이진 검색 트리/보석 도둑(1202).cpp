#include<bits/stdc++.h>
using namespace std;

int N, K;
int M, V;

int bag[300001];
priority_queue<int> pQ;
vector<pair<int, int>> v;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		cin >> M >> V;
		v.push_back({ M,V });
	}
	
	int C;
	for (int i = 0; i < K; i++) {
		cin >> bag[i];
	}
	sort(v.begin(),v.end());
	sort(bag, bag + K);
	
	long long res = 0;
	int idx = 0;
	for (int i = 0; i < K; i++) {
		int maxWeight = bag[i];

		while (idx < N && v[idx].first <= maxWeight) {
			pQ.push(v[idx].second);
			idx++;
		} 
		
		if (!pQ.empty()) { 
			res += pQ.top();
			pQ.pop();
		}
	}

	cout << res;

	return 0;
}