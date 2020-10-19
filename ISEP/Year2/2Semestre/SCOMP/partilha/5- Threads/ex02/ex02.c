/*
 * ex02.c
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

typedef struct {
	int number;
	char name[255];
	char address[255];
} structure;

void* function(void *arg) {
	structure estrutura=*((structure*)arg);
	printf("O numero é :%d\n", estrutura.number);
	printf("O nome é :%s\n", estrutura.name);
	printf("O endereço é :%s\n", estrutura.address);

}

int main(void) {
	structure array[5];
	pthread_t threads[5];
	strcpy(array[0].address, "Rua1");
	strcpy(array[0].name, "Name1");
	array[0].number = 1;
	strcpy(array[1].address, "Rua2");
	strcpy(array[1].name, "Name2");
	array[1].number = 2;
	strcpy(array[2].address, "Rua3");
	strcpy(array[2].name, "Name3");
	array[2].number = 3;
	strcpy(array[3].address, "Rua4");
	strcpy(array[3].name, "Name4");
	array[3].number = 4;
	strcpy(array[4].address, "Rua5");
	strcpy(array[4].name, "Name5");
	array[4].number = 5;
	int i;
	for (i = 0; i < 5; i++) {
		pthread_create(&threads[i], NULL, function, (void *) &array[i]);
	}
	pthread_exit(NULL);

}
