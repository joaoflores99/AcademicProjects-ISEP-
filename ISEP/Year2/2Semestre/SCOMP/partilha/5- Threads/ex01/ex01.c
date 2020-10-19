/*
 * ex01.c
 *
 *  Created on: May 6, 2018
 *      Author: rafa
 */

#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <pthread.h>

void* thread_func(void *arg) {
	printf("Sou a thread %u e estou a executar\n", pthread_self());
	pthread_exit((void*) NULL);
}

int main() {
	pid_t pid = fork(); // aqui já tem 1 filho

	if (pid == 0) { // apenas o 1º filho executa este código
		fork(); // 2º filho (na verdade é o primeiro filho do filho anterior)

		pthread_t thread_id;
		pthread_create(&thread_id, NULL, thread_func, NULL); // apenas dois processos chegam nesta parte do código,
															 // portanto serão criadas duas threads
		pthread_join(thread_id, NULL);
	}
	fork(); // aqui são criados mais 3 filhos, um pelo pai,um pelo filho do pai, outro pelo filho do filho
	// portanto no total existem 5 processos excluindo o processo pai
	// como está implícito na criação de um processo a criação também de uma thread, no total serão criadas 7 threads, excluindo a
	// thread do processo pai

}




