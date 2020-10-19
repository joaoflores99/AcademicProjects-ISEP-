/*
 * ex10.c
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
#include <limits.h>

typedef struct {
	int id_h;
	int id_p;
	int p;
} structure;

typedef struct {
	int id_p;
	int p;
} hipermecado;

pthread_t threads[6];
structure vetor[10000];
hipermecado vec1[10000];
hipermecado vec2[10000];
hipermecado vec3[10000];
int contador = 0;
pthread_mutex_t mutex1;
pthread_mutex_t mutex2;
pthread_mutex_t mutex3;
pthread_cond_t cond_var;
float mediabaixatotal = INT_MAX;
int h = 0, k, l, m;

void *filtering(void *arg) {
	int s1 = 0, o = 0, s2 = 0, s3 = 0, s4 = 0, s5 = 0, p = 0, q = 0, r = 0, t =
			0;
	float med1, med2, med3, med4, med5, medt;
	int i = *((int*) arg);
	if (i < 3) {
		for (int j = i * (10000 / 3); j < (i + 1) * (10000 / 3); j++) {
			if (vetor[j].id_h == 0) {
				vec1[k].id_p = vetor[j].id_p;
				vec1[k].p = vetor[j].p;
				k++;
				pthread_mutex_lock(&mutex1);
				contador++;
				pthread_mutex_unlock(&mutex1);
			} else if (vetor[j].id_h == 1) {
				vec2[l].id_p = vetor[j].id_p;
				vec2[l].p = vetor[j].p;
				l++;
				pthread_mutex_lock(&mutex1);
				contador++;
				pthread_mutex_unlock(&mutex1);
			} else if (vetor[j].id_h == 2) {
				vec3[m].id_p = vetor[j].id_p;
				vec3[m].p = vetor[j].p;
				m++;
				pthread_mutex_lock(&mutex1);
				contador++;
				pthread_mutex_unlock(&mutex1);
			}
		}
		pthread_mutex_lock(&mutex2);
		if (contador == 9999) {
			pthread_cond_broadcast(&cond_var);
		}
		pthread_mutex_unlock(&mutex2);
	} else {
		pthread_mutex_lock(&mutex2);
		while (contador < 9999) {
			pthread_cond_wait(&cond_var, &mutex2);
		}
		pthread_mutex_unlock(&mutex2);
		if (i == 3) {
			for (int j = 0; j < k; j++) {
				if (vec1[j].id_p == 0) {
					s1 = s1 + vec1[j].p;
					o++;
				} else if (vec1[j].id_p == 1) {
					s2 = s2 + vec1[j].p;
					p++;
				} else if (vec1[j].id_p == 2) {
					s3 = s3 + vec1[j].p;
					q++;
				} else if (vec1[j].id_p == 3) {
					s4 = s4 + vec1[j].p;
					r++;
				} else if (vec1[j].id_p == 4) {
					s5 = s5 + vec1[j].p;
					t++;
				}
			}
			if (o != 0 && p != 0 && q != 0 && r != 0 && t != 0) {
				med1 = s1 / o;
				med2 = s2 / p;
				med3 = s3 / q;
				med4 = s4 / r;
				med5 = s5 / t;
				medt = (med1 + med2 + med3 + med4 + med5) / 5;
			}
			pthread_mutex_lock(&mutex3);
			if (medt < mediabaixatotal) {
				h = 0;
				mediabaixatotal = medt;
			}
			pthread_mutex_unlock(&mutex3);
		} else if (i == 4) {

			for (int j = 0; j < l; j++) {
				if (vec2[j].id_p == 0) {
					s1 = s1 + vec2[j].p;
					o++;
				} else if (vec2[j].id_p == 1) {
					s2 = s2 + vec2[j].p;
					p++;
				} else if (vec2[j].id_p == 2) {
					s3 = s3 + vec2[j].p;
					q++;
				} else if (vec2[j].id_p == 3) {
					s4 = s4 + vec2[j].p;
					r++;
				} else if (vec2[j].id_p == 4) {
					s5 = s5 + vec2[j].p;
					t++;
				}
			}
			if (o != 0 && p != 0 && q != 0 && r != 0 && t != 0) {
				med1 = s1 / o;
				med2 = s2 / p;
				med3 = s3 / q;
				med4 = s4 / r;
				med5 = s5 / t;
				medt = (med1 + med2 + med3 + med4 + med5) / 5;
			}

			pthread_mutex_lock(&mutex3);
			if (medt < mediabaixatotal) {
				h = 1;
				mediabaixatotal = medt;
			}
			pthread_mutex_unlock(&mutex3);

		} else {
			for (int j = 0; j < l; j++) {
				if (vec3[j].id_p == 0) {
					s1 = s1 + vec3[j].p;
					o++;
				} else if (vec3[j].id_p == 1) {
					s2 = s2 + vec3[j].p;
					p++;
				} else if (vec3[j].id_p == 2) {
					s3 = s3 + vec3[j].p;
					q++;
				} else if (vec3[j].id_p == 3) {
					s4 = s4 + vec3[j].p;
					r++;
				} else if (vec3[j].id_p == 4) {
					s5 = s5 + vec3[j].p;
					t++;
				}
			}
			if (o != 0 && p != 0 && q != 0 && r != 0 && t != 0) {
				med1 = s1 / o;
				med2 = s2 / p;
				med3 = s3 / q;
				med4 = s4 / r;
				med5 = s5 / t;
				medt = (med1 + med2 + med3 + med4 + med5) / 5;
			}
			pthread_mutex_lock(&mutex3);
			if (medt < mediabaixatotal) {
				h = 2;
				mediabaixatotal = medt;
			}
			pthread_mutex_unlock(&mutex3);
		}
	}
	pthread_exit(NULL);
}

int main(void) {
	srand(time(NULL));
	int args[6];
	int i;
	pthread_mutex_init(&mutex2, NULL);
	pthread_mutex_init(&mutex1, NULL);
	pthread_mutex_init(&mutex3, NULL);
	pthread_cond_init(&cond_var, NULL);

	for (int i = 0; i < 10000; i++) {
		vetor[i].id_h = rand() % 3;
		vetor[i].id_p = rand() % 5;
		vetor[i].p = rand() % 50;
	}
	for (i = 0; i < 6; i++) {
		args[i] = i;
		pthread_create(&threads[i], NULL, filtering, (void*) &args[i]);
	}
	for (i = 0; i < 6; i++) {
		pthread_join(threads[i], NULL);
	}

	printf("O supermercado com a media de preços mais baixo é : %d com %f\n",
			(h + 1), mediabaixatotal);
}

