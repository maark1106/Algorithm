#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct node {
	int value;
	struct node* next;
}node;

node* head = NULL;

void push(int number) {
	node* newNode = (node*)malloc(sizeof(node));
	newNode->next = head;
	newNode->value = number;
	head = newNode;

	return;
}

void pop() {
	if (!head)
		return;

	node* delNode = head;
	head = head->next;
	free(delNode);

	return;
}

int sum() {
	int result = 0;
	node* curNode = head;
	while (curNode) {
		result += curNode->value;
		curNode = curNode->next;
	}
	return result;
}

void _free() {
	node* delNode = head;
	node* curNode = head;
	
	while (curNode) {
		curNode = curNode->next;
		free(delNode);
		delNode = curNode;
	}
}

int main() {

	int N, number;
	scanf("%d", &N);

	for (int i = 0; i < N; i++) {
		scanf("%d", &number);
		if (number) 
			push(number);
		else 
			pop();
	}

	printf("%d", sum());

	_free();

	return 0;
}