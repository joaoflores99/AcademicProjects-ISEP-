#include <string.h>
#include <stdlib.h>
#include "add_matrixes.h"

//função que soma os valores de 2 matrizes e guarda-os numa terceira matriz criada dinamicamente
int **add_matrixes(int **a, int **b, int y, int k){
	int i,j;
	int**matrix=new_matrix(y,k);
	for(i=0; i<y;i++){
		for(j=0; j<k; j++){
			matrix[i][j]=a[i][j]+b[i][j];
		}
	}
	
	return matrix;
	
}
