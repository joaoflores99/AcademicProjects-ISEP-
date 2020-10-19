/*
 * ex09.h
 *
 *  Created on: May 15, 2018
 *      Author: rafa
 */

#ifndef EX09_H_
#define EX09_H_

void *funcao_thread(void *arg);

#define NUMERO_LINHAS 3

typedef struct{
	int numero_comboio;
	int numero_gerado;
	int segundos;
} estrutura;


#endif /* EX09_H_ */
