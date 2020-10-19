#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int main(void){
	
	int i;
	
    for (i = 0; i < 4; i++) {
		pid_t pid=fork();
        if (pid == 0) {
            sleep(1); /*sleep(): unistd.h*/
            exit(0);
         }else{
			 if(pid%2==0){
			    wait();
              }
		 }
     }     
    printf("This is the end.\n");
	return 0;
	}
