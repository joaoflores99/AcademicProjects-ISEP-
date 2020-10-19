app: main.o asm.o 
	gcc -Wall -lm -g main.o asm.o  -o app -lrt

main.o: main.c
	gcc -Wall -lm -g -c main.c -o main.o

asm.o: asm.c
	gcc -Wall -lm -g -c asm.c -o asm.0
	
debug: app
	gdb -tui app
	
clean :
	rm *.o app*
	
run: app
	./app
