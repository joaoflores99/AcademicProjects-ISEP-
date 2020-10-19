app: main.o 
	gcc -Wall -lm -g main.o -o app -lrt

main.o: main.c
	gcc -Wall -lm -g -c main.c -o main.o

debug: app
	gdb -tui app
	
clean :
	rm *.o app*
	rm /dev/shm/ex04
	
	
run: app
	./app
