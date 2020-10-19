#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include "asm.h"
#define ARRAY_SIZE 1000


int main(){
	int numbers[ARRAY_SIZE];
	int n,i;
	time_t t;
	
	srand((unsigned) time (&t));
	
	for(i=0;i<ARRAY_SIZE;i++){
		numbers[i] = rand () % 10000;
	}
	
	n = rand () % 10000;
	int *nu=numbers;
	int times=prog(nu,n);
	
	printf("Vezes %d\n",times);
	
	return 0;
}
