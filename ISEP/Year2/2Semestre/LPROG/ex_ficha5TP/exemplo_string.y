%{
    #include <stdio.h>
    #include "exemplo.h"
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
  char my_char_ptr[] = "123 asda, 123.23, 134, asd1231asd121 , ,    ,123 ,sbsb";
  YY_BUFFER_STATE str_buffer = yy_scan_string(my_char_ptr);
  
  yyparse();
  
  yy_delete_buffer(str_buffer); /* free up memory */


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