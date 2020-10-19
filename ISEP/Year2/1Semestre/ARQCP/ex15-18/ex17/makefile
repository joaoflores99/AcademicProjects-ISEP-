CC = gcc -Wall -g

main: main.o approved_semester.o 
	${CC} main.o approved_semester.o -o main

approved_semester.o: approved_semester.s
	${CC} -c approved_semester.s

main.o: main.c approved_semester.h
	${CC} -c main.c

run: main
	./main

clean:
	rm -f *.o main
	
debug:
	gdb -tui main
