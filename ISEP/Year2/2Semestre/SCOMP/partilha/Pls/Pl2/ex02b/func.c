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
	 
	 A obj;
	 A * pointer=&obj;
	 
	 pointer->x=1234;
	 pointer->y= "HELLO WORLD,It's me,MARIO!!!!!!";
	
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
		read(fd[0],&pointer,sizeof(obj));
		printf("Numero inteiro:%d\n",pointer->x);
		printf("String:%s\n",pointer->y);
		close(fd[0]);
		close(fd[1]);
		exit(0);
	}
	//pai
	else{
		write(fd[1],&pointer,sizeof(obj));
		close(fd[0]);
		close(fd[1]);
		wait(0);
		
		
		
	}
	
	}
