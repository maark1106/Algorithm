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
* ��Ŭ���� ȣ������ �̿��� ������ ��������
* �� ���� �̵��� �� �ִ� �Ÿ��� �ִ��̶�� �� ����
* �ٷ� gcd�� ���ø� �� �־�� ��
* �׸��� ������ �Ÿ� �Է°� ���ÿ� �ٷ� ���� ������ �Ÿ� ���Ͽ�
* gcd�� �ٷ� ���� �� ����.
*/