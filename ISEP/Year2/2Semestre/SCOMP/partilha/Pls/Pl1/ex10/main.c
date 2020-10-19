#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>

#define ARRAY_SIZE 2000

int main (void){
	
  int numbers[ARRAY_SIZE];	/* array to lookup */
	int n;						/* the number to find */
	time_t t;					/*  needed  to  init.  the random  number  generator  (RNG) */
	int i,j;
	int status;
	pid_t p;
	
	/* intializes RNG (srand():stdlib.h; time(): time.h) */
	srand ((unsigned) time (&t));
	
	/* initialize array with random numbers (rand(): stdlib.h) */
	for (i = 0; i < ARRAY_SIZE; i++) {
		numbers[i] = rand () % 10000;
	}
	
	/* initialize n */
	n = rand () % 10000;

for (i = 0; i < 10; i++) {
		p = fork();
		if (p == 0) {
			int ind = i * 200;
			for (j = ind; j < ind + 200; j++) {
				if (numbers[j] == n) {
					exit(j % 200);
				}
			}
			exit(255);
		} 
	}
	
	while (wait(&status) > 0) {
		status = WEXITSTATUS(status);
		printf("Index: %d\n", status);
	}
	
	
	return 0;
}
