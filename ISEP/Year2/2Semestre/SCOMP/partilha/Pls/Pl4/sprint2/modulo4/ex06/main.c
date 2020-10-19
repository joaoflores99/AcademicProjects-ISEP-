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

typedef struct{
	int v;
}estrutura;

int main(){
	int fd;
	estrutura *addr=NULL;
	
	sem_t *sem;
	int pid;
	if((sem=sem_open("sem",O_CREAT | O_EXCL, 0644,0))==SEM_FAILED){
			printf("ERRO sem\n");
			exit(-1);
	}
	
	fd=shm_open("/shmtest",O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR);
	if(fd==-1){
		printf("Erro no fd\n");
		exit(-1);
	}
	ftruncate(fd,sizeof(estrutura));
	
	addr=(estrutura*) mmap(NULL,sizeof(estrutura),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	if(addr==MAP_FAILED){
		printf("Erro no no mmap\n");
		exit(-1);
	}
	addr->v=0;
	
	pid = fork();
	if(pid<0){
		printf("Erro no fork\n");
		exit(-1);
	}
	
	while(addr->v<8){
		if (pid == 0){
			printf("Eu sou o filho\n");
			sem_post(sem);
			addr->v++;
			if(addr->v>7){
				exit(-1);
			}
		}
		else{
			sem_wait(sem);
			if(addr->v==7){
				break;
			}
			printf("Eu sou o pai\n");
		}
	}
	
	if (sem_close(sem) < 0){
		printf("Semafore erro a fechar\n");
		exit(-1);
	}
	if (sem_unlink("sem") < 0){
		printf("SEM unlink erro.\n");
		exit(-1);
	}
	
	if(shm_unlink("/shmtest")<0){
		printf("shm unlink erro\n");
		exit(-1);
	}
	
	return 0;
}
