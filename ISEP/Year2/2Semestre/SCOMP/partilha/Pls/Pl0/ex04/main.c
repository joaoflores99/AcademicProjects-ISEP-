#include "stdio.h"

void sub(int * vec,int c,int count,int num){
	
	if(count!=c){
		*(vec+count)=*(vec+count)-num;
		sub(vec,c,count+1,num);
	}
}


int main(void){
	int vec[]= {1,2,3,4,5,6};
	int * ptr= vec;
	int c=6;
	int a=1;
	int count=0;
	sub(ptr,c,count,a);
	
	int j;
	for(j=0;j<c;j++){
		printf("%d\n", *(ptr+j));
	}
	
	return 0;
	
	}
