/*
 * ex06.c
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

int is_primo(int numero) {
	int divisores = 0;
	for (int i = 1; i <= numero; ++i) {
		if (numero % i == 0) {
			divisores++;
		}
		if (divisores > 2) { // se já tiver mais do que dois divisores então não é primo
			return 0;
		}
	}
	return divisores == 2; // para ser primo só pode ter dois divisores, o número 1, e o próprio número
}

void *verificar_primos(void *arg) {
	int numero_recebido = *((unsigned int *) arg);
	clock_t inicio;
	clock_t fim;
	inicio = clock();
	for (int i = 0; i <= numero_recebido; ++i) {
		if (is_primo(i)) {
			printf("O número %d é primo.\n", i);
		}
	}
	fim = clock();
	printf("Demorei %.2f milisegundos.\n", (double) (fim-inicio) / CLOCKS_PER_SEC *1000);
	return (void *) NULL;
}



int main(int argc, char **argv) {
	int numero_introduzido;
	pthread_t thread;

	do {
		printf("Introduza um número positivo.\n");
		scanf("%d", &numero_introduzido);
	} while (numero_introduzido < 0);

	if (pthread_create(&thread, NULL, verificar_primos,
			(void *) &numero_introduzido)) {
		perror("Erro ao criar a thread\n");
		exit(EXIT_FAILURE);
	}
	pthread_exit(NULL);
	return 0;
}
