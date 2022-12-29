#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

int gcd(int x, int y) {
	if (x % y == 0)
		return y;
	else
		return gcd(y, x % y);
}

int main() {

	int N, S, max_num, sister_distance;
	scanf("%d%d", &N, &S);

	if (N == 1) {
		scanf("%d", &sister_distance);
		printf("%d", abs(sister_distance - S));
		return 0;
	}
	
	for (int i = 0; i < N;i++) {
		scanf("%d", &sister_distance);
		int distance = abs(sister_distance - S);
		if (!i) {
			max_num = distance;
			continue;
		}
		max_num = gcd(max_num, distance);
	}
	printf("%d", max_num);

	return 0;
}

/*
* 유클리드 호제법을 이용한 간단한 구현문제
* 한 번의 이동할 수 있는 거리의 최댓값이라는 걸 보고
* 바로 gcd를 떠올릴 수 있어야 함
* 그리고 동생의 거리 입력과 동시에 바로 앞의 동생과 거리 비교하여
* gcd를 바로 구할 수 있음.
*/