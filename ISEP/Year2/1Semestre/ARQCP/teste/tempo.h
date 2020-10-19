#ifndef TEMPO_H 
#define TEMPO_H
	
	typedef struct{
	 short id;
	 int n_maratonas;
	 unsigned int * resultados;	
	}Candidato;
	
int calcula_tempos(Candidato * candidato);

#endif
