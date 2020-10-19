%{  
	#include <stdio.h>   
    int numErros=0, numArgs = 0;
    float  precoTotal = 0;
%}

 %union {
 char * id ;
 float real ;
 int inteiro ;
}

%token <id> CODIGO <id> NOME <real> PESO <id> DATA <id> SERVICO <real> VALOR <id> VACINA <inteiro> QUANTIDADE <id> ESPACO <id> VIRGULA <id> MUDA_LINHA
%%
	Start: LINHA Start|
	;	
	LINHA: conteudoLinha MUDA_LINHA
	;  
	conteudoLinha: CODIGO ESPACO NOME peso DATA ESPACO finalidade c 				{printf("%s %s paga %d servicos/vacinas %.2f\n", $2, $1, numArgs, precoTotal);} 
	;   
	peso: ESPACO | ESPACO PESO ESPACO
	;   
	finalidade: servicoValor | vacinaQuantidade						 				{numArgs++;}
	;   
	c: VIRGULA ESPACO finalidade c|													{numArgs++;}
	;   
	servicoValor: SERVICO ESPACO VALOR												{precoTotal += $3;}
	;   
	vacinaQuantidade: VACINA ESPACO QUANTIDADE 										{precoTotal+=$3*10;}
	;
%%

int main(){
  return  yyparse(); 
}

int yyerror(char *s){
	printf("ERRO SINTATICO/SEMANTICO:%s\n",s);
	return 0;
}
