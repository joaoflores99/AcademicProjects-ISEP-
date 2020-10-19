#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

void fatorial()
{
    int num,i;
	
	if(read(STDIN_FILENO,&num,sizeof(int)) <0){
		perror("ERRO NA LEITURA DO PIPE\n");
		exit(-1);
	}
	
	for(i=num;i>1;i--){
		num *=(i-1);	
	}
    
    if(write(STDOUT_FILENO,&num,sizeof(int))<0){
		perror("ERRO NA ESCRITA DO PIPE\n");
		exit(-1);
	}
	close(STDIN_FILENO);
	close(STDOUT_FILENO);
	exit(0);
}
