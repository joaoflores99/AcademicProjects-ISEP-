ex17: main.o func.o
	gcc main.o func.o -o ex17
	
main.o: main.c
	gcc -c main.c -o main.o
	
func17.o: func.c
	gcc -c func.c -o func.o

debug: app
	gdb -tui ex17
	
clean :
	rm *.o ex17*
	
run: ex17
	./ex17
