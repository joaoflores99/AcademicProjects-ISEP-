#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>

int array1[1000], array2[1000], pos2 = 0;
pthread_mutex_t mux;
pthread_cond_t cond;

void * funcProd(void *arg) {
	int pos = ((int) arg) * 200, i;
	
	for (i = pos; i < 200 + pos; i++) {
		if (array1[i] < 100) {
			
			pthread_mutex_lock(&mux);
			
			array2[pos2] = array1[i];
			pos2++;
			
			pthread_cond_broadcast(&cond);
			pthread_mutex_unlock(&mux);
		}
	}
	pthread_exit(NULL);
}

void * funcCli() {
	int i;
	pthread_mutex_lock(&mux);
	pthread_cond_wait(&cond, &mux);	
	for (i = 0; i < pos2; i++) {		
		printf("%d\n", array2[i]);		
	}
	pthread_mutex_unlock(&mux);
	pthread_exit(NULL);
}

int main() {
	int i;
	pthread_t produtor[5];
	pthread_t cliente[2];
	
	srand(time(NULL));
	
	for (i = 0; i < 1000; i++) {
		array1[i] = rand() % 1000;
	}
	
	pthread_mutex_init(&mux, NULL);
	pthread_cond_init(&cond, NULL);
	
	for (i = 0; i < 2; i++) {
		pthread_create(&cliente[i], NULL, funcCli, NULL);
	}
	
	int args[5];
	for (i = 0; i < 5; i++) {
		args[i] = i;
		pthread_create(&produtor[i], NULL, funcProd, (void *) args[i]);
	}
	
	return 0;
}
