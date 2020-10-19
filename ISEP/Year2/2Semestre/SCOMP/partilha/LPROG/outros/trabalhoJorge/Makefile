a.out: miaumiau.tab.c miaumiau.tab.h lex.yy.c
	gcc miaumiau.tab.c lex.yy.c -lfl
	
miaumiau.tab.c: miaumiau.y
	bison -d miaumiau.y 
	
lex.yy.c: miaumiau.flex
	flex miaumiau.flex
	
miaumiau.tab.h: miaumiau.flex
	flex miaumiau.flex

run: a.out
	./a.out<dados.txt
