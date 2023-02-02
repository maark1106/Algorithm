#include<iostream>
#include<cstdlib>
using namespace std;

bool Remove[10] = {0};

int push_check(int n);

int main() {

	int N, num;

	cin >> N >> num;
	for (int i = 0; i < num; i++) {
		int number;
		cin >> number;
		Remove[number] = true;
	}

	int min = abs(N - 100);

	for (int i = 0; i <= 1000000; i++) {
		
		int len = push_check(i);
		if (!len)
			continue;
		
		int cnt = len + abs(N - i);
		if (cnt < min)
			min = cnt;
	}

	cout << min;

	return 0;
}

int push_check(int n) {

	if (!n) {
		return Remove[0] ? 0 : 1;
	}

	int len = 0;

	while (n) {
		int check_num = n % 10;
		if (Remove[check_num])
			return 0;
		n /= 10;
		len++;
	}
	return len;
}