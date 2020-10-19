CC = gcc -Wall -g

main: main.o struct_A.o asm.o
	${CC} main.o struct_A.o asm.o -o main

struct_A.o: struct_A.c
	${CC} -c struct_A.c
	
asm.o: asm.s
	${CC} -c asm.s
	
main.o: main.c struct_A.h
	${CC} -c main.c

run: main
	./main

clean:
	rm -f *.o main
	
debug:
	gdb -tui main
