CC = gcc -Wall -g

main: main.o push.o pop.o
	${CC} main.o push.o pop.o -o main

push.o: push.c
	${CC} -c push.c
	
pop.o: pop.c
	${CC} -c pop.c	
	
main.o: main.c stack.h
	${CC} -c main.c

run: main
	./main

clean:
	rm -f *.o main
	
debug:
	gdb -tui main
