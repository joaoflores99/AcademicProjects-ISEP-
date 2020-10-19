/*
 * ex09.c
 *
 *  Created on: May 15, 2018
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
#include "ex09.h"

pthread_mutex_t mutexes[NUMERO_LINHAS];

void* funcao_thread(void *arg) {
	estrutura comboio = *((estrutura *) arg);
	switch (comboio.numero_gerado) {
	case 1:
		pthread_mutex_lock(&mutexes[0]);
		printf("Sou o comboio %d e estou a ir de A para B\n",
				comboio.numero_comboio);
		sleep(comboio.segundos);
		pthread_mutex_unlock(&mutexes[0]);
		printf("Já terminei a viagem de A para B e demorei %d segundos\n", comboio.segundos);
		break;
	case 2:
		pthread_mutex_lock(&mutexes[0]);
		printf("Sou o comboio %d e estou a ir de B para A\n",
				comboio.numero_comboio);
		sleep(comboio.segundos);
		pthread_mutex_unlock(&mutexes[0]);
		printf("Já terminei a viagem de B para A e demorei %d segundos\n", comboio.segundos);
		break;
	case 3:
		pthread_mutex_lock(&mutexes[1]);
		printf("Sou o comboio %d e estou a ir B para C\n",
				comboio.numero_comboio);
		sleep(comboio.segundos);
		pthread_mutex_unlock(&mutexes[1]);
		printf("Já terminei a viagem de B para C e demorei %d segundos\n", comboio.segundos);
		break;
	case 4:
		pthread_mutex_lock(&mutexes[1]);
		printf("Sou o comboio %d e estou a ir de C para B\n",
				comboio.numero_comboio);
		sleep(comboio.segundos);
		pthread_mutex_unlock(&mutexes[1]);
		printf("Já terminei a viagem de C para B e demorei %d segundos\n", comboio.segundos);
		break;
	case 5:
		pthread_mutex_lock(&mutexes[2]);
		printf("Sou o comboio %d e estou a ir de B para D\n",
				comboio.numero_comboio);
		sleep(comboio.segundos);
		pthread_mutex_unlock(&mutexes[2]);
		printf("Já terminei a viagem de B para D e demorei %d segundos\n", comboio.segundos);
		break;
	case 6:
		pthread_mutex_lock(&mutexes[2]);
		printf("Sou o comboio %d e estou a ir de D para B\n",
				comboio.numero_comboio);
		sleep(comboio.segundos);
		pthread_mutex_unlock(&mutexes[2]);
		printf("Já terminei a viagem de D para B e demorei %d segundos\n", comboio.segundos);
		break;
	}

	pthread_exit(NULL);
}

int main(void) {
	srand((unsigned) time(NULL));

	int numero_comboios;
	printf("Quantos comboios deseja simular?\n");
	do {
		scanf("%d", &numero_comboios);
	} while (numero_comboios <= 0);

	pthread_t threads[numero_comboios];
	estrutura comboios[numero_comboios];

	for (int i = 0; i < numero_comboios; ++i) {
		comboios[i].numero_comboio = i + 1;
		comboios[i].numero_gerado = rand() % 6 + 1; // gera numeros de 1 a 6 se for 1 - vai para A a B, se for 2 vai para B - A, etc
		comboios[i].segundos = rand() % 5 + 3; // tempo que vai demorar gera números de 3 a 7
		if (pthread_create(&threads[i], NULL, funcao_thread,
				(void *) &comboios[i]) < 0) {
			perror("Erro ao criar uma thread.");
			exit(EXIT_FAILURE);
		}
	}

	pthread_exit(NULL); // To allow other threads to continue execution,
						//the main thread should terminate by calling pthread_exit() rather than exit(3).

	return 0;
}

