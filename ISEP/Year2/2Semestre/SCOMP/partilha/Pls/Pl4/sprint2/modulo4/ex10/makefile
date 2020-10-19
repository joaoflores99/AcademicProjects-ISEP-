ex10:	main.o
		gcc -Wall -g main.o -o ex10	-lrt -lpthread
		
main.o:	insert.c
		gcc -Wall -g -c insert.c -o main.o
		
all:	ex10
		./ex10
		
ex10_consult_all:	main2.o
		gcc -Wall -g main2.o -o ex10_consult_all	-lrt -lpthread
		
main2.o:	consultAll.c
		gcc -Wall -g -c consultAll.c -o main2.o
		
all2:	ex10_consult_all
		./ex10_consult_all
		
ex10_consult:	main3.o
		gcc -Wall -g main3.o -o ex10_consult	-lrt -lpthread
		
main3.o:	consult.c
		gcc -Wall -g -c consult.c -o main3.o
		
all3:	ex10_consult
		./ex10_consult
		
clean:
	rm *.o
	rm ex10 ex10_consult ex10_consult_all
	rm dev/shm/*
		

