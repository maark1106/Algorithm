#include<bits/stdc++.h>
#include<unordered_map>
using namespace std;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int T;

	cin >> T;

	while (T--) {
		map<string, int> clothes;
		int N, res = 1;
		cin >> N;
		for (int i = 0; i < N; i++) {
			string clothType, clothName;
			cin >> clothName >> clothType;
			clothes[clothType]++;
		}		
		
		for (auto v : clothes)
			res *= v.second + 1;
		
		cout << res - 1;
	}
	
	
} 