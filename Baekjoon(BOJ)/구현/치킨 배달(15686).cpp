#include<bits/stdc++.h>
using namespace std;

int N, M, res = 2147000000;
vector<pair<int, int>> v1;
vector<pair<int, int>> v2;

void calDis(vector<int> & dis, int v2Idx) {

	for (int i = 0; i < v1.size(); i++) {
		int distance = abs(v2[v2Idx].first - v1[i].first) + abs(v2[v2Idx].second - v1[i].second);
		dis[i] = min(dis[i], distance);
	}
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);	

	
	cin >> N >> M;

	int num;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> num;
			if (num == 1)
				v1.push_back({ i,j });
			else if (num == 2)
				v2.push_back({ i,j });
		}
	}

	vector<int> check(v2.size(), 1);
	fill(check.begin(), check.begin() + M, 0);	

	do {			
		vector<int> disArr(v1.size(), 2147000000);		
		for (int i = 0; i < v2.size(); i++) {
			if (check[i] == 0) {
				calDis(disArr, i);
			}
		}

		int sum = 0;
		for (int i = 0; i < v1.size(); i++)
			sum += disArr[i];

		res = min(res, sum);
	} while (next_permutation(check.begin(), check.end()));		

	cout << res;

	return 0;
}