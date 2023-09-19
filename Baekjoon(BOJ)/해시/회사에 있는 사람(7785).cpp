#include<bits/stdc++.h>
#include<unordered_set>
using namespace std;

unordered_set<string> p;
int N;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;

	string name, status;
	for (int i = 0; i < N; i++) {
		cin >> name >> status;
		if (status == "enter")
			p.insert(name);
		else
			p.erase(name);
	}

	vector<string> v(p.begin(), p.end());

	sort(v.begin(), v.end(), greater<string>());

	for (string s : v)
		cout << s << "\n";
} 