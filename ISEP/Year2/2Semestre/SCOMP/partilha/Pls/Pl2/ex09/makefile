app: main.o func.o 
	gcc -Wall -lm -g main.o func.o  -o app

main.o: main.c
	gcc -Wall -lm -g -c main.c -o main.o

func.o: func.c
	gcc -Wall -lm -g -c func.c -o func.o
	
debug: app
	gdb -tui app
	
clean :
	rm *.o app*
	
run: app
	./app
