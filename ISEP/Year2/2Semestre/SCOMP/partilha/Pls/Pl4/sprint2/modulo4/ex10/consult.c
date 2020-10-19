#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/wait.h>
#include "semaphore.h"
#include "ex10.h"

int main(void) {
    
    int des;
    int des2;
    int tamanho_area_partilhada = sizeof(dados)* ARMAZENAMENTO;
    dados *data;
    int *contador;
    sem_t *sem_excl;
    
    des = shm_open(NOME_FICHEIRO_MEMORIA, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    if (des< 0) {
        perror("ERRO AO CRIAR A MEMORIA PARTLHADA");
        exit(EXIT_FAILURE);
    }
    
    if (ftruncate(des, tamanho_area_partilhada) < 0) {
        perror("ERRO AO DEFINIR O TAMANHO DA MEMORIA PARTILHADA");
        exit(EXIT_FAILURE);
    }
    
    des2 = shm_open(NOME_FICHEIRO_CONTADOR, O_CREAT | O_RDWR,S_IRUSR | S_IWUSR);
    if ( des2< 0) {
          perror("ERRO AO CRIAR A MEMORIA PARTLHADA");
        exit(EXIT_FAILURE);
    }
    
    if (ftruncate(des2, sizeof(int)) < 0) {
       perror("ERRO AO DEFINIR O TAMANHO DA MEMORIA PARTILHADA");
        exit(EXIT_FAILURE);
    }
    
    contador = (int *) mmap(NULL, sizeof(int),PROT_READ | PROT_WRITE,MAP_SHARED, des2, 0);
    if (contador== MAP_FAILED) {
        perror("ERRO AO MAPEAR O OBJETO NA MEMORIA");
        exit(EXIT_FAILURE);
    }
    
    data = (dados *) mmap(NULL, tamanho_area_partilhada,PROT_READ | PROT_WRITE,MAP_SHARED, des, 0);
    if (data== MAP_FAILED) {
        perror("ERRO AO MAPEAR O OBJETO NA MEMORIA");
        exit(EXIT_FAILURE);
    }
    sem_excl = sem_open(SEMAFORO_EXCLUSIVO, O_CREAT, 0644,1);
    if(sem_excl==NULL){
        perror("ERRO AP CRIAR O SEMAFORO");
        exit(EXIT_FAILURE);
    }
    
    int num_mec;
    sem_wait(sem_excl);
    printf("Introduza o número mecanográfico da pessoa que deseja visualizar os dados\n");
    scanf("%d", &num_mec);
    int i;
    for ( i = 0; i < *(contador); ++i) {
        if(data[i].numero==num_mec){
            printf("NOME: %s| NUMERO: %d | MORADA: %s",data[i].nome,data[i].numero,data[i].morada);
            return 0;
        }
    }
    printf("Não foi encontrado nenhum registo com esse número mecanográfico\n");
    sem_post(sem_excl);
    return 0;
}
