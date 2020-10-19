#include <stdio.h>
#include <string.h>
#include "func20.h"

int main() {
	char str[256];
	char word[256];
	int n = 0;
	printf("Frase? \n");
	fgets(str, 80, stdin);
	*(str + ((int) (strlen(str))) - 1) = 0;
	printf("Palavra? \n");
	scanf("%s" , word);
	printf("Que posição da palavra pretende começar \n");
	scanf("%d" , &n);  
	if(find_word(str,word, (str + n)) != NULL) {
		printf("A palavra %s pode ser encontrada na frase %s começando na posição %d \n" , word, str, n);
	} else {
		printf("A palavra %s não pode ser encontrada na frase %s começando na posição %d \n" , word, str, n);
	}
	return 0;
}
