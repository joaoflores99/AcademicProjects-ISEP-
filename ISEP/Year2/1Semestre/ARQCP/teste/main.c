#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "tempo.h"
#include "qualificados.h"

typedef struct{
	 short id;
	 int n_maratonas;
	 unsigned int * resultados;	
	}Candidato;
	
void inserir_dados(Candidato * ptr,int id ,int n_maratonas,unsigned int *resultados){
	ptr->id=id;
	ptr->n_maratonas=n_maratonas;
	ptr->resultados=resultados;
	
	}


int main(void){
	
	
	Candidato c1;
	Candidato * ptr1=&c1;
	int num_partidas_c1=3;
	unsigned int * resultados1 = (unsigned int *) calloc (4,2*num_partidas_c1);
	
	Candidato c2;
	Candidato * ptr2=&c2;
	int num_partidas_c2 =2;
	unsigned int * resultados2 = (unsigned int *) calloc (4,2* num_partidas_c2);
	
	Candidato c3;
	Candidato * ptr3=&c3;
	int num_partidas_c3=3; 
	unsigned int * resultados4 = (unsigned int *) calloc (4,2* num_partidas_c4);
	
	Candidato c4;
	Candidato * ptr4=&c4;	
	int num_partidas_c4=1; 
	unsigned int * resultados4 = (unsigned int *) calloc (4,2* num_partidas_c4);
	
	
	int id[]={1,2,3,4};
	short *candidatos = (short *) malloc(4,sizeof(short));

    int j;
    for( j=0;j<4;j++){
		(*(candidatos +j )= id[j];
	}
	
	
	
	int res []= {0x000A0000,0x000D212E,0x000E1E00,0x00120102,0x0091E00,0x000D0424};
	int res1 []= {0x000A0000,0x000C33817,0x00091E00,0x000C1C1F};
	int res2 [] = {0x00110000,0x0014020B,0x000E1E00,0x00111E00,0x000B0000,0x000D3B1C};
	int res3 []= {0x00110000,0x00132D00};
	
	inserir_dados(ptr1,1,num_maratonas_c1,res);
	inserir_dados(ptr2,2,num_maratonas_c2,res1);
	inserir_dados(ptr3,3,num_maratonas_c3,res2);
	inserir_dados(ptr4,4,num_maratonas_c4,res3);
	

	 
	int * id_candidatos= (int *) calloc(4,sizeof(int));
	
	
	
	*(id_candidatos+1)=1;
	*(id_candidatos+2)=2;
	*(id_candidatos+3)=3;
	*(id_candidatos+4)=4;
	
	
	
	qualificados(candidatos,4,10800,id_candidatos);
	
	
	
	int i ;
	for (i=0;i<4;i++){
		if( (*(id_candidatos +i)) !=NULL){
			printf("candidato:%d\n",*(id_candidatos +i));
			} 
		
		}
	
	
	free(id_candidatos);
	
	return 0;
	
	
	
	}
