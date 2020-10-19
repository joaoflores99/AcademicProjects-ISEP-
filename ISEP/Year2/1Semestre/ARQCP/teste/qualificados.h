#ifndef QUALIFICADOS_H 
#define QUALIFICADOS_H

typedef struct{
	 short id;
	 int n_maratonas;
	 unsigned int * resultados;	
	}Candidato;
	

void qualificados(Candidato * candidato, int n, int tempo_min, short * ids_qualificados);

#endif
