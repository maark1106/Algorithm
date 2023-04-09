#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct Node {
	struct Node* next;
	struct Node* prev;
	int data;
	int idx;
}Node;

typedef struct List {
	Node* head;
	Node* tail;
}List;

void add(List*plist, int data, int idx) {
	Node* newNode = (Node*)malloc(sizeof(Node));
	newNode->data = data;
	newNode->idx = idx;

	if (plist->head == NULL) {
		newNode->next = newNode;
		newNode->prev = newNode;
		plist->tail = plist->head = newNode;
		return;
	}
	
	newNode->prev = plist->tail;
	newNode->next = plist->head;
	plist->head->prev = newNode;
	plist->tail->next = newNode;
	plist->tail = newNode;
}


int main() {

	List list;
	list.head = NULL;
	list.tail = NULL;

	int N;
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		int data;
		scanf("%d", &data);
		add(&list, data, i);
	}

	Node* curNode = list.head;

	while (N--) {
		printf("%d ", curNode->idx);
		int K = curNode->data;

		if (K > 0) {
			Node* delNode = curNode;
			curNode = curNode->next;
			if (curNode == curNode->next) {
				free(delNode);
				break;
			}
			delNode->next->prev = delNode->prev;
			delNode->prev->next = delNode->next;
			free(delNode);
			for (int i = 0; i < K - 1; i++)
				curNode = curNode->next;
		}
		else {
			K = K * -1;
			Node* delNode = curNode;
			curNode = curNode->prev;
			if (curNode == curNode->prev) {
				free(delNode);
				break;
			}
			delNode->next->prev = delNode->prev;
			delNode->prev->next = delNode->next;
			free(delNode);
			for (int i = 0; i < K - 1; i++)
				curNode = curNode->prev;
		}		
	}

	return 0;
}