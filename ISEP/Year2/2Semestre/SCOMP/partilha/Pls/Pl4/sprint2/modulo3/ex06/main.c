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
			wait(0); 
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
	clock_t inicio;
	clock_t fim;
	double tempo_total;
	int fd,n,i,maxTotal=-1,max;
	int j;
	pid_t pid[10];
	
	int vec[1000];
	for(i=0;i<1000;i++){
		//vec[i]=bet();
		vec[i]=i;
	}
	
	estrutura *addr=NULL;
	fd=shm_open("/shmtest",O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR);
		if(fd==-1){
			printf("Erro no fd\n");
			exit(-1);
		}
	ftruncate(fd,sizeof(estrutura));
	addr=(estrutura*) mmap(NULL,sizeof(estrutura),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
	if(addr==MAP_FAILED){
			printf("Erro no mapeamento\n");
			exit(-1);
	}
	inicio = clock();
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
		
		if(addr==MAP_FAILED){
			printf("Erro no mapeamento\n");
			exit(-1);
		}
		
		for(j=0;j<10;j++){
			if(maxTotal<addr->array[j]){
				maxTotal=addr->array[j];
			}
		}
		fim = clock();

		tempo_total =  (fim - inicio) / (double) CLOCKS_PER_SEC * 1000 * 1000;
		printf("\n Max total: %d\n",maxTotal);
		printf("Demorei %.2f microsegundos\n", tempo_total);
		n=munmap(addr,sizeof(estrutura));
		if(n==-1){
			printf("Erro no nummap\n");
			exit(-1);
		}
		
	}
	
	else{
		max = vec[i *100];
		for (j = i * 100 + 1; j < i * 100 + 100; j++) {
			if (vec[j]>max) {
				max = vec[j];
			}
		}
		printf("Max %d : %d\n",i,max);
		addr->array[i]=max;
		n=munmap(addr,sizeof(estrutura));
		if(n==-1){
			printf("Erro no nummap\n");
			exit(-1);
			}
		exit(0);
	}

	shm_unlink("/shmtest");
	
	return 0;
}
