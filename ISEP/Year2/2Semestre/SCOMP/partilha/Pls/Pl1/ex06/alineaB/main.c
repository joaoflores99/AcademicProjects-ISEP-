#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>


//alinea b

//para serem criados exatamente 4 processos filho, utilizamos a funcao wait
// onde cada processo filho do processo principal aguarda que o processo filho termine 

int main(void){
	
	int i;
	int status=0;
    for (i = 0; i < 4; i++) {
        if (fork() == 0) {
            sleep(1); /*sleep(): unistd.h*/
            printf("Entered here\n");
            exit(0);
            
         }else{
			 wait(&status);
		 }
     }     
    printf("This is the end.\n");
	return 0;
	}
