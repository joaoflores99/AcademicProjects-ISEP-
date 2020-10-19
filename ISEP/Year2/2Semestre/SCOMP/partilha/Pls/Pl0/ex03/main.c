#include "stdio.h"

int sum_even(int * vec,int c,int count,int sum){
	
	if(count==c){
		return sum;
	}
	
		if(*(vec+count)%2==0){
			sum =sum + *(vec+count);
		}
		count++;
	return sum_even(vec,c,count,sum);
}


int main(void){
	int vec[]= {1,2,3,4,5,6};
	int * ptr= vec;
	int c=6;
	
	int count=0;
	int sum=0;
	int sum1= sum_even(ptr,c,count,sum);
	printf("%d\n",sum1);
	return 0;
	
	}
