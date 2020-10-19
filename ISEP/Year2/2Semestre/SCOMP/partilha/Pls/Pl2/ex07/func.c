#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "func.h"
#include <regex.h>

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

void func (int  vec[1000],int vec1[1000]){
	
	int i=5,tmp,sum[50000];
	int fd[5][2];
	if (pipe(fd[0]) == -1 || pipe(fd[1]) == -1 || pipe(fd[2]) == -1 || pipe(fd[3]) == -1 || pipe(fd[4]) == -1) {
		perror("Erro a criar o pipe\n");
		
	}
		
	int pid=spawn_childs(i);	
	if(pid==-1){
		exit(-1);
		}
	
	// filho 
	if(pid>0){
		if (pid == 1) {
			close(fd[0][0]);
			for (i = 0; i < 200; i++) {
				tmp = vec[i] + vec1[i];
				if (write(fd[0][1], &tmp, 4) < 0) {
					perror("Erro a escrever para o pipe\n%d");
				}
			}				
			close(fd[0][1]);
			exit(0);
		}
		if (pid == 2) {
			close(fd[1][0]);
			for (i = 200; i < 400; i++) {
				tmp = vec[i] + vec1[i];		
				if (write(fd[1][1], &tmp, 4) < 0) {
					perror("Erro a escrever para o pipe\n%d");
				}
			}					
			close(fd[1][1]);
			exit(0);
		}
		if (pid == 3) {
			close(fd[2][0]);
			
			for (i = 400; i < 600; i++) {
				tmp = vec[i] + vec1[i];			
				if (write(fd[2][1], &tmp, 4) < 0) {
					perror("Erro a escrever para o pipe\n%d");
				}
			}
				
			close(fd[2][1]);
			exit(0);
		}
		if (pid == 4) {
			close(fd[3][0]);			
			for (i = 600; i < 800; i++) {
				tmp = vec[i] + vec1[i];
				if (write(fd[3][1], &tmp, 4) < 0) {
					perror("Erro a escrever para o pipe\n%d");
				}
			}
			
			close(fd[3][1]);
			exit(0);
		}
		if (pid == 5) {
			close(fd[4][0]);
			for (i = 800; i < 1000; i++) {
				tmp = vec[i] + vec1[i];				
				if (write(fd[4][1], &tmp, 4) < 0) {
					perror("Erro a escrever para o pipe\n%d");
				}
			}					
			close(fd[4][1]);
			exit(0);
		}				
	//pai	
	}else{
		
		close(fd[0][1]);
		close(fd[1][1]);
		close(fd[2][1]);
		close(fd[3][1]);
		close(fd[4][1]);		
//-----------------------------------------------------------//			
	int x,t;
	int value;	
	for(t=0;t<5;t++){
		for (x = 0; x < 200; x++) {
			 if (read(fd[t][0], &value, 4) < 0){
			     perror("Erro a escrever para o pipe\n");
		     }		
		    sum[x] = value;
		}
	}
//-----------------------------------------------------------//	
		close(fd[0][0]);
		close(fd[1][0]);
		close(fd[2][0]);
		close(fd[3][0]);
		close(fd[4][0]);
	int y;
	for(y=0;y<1000;y++){
		printf("valor na posicao %d :%d\n",sum[y],y);
		}
		
	}
	
}

	
