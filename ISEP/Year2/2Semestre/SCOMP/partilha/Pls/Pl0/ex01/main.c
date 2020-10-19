#include "stdio.h"

int sum_even(int * vec,int c){
	int sum =0;
	int i;
	for (i=0;i<c;i++){
		if(*(vec+i)%2==0){
			sum =sum + *(vec+i);
		}
	}
	return sum;
}


int main(void){
	int vec[]= {1,2,3,4,5,6};
	int * ptr= vec;
	int c=6;
	
	int sum = sum_even(ptr,c);
	printf("%d\n",sum);
	return 0;
	
	}
