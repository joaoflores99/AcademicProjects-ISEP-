#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "func.h"

int main(void){

	int vec[1000];
//	int *ptr=vec;
	int vec1[1000];
	//int *ptr1=vec1;
	
	int i=0;
	
	for(i=0;i<1000;i++){
		vec[i]=1;
		vec1[i]=1;
	}

	
	func(vec,vec1);
	
	return 0;
	}
