app:	main.o 
		gcc -Wall -g main.o -o app -lpthread
		
main.o:	main.c
		gcc -Wall -g -c main.c -o main.o

run:	app
		./app

clean:
	rm *.o app
