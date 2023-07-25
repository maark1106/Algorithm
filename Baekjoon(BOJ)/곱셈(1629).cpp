#include<iostream>
#include<math.h>

using namespace std;

int A, B, C;

long long cal(int A, int B, int C) {

	if (B == 1) {
		return A % C;
	}
	long long tmp = cal(A, B / 2, C);

	if (B % 2 == 0)
		return tmp * tmp % C;
	else
		return tmp * tmp % C * A % C;
	
}

int main() {

	cin >> A >> B >> C;
	cout << cal(A, B, C);
	
	
	return 0;
}