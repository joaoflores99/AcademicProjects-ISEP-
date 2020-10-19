#ifndef EX10_H_
#define EX10_H_

// constantes e estruturas que serao utilizadas no consult, consultAll e no insert

	#define STRING_SIZE 255
	#define NOME_FICHEIRO_MEMORIA "/ex10"
	#define ARMAZENAMENTO 100
	#define SEMAFORO_EXCLUSIVO "sem_excl"
	#define NOME_FICHEIRO_CONTADOR "contador"

	typedef struct{
			int numero;
			char nome[STRING_SIZE];
			char morada[STRING_SIZE];
	} dados;


#endif 
