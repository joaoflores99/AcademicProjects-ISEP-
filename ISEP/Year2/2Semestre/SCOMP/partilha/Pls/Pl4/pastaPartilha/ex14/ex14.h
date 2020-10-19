#ifndef EX14_H_
#define EX14_H_
	#define FILENAME "/ex14"
	#define NUM_SEM 3
	#define SIZE 100

	typedef struct{
		pid_t pid;
		char timeinfo[SIZE];
		int readers;
		int writers;
	}s;

#endif
