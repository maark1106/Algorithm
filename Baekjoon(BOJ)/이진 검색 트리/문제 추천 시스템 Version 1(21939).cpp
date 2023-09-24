#include<bits/stdc++.h>
using namespace std;

int store[100001];
set<pair<int, int>> s;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	int N, P, L;

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> P >> L;
		s.insert({ L,P });
		store[P] = L;
	}	

	int M, num;

	cin >> M;

	string command;			
	while (M--) {
		cin >> command;
		if (command == "add") {
			cin >> P >> L;			
			s.insert({ L,P });
			store[P] = L;
		}
		else if (command == "recommend") {
			cin >> num;
			if (num == 1) {
				cout << (prev(s.end()))->second << "\n";
			}
			else
				cout << s.begin()->second << "\n";
		}
		else {
			cin >> num;
			s.erase({ store[num], num });
		}
	}


	return 0;
}