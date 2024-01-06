#include<bits/stdc++.h>
using namespace std;

int board1, board2, board3;
int max1, max2, max3;
int min1, min2, min3;
int prevMin1, prevMin2, prevMin3;
int prevMax1, prevMax2, prevMax3;
int N;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;	

	cin >> board1 >> board2 >> board3;
	
	prevMin1 = prevMax1 = board1;
	prevMin2 = prevMax2 = board2;
	prevMin3 = prevMax3 = board3;	

	for (int i = 1; i < N; i++) {

		cin >> board1 >> board2 >> board3;

		min1 = min(prevMin1, prevMin2) + board1;
		min2 = min(prevMin3, min(prevMin1, prevMin2)) + board2;
		min3 = min(prevMin2, prevMin3) + board3;

		max1 = max(prevMax1, prevMax2) + board1;
		max2 = max(prevMax3, max(prevMax1, prevMax2)) + board2;
		max3 = max(prevMax3, prevMax2) + board3;
		
		prevMin1 = min1;
		prevMin2 = min2;
		prevMin3 = min3;

		prevMax1 = max1;
		prevMax2 = max2;
		prevMax3 = max3;
	}

	cout << max(max3, max(max1, max2)) << " ";
	cout << min(min3, min(min1, min2));

	return 0;
}