%{
    #include <stdio.h>
    int numArgs=0, numErros=0;
%}

%union {
  char  *id;
  int   inteiro;
  float real;
}
%token <id>      ID 
%token <inteiro> INT 
%token <real>    REAL
%start inicio

%%
inicio:      /* vazio */
            | lista_args
            ;
lista_args: arg
            | lista_args ',' arg
            | lista_args ',' error {yyerror("falta argumento");}
            | lista_args {yyerror("falta virgula");} arg 
            ;
arg:        ID        {numArgs++;printf("ID:%s\n",$1);}
            | INT     {numArgs++;printf("INT:%d\n",$1);}
            | REAL    {numArgs++;printf("REAL:%f\n",$1);}
            ;
%%

int main(){
  
  yyparse();

  if(numErros==0)
    printf("Frase válida\n");
  else
    printf("Frase inválida\nNúmero de erros: %d\n",numErros);
	printf("Número de argumentos é %d\n",numArgs);
  return 0;
}

int yyerror(char *s){
  numErros++;
	printf("erro sintatico/semantico: %s\n",s);
}