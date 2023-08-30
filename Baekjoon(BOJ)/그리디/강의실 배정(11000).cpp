#include<bits/stdc++.h>

using namespace std;

int N;
vector<pair<int, int>> classTime;
priority_queue<int, vector<int>, greater<int> > pq;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	int s, e;
	for (int i = 0; i < N; i++) {
		cin >> s >> e;
		classTime.push_back({s,e});
	}

	sort(classTime.begin(), classTime.end());

	pq.push(classTime[0].second);
	for (int i = 1; i < N; i++) {	

		if (pq.top() <= classTime[i].first)
			pq.pop();		

		pq.push(classTime[i].second);
	}
	
	cout << pq.size();		
	
	return 0;
}	