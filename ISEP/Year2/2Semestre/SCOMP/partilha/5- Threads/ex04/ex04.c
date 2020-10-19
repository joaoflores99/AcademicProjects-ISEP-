/*
 * ex04.c
 *
 *  Created on: 15/05/2018
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

int matriz1[5][5];
int matriz2[5][5];
int matriz3[5][5];

void *enchematriz(void *arg) {
	int i = *((int*) arg);
	if (i == 0) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matriz1[i][j] = rand() % 5 + 1;
			}
		}
	} else if (i == 1) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matriz2[i][j] = rand() % 5 + 1;
			}
		}
	}
	if (i > 1) {
		i = i - 2;
		int c;
			for (c = 0; c < 5; c++) {
				matriz3[i][c] = 0;
				for (int r = 0; r < 5; r++) {
					matriz3[i][c]+= matriz1[i][r] * matriz2[r][c];
				}
			}
	}
	pthread_exit(NULL);
}

int main(void) {
	srand(time(NULL));
	pthread_t threads[7];
	int args[7];

	for (int i = 0; i < 2; i++) {
		args[i] = i;
		pthread_create(&threads[i], NULL, enchematriz, (void*) &args[i]);
	}
    for (int i = 0; i < 2; i++) {
        pthread_join(threads[i], NULL);
	}
	for (int i = 2; i < 7; i++) {
		args[i]=i;
		pthread_create(&threads[i], NULL, enchematriz, (void*) &args[i]);
	}
	for (int i = 2; i < 7; i++) {
		pthread_join(threads[i], NULL);
	}

	for (int i = 0; i < 5; i++) {
		printf("\n");
		for (int j = 0; j < 5; j++) {
			printf("%d   ", matriz1[i][j]);
		}
	}
	printf("\n");
	for (int i = 0; i < 5; i++) {
		printf("\n");
		for (int j = 0; j < 5; j++) {
			printf("%d   ", matriz2[i][j]);
		}
	}
	printf("\n");
	for (int i = 0; i < 5; i++) {
		printf("\n");
		for (int j = 0; j < 5; j++) {
			printf("%d   ", matriz3[i][j]);
		}
	}
}
