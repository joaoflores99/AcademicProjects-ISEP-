#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

void prog(){
	pid_t pid = fork();
	int status;
	if(pid==0){
		sleep(1);
		exit(1);
	}
	if(pid>0){
		waitpid(pid,&status,0);
		printf("Valor %d\n",WEXITSTATUS(status));
	}

	pid_t pid1= fork();
	
	if(pid1==0){
		sleep(2);
		exit(2);
	}
	if(pid1>0){
		waitpid(pid1,&status,0);
		printf("Valor %d\n",WEXITSTATUS(status));
	}

		
}
