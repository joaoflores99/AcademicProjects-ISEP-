typedef struct{
	int *stack;
	int elemento_apontado;
}Stack;

void push(Stack *stack,int element);
int pop(Stack *stack);
