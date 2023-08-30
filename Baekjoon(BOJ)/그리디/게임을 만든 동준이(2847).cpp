#include<bits/stdc++.h>
#define MAX 101
using namespace std;

int N;
int score[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> N;
	for (int i = 1; i <= N; i++)
		cin >> score[i];

	int res = 0;
	for (int i = N - 1; i > 0; i--) {
		while (score[i] >= score[i + 1]) {
			score[i]--;
			res++;
		}
	}

	cout << res;

	return 0;
}