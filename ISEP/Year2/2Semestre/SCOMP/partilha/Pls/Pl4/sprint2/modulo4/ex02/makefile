app:main.o
	gcc main.o -o app -lrt -lpthread
	
main.o: main.c
	gcc -c main.c -o main.o
	
debug: app
	gdb -tui app
	
clean :
	rm *.o app*
	rm O*
	rm /dev/shm/* 
		
run: app
	./app
