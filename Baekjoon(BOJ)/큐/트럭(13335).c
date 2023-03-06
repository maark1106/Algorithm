#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct truck {
	int weight;
	int time;
}truck;

int sum_weight, total_time;

typedef struct queue {
	truck arr[1000001];
	int count;
	int head;
	int tail;
}queue;

void queue_init(queue* q) {
	q->count = q->tail = q->head = 0;
}

int pop(queue* q) {
	int temp = q->arr[q->head].weight;
	q->head++;
	q->count--;
	return temp;
}

void push(queue* q, truck t) {
	sum_weight += t.weight;
	q->arr[q->tail++] = t;
	q->count++;
}

int main() {

	int N, length, max_weight;
	scanf("%d%d%d", &N, &length, &max_weight);
	queue q;
	queue_init(&q);

	sum_weight = 0;
	total_time = 0;

	truck* t_arr = (truck*)malloc(sizeof(truck) * N);
	if (t_arr == NULL) {
		printf("NULL");
		return -1;
	}

	for (int i = 0; i < N; i++) {
		scanf("%d", &t_arr[i].weight);
		t_arr[i].time = 0;
	}

	int truck_idx = 0;
	do{
		for (int i = q.head; i < q.tail; i++) {
			t_arr[i].time++;

			if (t_arr[i].time > length) {
				sum_weight -= pop(&q);
			}
		}

		if (sum_weight + t_arr[truck_idx].weight <= max_weight && truck_idx < N) {
			t_arr[truck_idx].time++;
			push(&q, t_arr[truck_idx++]);
		}

		total_time++;
	} while (sum_weight || truck_idx < N);

	printf("%d", total_time);
	free(t_arr);

	return 0;
}