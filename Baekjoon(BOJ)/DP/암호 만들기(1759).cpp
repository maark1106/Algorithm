#include<bits/stdc++.h>
using namespace std;


char alpabet[17];
vector<char> v;
int L, C;

bool check() {

	int cnt = 0;

	for (int i = 0; i < L; i++) {
		if (v[i] == 'a' || v[i] == 'e' || v[i] == 'i' ||
			v[i] == 'o' || v[i] == 'u')
			cnt++;
	}

	return (cnt >= 1 && L - cnt >= 2) ? true : false;
}

void printPermutation(int level) {

	if (v.size() == L) {
		if (check()) {
			for (int i = 0; i < L; i++)
				cout << v[i];
			cout << "\n";
		}
		return;
	}

	for (int i = level; i < C; i++) {
		v.push_back(alpabet[i]);
		printPermutation(i + 1);
		v.pop_back();
	}

}

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> L >> C;

	for (int i = 0; i < C; i++)
		cin >> alpabet[i];

	sort(alpabet, alpabet + C);

	printPermutation(0);


	return 0;
}