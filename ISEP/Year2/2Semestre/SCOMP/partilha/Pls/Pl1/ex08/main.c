#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <wait.h>

int main()
{
 pid_t p;
 
 if (fork() == 0) {
    printf("PID = %d\n", getpid()); exit(0);
 }
 
 if ((p=fork()) == 0) {
    printf("PID = %d\n", getpid()); exit(0);
 } 

 printf("Parent PID = %d\n", getpid());
 
 printf("Waiting... (for PID=%d)\n",p); 
 waitpid(p, NULL, 0);
 
 printf("Enter Loop...\n"); 
 while (1); /* Infinite loop */
}

// alinea a) apenas sao criados um processo pai e um filho !! Isto deve-se ao facto 
//de o pai esperar pela morte do filho, aparecendo apenas o ultimo filho "defunto"!

//alinea b) o processo filho morreu ou aconteceu alguma coisa e o processo pai pode nao ter recebido 
// a informacao ou estava ocupado com outra tarefa.


