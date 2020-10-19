#include "stdio.h"

void sub(int * vec,float * vec1,int c,int count){
	if(count!=c){
		*(vec1+count)=(1/(*(vec+count)));
		sub(vec,vec1,c,count+1);
	}
}


int main(void){
	int vec[]= {1,2,3,4,5,6};
	float vec1[6];
	int * ptr= vec;
	float * ptr1= vec1;
	int c=6;
	int count=0;
	sub(ptr,ptr1,c,count);
	
	int j;
	for(j=0;j<c;j++){
		printf("%f\n", *(ptr1+j));
	}
	
	return 0;
	
	}
