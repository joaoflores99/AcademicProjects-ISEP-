#include <stdlib.h>
#include <stdio.h>
#include "stack.h"

// guarda o valor de element na stack
void push(Stack *stack,int element){
	int *temp=NULL;
	temp=(int *)realloc(stack->stack,((stack->elemento_apontado)+1)*sizeof(int));
	if(temp==NULL){
		printf("Erro no push para a stack");
	}else{
		stack->stack=temp;
		temp=NULL;
		stack->stack[stack->elemento_apontado]=element;
		stack->elemento_apontado++;
	}
}
