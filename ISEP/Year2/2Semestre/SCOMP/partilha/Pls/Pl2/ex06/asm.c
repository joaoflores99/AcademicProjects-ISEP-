#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int baby(int num){
	pid_t pid;
	int i;
	for(i=0;i<num;i++){
		pid=fork();
		if(pid<0){
			return -1;
		}
		if(pid==0){
			return (i+i);
		}else
			wait();
	}
	return 0;
}

void prog(int *ptr,int *ptr1){
	int tmp,r,w,j,i,total=0;
	int fd[2];
	int p=pipe(fd);
	if(p==-1){
		exit(-1);
	}
	pid_t pid;
	for(i=0;i<5;i++){
		pid=fork();
		if(p==-1){
			printf("Erro no pid");
			exit(-1);
		}
		if (pid== 0) {
			tmp=0;
			for (j = i * 200; j < i * 200 + 200; j++) {
				tmp+=*(ptr+j)+*(ptr1+j);
			}
			w=write(fd[1],&tmp,sizeof(tmp));
			if(w==-1){
				printf("Erro de escrita");
			}
			exit(0);
		}
		else{
			
			r=read(fd[0],&tmp,sizeof(tmp));
			if(r==-1){
				printf("Erro na leitura");
			}
			total+=tmp;
			printf("valor intermedio %d\n",tmp);
		}
	}
	close(fd[1]);
	close(fd[0]);
	printf("valor final final %d\n",total);
}

//read is blocking by default. Therefore it waits until data are written into the pipe
