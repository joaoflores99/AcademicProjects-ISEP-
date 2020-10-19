#  Makefile de exemplo de uso do flex 
#  
#  O programa fica com o nome da pasta onde estamos
#       Não usar espaços no nome!  
#  
#  O ficheiro flex usado é o primeiro encontrado  pelo ls 
#   
#  Se existir um ficheiro .txt este será usado como standard input
#  ao correr o programa  
#  
#  A Makefile detecta se está em OSX ou Linux e muda as flags do Linker         
 
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


$(NAME):  $(NAME).c  
	gcc -Wall $(NAME).c  $(LINK_FLAGS) -o $(NAME)



$(NAME).c: $(FLEX_FILENAME) 
	flex -t $(FLEX_FILENAME) > $(NAME).c

run: $(NAME) 
	./$(NAME)   $(RUN_OPTS)


clean: 
	if  [ -f  $(NAME) ] ; then rm $(NAME) $(NAME).c ; fi   

