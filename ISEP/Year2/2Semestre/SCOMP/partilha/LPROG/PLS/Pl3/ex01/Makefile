#
#  Special makefile for demo01 
#
#  Do not use for other examples 
#  
space := $(subst ,, )
NAME=$(notdir $(patsubst %/,%,$(subst $(space),_,$(CURDIR))))


FLEX_FILENAME=$(firstword $(wildcard *.lex))

TEXT_FILENAME=$(firstword $(wildcard *.txt))


ifeq ($(strip $(TEXT_FILENAME)),) 
RUN_OPTS=
else 
RUN_OPTS=<$(TEXT_FILENAME)
endif 



ifeq ($(shell uname),Darwin)
	    LINK_FLAGS=-ll
    else
	    LINK_FLAGS=-lfl
endif 


$(NAME):  main.c  lex.yy.c defs.h   
	gcc -Wall main.c  lex.yy.c   $(LINK_FLAGS) -o $(NAME)



lex.yy.c: $(FLEX_FILENAME) defs.h 
	flex -t $(FLEX_FILENAME) > lex.yy.c



run: $(NAME) 
	./$(NAME)   $(RUN_OPTS)

clean:  $(NAME) 
	rm $(NAME) lex.yy.c 

