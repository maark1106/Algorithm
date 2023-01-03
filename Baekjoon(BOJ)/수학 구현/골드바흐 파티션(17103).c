#include<stdio.h>
#pragma warning(disable:4996)

#define MAX 10000000
int check[MAX] = {0};


int aristo() {

	for (int i = 2; i < MAX; i++) {
		if (!check[i]) {
			for (int j = 2; i * j <= MAX; j++) {
				check[i * j] = 1;
			}
		}
	}
}

int check_goldbah(int num) {

	int count = 0;
	for (int i = 2; i <= num / 2; i++) {
		if (!check[i] && !check[num - i]) {
			count++;
		}
	}
	return count;
}

int main() {

	aristo();
	int N;
	scanf("%d", &N);

	while (N--) {
		int num;
		scanf("%d", &num);

		printf("%d\n", check_goldbah(num));

	}


	return 0;
}