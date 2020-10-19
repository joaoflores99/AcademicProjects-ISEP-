#include <string.h>
#include <stdlib.h>
#include "struct_A.h"

// cria uma matriz de structA de lines linhas e columns colunas
structA **new_matrix(int lines, int columns){
	structA **mat;
	mat=(structA **)malloc(sizeof(structA *)*lines);
	mat[0]=(structA *)malloc(sizeof(structA)*columns*lines);
	int i;
	for(i=1;i<lines;i++){
		mat[i]=mat[0] +i*columns;
	}
	return mat;
}
