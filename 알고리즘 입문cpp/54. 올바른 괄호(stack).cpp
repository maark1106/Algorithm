#include<iostream>
#include<stack>
using namespace std;

int main() {

	char str[31];
	cin >> str;

	stack<char> s;

	for (int i = 0; str[i] != '\0'; i++) {
		if (str[i] == '(')
			s.push('(');
		else {
			if (s.empty()) {
				cout << "NO";
				return 0;
			}
			else
				s.pop();
		}
	}

	if (s.empty())
		cout << "YES";
	else
		cout << "NO";

	return 0;
}