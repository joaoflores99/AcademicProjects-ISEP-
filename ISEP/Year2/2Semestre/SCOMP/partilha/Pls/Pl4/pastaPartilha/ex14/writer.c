#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <unistd.h>
#include <sys/mman.h> 
#include <sys/stat.h>  
#include <fcntl.h>     
#include <semaphore.h>
#include "ex14.h"

int main(){
	
	time_t mytime;
	char* time_str;
	int fd=0;
	s *info;
	int size=sizeof(info);
	sem_t *semaforos[NUM_SEM];
	char*names[]={"torniquete","writer","excl1"};
	int values[NUM_SEM]={1,1,1};
	
	int i;
	for (i = 0; i < NUM_SEM; i++) {
		if ((semaforos[i] = sem_open(names[i], O_CREAT, 0644, values[i]))== SEM_FAILED) {
			perror("Erro a criar o semaforor\n");
			exit(1);
		}
	}
		
	if ((fd = shm_open(FILENAME,O_CREAT | O_RDWR,S_IRUSR | S_IWUSR)) < 0) {
		perror("Erro ao criar  a memória\n");
		exit(EXIT_FAILURE);
	}
	if (ftruncate(fd, size) < 0) {
		perror("Erro ao definir otamanho para a área de memória");
		exit(EXIT_FAILURE);
	}
	 if((info = mmap(NULL,size,PROT_READ|PROT_WRITE,MAP_SHARED, fd, 0))==MAP_FAILED){
		perror("Erro ao mapear o objeto em memória\n");
		exit(EXIT_FAILURE);
	}
	
	sem_wait(semaforos[2]);
	info->writers=info->writers+1;
	if(info->writers==1){
		sem_wait(semaforos[0]);
	}
	
	sem_post(semaforos[2]);
	sem_wait(semaforos[1]);
	info->pid=getpid();
	mytime=time(NULL);
    time_str = ctime(&mytime);
    time_str[strlen(time_str)-1] = '\0';
    info->pid=getpid();
    strcpy(info->timeinfo,time_str);
    strcpy(info->timeinfo,time_str);
    
	printf("Mumero de escritores :%d\n",info->writers);
	printf("numero de leitores :%d\n",info->readers);
	
	sem_wait(semaforos[2]);
	info->writers=info->writers-1;
	
	if(info->writers==0){
		sem_post(semaforos[0]);
	}
	sem_post(semaforos[2]);
	sem_post(semaforos[1]);
	return 0;
}
