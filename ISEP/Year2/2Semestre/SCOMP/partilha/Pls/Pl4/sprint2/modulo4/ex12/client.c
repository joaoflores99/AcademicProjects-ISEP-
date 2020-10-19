#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h> 
#include <unistd.h>	      
#include <sys/types.h>	
#include <fcntl.h>	       
#include <sys/mman.h>	
#include <sys/stat.h>  
#include <wait.h>
#include <string.h>
#include <semaphore.h>
#include <time.h>
#include "ex12.h"


int main(){
	int queue;
	Client *clients;
	sem_t *serving;
	sem_t *count;
	sem_t *exclusive;
	sem_t *next_client;
	srand(time(NULL));
	
	//Ćlient Queue and Tickets
	queue=shm_open("/clients",O_CREAT|O_EXCL|O_RDWR,S_IRUSR|S_IWUSR);
	if(queue==-1){
		perror("ERRO AO CRIAR A MEMORIA PARTILHADA");
		exit(EXIT_FAILURE);
	}
	if(ftruncate(queue,sizeof(Client))==-1){
		perror("ERRO AO AO DEFINIR O TAMANHO DA MEMORIA PARTILHADA");
		exit(EXIT_FAILURE);
	}
	
	clients=(Client*)mmap(NULL, sizeof(Client), PROT_READ|PROT_WRITE, MAP_SHARED, queue, 0);	
	if(clients<0){
		perror("ERRO AO MAPEAR O OBJETO NA MEMORIA PARTILHADA");
		exit(EXIT_FAILURE);
	}
	//Semaphores
	serving = sem_open("serving", O_CREAT | O_EXCL, 0644, 1);
    if (serving == SEM_FAILED){
        perror("ERRO AO CRIAR O SEMAFORO");
	   exit(EXIT_FAILURE);
    }
    
   count= sem_open("clear ", O_CREAT | O_EXCL, 0644, 1);
    if (count == SEM_FAILED){
       perror("ERRO AO CRIAR O SEMAFORO");
	   exit(EXIT_FAILURE);
    }
    
    exclusive = sem_open("exclusive", O_CREAT | O_EXCL, 0644, 1);
    if (exclusive == SEM_FAILED){
       perror("ERRO AO CRIAR O SEMAFORO");
	   exit(EXIT_FAILURE);
    }
    
    next_client = sem_open("next_client", O_CREAT | O_EXCL, 0644, 1);
    if (next_client == SEM_FAILED){
        perror("ERRO AO CRIAR O SEMAFORO");
	   exit(EXIT_FAILURE);
    }
    
    sem_wait(exclusive);//
    clients->numClient++;//Exclusividade
    int client=clients->numClient;
    sem_post(exclusive);//
    
    sem_post(serving); //Faz pedido 
    sem_wait(count); //Espera resposta
    sem_wait(exclusive);//
    int ticket=clients->numTicket;//Exclusividade
	sem_post(exclusive);
    
    if(ticket==-1){
		printf("NAO EXISTEM TICKTS DISPONIVEIS\n");
		exit(0);
	}
    printf("Client %d -> Ticket %d\n",client,ticket);    
    sem_post(next_client);
    return 0;
}
