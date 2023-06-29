#include<iostream>
using namespace std;

int N, arr[101], res[101];

void merge_sort(int l, int r) {
	if (l >= r)
		return;

	int mid = (l + r) / 2;

	merge_sort(l, mid);
	merge_sort(mid + 1, r);

	int p1 = l, p2 = mid + 1, p3 = l;

	while (p1 <= mid && p2 <= r) {
		if (arr[p1] < arr[p2])
			res[p3++] = arr[p1++];
		else
			res[p3++] = arr[p2++];
	}
	while (p1 <= mid)
		res[p3++] = arr[p1++];
	while (p2 <= r)
		res[p3++] = arr[p2++];

	for (int i = l; i <= r; i++)
		arr[i] = res[i];
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	merge_sort(0, N - 1);
	for (int i = 0; i < N; i++)
		cout << arr[i] << " ";

	return 0;
}
