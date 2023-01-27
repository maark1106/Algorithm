#include<stdio.h>
#pragma warning(disable:4996)

int main() {

	int stack[5001], stack_idx = 0;
	int person[5001] = { 0 }, person_idx = -1, N, K;

	scanf("%d%d", &N, &K);
	
	while (stack_idx < N) {
		
		for (int i = 0; i < K; i++) {
			person_idx++;
			person_idx = person_idx % N;
			if (person[person_idx] == 1) {
				while (person[person_idx] == 1) {
					person_idx++;
					person_idx = person_idx % N;
				}
			}
		}
		stack[stack_idx++] = person_idx + 1;
		person[person_idx] = 1;
	}

	printf("<");
	for (int i = 0; i < N - 1; i++) {
		printf("%d, ", stack[i]);
	}
	printf("%d>", stack[N - 1]);

	return 0;
}