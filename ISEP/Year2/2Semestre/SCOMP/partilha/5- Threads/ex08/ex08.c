/*
 * ex08.c
 *
 *  Created on: 19/05/2018
 *      Author: joao
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

int data[1000];
int resultado[1000];
static const int NUMERO_THREADS = 5;
static const int TAMANHO_VETOR = 1000;
pthread_t threads[5];

void *calculos(void *arg) {
	int i = *((int*) arg);
	for (int j = i * (TAMANHO_VETOR / NUMERO_THREADS);
			j < (i + 1) * (TAMANHO_VETOR / NUMERO_THREADS); j++) {
		resultado[j] = data[j] * 2 + 10;
	}

	if (i != 0) {
		pthread_join(threads[i - 1], NULL);
	}
	for (int j = i * (TAMANHO_VETOR / NUMERO_THREADS);
			j < (i + 1) * (TAMANHO_VETOR / NUMERO_THREADS); j++) {
		printf("Posição %d = %d\n", j, resultado[j]);
	}
	pthread_exit(NULL);
}

int main(void) {
	srand(time(NULL));
	int args[5];

	for (int i = 0; i < 1000; i++) {
		data[i] = rand() % 10 + 1;
	}

	for (int i = 0; i < NUMERO_THREADS; i++) {
		args[i] = i;
		pthread_create(&threads[i], NULL, calculos, (void*) &args[i]);
	}
	pthread_exit(NULL);

}
