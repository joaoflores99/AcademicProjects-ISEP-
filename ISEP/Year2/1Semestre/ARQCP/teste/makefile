app: main.o tempo.o  qualificados.o
	gcc -Wall -lm -g main.o tempo.o  qualificado -o app

main.o: main.c
	gcc -Wall -lm -g -c main.c -o main.o

qualificado.o: qualificado.s
	gcc -Wall -lm -g -c qualificado.s -o qualificado.o
	
qualificado.o: tempo.s
	gcc -Wall -lm -g -c tempo.s -o tempo.o	
	
debug: app
	gdb -tui app
	
clean :
	rm *.o app*
	
run: app
	./app
