#include <stdio.h>
#include <sys/mman.h>
#include <sys/stat.h> 
#include <fcntl.h> 
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <time.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/wait.h>


int main(void){
int fd[2];
int vec[1000000];
int vec2[1000000];
int i;
int temp;
pid_t pid;
	srand((unsigned) time (NULL));
	for(i=0;i<1000000;i++){
		vec[i]= rand()%50;
		vec2[i]= rand()%50;
	}
	
	if(pipe(fd) == -1){
		perror("ERRO NA CRIACAO DO PROCESSO FILHO");
		exit(EXIT_FAILURE);
	}
	
	int ret,data_size = sizeof(int);
	int *shared_data;
	ret = shm_open("/shmtest", O_CREAT|O_EXCL|O_RDWR,S_IRUSR|S_IWUSR);
	if(ret ==-1){
		perror("ERRO AO CRIAR A MEMORIA PARTILHADA");
		exit(EXIT_FAILURE);
	}
	int f=ftruncate (ret,data_size);
	if(f==-1){
		perror("ERRO AO MAPEAR O OBJETO NA MEMORIA");
		exit(EXIT_FAILURE);
		}
	shared_data = (int*)mmap(NULL,data_size,PROT_READ|PROT_WRITE,MAP_SHARED,ret,0);
	if(shared_data  == NULL){
		perror("ERRO AO MAPEAR O OBJETO NA MEMORIA");
		exit(EXIT_FAILURE);
	}
	//------------ INICIO PIPES------------//
	pid = fork();
	if (pid<0){
		perror("ERRO NA CRIACAO DO PROCESSO FILHO");
		exit(EXIT_FAILURE);
	}
	//filho
	if(pid==0){
		
		for(i=0;i<1000000;i++){
			if(read(fd[0],&temp,sizeof(int))==-1){
				perror("ERRO NA LEITURA DO PIPE");
				exit(EXIT_FAILURE);
			}
			vec[i]=temp;
		}
		
		exit(0);
	//pai
	} else{
		
		clock_t inicioPipe = clock();
		for(i=0;i<1000000;i++){
			if(write(fd[1],&vec[i],sizeof(int))==-1){
				perror("ERRO NA ESCRITA DO PIPE");
				exit(EXIT_FAILURE);
			}
		}
		wait(0);
		clock_t fimPipe = clock();
		double resPipe = (double)(fimPipe-inicioPipe)/CLOCKS_PER_SEC;
		printf("AO UTILIZAR PIPE DEMOROU %.4f SEGUNDOS\n",resPipe);
		
	}
//------------------FIM PIPES-----------------------//	
	


//----------------INICIO MEMORIA PARTILHADA----------------//

	pid = fork();
	if (pid<0){
		perror("ERRO NA CRIACAO DO PROCESSO FILHO");
		exit(EXIT_FAILURE);
	}
	if(pid==0){
		
		for(i=0;i<10;i++){
			vec2[i]=*(shared_data);
		}	
		exit(0);
	} else{	
		clock_t inicioMem = clock();
		for(i=0;i<10;i++){
			*(shared_data+i)=vec2[i];
		}
		wait(0);
		clock_t fimMem = clock();
		double resMem = (double)(fimMem-inicioMem)/ CLOCKS_PER_SEC;
		printf("AO UTILIZAR A MEMORIA PARTILHADA DEMOROU %.19f SEGUNDOS\n ",resMem);
		
	}
	
	munmap(shared_data, data_size); 
	shm_unlink("/shmtest");
return 0;
	}
