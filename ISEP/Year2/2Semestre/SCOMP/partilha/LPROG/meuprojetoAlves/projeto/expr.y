%{  
	#include <stdio.h>   
    int numErros=0, numArgs = 0;
	int yylex();
	int yyerror(char*s);
%}

 %union {
 char * id ;
 float real ;
 int inteiro ;
}

%token <id> CODIGO NOME DATA SERVICO VACINA
%token <real> PESO VALOR 
%token <inteiro> QUANTIDADE
%type <real> finalidade finalidades servicoValor vacinaQuantidade
%%
	Start: 
		|Start LINHA '\n' {numArgs = 0;}
		|Start '\n' {numArgs = 0;}
		;
			
	LINHA: CODIGO NOME peso DATA  finalidades 				{printf("%s %s paga %d servicos/vacinas %.2f\n", $2, $1, numArgs, $5);} 
	;   
	peso: 
		| PESO
		; 
	finalidades: finalidade									{$$=$1;}
				| finalidades ',' finalidade				{$$+=$3;}
				;
				
	finalidade: servicoValor | vacinaQuantidade						 				
	;
	   
	servicoValor: SERVICO VALOR								{$$ += $2;numArgs++;}
	; 
	
	vacinaQuantidade: VACINA QUANTIDADE 					{$$+=$2*10;numArgs++;}
	;
%%

int main(){
  return  yyparse(); 
}

int yyerror(char *s){
	printf("ERRO SINTATICO/SEMANTICO:%s\n",s);
	return 0;
}
