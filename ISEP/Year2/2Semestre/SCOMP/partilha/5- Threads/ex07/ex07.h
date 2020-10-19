/*
 * ex07.h
 *
 *  Created on: May 6, 2018
 *      Author: rafa
 */

#ifndef EX07_H_
#define EX07_H_

#define TAMANHO_BASE_DADOS 10000
#define NUMERO_THREADS 10
#define TAMANHO_KEY 5
#define LIMITE 49

typedef struct {
	int key[TAMANHO_KEY];
} estrutura;

typedef struct {
	estrutura keys[TAMANHO_BASE_DADOS/NUMERO_THREADS];
} vetor_thread;

int contains(estrutura key, int numero);

void preencher_keys(estrutura *keys);

void *iterar_keys(void *arg);

#endif /* EX07_H_ */
