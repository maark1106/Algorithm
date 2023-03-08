#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct node {
	char data;
	struct node* left;
	struct node* right;
}node;

node* make_node(char data) {

	node* newNode = (node*)malloc(sizeof(node));
	newNode->left = NULL;
	newNode->right = NULL;
	newNode->data = data;

	return newNode;
}

void make_left_node(node* main, node* subNode, char p_data) {

	if (!main)
		return;

	if (main->data == p_data) {//
		main->left = subNode;
		return;
	}
	make_left_node(main->left, subNode, p_data);
	make_left_node(main->right, subNode, p_data);
}

void make_right_node(node* main, node* subNode, char p_data) {

	if (!main)
		return;

	if (main->data == p_data) {
		main->right = subNode;
		return;
	}
	make_right_node(main->left, subNode, p_data);
	make_right_node(main->right, subNode, p_data);
}

void display_pre_order(node* main) {

	if (!main)
		return;

	printf("%c", main->data);
	display_pre_order(main->left);
	display_pre_order(main->right);
}

void display_in_order(node* main) {

	if (!main)
		return;

	display_in_order(main->left);
	printf("%c", main->data);
	display_in_order(main->right);
}

void display_post_order(node* main) {

	if (!main)
		return;

	display_post_order(main->left);
	display_post_order(main->right);
	printf("%c", main->data);
}

void memory_free(node* main) {

	if (!main) {
		return;
	}

	memory_free(main->left);
	memory_free(main->right);
	free(main);
}


int main() {

	int N;
	char p_data, sub1_data, sub2_data;
	scanf("%d", &N);
	getchar();

	node* root = make_node('A');

	for (int i = 0; i < N; i++) {
		scanf("%c %c %c", &p_data, &sub1_data, &sub2_data);
		getchar();

		if (sub1_data != '.') {
			node* subNode = make_node(sub1_data);
			make_left_node(root, subNode, p_data);
		}
		if (sub2_data != '.') {
			node* subNode = make_node(sub2_data);
			make_right_node(root, subNode, p_data);
		}
	}

	display_pre_order(root);
	puts("");
	display_in_order(root);
	puts("");
	display_post_order(root);

	memory_free(root);
	
	return 0;
}