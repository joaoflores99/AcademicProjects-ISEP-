#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

#define SOCIOS 100
char mocao[SOCIOS];
char respostas[SOCIOS];
int sins=0,naos=0;
int readIndex=0;
int iniciada=0,terminada=0;
int votaram;

pthread_mutex_t controlo_votacao;
pthread_mutex_t controlo_respostas;

pthread_cond_t votacao;
pthread_t socios[SOCIOS];
int votou=1,nao_votou=0;

void *socio_voto(void *arg){
	time_t t;
	srand(time(&t));
	
		pthread_mutex_lock(&controlo_votacao);
		while(iniciada==0){
			if(terminada==1){
				pthread_exit((void*)nao_votou);
			}
			pthread_cond_wait(&votacao,&controlo_votacao);
		}
		printf("%s\n",mocao);
		
		int resposta=rand()%1;
		int localIndex=readIndex++;
		
		
		if(resposta==0){
			respostas[localIndex]='N';
			naos++;
		}else{
			respostas[localIndex]='S';
			sins++;
		}
		
		pthread_mutex_unlock(&controlo_votacao);	
		
		pthread_exit((void*)votou);
	
}

void *gerar_threads(void *arg){
	int i;
	void *ret;
	
	for(i=0;i<SOCIOS;i++){
		pthread_create(&socios[i],NULL,socio_voto,NULL);
		sleep(2);
	}
	
	for(i=0;i<SOCIOS;i++){
	 if (pthread_join(socios[i],&ret) != 0) {
			perror("Erro no pthread_join()!\n");
			exit(0);
		}
		
		int n = (int)ret;
		if(n==1){
			votaram++;
		}	
	}
	
	pthread_exit(NULL);
}

int main(){
	pthread_t thread;
		
	if(pthread_mutex_init(&controlo_votacao, NULL)!=0){
		perror("Fail on mutex creation\n");
		return -1;
	}
	
	if(pthread_mutex_init(&controlo_respostas, NULL)!=0){
		perror("Fail on mutex creation\n");
		return -1;
	}

	if(pthread_cond_init(&votacao,NULL)!=0){ 
		perror("Fail on condition variable creation\n");
		return -1;
	}
	
	printf("Escreva a moção para a votação\n");
	fgets(mocao,100,stdin);
	
	if(pthread_create(&thread,NULL,gerar_threads,NULL)!=0){ 
		perror("Fail on thread creation\n");
		return -1;
	}
	printf("VOTAÇÃO INICIADA\n");
	iniciada=1;
	pthread_cond_broadcast(&votacao);
	sleep(120);
	printf("VOTAÇÃO TERMINADA\n");
	
	pthread_mutex_lock(&controlo_votacao);
	terminada=1;
	iniciada=0;
	pthread_mutex_unlock(&controlo_votacao);
	
	printf("Votos : %d\nVotos 'SIM' : %d\nVotos 'NÃO' : %d\n",votaram,sins,naos);
	
	if(pthread_mutex_destroy(&controlo_votacao)!=0){
		perror("Fail on mutex destruction\n");
		return -1;
	}
	
	if(pthread_mutex_destroy(&controlo_respostas)!=0){
		perror("Fail on mutex destruction\n");
		return -1;
	}
	
	if(pthread_cond_destroy(&votacao)!=0){
		perror("Fail on condition variable destruction\n");
		return -1;
	}
	
	return 0;
}
