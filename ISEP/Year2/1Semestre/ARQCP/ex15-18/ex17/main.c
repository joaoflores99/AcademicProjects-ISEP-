#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include "approved_semester.h" 

//função main para testar
int main(void){
	
	group g;
	group *ptr=&g;
	int length=3;
	unsigned char* ptrChar=(unsigned char *)malloc(length*sizeof(char));
	ptrChar[0]=1;
	ptrChar[1]=2;
	ptrChar[2]=255;
	ptr->individual_grades=ptrChar;
	ptr->n_students=length;		
	
	int num=approved_semester(ptr);
	
	printf("Número de alunos aprovados: %d\n",num);
	
	free(ptr->individual_grades);
	
	return 0;
}
