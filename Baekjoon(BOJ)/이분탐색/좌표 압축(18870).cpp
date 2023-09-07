#include<bits/stdc++.h>
#define MAX 1000002

using namespace std;

int N;
int arr[MAX];
vector<int> sortArr;

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> N;
	
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		sortArr.push_back(arr[i]);
	}

	sort(sortArr.begin(), sortArr.end());
	sortArr.erase(unique(sortArr.begin(), sortArr.end()), sortArr.end());

	int idx = sortArr.size();
	for (int i = 0; i < N; i++) {
		int target = arr[i];
		int lt = 0, rt = idx - 1;
		
		while (lt <= rt) {
			int mid = (lt + rt) / 2;

			if (target == sortArr[mid]) {
				cout << mid << " ";
				break;
			}
			else if (target > sortArr[mid])
				lt = mid + 1;
			else
				rt = mid - 1;
		}		
	}

	
	return 0;
}