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




int main(){
	pid_t pid[3];
	
	sem_t *sem1,*sem2,*sem3;
	if((sem1=sem_open("sem1",O_CREAT | O_EXCL, 0644,1))==SEM_FAILED){
			printf("ERRO sem 1\n");
			exit(-1);
	}
	if((sem2=sem_open("sem2",O_CREAT | O_EXCL, 0644,0))==SEM_FAILED){
			printf("ERRO sem 2\n");
			exit(-1);
	}
	if((sem3=sem_open("sem3",O_CREAT | O_EXCL, 0644,0))==SEM_FAILED){
			printf("ERRO sem 3\n");
			exit(-1);
	}
	
	pid[0]=fork();
	if(pid[0]<0){
		printf("Erro fork");
		exit(-1);
	}
	if(pid[0]==0){
		printf("Sistemas ");
		
		pid[1]=fork();
		if(pid[1]<0){
			printf("Erro fork");
			exit(-1);
		}
		sem_post(sem2);
		exit(1);
		
	}else{
		wait(&pid[0]);
		sem_wait(sem1);
		sem_wait(sem2);
		sem_wait(sem3);
		printf("a ");
	}
	
	if(pid[1]==0){
		sem_wait(sem2);
		printf("de ");
		
		pid[2]=fork();
		if(pid[2]<0){
			printf("Erro fork");
			exit(-1);
		}
		sem_post(sem3);
		exit(1);
		
	}else{
		wait(&pid[1]);
		sem_wait(sem1);
		sem_wait(sem2);
		sem_wait(sem3);
		printf("Computadores ");
		
		
	}
	
	if(pid[2]==0){
		sem_wait(sem3);
		printf("melhor ");
		
		exit(1);
		
	}else{
		wait(&pid[2]);
		sem_wait(sem1);
		sem_wait(sem2);
		sem_wait(sem3);
		printf("disciplina! ");
	}
	
	if (sem_unlink("sem1") < 0){
		printf("SEM unlink erro.\n");
		exit(-1);
	}
	if (sem_unlink("sem2") < 0){
		printf("SEM unlink erro.\n");
		exit(-1);
	}
	if (sem_unlink("sem3") < 0){
		printf("SEM unlink erro.\n");
		exit(-1);
	}
	return 0;
}
