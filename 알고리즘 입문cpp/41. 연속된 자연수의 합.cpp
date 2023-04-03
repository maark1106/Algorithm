#include <iostream>
#include <vector>
#include<algorithm>

using namespace std;

int main() {

	int N, target, mid, cnt = 0;
	int idx;

	cin >> N >> target;
	vector<int> arr(N);
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	sort(arr.begin(), arr.end());	
	
	int l = 0, r = N - 1;	
	while (l <= r) {
		mid = (l + r) / 2;
		if (target == arr[mid]) {
			cout << mid + 1;
			break;
		}
		else if (target > arr[mid])
			l = mid + 1;
		else
			r = mid - 1;
	}


	return 0;
}
