app:	main.o 
		gcc -Wall -g main.o -o app -lrt -lpthread
		
main.o:	main.c
		gcc -Wall -g -c main.c -o main.o

run:	app
		./app
		
clean:
	rm *.o app
	rm /dev/shm/ex03
	rm /dev/shm/sem*
