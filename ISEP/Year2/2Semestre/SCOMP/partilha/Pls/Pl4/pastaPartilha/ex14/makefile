ex14reader:main.o
		gcc -Wall -g main.o -o ex14reader	-lrt -lpthread
		
main.o:	reader.c
		gcc -Wall -g -c reader.c -o main.o

ex14writer:main1.o
		gcc -Wall -g main1.o -o ex14writer	-lrt -lpthread
		
main1.o:writer.c
		gcc -Wall -g -c writer.c -o main1.o
		
all:	ex14reader
		./ex14reader &

all2:	ex14writer
		./ex14writer &

clean:
	rm *.o ex14reader ex14writer
