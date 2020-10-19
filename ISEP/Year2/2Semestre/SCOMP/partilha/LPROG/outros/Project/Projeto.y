%{  /* demo02/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
     void incrementar(double n);
     void imprimir(char *nome, char *codigo);
     double total = 0;
     double cont = 0;
%}

%define parse.trace

%union {
	double val;
	char *str;
	int qtd;
}
%token <str> CODIGO <str> NOME <val> PESO <str> DATA <str> SERVICO <val> VALOR <str> VACINA <qtd> QUANTIDADE <str> ESPACO <str> VIRGULA <str> MUDA_LINHA
%%
	FICHEIRO	: | LINHA FICHEIRO
	;
	
	LINHA: S MUDA_LINHA
	;
   
	S	: CODIGO ESPACO NOME T DATA ESPACO Q K {imprimir($3, $1); total = 0; cont = 0;}
	;
   
	T	: 	ESPACO | ESPACO PESO ESPACO
	;
   
	Q	: SV | VQ
	;
   
	K	:  	| VIRGULA ESPACO Q K
	;
   
	SV	: SERVICO ESPACO VALOR	{ incrementar($3); }
	;
   
	VQ	: VACINA ESPACO QUANTIDADE { incrementar($3 * 10); }
	;
%%
int main() {
	  //yydebug = 1;
      return yyparse();
}
void yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
void incrementar(double n) {
	cont++;
	total += n;
}
void imprimir(char *nome, char *codigo) {
	printf("%s (%s) paga %.0f serviços/vacinas %.2f.\n", nome, codigo, cont, total);
}

