#include<iostream>
#define swap(type, x, y)do{type t= x; x= y ;y = t;}while(0);
using namespace std;

int result = 0 ,N;
char arr[52][52];

void check_col();
void check_row();

int main() {

	cin >> N;

	for (int row = 0; row < N; row++)
		for (int col = 0; col < N; col++)
			cin >> arr[row][col];

	for (int row = 0; row < N; row++) {
		for (int col = 0; col < N - 1; col++) { // 
			swap(char, arr[row][col], arr[row][col + 1]);
			check_row();
			check_col();
			swap(char, arr[row][col], arr[row][col + 1]);
		}
	}

	for (int col = 0; col < N; col++) {
		for (int row = 0; row < N - 1; row++) { // 
			swap(char, arr[row][col], arr[row + 1][col]);
			check_row();
			check_col();
			swap(char, arr[row][col], arr[row + 1][col]);
		}
	}

	cout << result;

	return 0;
}

void check_col() {

	int max = 1;

	for (int row = 0; row < N; row++) {
		max = 1;
		for (int col = 0; col < N; col++) {
			if (arr[row][col] == arr[row][col + 1])
				max++;
			else {
				if (result < max) {
					result = max;
				}
				max = 1;
			}
		}
	}
}

void check_row() {

	int max = 1;

	for (int col = 0; col < N; col++) {
		max = 1;
		for (int row = 0; row < N; row++) {
			if (arr[row][col] == arr[row + 1][col])
				max++;
			else {
				if (result < max) {
					result = max;
				}
				max = 1;
			}
		}
	}
}