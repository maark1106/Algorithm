#include<bits/stdc++.h>
#define MAX 500001

using namespace std;

int N, M;
int myCard[MAX];
int targetCard[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> myCard[i];

	sort(myCard, myCard + N);

	cin >> M;
	for (int i = 0; i < M; i++)
		cin >> targetCard[i];


	for (int i = 0; i < M; i++) {
		int target = targetCard[i];
		int lt = 0, rt = N - 1;
		bool flag = false;

		while (lt <= rt) {
			int mid = (lt + rt) / 2;
			if (myCard[mid] == target) {
				flag = true;
				break;
			}

			if (myCard[mid] > target)
				rt = mid - 1;
			else
				lt = mid + 1;
		}

		if (flag)
			cout << 1 << " ";
		else
			cout << 0 << " ";
	}

	return 0;
}