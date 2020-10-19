/*
 * ex07.c
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
#include "ex07.h"

int estatisticas[LIMITE]; // vetor onde vai conter as estatísticas (número de ocorrências) para cada número
pthread_mutex_t semaforos_exclusivos[LIMITE]; // mutex's para cada número (1 a 49)

int main(void) {
	estrutura keys[TAMANHO_BASE_DADOS];
	preencher_keys(keys);

//	for (int i = 0; i < TAMANHO_BASE_DADOS; ++i) {
//		printf("Sou a key número %d e estas são os meus números:", i+1);
//		for (int j = 0; j < TAMANHO_KEY; ++j) {
//			printf(" %d | ", keys[i].key[j]);
//		}
//		printf("\n");
//	}

	pthread_t threads[NUMERO_THREADS];
	vetor_thread parametros_thread[NUMERO_THREADS];
	for (int i = 0; i < LIMITE; ++i) {
		pthread_mutex_init(&semaforos_exclusivos[i], NULL);
	}

	for (int i = 0; i < NUMERO_THREADS; ++i) {
		int contador = 0;
		for (int j = i * (TAMANHO_BASE_DADOS / NUMERO_THREADS);
				j < (i + 1) * (TAMANHO_BASE_DADOS / NUMERO_THREADS); ++j) {
			parametros_thread[i].keys[contador] = keys[j];
			contador++;
		}
		if (pthread_create(&threads[i], NULL, iterar_keys,
				(void *) &parametros_thread[i])) {  // cada thread apenas vai iterar TAMANHO_BASE_DADOS/NUMERO_THREADS elementos
													// cada thread recebe também como parâmetro os 1000 elementos
													// para simplificar também podiamos colocar o vetor como variável global
													// pasando apenas como parâmetro o indíce cuja cada thread devia de começar a fazer
													// a respetiva iteraçao.
			perror("Erro ao criar um thread.\n");
			exit(EXIT_FAILURE);
		}
	}

	for (int i = 0; i < NUMERO_THREADS; ++i) {
		pthread_join(threads[i], NULL);
	}

	for (int i = 0; i < LIMITE; ++i) {
		printf("O número %d apareceu %d vezes\n", (i + 1), estatisticas[i]);
	}

	return 0;
}

void *iterar_keys(void *arg) {
	vetor_thread parametro_recebido = *((vetor_thread *) arg);
	for (int i = 0; i < TAMANHO_BASE_DADOS / NUMERO_THREADS; ++i) {
		for (int j = 0; j < TAMANHO_KEY; ++j) {
			int key_obtida = parametro_recebido.keys[i].key[j]; // key obtida é entre 1 a 49 portanto, a sua posição será sempre
																// key obtida -1 (arrays começam em 0...)
			pthread_mutex_lock(&semaforos_exclusivos[key_obtida-1]);// garantir que o incremento é uma operação atómica
																	// como são 49 contadores, é necessário um semáforo para cada contador
																	// portanto a necessidade de criar um vetor de semáforos
			estatisticas[key_obtida-1]++;
			pthread_mutex_unlock(&semaforos_exclusivos[key_obtida-1]);
		}
	}
	return (void *) NULL;
}

void preencher_keys(estrutura *keys) {
	srand((unsigned) time(NULL) * getpid());
	for (int i = 0; i < TAMANHO_BASE_DADOS; ++i) {
		for (int j = 0; j < TAMANHO_KEY; ++j) {
			int numero_gerado = rand() % LIMITE + 1;
			while (contains(keys[i], numero_gerado)) { // garantir que não coloca números repetidos
				numero_gerado = rand() % LIMITE + 1;
			}
			keys[i].key[j] = numero_gerado;
		}
	}
}

int contains(estrutura key, int numero) {
	for (int i = 0; i < TAMANHO_KEY; ++i) {
		if (key.key[i] == numero) {
			return 1;
		}
	}
	return 0;
}
