#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/wait.h>

#define SIZE_NOME 50
#define NUM_DISCIPLINAS 10

typedef struct{
	int numero;
	char nome[SIZE_NOME];
	int disciplinas[NUM_DISCIPLINAS];
	int flag;					//flag para verificar se o aluno ja foi escrito. 
								//Flag esta que vai serir como se fosse um semaforo 
								//onde o filho espera que o pai termine de escrever 
								//os dados do aluno na memoria partilhada 
}aluno;

int main(void){
	int fd,data_size=sizeof(aluno),nota,soma=0,max=-999,min=999,num;
	char nome[SIZE_NOME];
	aluno *alunos;
	if((fd=shm_open("/ex08",O_CREAT|O_EXCL|O_RDWR,
			S_IRUSR|S_IWUSR))==-1){
		perror("ERRO AO CRIAR A MEMORIA PARTILHADA");
		exit(EXIT_FAILURE);
	}
	if(ftruncate(fd,data_size)==-1){
		perror("ERRO AO ALOCAR ESPACO NA MEMORIA");
		exit(EXIT_FAILURE);
	}
	if((alunos=(aluno*)mmap(NULL,data_size,
					PROT_READ|PROT_WRITE,MAP_SHARED,fd,0))==MAP_FAILED){
		perror("ERRO AO MAPEAR O OBJETO NA MEMORIA");
		exit(EXIT_FAILURE);
	}
	alunos->flag=0;
	pid_t pid;
	if((pid=fork())!=-1){
		if(pid==0){
			while(alunos->flag!=1);						//espera que o pai escreva na memoria partilhada
			int j;
			for( j=0;j<NUM_DISCIPLINAS;j++){
				soma=soma+alunos->disciplinas[j];
				if(alunos->disciplinas[j]>max){
					max=alunos->disciplinas[j];
				}
				if(alunos->disciplinas[j]<min){
					min=alunos->disciplinas[j];
				}
			}
			float media=soma/NUM_DISCIPLINAS;
			printf("O ALUNO %s (%d) POSSUI:\n",alunos->nome,alunos->numero);
			printf("MEDIA: %0.2f\n",media);
			printf("NOTA MAIS ALTA: %d\n",max);
			printf("NOTA MAIS BAIXA: %d\n",min);
		}else{
			printf("NOME DO ALUNO:\n");
			fgets(nome,255,stdin);
			int tamanho = strlen(nome);
			nome[tamanho-1] = '\0'; // remover o /n
			strcpy(alunos->nome,nome);
			printf("NUMERO DO ALUNO:\n");
			scanf("%d",&num);
			alunos->numero=num;
			int i;
			for( i=0;i<NUM_DISCIPLINAS;i++){
				do{
					printf("INSIRA A %dº NOTA\n",i+1);
					scanf("%d",&nota);
				}while(nota<0 || nota>20);
				alunos->disciplinas[i]=nota;
			}
			alunos->flag=1;							//sinaliza que o pai acabou de escrever
			wait(NULL);
			int r=munmap(alunos,data_size);
			if(r<0){
				perror("ERRO AO DESMAPEAR O OBJETO DA MEMORIA");
				exit(EXIT_FAILURE);
			}
			close(fd);
			r=shm_unlink("/ex08");
			if(r<0){
				perror("ERRO AO REMOVER DO SISTEMA");
				exit(EXIT_FAILURE);
			}
		}
	}
	return 0;
}
