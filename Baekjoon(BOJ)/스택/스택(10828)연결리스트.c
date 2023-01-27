#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#pragma warning(disable:4996)

typedef struct node {
	struct node* next;
	int value;
}node;

node* head = NULL;
int count = 0;

void push() {

	count++;
	node* newNode = (node*)malloc(sizeof(node));
	newNode->next = NULL;
	scanf("%d", &newNode->value);
	getchar();

	if (!head) {
		head = newNode;
		return;
	}

	newNode->next = head;
	head = newNode;
	return;
}

int pop() {

	int num;
	node* delNode;

	if (!head) {
		return -1;
	}

	count--;
	delNode = head;
	num = head->value;
	head = head->next;
	free(delNode);
	return num;
}
 
int main() {

	int N;

	scanf("%d", &N);
	getchar();

	while (N--) {
		char command[10];
		scanf("%s", command);

		if (!strcmp(command, "push")) {
			push();
		}
		else if (!strcmp(command, "pop")) {
			printf("%d\n", pop());
		}
		else if (!strcmp(command, "size")) {
			printf("%d\n", count);
		}
		else if (!strcmp(command, "empty")) {
			printf("%d\n", head == NULL ? 1 : 0);
		}
		else if (!strcmp(command, "top")) {
			printf("%d\n", head == NULL ? -1 : head->value);
		}
	}

	return 0;
}