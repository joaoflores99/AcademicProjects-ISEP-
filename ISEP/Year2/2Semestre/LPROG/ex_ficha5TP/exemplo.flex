%{
  #include"exemplo.tab.h" // header greado pelo bison
  extern int numErros;
%}


%%

,                        return yytext[0];
[0-9]+                   yylval.inteiro=atoi(yytext); return INT;
[0-9]+\.[0-9]+           yylval.real=atof(yytext); return REAL;
[_a-zA-Z][_a-zA-Z0-9]*   yylval.id=yytext; return ID;
[ \t]                    /* ignorado */

.                        printf("Erro lexico: simbolo desconhecido %s\n",yytext); numErros++;

\n                       return 0;
<<EOF>>                  return 0;

%%
