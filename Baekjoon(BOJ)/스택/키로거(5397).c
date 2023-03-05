#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#pragma warning(disable:4996)

typedef struct node {
	char data;
	struct node* right;
	struct node* left;
}node;

node* curNode = NULL;

void push(char data) { 
	node* newNode = (node*)malloc(sizeof(node));
	newNode->left = newNode->right = NULL;
	newNode->data = data;

	if (curNode->right) {
		curNode->right->left = newNode;
		newNode->right = curNode->right;
	}

	curNode->right = newNode;//
	newNode->left = curNode;
	
	curNode = newNode;
}

void distroy() {
	node* delNode = curNode;

	curNode->left->right = curNode->right;
	if (curNode->right) {
		curNode->right->left = curNode->left;
	}

	curNode = curNode->left;
	//free(delNode);
	delNode = NULL;
}

void print(node* head) {

	node* print_node = head->right;
	while (print_node) {
		printf("%c", print_node->data);//
		print_node = print_node->right;
	}

	puts("");
	return;
}

void _free(node* head) {

	node* delNode;
	curNode = delNode = head->right;

	while (curNode) {
		curNode = curNode->right;
		free(delNode);
		delNode = NULL;
		delNode = curNode;
	}
	delNode = NULL;
	return;
}

int main() {

	int N;
	char str[1000001] = { 0 };
	
	scanf("%d", &N);
	getchar();

	while (N--) {
		gets(str);
		node* head = (node*)malloc(sizeof(node));
		head->left = head->right = NULL;
		curNode = head;

		for (char* p = str; *p != 0; p++) {
			if (*p == '-') {
				if(curNode->left)
					distroy();
			}
			else if (*p == '<') {//
				if(curNode != head)
					curNode = curNode->left;
			}
			else if (*p == '>') {
				if(curNode->right)
					curNode = curNode->right;
			}
			else {
				push(*p);
			}
		}

		print(head);
		//_free(head);
	}

	return 0;
}