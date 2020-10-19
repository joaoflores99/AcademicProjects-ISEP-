
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>



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
	int status;
	
	status=spawn_childs(6);
	status=status*2;
	printf("Exit value: %d\n", status);
		
	
	return 0;
	}
