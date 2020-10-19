%{


%}

%token INTEIRO REAL ID

%left '<' '>' '=' DIF MEN_IG MAI_IG
%left '+' '-'
%left '*' '/'
%left '^'
%nonassoc MENOS_UNARIO

%%

regra:  /* vazio */ 
      | regra expressao '\n' {printf("resultado é %f\n",$2)}
      ;

expressao: expressao '+' expressao          {$$=$1+$3;}
         | expressao '-' expressao          {$$=$1-$3;}
         | expressao '*' expressao          {$$=$1*$3;}
         | expressao '/' expressao          {$$=$1/$3;}
         | '-' expressao %prec MENOS_UNARIO {$$=-$2;}
         | operando                         {$$=$1;}
         ;
operando:  INTEIRO                          {$$=$1;}
         | REAL                             {$$=$1;}
         | ID                               {$$=123;}
         ;
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
