#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct Stack {
	int* arr;
	int top;
}Stack;

void stack_initialize(Stack* stack, int N) {
	stack->top = 0;
	stack->arr = (int*)malloc(sizeof(int) * N);
}

void push(Stack* stack, int number) {
	stack->arr[stack->top++] = number;
}

void pop(Stack* stack) {
	stack->top--;
}

int main() {

	Stack stack;
	int N, number, result = 0;

	scanf("%d", &N);
	stack_initialize(&stack,N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &number);
		if (number) {
			push(&stack, number);
		}
		else {
			pop(&stack);
		}
	}

	for (int i = 0; i < stack.top; i++) {
		result += stack.arr[i];
	}

	printf("%d", result);

	free(stack.arr);

	return 0;
}