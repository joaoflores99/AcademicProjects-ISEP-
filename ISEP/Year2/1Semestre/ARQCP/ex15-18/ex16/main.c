#include <stdio.h> 
#include <stdlib.h>
#include "stack.h"

//função principal para testar
int main(void){
	Stack stack;
	stack.stack=NULL;
	stack.elemento_apontado=0;
	Stack *ptr=&stack;
	int x;
	int a=1;
	int b=2;
	push(ptr,a);
	push(ptr,b);
	
	printf("Stack\n");
	for(x=0;x< stack.elemento_apontado;x++){
		printf("%d\n",stack.stack[x]);
	}
	printf("Numero de elementos na stack: %d\n",stack.elemento_apontado);
	
	b=pop(ptr);
	printf("Elemento retirado:%d\n",b);
	printf("Stack\n");
	for(x=0;x<stack.elemento_apontado;x++){
		printf("%d\n",stack.stack[x]);
	}
	
	printf("Numero de elementos na stack: %d\n",stack.elemento_apontado);
	
	a=pop(ptr);
	printf("Elemento retirado:%d\n",a);
	printf("Stack\n");
	for(x=0;x<stack.elemento_apontado;x++){
		printf("%d\n",stack.stack[x]);
	}
	printf("Numero de elementos na stack: %d\n",stack.elemento_apontado);

	return 0;
}
