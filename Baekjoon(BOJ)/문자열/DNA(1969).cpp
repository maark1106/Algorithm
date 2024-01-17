#include<bits/stdc++.h>
using namespace std;

int N, M;
string str[1001];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		cin >> str[i];
	}

	string res;
	int difCnt = 0;
	for (int i = 0; i < M; i++) {
		int alCnt[26] = { 0 };
		for (int j = 0; j < N; j++) {
			alCnt[str[j][i] - 'A']++;
		}

		string maxString;
		int cnt = 0;
		for (int j = 0; j < 26; j++) {
			if (alCnt[j] > cnt) {
				cnt = alCnt[j];
				maxString = 'A' + j;
			}
		}		
		difCnt += N - cnt;
		res += maxString;
	}

	cout << res << "\n" << difCnt;

	return 0;
}