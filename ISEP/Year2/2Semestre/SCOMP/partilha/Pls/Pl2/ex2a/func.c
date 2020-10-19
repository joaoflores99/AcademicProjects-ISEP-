#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "func.h"
#include <string.h>


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
	
	char * c= "HELLO WORLD";
	int number;
	
	
	
	int fd[2];
	int p=pipe(fd);
	
	if(p==-1){
		exit(-1);
	}
	
	int fd2[2];
	int p2=pipe(fd2);
	
	if(p2==-1){
		exit(-1);
	}
	
	int pid= spawn_childs(1);
	//erro
	
	if(pid<0){
		exit(-1);
	}
	//filho
	if(pid==0){
		read(fd[0],&number,100);
		printf("Numero inteiro:%d\n",number);
		close(fd[0]);
		close(fd[1]);
		read(fd2[0],&c,100);
		printf("String:%s\n",c);
		close(fd2[0]);
		close(fd2[1]);
		exit(0);
	}
	//pai
	else{
		int num=55555;
		write(fd[1],&num,100);
		close(fd[0]);
		close(fd[1]);
		write(fd2[1],&c,strlen(c)+1);
		close(fd2[0]);
		close(fd2[1]);
		wait(0);
		
		
		
	}
}
	
	
