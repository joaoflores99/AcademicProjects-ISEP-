#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>


int spawn_childs(int num){
	int i;
	for(i=0;i<num;i++) { 
        if(fork() == 0) { 
            return (i+1);
        } else {
			wait(); 
		} 
	}
	return(0);

}


int main(void){
   int read_msg=0; 
	int fd[2];
	int p=pipe(fd);
	if(p==-1){
		exit(-1);
	}
	
	//pid_t 
	int pid= spawn_childs(1);
	if(pid<0){
		exit(-1);
	}
	if(pid==0){
		read(fd[0], &read_msg, 100); 
		printf("Pid pai %d \n",read_msg);
		
		close(fd[0]);
		close(fd[1]);
		
		exit(0);
	}
	else{
		
		int pid2=getpid();
		write(fd[1],&pid2,100);
		wait(0);
		
		close(fd[0]);
		close(fd[1]);
		
		printf("Pid pai %d \n",pid2);
	}


	return 0;
	}
