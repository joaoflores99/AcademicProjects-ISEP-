%{    
   #include "expr.tab.h"
%}

%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%

(V8)|(V10)|(Anti-Rabica)|(Giardiase)|(Tosse)|(Leptospirose) 			{ yylval.id=strdup(yytext);return VACINA;}
[A-Z][0-9]{4}     														{ yylval.id=strdup(yytext);return CODIGO;}
(0|[1-9][0-9]*).[0-9]													{ yylval.real=atof(yytext);return PESO;}
20[0-9]{2}\/((0[1-9])|(1[0-2]))\/((0[1-9])|([1-2][0-9])|(3[0-1]))		{ yylval.id = strdup(yytext);return DATA;}
(banho)|(massagem)|(tosquia)											{ yylval.id=strdup(yytext);return SERVICO;}
(0|[1-9][0-9]*).[0-9]+  												{ yylval.real=atof(yytext);return VALOR;}
[1-9]																	{ yylval.inteiro=atoi(yytext);return QUANTIDADE;}
[A-Z][a-z]+																{ yylval.id=strdup(yytext);return NOME;} 
[ \t\r] 																
[,\n] 																	{ return yytext[0];}
.              			    											{ printf("ERRO LEXIXO:SIMBOLO DESCONHECIDO %s \n" , yytext); }
<<EOF>>                	    											{return 0;}

%% 
  /* no code here */  
