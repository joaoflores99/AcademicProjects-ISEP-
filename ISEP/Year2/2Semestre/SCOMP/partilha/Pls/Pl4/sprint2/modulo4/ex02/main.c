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
	int j,i,t;
	j=0;
	sem_t *sem[8];
	char s[6]="sem";
	pid_t pid[8];
	if((sem[0]=sem_open("sem0",O_CREAT | O_EXCL, 0644,1))==SEM_FAILED){
			printf("ERRO sem 0\n");
			exit(-1);
		}
	for(i=1;i<8;i++){
		sprintf(s,"%d",i);
		if((sem[i]=sem_open(s,O_CREAT | O_EXCL, 0644,0))==SEM_FAILED){
			printf("ERRO sem %d\n",i);
			exit(-1);
		}
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
			
		for(j=0;j<8;j++){
			fscanf(file,"%d",&t);
			printf("Valor %d %d\n",j,t);
		}
	}
	else{
		sem_wait(sem[i]);
		//for (j = i * 200; j < i * 200 + 200; j++){
		for(j=i;j<i*2+2;j++){
			fscanf(fileNUM,"%d",&t);
			fprintf(fileOut," %d",t);
		}
		fclose(fileNUM);
		fclose(fileOut);
		//sem_post(sem[i]);
		sem_post(sem[i+1]);
		exit(0);
	}
	/*if(sem_unlink("sem0")<0){
			printf("Erro no unlink 0\n");
			exit(-1);
	}
	for(i=1;i<8;i++){
		char ss=sprintf(s,"%d",i);
		if(sem_unlink(ss)<0){
			printf("Erro no unlink %d\n",i);
			exit(-1);
		}
	}*/
	return 0;
}
