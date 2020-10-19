%{
    #include <stdio.h>
    int numErros=0, numArgs = 0;
    float  precoTotal = 0;
%}

%union{
	char *string;
	double real;
	int quantidade;
}

%token <string> 	CODIGO
%token <string>   	NOME
%token <real>		PESO
%token <string>   	DATA
%token <string>		SERVICO
%token <real>		VALOR
%token <string>		VACINA
%token <quantidade> QUANTIDADE
%token				VIRGULA
%start inicio

%%

inicio: CODIGO NOME peso DATA a b	 {printf("%s %s paga %d servicos/vacinas %.2f\n", $2, $1, numArgs, precoTotal);} 
peso: PESO|/*vazio*/
a: servico | vacina 			 	{numArgs++;}
servico: SERVICO VALOR				{precoTotal += $2;}
vacina: VACINA QUANTIDADE			 {precoTotal+=$2*10;}
b: VIRGULA a b|/*vazio*/			 {numArgs++;}

%%

int main(){
  
  yyparse();

  if(numErros==0){
  } 
  else
    printf("Frase inválida\nNúmero de erros: %d\n",numErros); 
  return 0;
}

int yyerror(char *s){
	numErros++;
	printf("erro sintatico/semantico: %s\n",s);
}
