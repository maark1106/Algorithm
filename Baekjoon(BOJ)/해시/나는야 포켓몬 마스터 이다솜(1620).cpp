#include<bits/stdc++.h>
#include<unordered_map>
using namespace std;

unordered_map<string, int> dic;
string str[100001];
int N, M;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> N >> M;
	string name;

	for (int i = 1; i <= N; i++) {
		cin >> name;
		dic[name] = i;
		str[i] = name;
	}
	
	string command;
	while (M--) {
		cin >> command;		

		if (isdigit(command[0]))
			cout << str[stoi(command)] << "\n";
		else
			cout << dic[command] << "\n";

	}
} 