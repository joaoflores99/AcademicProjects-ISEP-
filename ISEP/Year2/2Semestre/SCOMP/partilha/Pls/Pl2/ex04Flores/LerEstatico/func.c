#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "func.h"
#include <string.h>


#define MAX_SIZE 256

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
    int fd[2];
    char string[MAX_SIZE];
    char * c=string;
    
	int p=pipe(fd);
	
	if(p==-1){
		exit(-1);
	}
	
	int pid=spawn_childs(1);
	if(pid<0){
		exit(-1);	
		}

	//filho
	if (pid == 0){ 
		
		close(fd[1]);
        printf("/nFile Content using pipes:%s/n",c);
		close(fd[0]);
        exit(0);
        
    //pai
    }else{
	  if(pid>0){ 
        close(fd[0]);	
		FILE *txtfile;
		
        txtfile = fopen("./hello.txt", "r");         
        while (fgets(string, MAX_SIZE, txtfile) != NULL){ 	
			   write(fd[1],string,strlen(string)+1);
		}			
	    close(fd[1]);
		wait(&status);
 }
}
}
