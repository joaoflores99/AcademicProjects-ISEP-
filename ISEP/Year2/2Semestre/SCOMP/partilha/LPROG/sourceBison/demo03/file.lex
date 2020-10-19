%{    /* demo03/file.lex  */  
   #include "expr.tab.h"
%}

%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%

[0-9]+\.[0-9]+      { yylval = atof(yytext); return FLOAT;}
\+             return PLUS;  
.              { /* ignore everything else */ }
%% 
  /* no code here */  
