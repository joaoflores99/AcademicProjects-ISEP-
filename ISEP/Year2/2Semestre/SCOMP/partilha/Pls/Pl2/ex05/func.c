#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include "func.h"
#include <regex.h>
#include <string.h> 



int spawn_childs(int num){
	int i;
	for(i=0;i<num;i++) { 
        if(fork() == 0) { 
            return (i+1);
        } 
	}
	return(0);

}

  void upperLower (char s[]){
	int i=0;
	/*while(*(pointer)!='\0'){	
			if(*(pointer+i)<='z' && *(pointer+i)>='a'){					
				*(pointer+i)-=32;				
			}
		 else{
			if(*(pointer+i)<='Z' && *(pointer+i)>='A'){
				*(pointer+i)=*(pointer+i)+32;
				}
		}
		i++;
		}
		*/
		while (s[i] != '\0') {
            if(s[i] >= 'a' && s[i] <= 'z') {
                s[i] -= 32;
            } else if (s[i] >= 'A' && s[i] <= 'Z') {
                s[i] += 32;
            }
            i++;
        }
		
		
		
	}



void func (){
	
	
	char s[256];
	//char* pointer=s;
    int fd1[2];// UP
    int fd2[2]; // DOWN
    
	int p1=pipe(fd1);
	int p2=pipe(fd2);
	
	if(p1==-1|| p2==-1){
		exit(-1);
	}
	
	int pid=spawn_childs(1);
	if(pid<0){
		exit(-1);	
		}

	//filho
	if (pid == 0){ 
		printf("Introduza uma mensagem:\n");
		scanf("%[^\n]%*c", s);
        write(fd1[1], s, 256);         
        close(fd1[1]);
        read(fd2[0], s,256);        
        close(fd2[0]);        
        printf("Mensagem com o tamanho das letras invertido:%s\n",s);
        exit(0);
        
     //Pai 
     }else{
		 if(pid>0){
		    read(fd1[0], s, 256); 
		    close(fd1[0]);	
		
		    upperLower(s);		
		    close(fd2[0]);
		    write(fd2[1], s, 256); 
		    close(fd2[1]);
		    wait(0);		
	     }
      }   
        
   
}

	
