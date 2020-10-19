#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

int lsTest(char *argv2){
	int status;
	pid_t pid=fork();
	
	if(pid==0){
		argv2=strcat("~/",argv2);
		int s=execlp("ls","ls",argv2,NULL);
		exit(s);
	}
	else{
		waitpid(pid,&status,0);
		status=WEXITSTATUS(status);
	}
	return status;
}

void printValor(int valor){
	if(valor ==1){
		printf("Ficheiro encontrado");
	}
	if(valor ==2){
		printf("Subdiretorio com erro");
	}
	if(valor ==3){
		printf("Nao existe");
	}
}
