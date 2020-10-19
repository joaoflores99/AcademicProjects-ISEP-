#include <stdlib.h>
#include "stack.h"

// retorna o valor guardado na stack
int pop(Stack *stack){
		int a=stack->stack[(stack->elemento_apontado)-1];
		int *temp=NULL;
		temp=(int *)realloc(stack->stack,((stack->elemento_apontado)-1)*sizeof(int));
		stack->stack=temp;
		stack->elemento_apontado--;
		return a;
}
