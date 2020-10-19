#include <stdio.h>
#include <string.h>

void copy(char * str1,char * str2){
	
	int i=0;
	while(*(str1+i)!='\0'){
		*(str2+i)=*(str1+i);
		i++;
	}
	
}
int main (void){
	
	char *a ="HELLO";
	char *b ="";
	
	//char * str1=a;
	//char * str2=b;
	
	copy(a,b);
	
	printf("String b:%s\n",b);
	
	
	return 0;
	}
