#include<stdio.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char *argv[]) {
	int num = 100;
	pid_t pid;
	int fd, data_size = sizeof(int);
	int *addr;
	int i;

	fd = shm_open("/ex04", O_CREAT | O_EXCL | O_RDWR,S_IRUSR | S_IWUSR);					//criar memoria partilhada
	if (ftruncate(fd, data_size) != -1) {
		addr = (int *) mmap(NULL, data_size, PROT_READ | PROT_WRITE,MAP_SHARED, fd, 0);				
		if (addr != MAP_FAILED) {					
			*addr = num;					
		}
	} else {
		perror("ERRO AO CRIAR A MEMORIA PARTILHADA");
		exit(-1);
	}
	
	pid=fork();
	if (pid == -1) {	
		perror("ERRO AO CRIAR O PROCESSO FILHO");
	}else{	
	//filho								
		if (pid == 0) {								
			for (i =0; i < 1000000; i++) {
				*addr = *addr + 1;
				*addr = *addr - 1;
			}
			printf("FILHO: %d\n", *addr); //faz as somas e as subtraçoes desejadas e imprime o resultado final
			exit(EXIT_SUCCESS);
	//pai
		}else{										
			for (i = 0; i < 1000000; i++) {		
				*addr = *addr + 1;
				*addr = *addr - 1;
			}
			printf("PAI: %d\n", *addr);

			int r = munmap(addr, data_size);		//remove a memoria partilhada
			if (r < 0) {
				exit(1);
			}
			close(fd);
			r = shm_unlink("/ex04");
			if (r < 0) {
				exit(1);
			}									//elimina a memoria partilhada
		}
	} 
	
	return 0;
//o resultado não se encontra sempre correto devido ao facto de os processos estarem a tentar aceder
//a memoria partilhada ao mesmo tempo e acederem a valores do outro ciclo
}

