%{    /* demo01/file.lex  */ 
     #include "defs.h"
     extern int yylval; 
%}

%option noinput 
%option nounput 
%%

[0-9]+         { yylval = atoi(yytext); return INTEGER;}
\+             return PLUS;
<<EOF>>        return 0;   /* file has ended -- not needed */ 
.              { /* ignore everything else */ }
%% 
  /* no code here */  
