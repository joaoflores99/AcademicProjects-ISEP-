%{    /* demo08/file.lex  */  
   #include "expr.tab.h"
%}
%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%
[0-9]+         { yylval=atoi(yytext); return INTEGER;}
mul            return MUL; 
sum            return SUM; 
\n             return yytext[0];    
.              { /* ignore everything else */ }
%% 
