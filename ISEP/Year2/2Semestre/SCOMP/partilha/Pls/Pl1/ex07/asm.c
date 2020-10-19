#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

int prog(int *numbers,int n){
	pid_t pid;
	int i;
	int status;
	int t=0;
	pid=fork();
	int valor;
	if(pid<0){
		exit(1);
	}
	if(pid==0){
		for(i=0;i<1000/2;i++){
			if(*(numbers+i)==n){
				t++;
			}
		}
		exit(t);
	}else{
		wait(&status);
		valor=status;
		for(i=1000/2;i<1000;i++){
			if(*(numbers+i)==n){
				t++;
			}
		}
		status=WEXITSTATUS(valor);
		t=t+status;
	}
	return t;
}
