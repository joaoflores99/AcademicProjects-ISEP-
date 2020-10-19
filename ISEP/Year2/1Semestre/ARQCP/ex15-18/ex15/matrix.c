#include <string.h>
#include <stdlib.h>

// cria na memoria dinamica uma matriz de inteiros com lines linhas e columns colunas
int **new_matrix(int lines, int columns){
	int **mat;
	mat=(int **)malloc(sizeof(int *)*lines);
	int i;
	for(i=0;i<lines;i++){
		mat[i]=(int *)malloc(sizeof(int)*columns);
	}
	return mat;
}
