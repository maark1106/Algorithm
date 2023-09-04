#include<bits/stdc++.h>

using namespace std;

int minusArr[51];
int plusArr[51];
int N;

int main() {

	cin >> N;
	int num;

	int pIdx = 0;
	int mIdx = 0;
	for (int i = 0; i < N; i++) {
		cin >> num;
		if (num > 0)
			plusArr[pIdx++] = num;
		else
			minusArr[mIdx++] = num;
	}

	sort(plusArr, plusArr + pIdx, greater<>());
	sort(minusArr, minusArr + mIdx);

	int res = 0;

	for (int i = 0; i < pIdx; i++) {

		if (i == pIdx - 1) {
			res += plusArr[i];
			break;
		}

		if (plusArr[i] + plusArr[i + 1] < plusArr[i] * plusArr[i + 1]) {
			res += plusArr[i] * plusArr[i + 1];
			i++;
		}
		else {
			res += plusArr[i];
		}
	}

	for (int i = 0; i < mIdx; i++) {

		if (i == mIdx - 1) {
			res += minusArr[i];
			break;
		}

		if (minusArr[i] + minusArr[i + 1] < minusArr[i] * minusArr[i + 1]) {
			res += minusArr[i] * minusArr[i + 1];
			i++;
		}
		else {
			res += minusArr[i];
		}
	}

	cout << res;

	return 0;
}