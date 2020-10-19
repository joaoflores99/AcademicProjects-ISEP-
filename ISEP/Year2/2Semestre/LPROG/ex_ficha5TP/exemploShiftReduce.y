%{


%}

%token IF THEN ELSE TOKEN EXP STM

%%

if_stmt:  IF expr THEN stmt
        | IF expr THEN stmt ELSE stmt
        ;


expr: EXP ;
stmt: if_stmt
      | STM ;

%%


void main()
{
  yyparse();
  return yynerrs;
}

int yyerror(char *s)
{
  printf("Erro sintactico/semantico: %s\n",n);
}
