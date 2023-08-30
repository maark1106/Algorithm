#include<bits/stdc++.h>
#define MAX 1000001

using namespace std;

int N, T;
long long price[MAX];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> T;

	while (T--) {
		cin >> N;
		for (int i = 1; i <= N; i++)
			cin >> price[i];

		long long res = 0, max = price[N];
		for (int i = N - 1; i >= 1; i--) {
			if (price[i] < max) // ���� �ְ��� �۴ٸ� ���� �����
				res += max - price[i];
			else //�ִ� �ְ� ����
				max = price[i];
		}	

		cout << res << "\n";
	}
	
	return 0;
}