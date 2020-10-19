#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/wait.h> 
#include "func.h"
void func(){
	
	char username[80];
	char password[80];
	int fd[2];
	char x[20];
	int bytes;
	
	if(pipe(fd) == -1) {
		perror("ERRO NA CRIACAO DO PIPE\n");
	}
	
	
	printf("INSIRA O SEU USERNAME: \n");
	scanf("%s" , username);
	printf("INSIRA A SUA PALAVRA-PASSE: \n");
	scanf("%s" , password);
	
	pid_t pid = fork();
	if(pid<0){
		perror("ERRO NA CRIACAO DO PROCESSO FILHO");
	}
	//filho
	if(pid == 0) {
		close(fd[0]);
		dup2(fd[1], 1);
		execlp("./Validate/validate" , "./validate", username , password , NULL);
	//pai	
	} else { 
		close(fd[1]);
		bytes=read(fd[0],x,20);
		if(bytes == -1) {
			perror("ERRO NA LEITURA DO PIPE\n");
		}else{
			x[bytes - 1] = 0;
			if(strcmp(x , "3 ") == 0) {
				printf("UTILIZADOR NAO REGISTADO NO SISTEMA\n");
			}
			if(strcmp(x , "2 ") == 0) {
				printf("PALAVRA-PASSE INVALIDA\n");
			}
			if(strcmp(x , "1 ") == 0) {
				printf("LOGIN REALIZADO COM SUCESSO\n");
			}			
		}
		close(fd[0]);
	}
	
}
