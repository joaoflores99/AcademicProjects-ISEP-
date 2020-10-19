#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h> 
#include "func.h"


void switchDescriptor(int my_descriptor,int descriptor_to_redirect){
	if(dup2(my_descriptor,descriptor_to_redirect)<0){
		exit(-1);
	}
}

void func(){

	int fd[2]; 			
	int num,fact;
	
	
	if(pipe(fd) == -1){
		perror("ERRO AO CRIAR O PIPE.\n");
		exit(-1);
	}
	
	pid_t pid = fork();
	
	if (pid < 0){
		perror("ERRO AO CRIAR O PROCESSO FILHO.\n");
		exit(-1);
	}
	
	//pai
    if (pid > 0) 	{
		
		 printf("INSIRA UM NUMERO:\n");
		 scanf("%d",&num);
		 
		 if(write(fd[1],&num,sizeof(int))<0){
			perror("ERRO AO ESCREVER NO PIPE.\n");
			exit(-1);
		 }
		 
		 sleep(2);
		 
		 if(read(fd[0],&fact,sizeof(int))<0){
			 perror("ERRO AO LER NO PIPE.\n");
			exit(-1);
		 }
		 printf("O RESULTADO DO FACOTRIAL DE %d E: %d.\n",num,fact);
		 close(fd[1]);
		 close(fd[0]);
		
		wait(NULL);
		
	// filho
	} else{			
/**
		alterar os descritores do processo que vou chamar para assim poderem ser usados no programa fatorial onder 
	 	sera passado o valor lido e sera retornado o fatorial do mesmo
**/
		 switchDescriptor(fd[0],STDIN_FILENO);
		 switchDescriptor(fd[1],STDOUT_FILENO);
		 execlp("fatorial/app","app",NULL);		
	}
}
