#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>
#include <wait.h>
#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "string.h"
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/types.h>

#define BUFFER_SIZE 10
 
typedef struct {
    int counter;
    int array[BUFFER_SIZE];
}buffer;

int main() {
	int w,r;
	int fd2[2];
	buffer * buff=NULL;
    //int size = sizeof(buff);
    int i = 0;
    int in = 0;
    int out = 0;   
    
    int p=pipe(fd2);
    if(p==-1){
		printf("Erro no pipe\n");
		exit(-1);
	}
    
    /*fd = shm_open("/ex10", O_CREAT | O_EXCL|O_RDWR, S_IRUSR|S_IWUSR);
    if(fd<0){
		perror("ERRO AO CRIAR A ZONA DE MEMORIA PARTILHADA");
		exit(EXIT_FAILURE);
	}
	if(ftruncate (fd, size)<0){
		perror("ERRO AO DEFINIR O TAMAMNHO DA ZONA DE MEMORIA PARTILHADA ");
		exit(EXIT_FAILURE);
	}  
    buff = (buffer*) mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);    
    if(buff<0){
		perror("ERRO AO MAPEAR O OBJETO NA MEMORIA PARTILHADA ");
		exit(EXIT_FAILURE);
	}*/
	
    buff->counter = 0;  
    pid_t pid=fork();
    if(pid<0){
		perror("ERRO CRIAR O PROCESSO FILHO ");
		exit(EXIT_FAILURE);
	}
     
    if (pid == 0) {      
        int nextConsumed; 
        r=read(fd2[0],buff,sizeof(buffer));
		if(r==-1){
				printf("Erro na leitura");
		}
        while (i < 30) {            
            while((buff->counter) == 0);           
            nextConsumed = buff->array[out];
            printf("ESTOU NA POSICAO %d E LI O NUMERO %d\n", i,nextConsumed);
            out = (out + 1) % BUFFER_SIZE;
            (buff->counter)--;
            i++;
        }       
        exit(0);       
    } else {   
        int nextProduced;   
        w=write(fd2[1],buff,sizeof(buffer));
		if(w==-1){
			printf("Erro de escrita");
		}     
        while(i < 30) {            
            nextProduced = i + 1;            
            while ((buff->counter) == BUFFER_SIZE);            
            buff->array[in] = nextProduced;
            in = (in + 1) % BUFFER_SIZE;
            (buff->counter)++;
            i++;   
        }
        wait(NULL);
    }    
    /*rm = munmap(buff, size);
    if (rm < 0) {
       perror("ERRO AO DESMAPEAR O OBJETO DA MEMORIA\n");
	   exit(EXIT_FAILURE);
    }    
    rm = close(fd);
    if (remove < 0) {
        perror("ERRO AO FECHAR O DESCRIPTOR\n");
		exit(EXIT_FAILURE);
    }    
    rm = shm_unlink("/ex10");
    if (rm < 0) {       
		perror("ERRO AO FECHAR A ZONA DE MEMORIA PARTILHADA\n");
		exit(EXIT_FAILURE);
	}*/
         
    return 0;
}
