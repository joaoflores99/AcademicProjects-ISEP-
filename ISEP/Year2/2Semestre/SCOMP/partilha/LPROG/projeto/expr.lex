%{    
   #include "expr.tab.h"
   /*extern int numErros;*/
%}

A (banho)|(massagem)|(tosquia);
B (V8)|(V10)|(Anti-Rábica)|(Giardíase)|(Tosse)|(Leptospirose); 

%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%

[\t] 																	{}
(V8)|(V10)|(Anti-Rábica)|(Giardíase)|(Tosse)|(Leptospirose)				{ yylval.id=strdup(yytext); return VACINA;}
\n 																		{ yylval.id=strdup(yytext); return ENTER;}
[A-Z][0-9]{4}     														{ yylval.id=strdup(yytext); return CODIGO;}
[A-Z][A-Za-z]*															{ yylval.id=strdup(yytext); return NOME;}  
[1-9][0-9]*\.[0-9] 													{ yylval.real=atof(yytext); return PESO;}
20[0-9]{2}\/((0[1-9])|(1[0-2]))\/((0[1-9])|([1-2][0-9])|(3[0-1]))		{ yylval.id = strdup(yytext); return DATA;}
(banho)|(massagem)|(tosquia)											{ yylval.id=strdup(yytext); return SERVICO;}
[1-9][0-9]*\.[0-9]{2}  												{ yylval.real=atof(yytext); return VALOR;}
[1-9][0-9]*																{ yylval.inteiro=atoi(yytext); return QUANTIDADE;}
","																		{ yylval.id=strdup(yytext); return VIRGULA;}
" "																		{ yylval.id=strdup(yytext); return ESPACO;}
.              			    											{ printf("ERRO LEXIXO OU SIMBOLO DESCONHECIDO OU CARATER DESCONHECIDO (%s) \n" , yytext);}
<<EOF>>                	    											{return 0;}

%% 
  /* no code here */  
