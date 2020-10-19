#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

void prog(int *vec){
	int max,i,j;
	pid_t pid;
	for (i = 0; i < 5; i++) {
		pid=fork();
		if (pid== 0) {
			max = vec[i * 200];
			for (j = i * 200 + 1; j < i * 200 + 200; j++) {
				if (vec[j] > max) {
					max = vec[j];
				}
			}
			printf("Maximo parcial : %d\n",max);
			exit(max);
		}
		else{
			int pid1=0;
			waitpid(pid,&pid1,0);
			if(WEXITSTATUS(pid1)>max){
				max=WEXITSTATUS(pid1);
			}
			printf("Maximo : %d\n",max);
		}
	}
	pid=fork();
	int result[1000];
	if(pid<0){
		exit(-1);
	}
	if(pid==0){
		for(i=0;i<500;i++){
			result[i]=((int) vec[i]/max)*100;
			printf("Indice %d",i);
		}
	}
	else{
		int pid1=0;
		waitpid(pid,&pid1,0);
		for(i=500;i<1000;i++){
			result[i]=((int) vec[i]/max)*100;
			printf("Indice %d",i);
		}
	}
}

