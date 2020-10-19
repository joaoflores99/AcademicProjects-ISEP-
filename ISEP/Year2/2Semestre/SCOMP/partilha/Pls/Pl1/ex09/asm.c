#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>


void prog(){
	pid_t pid, f;
	int i,j,status,number;
	for(i = 0; i < 10; i++){
		pid=fork();
		if(pid==0){
			for (j = 0; j < 100; j++) {
				number = i * 100 + j + 1;
				printf("Numero %d \n", number);
			}
			printf("\n");
			exit(0);
		}
		else{
			wait(&pid);
		}
	}
	
}
