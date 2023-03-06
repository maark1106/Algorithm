#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct queue {
	int arr[3002];
	int count;
	int head;
	int tail;
}queue;

void queue_init(queue* q, int N) {
	q->head = 0;
	q->tail = N;
	q->count = N;

	for (int i = 0; i < N; i++) {
		q->arr[i] = i + 1;
	}
}

int distroy(queue* q) {
	
	int temp = q->arr[q->head];
	q->count--;
	q->head++;
	return temp;
}

void move(queue* q) {
	q->arr[q->tail++] = q->arr[q->head++];
}

int main() {

	queue q;
	int N, result[1001], result_idx = 0;

	scanf("%d", &N);

	queue_init(&q, N);

	for (int i = 0;q.count != 1; i++) {
		if (i % 2 == 0) {
			result[result_idx++] = distroy(&q);

		}
		else {
			move(&q);
		}
	}

	for (int i = 0; i < result_idx; i++)
		printf("%d ", result[i]);
	printf("%d", q.arr[q.head]);

	return 0;
}