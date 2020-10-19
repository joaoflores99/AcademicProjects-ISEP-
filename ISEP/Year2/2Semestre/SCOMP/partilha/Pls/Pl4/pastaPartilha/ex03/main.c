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


const char* NOME_MEMORIA_PARTILHADA = "ex03";
const char* SEMAFORO = "sem3";
const int NUMERO_PALAVRAS = 50;
const int TAMANHO_PALAVRA = 80;

int main(void) {
	sem_t *semaforo;
	if ((semaforo = sem_open(SEMAFORO, O_CREAT, 0644, 1)) == NULL) { // começar a 1, para garantir exclusão mútua
		perror("ERRO AO ABRIR O SEMAFORO\n");
		exit(EXIT_FAILURE);
	}
	
	sem_wait(semaforo); // apenas 1 processo pode aceder à memória partilhada
	int descritor;
	int tamanho_memoria_partilhada = NUMERO_PALAVRAS * TAMANHO_PALAVRA;
	char *memoria;
	
	if ((descritor = shm_open(NOME_MEMORIA_PARTILHADA,O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) < 0) {
		perror("ERRO AO CRIAR A MEMORIA PARTILHADA\n");
		exit(EXIT_FAILURE);
	}
	if (ftruncate(descritor, tamanho_memoria_partilhada) < 0) {
		perror("ERRO AO DEFINIR O TAMANHO DA ZONA DE MEMORIA PARTILHADA\n");
		exit(EXIT_FAILURE);
	}
	if ((memoria = mmap(NULL, tamanho_memoria_partilhada,PROT_READ | PROT_WRITE,MAP_SHARED, descritor, 0)) == MAP_FAILED) {
		perror("ERRO AO MAPEAR O OBJETO EM MEMORIA\n");
		exit(EXIT_FAILURE);
	}
	srand((unsigned) time(NULL));

	if(*(memoria+NUMERO_PALAVRAS*TAMANHO_PALAVRA)!='\0'){ 
		close(descritor);
		if(shm_unlink(NOME_MEMORIA_PARTILHADA)<0){
			perror("ERRO AO APAGAR A ZONA DE MEMORIA PARTILHADA\n");
			exit(EXIT_FAILURE);
		}
		return 0; // terminar o processo
	}
	sprintf(memoria, "%sI’m the Father - with PID %d\n", memoria, getpid());
	sleep(rand() % 5 + 1);
	sem_post(semaforo);
	return 0;
}

