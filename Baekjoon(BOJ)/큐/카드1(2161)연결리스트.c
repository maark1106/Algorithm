#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct node {
	int data;
	struct node* next;
}node;

node* head, * tail;
int count;

void node_init(int N) {

	count = N;
	head = tail = NULL;

	for (int i = 0; i < N; i++) {
		node *newNode = (node*)malloc(sizeof(node));
		newNode->next = NULL;
		newNode->data = i + 1;

		if (!head) {
			head = tail = newNode;
		}
		else {
			tail->next = newNode;
			tail = newNode;
		}
	}
}

int distroy() {
	count--;
	int temp = head->data;
	node* delNode = head;
	head = head->next;
	free(delNode);
	return temp;
}

void move() {
	tail->next = head;
	head = head->next;
	tail = tail->next;
	tail->next = NULL;
}

void simulate() {
	
	int result[1001], result_idx = 0;

	for (int i = 0; count != 1; i++) {
		if (i % 2 == 0) {
			result[result_idx++] = distroy();
		}
		else {
			move();
		}
	}

	for (int i = 0; i < result_idx; i++)
		printf("%d ", result[i]);

	printf("%d", head->data);
	//free(head);
}


int main() {

	int N;
	scanf("%d", &N);

	node_init(N);
	simulate();	
	free(head);

	return 0;
}