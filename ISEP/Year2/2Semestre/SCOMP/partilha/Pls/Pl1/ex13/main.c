#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

int main(){
	int i;
	for(i=0;i<10;i++){
		execlp("echo","echo","SCOMP",NULL);
	}
}

//alinea a 
	//apenas da print ECHO uma vez porque o todo o codigo é alterado apos a chamada do exec.
	
//alinea b
	//o print so é dado uma vez porque apos o exec o codigo é alterado, isto é como a
	//chamada ao for novamente tambem foi alterado.
