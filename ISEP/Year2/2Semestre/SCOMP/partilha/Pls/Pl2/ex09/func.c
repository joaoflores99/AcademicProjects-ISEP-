#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h> 
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

void func(sales p[], int produtos[]){
	
	
	
	int i;
	int j=0;
	int aux;
	int temp;
	
	int fd[2];
	if(pipe(fd) == -1) {
		printf("Erro ao criar pipe \n");
		exit(-1);
	}
	
	
	int pid = spawn_children(10);
	
	
	if(pid != 0) {
		close(fd[0]);
		aux = (pid) * 5000;
		for(j=(pid-1)*5000; j<aux; j++) {
			if(p[j].quantity >= 20) {	
				temp = p[j].product_code;	
				if(write(fd[1] , &temp , sizeof(int)) == -1) {
					printf("Erro na escrita no pipe  na posição/n");
				}
			}
		}
		close(fd[1]);	
		exit(0);	
	} else {
		close(fd[1]);
		i=0;
		while(1) {
			if(read(fd[0] ,  &temp , sizeof(int)) == -1) {
				printf("Erro na leitura do pipe\n");
				break;
			} else {
				if(temp == produtos[i-1] && i != 0) {
					break;
				} else {
					produtos[i] = temp;
				}
				i++;	
			}
		}
		close(fd[0]);
		for(j=0; j<i; j++) {
			printf("Codigo do produto na posição %d = %d \n" , j , produtos[j]);
		}
	}
}
