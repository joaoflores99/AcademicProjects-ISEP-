ex08:	main.o
		gcc -Wall -g main.o -o ex08	-lrt -lpthread
		
main.o:	ex08.c
		gcc -Wall -g -c ex08.c -o main.o
		
all:	ex08
		./ex08

clean:
	rm *.o ex08