#include<bits/stdc++.h>
using namespace std;

set<int> s;
int N, Q, pos = 0;
bool u[500001];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N >> Q;

	int num;
	for (int i = 0; i < N; i++) {
		cin >> num;
		if (num) {
			u[i] = true;
			s.insert(i);
		}
	}

	int command;
	while (Q--) {
		cin >> command;
		if (command == 1) {
			cin >> num;
			if (u[num - 1] == true) {
				u[num - 1] = false;
				s.erase(num - 1);
			}
			else {
				u[num - 1] = true;
				s.insert(num - 1);
			}
		}
		else if (command == 2) {
			cin >> num;
			pos = (pos + num) % N;
		}
		else {
			if (s.empty())
				cout << -1 << "\n";
			else {
				auto it = s.lower_bound(pos);
				if (it == s.end()) {
					cout << (N - pos) + *s.begin() << "\n";
				}
				else {		
 					cout << *it - pos << "\n";
				}
			}
		}
	}

	return 0;
}