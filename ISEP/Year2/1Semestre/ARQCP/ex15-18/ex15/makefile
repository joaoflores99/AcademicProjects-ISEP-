CC = gcc -Wall -g

main: main.o add_matrixes.o matrix.o 
	${CC} main.o add_matrixes.o matrix.o -o main

add_matrixes.o: add_matrixes.c
	${CC} -c add_matrixes.c
	
matrix.o: matrix.c
	${CC} -c matrix.c
	
main.o: main.c add_matrixes.h
	${CC} -c main.c

run: main
	./main

clean:
	rm -f *.o main
	
debug:
	gdb -tui main
