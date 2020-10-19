#include <stdio.h> 
#include <stdlib.h>
#include "add_matrixes.h" 

//função principal para testar
int main(void){
	
	int ** mat;
	int ** mat2;
	int ** matRes;
	mat=new_matrix(3,3);
	mat2=new_matrix(3,3);
	int i,j,num=1;
	
	for(i=0;i<3;i++){
		for(j=0;j<3;j++){
			mat[i][j]=num++;
		}
	}
	
	for(i=0;i<3;i++){
		for(j=0;j<3;j++){
			mat2[i][j]=num++;
		}
	}
	
	for(i=0;i<3;i++){
		for(j=0;j<3;j++){
			printf("%d\n",mat[i][j]);
		}
	}
	
	printf("\n");
	
	for(i=0;i<3;i++){
		for(j=0;j<3;j++){
			printf("%d\n",mat2[i][j]);
		}
	}
	
	printf("\n");
	
	matRes=add_matrixes(mat,mat2,3,3);
	
	for(i=0;i<3;i++){
		for(j=0;j<3;j++){
			printf("%d\n",matRes[i][j]);
		}
	}
	
	for(i=0;i<3;i++){
		free(matRes[i]);
	}
	
	free(matRes);
	
	for(i=0;i<3;i++){
		free(mat[i]);
	}
	
	free(mat);
	
	for(i=0;i<3;i++){
		free(mat2[i]);
	}
	
	free(mat2);
	
	return 0;
}
