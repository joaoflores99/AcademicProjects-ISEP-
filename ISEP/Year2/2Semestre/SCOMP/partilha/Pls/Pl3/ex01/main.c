#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "string.h"
#include <unistd.h>
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

int main(){
	int fd;
	estrutura *addr=NULL;
	
	pid_t pid=spawn_childs(2);
	
	if(pid==1){
		fd=shm_open("/shmtest",O_CREAT|O_EXCL|O_RDWR, S_IRUSR|S_IWUSR);
		ftruncate(fd,sizeof(estrutura));
		addr=(estrutura*) mmap(NULL,sizeof(estrutura),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
		if(addr!=MAP_FAILED){
			addr->numero=1;
			strcpy(addr->nome,"jose");
		}
	}
	if(pid==2){
		fd=shm_open("/shmtest",O_EXCL|O_RDWR, S_IRUSR|S_IWUSR);
		ftruncate(fd,sizeof(estrutura));
		addr= (estrutura *)mmap(NULL,sizeof(estrutura),PROT_READ|PROT_WRITE,MAP_SHARED,fd,0);
	
		if(addr!=MAP_FAILED){
			printf("Nome %s\n",addr->nome);
			printf("Numero %d\n",addr->numero);
		}
	}
	if(pid==0){
		shm_unlink("/shmtest");
	}
	return 0;
}
