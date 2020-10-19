#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <sys/wait.h>
#include <string.h>
#include "val.h" 

#define ARRAY_SIZE 2


int val(char username[80], char password[80]){
	
	userInfo uR[2];
	
	strcpy(uR[0].username , "Joao");
	strcpy(uR[0].password , "012");
	strcpy(uR[1].username , "Mota");
	strcpy(uR[1].password , "011 ");
	
	int i;
	
	for(i=0; i<ARRAY_SIZE; i++) {
		if(strcmp(uR[i].username , username) == 0) {
			if(strcmp(uR[i].password , password) == 0) {
				return 1;
			} else {
				return 2;
			}
		}
	}
	return 3;
	
}
