#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

int main(void){
	
	int i;
    int status;

	for(i=0;i<4;i++) { 
		pid_t pid = fork();
        if(pid == 0) { 
			sleep(1);
            exit(i+1);
        } else {
			if(pid%2 == 0) {
				waitpid(pid,&status,0);
				printf(" O processo foi criado na posição:%d\n" , WEXITSTATUS(status));
			}
		} 
	}
	printf ("This is the end.\n");
	return 0;
}
