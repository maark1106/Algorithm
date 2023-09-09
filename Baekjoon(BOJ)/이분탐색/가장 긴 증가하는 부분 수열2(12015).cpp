#include<bits/stdc++.h>
using namespace std;

int N;
vector<int> lis;


int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	int num;
	for (int i = 0; i < N; i++) {
		cin >> num;

		if (lis.empty() || lis.back() < num)
			lis.push_back(num);
		else {
			int idx = lower_bound(lis.begin(), lis.end(), num) - lis.begin();
			lis[idx] = num;
		}
	}
	
	cout << lis.size();

	return 0;
}