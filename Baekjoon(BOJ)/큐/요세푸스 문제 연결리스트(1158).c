#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct node {

	struct node* left;
	struct node* right;
	int value;
}node;

node* head = NULL;
node* tail = NULL;

node* create_node(int num) {

	node* newNode = (node*)malloc(sizeof(node));
	newNode->right = newNode->left = NULL;
	newNode->value = num;

	return newNode;
}

int main() {

	int N, K, idx = 0;
	int* stack;
	scanf("%d%d", &N, &K);
	stack = (int*)malloc(sizeof(int) * N);

	for (int i = 0; i < N; i++) {
		node* newNode = create_node(i + 1);
		if (!i) {
			head = tail = newNode;
			newNode->right = newNode;
			newNode->left = newNode;
		}
		else {
			newNode->left = tail;
			tail->right = newNode;
			newNode->right = head;
			head->left = newNode;
			tail = newNode;
		}
	}

	tail = head;

	while (1) {

		for (int i = 0; i < K - 1; i++)
			tail = tail->right;//

		stack[idx++] = tail->value;
		node* delNode = tail;
		tail->left->right = tail->right;
		tail->right->left = tail->left;
		tail = tail->right;
		if (tail->right == tail) {
			stack[idx++] = tail->value;
			free(tail);
			break;
		}
		free(delNode);
	}

	printf("<");
	for (int i = 0; i < N - 1; i++)
		printf("%d, ", stack[i]);
	printf("%d>", stack[N - 1]);
	

	return 0;
}