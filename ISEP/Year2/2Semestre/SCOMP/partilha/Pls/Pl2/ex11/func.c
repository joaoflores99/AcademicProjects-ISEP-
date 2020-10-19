#include <stdio.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "func.h"

int spawn_children(int n){
	int p, i;
	for(i=0; i<n; i++){
		p = fork();
		if(p<0){
			perror("ERRO NA CRIACAO DO PROCESSO FILHO %d \n");
			exit(-1);
		}
		if(p==0){
			return i+1;
		} 
	}
	return 0;
}


void func(){
	
	int fd[5][2];
	
	if(pipe(fd[0])|| pipe(fd[1])||pipe(fd[2])||pipe(fd[3])||pipe(fd[4])){
		perror("ERRO NA CRIACAO DO PIPE\n");
	}
	
	int pid = spawn_children(5);
	
	if(pid<0){
	   perror("ERRO NA CRIACAO DO PROCESSO FILHO\n");
	}
	int other;
	int rn;
	
	srand (time(NULL) ^ getpid());
	rn = rand() % (500 + 1);	
	
	if(pid >0) {
		close(fd[pid - 1][1]);
		if(read(fd[pid - 1][0], &other, sizeof(int)) == 0) {
			perror("ERRO NA LEITURA DO PIPE\n");
			exit(-1);
		}
		close(fd[pid - 1][0]);
		
		printf("FILHO COM PID= %d - %d; OTHER - %d\n", getpid(), rn, other);
		
		if (other > rn) {
			rn = other;
		} 
		if (pid != 5) {
			close(fd[pid][0]);
			if(write(fd[pid][1], &rn, sizeof(int)) < 0) {
				perror("ERRO NA ESCRITA DO PIPE\n");
				exit(-1);
			}
			close(fd[pid][1]);
		} else {
			close(fd[0][0]);
			if(write(fd[0][1], &rn, sizeof(int)) < 0) {
				perror("ERRO NA ESCRITA DO PIPE\n");
			}
			close(fd[0][1]);
		}
		exit(0);
		
	} else {
		printf("PAI - %d\n", rn);
		
		close(fd[1][0]);
		if(write(fd[1][1], &rn, sizeof(int)) < 0) {
			perror("ERRO NA ESCRITA DO PIPE\n");
			exit(-1);
		}
		close(fd[1][1]);
		
		wait(NULL);
		
		close(fd[4][1]);		
		if(read(fd[4][0], &other, sizeof(int)) < 0) {
			perror("ERRO NA LEITURA DO PIPE\n");
			exit(-1);
		}
		close(fd[4][0]);
		
		if (other > rn) {
			printf("O MAIOR NUMERO E: %d\n", other);
		} else {
			printf("O MAIOR NUMERO E : %d\n", rn);
		}
	}
}
