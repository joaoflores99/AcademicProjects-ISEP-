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

const char* NOME_MEMORIA_PARTILHADA = "ex04";
const char* SEMAFORO = "sem4";
const int NUMERO_PALAVRAS = 50;
const int TAMANHO_PALAVRA = 80;

int main(void) {
	sem_t *semaforo;
	if ((semaforo = sem_open(SEMAFORO, O_CREAT, 0644, 1)) == NULL) { // começar a 1, para garantir exclusão mútua
		perror("Erro ao abrir o semáforo\n");
		exit(EXIT_FAILURE);
	}
	/**
	 * Calcular intervalo relativo e adicionar mais 12 segundos
	 */
	struct timespec tempo;
	if (clock_gettime(CLOCK_REALTIME, &tempo) < 0) {
		perror("Erro ao calcular o intervalo atual\n");
		exit(EXIT_FAILURE);
	}
	tempo.tv_sec += 12; // adicionar 12 segundos
	if (sem_timedwait(semaforo, &tempo) < 0) { // apenas 1 processo pode agora aceder à memória partilhada
		perror(
				"Passaram os 12 segundos e não consegui aceder à memória partilhada\n");
		exit(EXIT_FAILURE);
	}

	int descritor;
	int tamanho_memoria_partilhada = NUMERO_PALAVRAS * TAMANHO_PALAVRA
			* sizeof(char); // sizeof(char)=1
	char *memoria;
	if ((descritor = shm_open(NOME_MEMORIA_PARTILHADA,
	O_CREAT | O_RDWR, S_IRUSR | S_IWUSR)) < 0) {
		perror("Erro ao criar a memória partilhada\n");
		exit(EXIT_FAILURE);
	}
	if (ftruncate(descritor, tamanho_memoria_partilhada) < 0) {
		perror("Erro ao definir um tamanho para a memória partilhada\n");
		exit(EXIT_FAILURE);
	}
	if ((memoria = mmap(NULL, tamanho_memoria_partilhada,
	PROT_READ | PROT_WRITE,
	MAP_SHARED, descritor, 0)) == MAP_FAILED) {
		perror("Erro ao mapear o objeto em memória\n");
		exit(EXIT_FAILURE);
	}
	char resposta[TAMANHO_PALAVRA];
	printf("Deseja remover alguma linha do ficheiro? (sim/nao)\n");
	scanf("%s", resposta);
	if (!strcmp(resposta, "sim")) { // se for sim
		int numero_linha;
		char memoria2[NUMERO_PALAVRAS * TAMANHO_PALAVRA];
		printf("%s", memoria);
		printf("Introduza o número da linha que deseja remover\n");
		scanf("%d", &numero_linha);
		int contador = 1;
		int i = 0;
		int j = 0;
		do { // copiar agora o ficheiro todo , sem a linha selecionada
			if (memoria[i] == '\n') {
				contador++;
			}
			if (contador == numero_linha) {
				i++; // avançar para o próximo caractar, se não, não entrava no ciclo
				while (memoria[i] != '\n') {
					i++;
				}
				if(numero_linha==1){
					i++; // para não colocar na primeira linha um linha em branco (\n)
				}
				contador++;
			}
			memoria2[j] = memoria[i];
			j++;
			i++;
		} while (memoria[i] != '\0');
		strcpy(memoria,memoria2);
		printf("%s", memoria);
	}
	srand((unsigned) time(NULL));

	if (*(memoria + NUMERO_PALAVRAS * TAMANHO_PALAVRA) != '\0') { // se já estiver no fim do ficheiro
		close(descritor);
		if (shm_unlink(NOME_MEMORIA_PARTILHADA) < 0) {
			perror("Erro ao apagar a zona de memória partilhada\n");
			exit(EXIT_FAILURE);
		}
		return 0; // terminar o processo
	}
	sprintf(memoria, "%sI’m the Father - with PID %d\n", memoria, getpid());
	sleep(rand() % 5 + 1);
	sem_post(semaforo);
	return 0;
}

