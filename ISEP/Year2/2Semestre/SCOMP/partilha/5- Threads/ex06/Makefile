ex06:	main.o
		gcc -Wall -g main.o -o ex06	-lrt -lpthread
		
main.o:	ex06.c
		gcc -Wall -g -c ex06.c -o main.o
		
all:	ex06
		./ex06

clean:
	rm *.o ex06