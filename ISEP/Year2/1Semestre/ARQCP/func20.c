#include <stdio.h>
#include <string.h>

char* find_word(char* str, char* word, char* initial_addr) {		
	char *p1 = initial_addr;
	char *p3 = initial_addr;
	char *p2 = word;
	int cont1 = 0;
	while(*p1 != '\0') {
		if(*p1 == *p2) {
			p3 = p1;
			while(*p2 != '\0' && *p2 == *p3) {
				p3++;
				p2++;
				cont1++;
			}
			p2 = word;
			if(cont1 == ((int) strlen(word))) {
				return p1;
			}
		}
		p1++;
	}
	return NULL;	
}
