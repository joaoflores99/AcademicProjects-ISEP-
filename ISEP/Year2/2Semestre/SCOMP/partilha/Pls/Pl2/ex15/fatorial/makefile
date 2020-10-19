app: main.o fatorial.o 
	gcc -Wall -lm -g main.o fatorial.o  -o app

main.o: main.c
	gcc -Wall -lm -g -c main.c -o main.o

fatorial.o: fatorial.c
	gcc -Wall -lm -g -c fatorial.c -o fatorial.o
	
debug: app
	gdb -tui app
	
clean :
	rm *.o app*
	
run: app
	./app
