#include<bits/stdc++.h>
#include<unordered_map>
using namespace std;

unordered_map<string, string> address;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int N, M;

	cin >> N >> M;

	string addr, password;
	for (int i = 1; i <= N; i++) {
		cin >> addr >> password;
		address[addr] = password;
	}
	
	string target;
	for (int i = 1; i <= M; i++) {
		cin >> target;
		cout << address[target]<<"\n";
	}
} 