#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "func.h"
#include <string.h>

char* readFile(char *filename){
   char *buffer = NULL;
   int string_size, read_size;
   FILE *handler = fopen(filename, "rt");

   if (handler)
   {
       // Seek the last byte of the file
       fseek(handler, 0, SEEK_END);
       // Offset from the first to the last byte, or in other words, filesize
       string_size = ftell(handler);
       // go back to the start of the file
       rewind(handler);
       // Allocate a string that can hold it all
       buffer = (char*) malloc(sizeof(char) * (string_size + 1) );
       // Read it all in one operation
       read_size = fread(buffer, sizeof(char), string_size, handler);
       // fread doesn't set it so put a \0 in the last position
       // and buffer is now officially a string
       buffer[string_size] = '\0';

       if (string_size != read_size){
           free(buffer);
           buffer = NULL;
       }
      
       fclose(handler);
    }
  //  printf("%s\n", buffer);
    return buffer;
}

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
	char *c="";
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
	if (pid == 0){ 
		
        close(fd[1]);
        read(fd[0], &c, strlen(c)+1);
        printf("File Content using pipes:%s/n",c);
        close(fd[0]);
        exit(0);
        
    //pai
    }else{
		if(pid>0){ 
        close(fd[0]); 
        c = readFile("hello.txt");    
        write(fd[1], c, strlen(c)+1);
        close(fd[1]);
        wait(&status);
        }
 }
}
