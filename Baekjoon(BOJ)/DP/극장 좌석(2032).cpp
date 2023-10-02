#include<bits/stdc++.h>
using namespace std;

int arr[42];
int N, M;
bool check[42];

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);	

	cin >> N >> M;	
	int vipNum;
	for (int i = 0; i < M; i++) {		
		cin >> vipNum;
		check[vipNum] = true;
	}	
	
	arr[0] = 1;
	arr[1] = 1;	
	
	for (int i = 2; i <= N; i++) {
		if (check[i] || check[i - 1])
			arr[i] = arr[i - 1];
		else
			arr[i] = arr[i - 1] + arr[i - 2];
	}

	cout << arr[N];

	return 0;
}