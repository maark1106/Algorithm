#include<bits/stdc++.h>
using namespace std;

void solve() {

	multiset<int> s;

	int N;

	cin >> N;

	char command;
	int data;
	while (N--) {
		cin >> command;

		if (command == 'I') {
			cin >> data;
			s.insert(data);
		}
		else {
			cin >> data;
			if (!s.empty()) {
				if (data == 1)
					s.erase(prev(s.end()));
				else
					s.erase(s.begin());
			}
		}
	}


	if (s.empty())
		cout << "EMPTY\n";
	else
		cout << *prev(s.end()) << " " << *s.begin() << "\n";
}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	int T;

	cin >> T;

	while (T--) {
		solve();
	}
	
	
	return 0;
}