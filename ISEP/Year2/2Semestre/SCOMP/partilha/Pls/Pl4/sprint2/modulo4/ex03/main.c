#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "string.h"
#include <unistd.h>
#include <sys/types.h>
#include <semaphore.h>
#include <time.h>
#include <stdlib.h>

#define NOME_MEMORIA_PARTILHADA "ex03"
#define NUMERO_PALAVRAS 50
#define TAMANHO_PALAVRA 80

int main(void) {
	sem_t *semaforo= sem_open("sem3", O_CREAT, 0644, 1);
	if (semaforo == NULL) { 
		perror("Erro ao abrir o semáforo");
	}
	
	sem_wait(semaforo);
	int tamanho_memoria_partilhada = NUMERO_PALAVRAS * TAMANHO_PALAVRA; 
	char *memoria;
	int descritor = shm_open(NOME_MEMORIA_PARTILHADA,O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
	
	if (descritor< 0) {
		perror("Erro ao criar a memória partilhada\n");
	}
	if (ftruncate(descritor, tamanho_memoria_partilhada) < 0) {
		perror("Erro ao definir um tamanho para a memória partilhada\n");
	}
	memoria = mmap(NULL, tamanho_memoria_partilhada,PROT_READ | PROT_WRITE,MAP_SHARED, descritor, 0) ;
	if (memoria== MAP_FAILED) {
		perror("Erro ao mapear o objeto em memória\n");
	
	}
	srand((unsigned) time(NULL));

	if(*(memoria+NUMERO_PALAVRAS*TAMANHO_PALAVRA)!='\0'){ 
		if(close(descritor)<0){
			perror("Erro ao fechar\n");
		}else{
			if(shm_unlink(NOME_MEMORIA_PARTILHADA)<0){
				perror("Erro ao apagar a zona de memória partilhada\n");
		
		}
	}
		return 0; 
	}
	sprintf(memoria, "%sI’m the Father - with PID %d\n", memoria, getpid());
	sleep(rand() % 5 + 1);
	sem_post(semaforo);
	return 0;
}

