ex02:	main.o
		gcc -Wall -g main.o -o ex02	-lrt -lpthread
		
main.o:	ex02.c
		gcc -Wall -g -c ex02.c -o main.o
		
all:	ex02
		./ex02

clean:
	rm *.o ex02