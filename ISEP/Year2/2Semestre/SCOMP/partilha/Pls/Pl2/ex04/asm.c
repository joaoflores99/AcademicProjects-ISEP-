#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>





void prog(){
	int fd[2];
	//char *string=malloc(100);
	char string[100];
	int pipe1=pipe(fd);
	if(pipe1==-1){
		exit(-1);
	}
	pid_t pid=fork();
	if(pid<0){
		exit(-1);
	}
	if(pid==0){
		close(fd[1]);
		read(fd[0],string,100);
		printf("String %s\n",string);
		close(fd[0]);
		exit(0);
	}
	else{
		close(fd[0]);
		FILE *fp;
		char str[100];
		fp = fopen("text.txt" , "rt");
		if(fp == NULL) {
			perror("Error opening file");
		}
		fgets (str, 100, fp);
		fclose(fp);
		write(fd[1],str,100);
		close(fd[1]);
		wait(0);
	}
}


