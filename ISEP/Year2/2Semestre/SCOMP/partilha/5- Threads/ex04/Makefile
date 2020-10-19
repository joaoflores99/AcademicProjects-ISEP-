ex04:	main.o
		gcc -Wall -g main.o -o ex04	-lrt -lpthread
		
main.o:	ex04.c
		gcc -Wall -g -c ex04.c -o main.o
		
all:	ex04
		./ex04

clean:
	rm *.o ex04