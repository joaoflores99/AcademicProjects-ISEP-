/*
 * ex03.c
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
#include "ex03.h"

int array[TAMANHO_ARRAY];
int numero_introduzido;

void* iterar_vetor_parcialmente(void *arg) {
	int indice_comecar = *((int *) arg);
	printf("Vou começar no indice: %d\n", indice_comecar);
	retorno *meu_retorno = NULL;
	for (int i = indice_comecar;
			i < (indice_comecar + (TAMANHO_ARRAY / NUMERO_THREADS)); ++i) {
		if (array[i] == numero_introduzido) {
			meu_retorno = (retorno *) malloc(sizeof(retorno));
			meu_retorno->numero = (indice_comecar
					/ (TAMANHO_ARRAY / NUMERO_THREADS)) + 1;
			if (meu_retorno == NULL) {
				perror("Erro ao alocar memória para a estrutura de retorno\n");
				pthread_exit((void*) NULL);
			}
			break;
		}
	}
	pthread_exit((void*) meu_retorno);
}

int contains(int numero, int *vetor) {
	for (int i = 0; i < TAMANHO_ARRAY; ++i) {
		if (*(vetor + i) == numero) {
			return 1;
		}
	}
	return 0;
}

void preencher_vetor(int *vetor) {
	srand((unsigned) time(NULL));
	int numero_gerado;
	for (int i = 0; i < TAMANHO_ARRAY; ++i) {
		numero_gerado = rand() % LIMITE + 1;
		while (contains(numero_gerado, vetor)) {
			numero_gerado = rand() % LIMITE + 1;
		}
		vetor[i] = numero_gerado;
		//	printf("Coloquei o número %d no vetor\n", vetor[i]);
	}

}

int main() {

	pthread_t threads[5];
	preencher_vetor(array);
	void *ret;
	retorno retorno_thread;
	int argumentos[NUMERO_THREADS];

	do {
		printf("Introduza um número de 1 a %d\n", LIMITE);
		scanf("%d", &numero_introduzido);
	} while (numero_introduzido < 1 || numero_introduzido > LIMITE);

	for (int i = 0; i < NUMERO_THREADS; ++i) {
		argumentos[i] = i * (TAMANHO_ARRAY / NUMERO_THREADS);
		pthread_create(&threads[i], NULL, iterar_vetor_parcialmente,
				(void *) &argumentos[i]);

	}

	puts("Todas as threads foram criadas");

	for (int j = 0; j < NUMERO_THREADS; ++j) {
		pthread_join(threads[j], (void *) &ret);
		if (ret != NULL) {
			retorno_thread = *((retorno*) ret);
			printf("A thread que encontrou o número %d foi a thread: %d\n",
					numero_introduzido, retorno_thread.numero);
			free(ret);
			break;
		}
		if (j == NUMERO_THREADS - 1) {
			printf("Nenhuma thread conseguiu encontrar o número %d\n", numero_introduzido);
		}
		free(ret);
	}

	return 0;
}

