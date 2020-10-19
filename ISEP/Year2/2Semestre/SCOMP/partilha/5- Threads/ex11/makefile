ex01:	main.o
		gcc -Wall -g main.o -o ex01	-lrt -lpthread
		
main.o:	ex11.c
		gcc -Wall -g -c ex11.c -o main.o
		
all:	ex01
		./ex01
		
ex01_consult_all:	main2.o
		gcc -Wall -g main2.o -o ex01_consult_all	-lrt -lpthread
		
main2.o:	ex14_reader.c
		gcc -Wall -g -c ex14_reader.c -o main2.o
		
all2:	ex01_consult_all
		./ex01_consult_all
			
clean:
	rm ex01 main*.o
		

