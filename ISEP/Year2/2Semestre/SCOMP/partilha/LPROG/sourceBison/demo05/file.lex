%{    /* demo05/file.lex  */  
   #include "expr.tab.h"
%}

%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%

[0-9]+         { yylval = atoi(yytext); return INTEGER;}
[-+*/]         return yytext[0] ; /* the first/only char of yytext */  
.              { /* ignore everything else */ }
%% 
