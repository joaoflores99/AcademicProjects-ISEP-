%{  
	#include "lex.yy.h"  
    int numErros=0, numArgs = 0;
    double  precoTotal = 0;
    void yyerror( char * s ); 
    void aumento(double n);
    void imprimir(char *nome, char *codigo);
     double cont = 0;
%}

%define parse.trace

%union {
	char *id;
	double real;
	int inteiro;
}

%token <id> CODIGO <id> NOME <REAL> PESO <id>  DATA <id> SERVICO <real> VALOR <id> VACINA <inteiro> QUANTIDADE <id> VIRGULA <id> ESPACO <id> ENTER 


%%

	LINHA:  | START e LINHA																	     			      														;
	e : | ENTER																																							;
	START:  CODIGO ESPACO NOME ESPACO peso DATA ESPACO finalidade c		 {printf("%s %s paga %d servicos/vacinas %.2f\n", $2, $1, numArgs, precoTotal);}              ;
	peso: | PESO ESPACO																					      														    ;
	finalidade: servico  | vacina 			 	     					{numArgs++;}							 														;
	servico: SERVICO ESPACO VALOR							 			{aumento($3);} 				        													        ;
	vacina: VACINA  ESPACO QUANTIDADE			 			 			{aumento($3*10);}					     														;
	c: | VIRGULA ESPACO finalidade c			 				 		{numArgs++;}									     											;

%%

void yyerror(char *s){

	printf("ERRO SINTATICO/SEMANTICO:(%s)\n",s);
}

int main() {
	//yydebug = 1;
	return yyparse();
}

void aumento(double n) {
	cont++;
	precoTotal += n;
}
