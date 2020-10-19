/*
 * ex11.c
 *
 *  Created on: May 19, 2018
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
#include "ex11.h"

prova provas[NUMERO_PROVAS];
pthread_mutex_t mutex1;
pthread_mutex_t mutex2;
pthread_mutex_t mutex3;
pthread_mutex_t mutex4;
pthread_cond_t cond1;
pthread_cond_t cond2;
pthread_cond_t cond3;
pthread_cond_t cond4;
pthread_cond_t cond5;
pthread_cond_t cond6;
int avisarProva = 0;
int avisarNotaPositiva = 0;
int avisarNotaNegativa = 0;
int iteracoesNotaFinal = 0;
int iteracoesPositivaNegativa = 0;
int positivas = 0;
int negativas = 0;
void* funcao_thread(void *arg) {
	int numero_thread = *((int *) arg);
	//printf("Sou a %dª thread.\n", numero_thread);
	switch (numero_thread) {
	case 1:
		for (int i = 0; i < NUMERO_PROVAS; ++i) {
			provas[i].numero = rand() % 1001;
			provas[i].notaG1 = rand() % 101;
			provas[i].notaG2 = rand() % 101;
			provas[i].notaG3 = rand() % 101;
			pthread_mutex_lock(&mutex1);
			while (avisarProva >= 1) {
				pthread_cond_wait(&cond1, &mutex1);
			}
			avisarProva = 1;
			pthread_cond_signal(&cond2);
			pthread_mutex_unlock(&mutex1);

		}

		break;
	case 2:
	case 3:
		do {
			pthread_mutex_lock(&mutex1);
			while (avisarProva == 0) {
				pthread_cond_wait(&cond2, &mutex1);
			}
			if (iteracoesNotaFinal == NUMERO_PROVAS) {
				pthread_exit(NULL);
			}
			provas[iteracoesNotaFinal].nota_final =
					(provas[iteracoesNotaFinal].notaG1
							+ provas[iteracoesNotaFinal].notaG2
							+ provas[iteracoesNotaFinal].notaG3) / 3;
			//printf("Nota final deste aluno é: %d\n", provas[iteracoesNotaFinal].nota_final);
			if (provas[iteracoesNotaFinal].nota_final >= 50) {
				pthread_mutex_lock(&mutex2);
				while (avisarNotaPositiva == 1) {
					pthread_cond_wait(&cond5, &mutex2);
				}
				avisarNotaPositiva = 1;
				pthread_cond_signal(&cond3);
				pthread_mutex_unlock(&mutex2);
			} else {
				pthread_mutex_lock(&mutex3);
				while (avisarNotaNegativa == 1) {
					pthread_cond_wait(&cond6, &mutex3);
				}
				avisarNotaNegativa = 1;
				pthread_cond_signal(&cond4);
				pthread_mutex_unlock(&mutex3);
			}
			iteracoesNotaFinal++;

			avisarProva = 0;
			pthread_cond_signal(&cond1);
			pthread_mutex_unlock(&mutex1);

		} while (iteracoesNotaFinal < NUMERO_PROVAS);

		break;
	case 4:
		do {
			pthread_mutex_lock(&mutex2);
			while (avisarNotaPositiva == 0) {
				pthread_cond_wait(&cond3, &mutex2);
			}
			if(iteracoesPositivaNegativa==NUMERO_PROVAS){
				pthread_exit(NULL);
			}
			positivas++;

			pthread_mutex_lock(&mutex4);
			iteracoesPositivaNegativa++;
			pthread_mutex_unlock(&mutex4);
			avisarNotaPositiva = 0;
			pthread_cond_signal(&cond5);
			pthread_mutex_unlock(&mutex2);

		} while (iteracoesPositivaNegativa < NUMERO_PROVAS);
		break;
	case 5:
		do {
			pthread_mutex_lock(&mutex3);
			while (avisarNotaNegativa == 0) {
				pthread_cond_wait(&cond4, &mutex3);
			}
			if(iteracoesPositivaNegativa==NUMERO_PROVAS){
				pthread_exit(NULL);
			}
			negativas++;
			//printf("Número de negativas atualmente: %d\n", negativas);
			pthread_mutex_lock(&mutex4);
			iteracoesPositivaNegativa++;
			pthread_mutex_unlock(&mutex4);
			avisarNotaNegativa = 0;
			pthread_cond_signal(&cond6);
			pthread_mutex_unlock(&mutex3);


		} while (iteracoesPositivaNegativa < NUMERO_PROVAS);
		break;
	}
	if (numero_thread == 3) {
		avisarProva = 1;
		pthread_cond_signal(&cond2);
	} else if (numero_thread == 2) {
		avisarProva = 1;
		pthread_cond_signal(&cond2);
	} else if (numero_thread == 4) {
		avisarNotaNegativa = 1;
		pthread_cond_signal(&cond4);
	} else if (numero_thread == 5) {
		avisarNotaPositiva = 1;
		pthread_cond_signal(&cond3);
	}
	pthread_exit(NULL);
}

int main() {
	if(NUMERO_PROVAS==0){
		printf("Não existem provas para analisar.\n");
		return 0;
	}
	srand((unsigned) time(NULL)+getpid());

	pthread_t threads[NUMERO_THREADS];
	int numero_thread[NUMERO_THREADS];

	pthread_mutex_init(&mutex1, NULL);
	pthread_mutex_init(&mutex2, NULL);
	pthread_mutex_init(&mutex3, NULL);
	pthread_mutex_init(&mutex4, NULL);

	pthread_cond_init(&cond1, NULL);
	pthread_cond_init(&cond2, NULL);
	pthread_cond_init(&cond3, NULL);
	pthread_cond_init(&cond4, NULL);
	pthread_cond_init(&cond5, NULL);
	pthread_cond_init(&cond6, NULL);

	for (int i = 0; i < NUMERO_THREADS; ++i) {
		numero_thread[i] = i + 1;
		if (pthread_create(&threads[i], NULL, funcao_thread,
				(void *) &numero_thread[i]) < 0) {
			perror("Erro ao criar uma thread.");
			exit(EXIT_FAILURE);
		}
	}
	for (int i = 0; i < NUMERO_THREADS; ++i) {
		pthread_join(threads[i], NULL);
	}
	puts("Todas as threads já terminaram, posso agora calcular as percentagens");
	float percentagemPositiva;
	percentagemPositiva = ((float)positivas/NUMERO_PROVAS)*100;
	float percentagemNegativa;
	percentagemNegativa =  ((float)negativas/NUMERO_PROVAS)*100;
	printf("A percentagem de notas positivas é de: %.2f\n",percentagemPositiva);
	printf("A percentagem de notas negativas é de: %.2f\n",percentagemNegativa);
	return 0;
}

