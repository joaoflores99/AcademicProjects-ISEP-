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
#include "asm.h"


int bet(){
	time_t t;
	srand((unsigned) time (&t));
	return rand () % 1000+1;
}

int main(){
	int j,i,t;
	j=0;
	
	sem_t *sem;
	pid_t pid[8];
	
	if((sem=sem_open("sem",O_CREAT | O_EXCL, 0644,1))==SEM_FAILED){
		printf("ERRO\n");
		exit(-1);
	}
	
	FILE *fileOut=fopen("Output.txt","w");
	if(fileOut==NULL)
		printf("ERRO file\n");
	
	for(i=0;i<8;i++){
		pid[i]=fork();
		if(pid[i]<0){
			printf("ERRO fork\n");
			exit(-1);
		}
		if(pid[i]==0){
			break;
		}
	}
	
	FILE *fileNUM=fopen("NUMBER.txt","r");
	if(fileNUM==NULL)
		printf("ERRO file\n");
	
	if(pid[i]>0){
		for(j=0;j<8;j++){
			wait(0);
	}
	
	
		
	FILE *file=fopen("Output.txt","r");
	if(fileNUM==NULL)
		printf("ERRO file\n");
			
	for(j=0;j<1600;j++){
		fscanf(file,"%d",&t);
		printf("Valor %d %d\n",j,t);
		}
	}
	else{
		sem_wait(sem);
		for (j = i * 200; j < 200*i + 200; j++){
			fscanf(fileNUM,"%d",&t);
			fprintf(fileOut," %d",t);
		}
		fclose(fileNUM);
		fclose(fileOut);
		sem_post(sem);
		exit(0);
	}
	
	if(sem_unlink("sem")<0){
		printf("Erro no unlink\n");
		exit(-1);
	}
	return 0;
}

