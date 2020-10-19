#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "string.h"
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/types.h>
#include "asm.h"

int spawn_childs(int num){
	int i;
	for(i=0;i<num;i++) { 
        if(fork() == 0) { 
            return (i+1);
        } else {
			//wait(0); 
		} 
	}
	return(0);
}

int bet(){
	time_t t;
	srand((unsigned) time (&t));
	return rand () % 1001;
}

int average(estrutura* e){
	int i,valor=0;
	for(i=0;i<10;i++){
		valor = valor + e->array[i];
	}
	return valor/10;
}


int main(){
	int i,maxTotal=-1,max;
	int fd2[2];
	int j,w,r;
	pid_t pid[10];
	
	int vec[1000];
	for(i=0;i<1000;i++){
		//vec[i]=bet();
		vec[i]=i;
	}
	
	int p=pipe(fd2);
	if(p==-1){
		printf("Erro no pipe\n");
		exit(-1);
	}
	estrutura e1;
	estrutura *addr=&e1;
	
	if(addr==MAP_FAILED){
			printf("Erro no mapeamento\n");
			exit(-1);
	}
	
	for(i=0;i<10;i++){
		pid[i]=fork();
		if(pid[i]<0){
			printf("ERRO fork\n");
			exit(-1);
		}
		if(pid[i]==0){
			break;
		}
	}
	
	if(pid[i]>0){
		for(j=0;j<10;j++){
			wait(0);
		}
		r=read(fd2[0],addr,sizeof(addr));
		if(r==-1){
				printf("Erro na leitura");
		}
		
		for(j=0;j<10;j++){
			if(maxTotal<addr->array[j]){
				maxTotal=addr->array[j];
			}
		}
		
	}
	
	else{
		max = vec[i *100];
		for (j = i * 100 + 1; j < i * 100 + 100; j++) {
			if (vec[j]>max) {
				max = vec[j];
			}
		}
		addr->array[i]=max;
		printf("Max %d : %d\n",i,max);
		w=write(fd2[1],addr,sizeof(addr));
		if(w==-1){
			printf("Erro de escrita");
		}
		exit(0);
	}
	
	printf("\n Max total: %d\n",maxTotal);
	
	return 0;
}
