#include "stdio.h"

void sub(int * vec,int c,int v){
	int i;
	for (i=0;i<c;i++){
		*(vec+i)= *(vec +i)- v;
		
	}
}


int main(void){
	int vec[]= {1,2,3,4,5,6};
	int * ptr= vec;
	int c=6;
	int v=1;
	
	
    sub(ptr,c,v);
    
    int i;
    printf("DEPOIS\n");
    for(i=0;i<6;i++){
		printf("%d\n",vec[i]);
		}
    
    
    
	return 0;
	
	}
