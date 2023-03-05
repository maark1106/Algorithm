#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996)

typedef struct Stack {
	char arr[31];
	int top;
}Stack;

void push(Stack*stack, char ch) {
	stack->arr[stack->top++] = ch;
}

char pop(Stack* stack) {
	if (!stack->top)
		return 0;

	stack->top--;
	return stack->arr[stack->top];
}

int main() {

	char str[31];
	int flag = 1, result = 0; // flag 1 > + ,0 > -
	int temp = 1;

	Stack stack;
	stack.top = 0;

	gets(str);
	
	for (char* p = str; *p != 0; p++) {

		if (*p == '(') {
			push(&stack, *p);
			temp *= 2;
		}
		else if (*p == '[') {
			push(&stack, *p);
			temp *= 3;
		}
		else if (*p == ')') {
			if (pop(&stack) != '(') {
				printf("0");
				return 0;
			}
			if (*(p - 1) == '(') {
				result += temp;
			}

			temp /= 2;
		}
		else if (*p == ']') {
			if (pop(&stack) != '[') {
				printf("0");
				return 0;
			}
			if (*(p - 1) == '[') {
				result += temp;
			}
			
			temp /= 3;
		}
	}
	
	if (stack.top != 0)
		printf("0");
	else
		printf("%d", result);

	return 0;
}