%{
#include "calc.tab.h"
%} 
%option noinput 
%option nounput 
%option header-file="lex.yy.h"
%%
"print"            {return PRINT;}
"exit"             {return EXIT_COMMAND;}
#[0-9][0-9]?       {yylval.num = atoi(yytext+1); return IDENTIFIER;}
[0-9]+             {yylval.num = atoi(yytext); return NUMBER;}
[ \t\n]            ;
[-+=;]             {return yytext[0];}
.                  {printf("unexpected character");}

%%

