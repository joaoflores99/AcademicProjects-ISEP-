#include <stdio.h> 
#include <stdlib.h>
#include "struct_A.h" 

//função principal para testar
int main(void){
	structA ** mat;
	mat=new_matrix(4,3);
	structA str;
	structA *s=&str;
	
	unionB ub;
	unionB *b=&ub;
	b->b='x';
	s->ub = ub;
	int i,j;
	for(i=0;i<4;i++){
		for(j=0;j<3;j++){
			mat[i][j]=str;
		}
	}
	char res=return_unionB_b(mat,0,0);
	printf("%c\n",res);
	free(mat[0]);
	free(mat);
	return 0;
}
