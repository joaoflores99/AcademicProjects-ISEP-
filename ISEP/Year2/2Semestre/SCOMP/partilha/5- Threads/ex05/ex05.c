/*
 * ex05.c
 *
 *  Created on: May 6, 2018
 *      Author: rafa
 */

#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <pthread.h>
#include <limits.h>
#include "ex05.h"

int menor_saldo = INT_MAX;
int maior_saldo = 0;
float media_saldo;
int saldos[TAMANHO_ARRAY];

void preencher_vetor(int *vetor) {
	srand(time(NULL)*getpid());
	for (int i = 0; i < TAMANHO_ARRAY; ++i) {
		*(vetor + i) = rand() % LIMITE;
	}
}

void* iterar_vetor(void *args) {
	int numero_thread = *((int*) args);
	if (numero_thread == 1) { // procurar o menor saldo
		printf("Sou a primeira thread e quero saber o menor saldo.\n");
		for (int i = 0; i < TAMANHO_ARRAY; ++i) {
			if (saldos[i] < menor_saldo) {
				menor_saldo = saldos[i];
			}
		}
	} else if (numero_thread == 2) { // procurar o maior saldo
		printf("Sou a segunda thread e quero saber o maior saldo.\n");
		for (int i = 0; i < TAMANHO_ARRAY; ++i) {
			if (saldos[i] > maior_saldo) {
				maior_saldo = saldos[i];
			}
		}
	} else if (numero_thread == 3) { // fazer a média de cada saldo
		printf("Sou a terceira thread e quero saber a média de saldos.\n");
		int soma = 0;
		for (int i = 0; i < TAMANHO_ARRAY; ++i) {
			soma += saldos[i];
		}
		if (!TAMANHO_ARRAY) {
			printf("Impossível fazer a divisão\n");
		} else {
			media_saldo = (float) soma / TAMANHO_ARRAY;
		}
	}
	pthread_exit((void *) NULL);
}

int main(void) {
	preencher_vetor(saldos);
	pthread_t threads[NUMERO_THREADS];
	int argumentos[NUMERO_THREADS];
	for (int i = 0; i < NUMERO_THREADS; ++i) {
		argumentos[i] = i + 1;
		pthread_create(&threads[i], NULL, iterar_vetor,
				(void *) &argumentos[i]);
	}
	printf("Todas as %d threads foram criadas.\n", NUMERO_THREADS);
	for (int i = 0; i < NUMERO_THREADS; ++i) {
		pthread_join(threads[i], (void *) NULL);
	}
	printf("O menor saldo é de: %d\n", menor_saldo);
	printf("O maior saldo é de: %d\n", maior_saldo);
	printf("A média de todos os saldos é de: %.2f\n", media_saldo);
	return 0;
}

