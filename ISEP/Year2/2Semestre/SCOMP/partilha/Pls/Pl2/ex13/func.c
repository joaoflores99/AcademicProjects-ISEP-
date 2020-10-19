#include <stdio.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include "func.h"

int spawn_children(int n){
	int p, i;
	for(i=0; i<n; i++){
		p = fork();
		if(p<0){
			printf("Erro na criacao do processo %d \n" , i);
			exit(-1);
		}
		if(p==0){
			return i+1;
		} 
	}
	return 0;
}
void func(){
	
	int fd[2];
	char fileReader[150];
	if(pipe(fd)<0){
		perror("ERRO DE CRIACAO DO PIPE\n");
	}
	
	int pid=spawn_children(1);
	if(pid<0){
		perror("ERRO DE CRIACAO DO PROCESSO FILHO\n");
		}
	//filho
	if(pid>0){
		close(fd[0]);
		dup2(fd[1],1);
		close(fd[1]);
		execlp("sort","sort","sortfx.tx",NULL);
		perror("ERRO AO EXECUTAR A FUNCAO EXEC\n");
		exit(0);
	//pai	
	}else{
		 close(fd[1]);
		 int n=read(fd[0],fileReader,150);
			if(n<0){
				perror("ERRO NA LEITURA DO PIPE\n");
				}else{
					fileReader[n-1]=0;
					printf("\nSTRING:\n%s\n",fileReader);
					}
		close(fd[0]);
	  }
	
}
