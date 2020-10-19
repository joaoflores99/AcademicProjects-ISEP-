ex10:	main.o
		gcc -Wall -g main.o -o ex10	-lrt -lpthread
		
main.o:	ex10.c
		gcc -Wall -g -c ex10.c -o main.o
		
all:	ex10
		./ex10

clean:
	rm *.o ex10