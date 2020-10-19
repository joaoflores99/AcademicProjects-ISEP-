#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "string.h"
#include <unistd.h>
#include <sys/types.h>
#include <semaphore.h>
#include <time.h>
#include <stdlib.h>
#include <time.h>
#include <sys/types.h>


int create_childs(int num){
	int i;
	for (i = 1; i <= num; i++){
		pid_t pid = fork();
		if (pid < 0){
			return -1;
		}
		if (pid == 0){
			return i;
		}
	}
	return 0;
}

int main(){
	sem_t *sem;
	int pid;
	if((sem=sem_open("sem",O_CREAT | O_EXCL, 0644,0))==SEM_FAILED){
			printf("ERRO sem\n");
			exit(-1);
	}
	//pid= create_childs(1);
	pid = fork();
	if(pid<0){
		printf("Erro no fork\n");
		exit(-1);
	}
	if (pid == 0){
		printf("Eu sou o filho.\n");
		sem_post(sem);
		exit(1);
	}
	else{
		sem_wait(sem);
		printf("Eu sou o pai.\n");
	}
	/*if (sem_close(sem) < 0){
		printf("Semafore erro a fechar\n");
		exit(-1);
	}*/
	if (sem_unlink("sem") < 0){
		printf("SEM unlink erro.\n");
		exit(-1);
	}
	return 0;
}
