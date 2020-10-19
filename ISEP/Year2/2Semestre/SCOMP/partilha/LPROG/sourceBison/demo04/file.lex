%{    /* demo04/file.lex  */  
   #include "expr.tab.h"
%}
%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%
[0-9]+         { yylval.number=atoi(yytext); return INTEGER;}
[-+*/]         { yylval.op=yytext[0];  return OPERATOR; }  
.              { /* ignore everything else */ }
%% 
