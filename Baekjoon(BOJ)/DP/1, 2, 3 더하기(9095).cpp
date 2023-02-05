#include<iostream>
using namespace std;

int result;

void cal(int N, int sum) {

	if (sum > N)
		return;

	if (sum == N)
		result++;

	for (int i = 1; i < 4; i++) {
		cal(N, i + sum);
	}
}

int main() {

	int N, count;

	cin >> count;

	while (count--) {
		cin >> N;
		result = 0;
		for (int i = 1; i < 4; i++) {
			cal(N, i);
		}
		cout << result << endl;
	}

	return 0;
}