%{    /* demo02/file.lex  */  
   #include "expr.tab.h"
%}

%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%

[0-9]+         { yylval = atoi(yytext); return INTEGER;}
\+             return PLUS;  
.              { /* ignore everything else */ }
%% 
  /* no code here */  
