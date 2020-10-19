#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include "asm.h"
#define ARRAY_SIZE 1000

int main(void) {
	
	int numbers[ARRAY_SIZE];
	int i;
	time_t t;
	
	srand((unsigned) time (&t));
	
	for(i=0;i<ARRAY_SIZE;i++){
		numbers[i] = rand () % 255;
	}
	int *n=numbers;
	prog(n);
	
	return 0;
}
