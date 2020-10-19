#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "func.h"

int spawn_childs(int num){
	int i;
	for(i=0;i<num;i++) { 
        if(fork() == 0) { 
            return (i+1);
        } else {
			wait(0); 
		} 
	}
	return(0);

}


void func(){
	 
	int status;
	char *c;
    int fd[2];
	int p=pipe(fd);
	
	if(p==-1){
		exit(-1);
	}
	
	int pid=spawn_childs(1);
	if(pid<0){
		exit(-1);	
		}
		
	//filho
	if(pid==0){
		
		read(fd[0],&c,sizeof(c));
		printf("First Message:%s\n",c);
		
		read(fd[0],&c,sizeof(c));
		printf("Second Message:%s\n",c);
		
		close(fd[0]);
		close(fd[1]);
		exit(0);
	}
	//pai
	else{
		c="HELLO WORLD";
		write(fd[1],&c,sizeof(c));
		
		c="Goodbye!!!";
		write(fd[1],&c,sizeof(c));
		close(fd[0]);
		close(fd[1]);
		wait(&status);
		int id= WEXITSTATUS(status);
		printf("PID:%d\n Exit value:%d\n",pid,id);
		
		
		
	}
	
	}
