ex12:	main.o
		gcc -Wall -g main.o -o ex12	-lrt -lpthread

main.o:	seller.c
		gcc -Wall -g -c seller.c -o main.o
		
all:	ex12
		./ex12
				
client:	main2.o
		gcc -Wall -g main2.o -o client	-lrt -lpthread
		
		
main2.o:	client.c
		gcc -Wall -g -c client.c -o main2.o
		

all2:	client
		./client 
		
clean:
	rm *.o ex12
	rm /dev/shm/sem.serving 
	rm /dev/shm/sem.count 
	rm /dev/shm/sem.exclusive 
	rm /dev/shm/sem.next_client
