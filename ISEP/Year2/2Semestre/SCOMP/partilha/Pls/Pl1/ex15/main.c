#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "asm.h"

int main(int argc, char *argv[]){
	int valor;
	if(argc>1){
		printf("Ficheiro %s\n",argv[1]);
		valor=lsTest(argv[1]);
		printValor(valor);
	}
	else{
		printf("Falta o nome do ficheiro\n");
	}
	return 0;
}
