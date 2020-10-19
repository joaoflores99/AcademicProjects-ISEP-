#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/wait.h>
#include "semaphore.h"

int main(void) {
	pid_t pid;
	sem_t *semaforo;
	sem_t *semaforo2;
	int i = 0;
	if ((semaforo = sem_open("semaforo", O_CREAT | O_EXCL, 0644, 1)) == NULL) { 
		perror("Erro ao criar o semáforo\n");
		exit(EXIT_FAILURE);
	}

	if ((semaforo2 = sem_open("semaforo2", O_CREAT | O_EXCL, 0644, 1)) == NULL) { 
		perror("Erro ao criar o semáforo2\n");
		exit(EXIT_FAILURE);
	}
	pid = fork();			
	if (pid == 0) {					
		while (1) {					
			sem_wait(semaforo);				
			printf("C");
			setbuf(stdout, NULL);
			i++;
			sem_post(semaforo2);
		}
	} else {
		while (1) {
			sem_wait(semaforo2);
			printf("S");
			setbuf(stdout, NULL);
			i++;
			sem_post(semaforo);
		}
	}
	if (pid == 0) {
		exit(EXIT_SUCCESS);
	}
	wait(NULL);
	sem_unlink("semaforo");
	sem_unlink("semaforo2");
	return 0;
}
