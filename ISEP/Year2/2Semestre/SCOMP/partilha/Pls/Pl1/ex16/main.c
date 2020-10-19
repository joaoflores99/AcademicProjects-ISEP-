#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>


void my_exec(char * command){
	int status;
    status= execlp(command,command,NULL);
	exit(status);
	}

int main(){
	
    //char string [255]="ls";
	//char * command= string;
	

	//char string1 [255]="ps";
	//char * command1= string1;
	
	char string2 [255]="who ";
	char * command2= string2;


	int status;
	
	pid_t pid =fork();
	
	if(pid==0){
		//my_exec(command);
	//my_exec(command1);
		my_exec(command2);
		
		} else {
			wait(&status);
			status=WEXITSTATUS(status);
			
			if(status==0){
				 //printf("O comando (%s) foi executado com sucesso!\n",string);
				 //printf("O comando (%s) foi executado com sucesso!\n",string1);
				 //printf("O comando (%s) foi executado com sucesso!\n",string2);
			} else{
				  //printf("O comando (%s) nao foi executado com sucesso!\n",string);
				 //printf("O comando (%s) nao foi executado com sucesso!\n",string1);
				 //printf("O comando (%s) nao foi executado com sucesso!\n",string2);
				}
		}	
		
	return 0;
}
