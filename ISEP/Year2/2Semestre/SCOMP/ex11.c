#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <wait.h>
#include <time.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <semaphore.h>
#include <pthread.h>
#define PROVAS 300
#define THREADS 5
#define BOUND 50

typedef struct{
	int number;
	int notaG1;
	int notaG2;
	int notaG3;
	int notaFinal;
}Prova;

Prova provas[PROVAS] ;

int toCalculate=0;
int calculated=0;
int readIndex=0;
int incremented=0;
int posToIncrement=0;
int negToIncrement=0;
int pos=0,neg=0;

pthread_cond_t inserted;
pthread_cond_t positive;
pthread_cond_t negative;
pthread_mutex_t mutex;
pthread_mutex_t increment;

void *generate_provas(void * arg){
		time_t t;
		srand ((unsigned) time (&t));
		int i;
		for(i=0;i<PROVAS;i++){
			
			provas[i].number=i;
			provas[i].notaG1= rand()%100 +1 ;
			provas[i].notaG2= rand()%100 +1;
			provas[i].notaG3= rand()%100 +1;
			
			pthread_mutex_lock(&mutex); 
			toCalculate++;
			pthread_cond_broadcast(&inserted);  
			
			pthread_mutex_unlock(&mutex);
		}	
	
		pthread_exit(NULL);
}
void *calculate_notaFinal(void * arg){
	
	while(calculated<PROVAS){
		pthread_mutex_lock(&mutex);
		
		while(toCalculate==0){
			if(calculated == PROVAS){	//if not,check if the total processed elements is not already 300,if so,unlock the mutex(to avoid problemns destructing it) and exits.
					pthread_mutex_unlock(&mutex);
					pthread_exit(NULL);
			}	
			pthread_cond_wait(&inserted, &mutex);
		}
		
		int localIndex=readIndex++;
		toCalculate--;
		calculated++;
		pthread_mutex_unlock(&mutex);
		
		int nota =(provas[localIndex].notaG1+provas[localIndex].notaG2+provas[localIndex].notaG3)/3;
		provas[localIndex].notaFinal=nota;
		
		pthread_mutex_lock(&increment);
		
		if(nota>=BOUND){
			posToIncrement++;
			pthread_cond_signal(&positive);
		}else{
			negToIncrement++;
			pthread_cond_signal(&negative);
		}
		
		pthread_mutex_unlock(&increment);
		
	}
	
	pthread_exit(NULL);
}

void *increment_pos(void * arg){
	while(incremented<PROVAS){
		pthread_mutex_lock(&increment);
		while(posToIncrement==0){
			if(calculated==PROVAS){		//if not,check if the other thread did not update the total of elements processed and all of them are 
					pthread_mutex_unlock(&increment);    //if so,unlock the incrementation mutex and exit
					pthread_exit(NULL);
			}
			pthread_cond_wait(&positive,&increment);
		}
		pos++;
		incremented++;
		posToIncrement--;
		pthread_mutex_unlock(&increment);
	}
	pthread_cond_signal(&negative);
	pthread_exit(NULL);
}
void *increment_neg(void * arg){
	while(incremented<PROVAS){
		pthread_mutex_lock(&increment);
		while(negToIncrement==0){
			if(calculated==PROVAS){		//if not,check if the other thread did not update the total of elements processed and all of them are 
					pthread_mutex_unlock(&increment);    //if so,unlock the incrementation mutex and exit
					pthread_exit(NULL);
			}
			pthread_cond_wait(&negative,&increment);
		}
		neg++;
		incremented++;
		negToIncrement--;
		pthread_mutex_unlock(&increment);
	}
	pthread_cond_signal(&positive);
	pthread_exit(NULL);
}

int main(){
	int i;
	pthread_t t[5];
	
		if(pthread_mutex_init(&mutex, NULL)!=0){
			perror("Fail on mutex creation\n");
			return -1;
		}
		if(pthread_mutex_init(&increment, NULL)!=0){
			perror("Fail on mutex creation\n");
			return -1;
		}
		
		if(pthread_cond_init(&inserted,NULL)!=0){ 
			perror("Fail on condition variable creation\n");
			return -1;
		}
		
		if(pthread_cond_init(&positive,NULL)!=0){ 
			perror("Fail on condition variable creation\n");
			return -1;
		}
		
		if(pthread_cond_init(&negative,NULL)!=0){ 
			perror("Fail on condition variable creation\n");
			return -1;
		}
		
		 if(pthread_create(&t[0],NULL,generate_provas,NULL)!=0){
				perror("Erro no pthread_create()\n");
				exit(0);
		 }
		 if(pthread_create(&t[1],NULL,calculate_notaFinal,NULL)!=0){
				perror("Erro no pthread_create()\n");
				exit(0);
		 }
		 if(pthread_create(&t[2],NULL,calculate_notaFinal,NULL)!=0){
				perror("Erro no pthread_create()\n");
				exit(0);
		 }
		 if(pthread_create(&t[3],NULL,increment_pos,NULL)!=0){
				perror("Erro no pthread_create()\n");
				exit(0);
		 }
		 if(pthread_create(&t[4],NULL,increment_neg,NULL)!=0){
				perror("Erro no pthread_create()\n");
				exit(0);
		 }
		 
		 for(i=0;i<5;i++){
			if(pthread_join(t[i],NULL)!=0){
				perror("Fail on thread destruction\n");
				return -1;
			}
		}
		
		if(pthread_mutex_destroy(&mutex)!=0){
			perror("Fail on mutex destruction\n");
			return -1;
		}
		
		if(pthread_mutex_destroy(&increment)!=0){
			perror("Fail on mutex destruction\n");
			return -1;
		}
		
		
		if(pthread_cond_destroy(&inserted)!=0){
			perror("Fail on condition variable destruction\n");
			return -1;
		}
		
		if(pthread_cond_destroy(&negative)!=0){
			perror("Fail on condition variable destruction\n");
			return -1;
		}
		
		if(pthread_cond_destroy(&positive)!=0){
			perror("Fail on condition variable destruction\n");
			return -1;
		}
		 
		 printf("POSITIVES : %.2f \nNEGATIVES : %.2f \n",(pos*100)/(float)PROVAS,(neg*100)/(float)PROVAS);
	return 0;
}
