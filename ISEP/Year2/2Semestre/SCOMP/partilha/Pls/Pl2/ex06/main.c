#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "asm.h"

int main(){
	int vec[1000];
	int *ptr=vec;
	int vec1[1000];
	int *ptr1=vec1;
	int i=0;
	for(i=0;i<1000;i++){
		vec[i]=1;
		vec1[i]=1;
	}
	vec[990]=5;
	vec1[990]=5;
	prog(vec,vec1);
	return 0;	
}
