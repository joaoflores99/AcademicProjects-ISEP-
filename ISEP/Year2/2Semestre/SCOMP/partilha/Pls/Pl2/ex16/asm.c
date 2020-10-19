#include <stdio.h>
#include <sys/types.h>
#include <unistd.h> 
#include <sys/wait.h>
#include <time.h>
#include <stdlib.h>

void prog(){
	int fd1[2],fd2[2];
	int pid1,pid2,pid3;
	int p1=pipe(fd1),p2=pipe(fd2);
	if(p1==-1){
		printf("Erro no pipe 1\n");
		exit(-1);
	}
	if(p2==-1){
		printf("Erro no pipe 2\n");
		exit(-1);
	}
	
	pid1=fork();
	if(pid1<0){
		printf("Erro no fork 1\n");
		exit(-1);
	}
	if(pid1==0){
		printf("Filho 1\n");
		int d1=dup2(fd1[1], 1);
		if(d1==-1){
			printf("Erro no dup\n");
			exit(-1);
		}
		close(fd1[0]);
		close(fd1[1]);
		execlp("ls", "", NULL);
		printf("primeiro exec falhou\n");//nao corre se o exec der certo
		exit(1);
	}
	
	
	pid2=fork();
	if(pid2<0){
		printf("Erro no fork 2\n");
		exit(-1);
	}
	if(pid2==0){
		printf("Filho 2\n");
		int d2=dup2(fd1[0], 0);	
		if(d2==-1){
			printf("Erro no dup\n");
			exit(-1);
		}
		int d3=dup2(fd2[1], 1);
		if(d3==-1){
			printf("Erro no dup\n");
			exit(-1);
		}
		close(fd1[0]);
		close(fd1[1]);
		close(fd2[0]);
		close(fd2[1]);
		execlp("sort", "", NULL);
		printf("Segundo exec falohu\n");
		exit(-1);

	}
	
	pid3=fork();
	if(pid3<0){
		printf("Erro no fork 3\n");
		exit(-1);
	}
	if(pid3==0){
		printf("Filho 3\n");
		close(fd1[0]);
		close(fd1[1]);
		dup2(fd2[0], 0);
		close(fd2[0]);
		close(fd2[1]);
		execlp("wc", "", "-l", NULL);
		printf("3 exec falhou\n");
		exit(-1);
	}
	
	else{
		printf("Pai 1\n");
		wait(NULL);
		printf("Pai 2\n");
		close(fd2[0]);
		close(fd2[1]);
	}
}
