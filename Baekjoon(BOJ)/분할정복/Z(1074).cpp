#include<iostream>
#include<vector>
#include<math.h>
#include<algorithm>

using namespace std;

int N, r, c;
int res = 0;

void divide(int y, int x, int size) {

	if (y == r && x == c) {
		cout << res;
		return;
	}
	
	if ((r >= y && r < y + size) && (c >= x && c < x + size)) {
		divide(y, x, size / 2);
		divide(y, x + size / 2, size / 2);
		divide(y + size / 2, x, size / 2);
		divide(y + size / 2, x + size / 2, size / 2);
	}
	else
		res += size * size;
	
}


int main() {

	cin >> N >> r >> c;

	divide(0, 0, pow(2, N));	

	return 0;
}