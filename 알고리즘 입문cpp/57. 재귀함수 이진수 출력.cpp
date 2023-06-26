#include<iostream>
using namespace std;

void convert_binary(int n) {

	if (n == 0)
		return;
	convert_binary(n / 2);
	cout << n % 2;
}

int main() {

	int N;
	cin >> N;

	convert_binary(N);

	return 0;
}