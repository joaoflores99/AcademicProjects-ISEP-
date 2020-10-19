%{    
   #include "expr.tab.h"
%}

%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%

[\t] 																	{}
\n 																		{ yylval.id = strdup(yytext); return MUDA_LINHA;}
[A-Z][0-9]{4}     														{ yylval.id=strdup(yytext);return CODIGO;}
[A-Z][a-z]+																{ yylval.id=strdup(yytext);return NOME;}  
[0-9]+\.[0-9]{2} 														{ yylval.real=atof(yytext);return PESO;}
20[0-9]{2}\/((0[1-9])|(1[0-2]))\/((0[1-9])|([1-2][0-9])|(3[0-1]))		{ yylval.id = strdup(yytext);return DATA;}
(banho)|(massagem)|(tosquia)											{ yylval.id=strdup(yytext);return SERVICO;}
[0-9]+\.[0-9]+  														{ yylval.real=atof(yytext);return VALOR;}
(V8)|(V10)|(Anti-Rábica)|(Giardíase)|(Tosse)|(Leptospirose) 			{ yylval.id=strdup(yytext);return VACINA;}
([1-9]+|[0][1-9]+)														{ yylval.inteiro=atoi(yytext);return QUANTIDADE;}
","																		{ yylval.id=strdup(yytext);return VIRGULA;}
" "																		{ yylval.id=strdup(yytext);return ESPACO;}
.              			    											{ printf("ERRO LEXIXO:SIMBOLO DESCONHECIDO %s \n" , yytext); }
<<EOF>>                	    											{return 0;}

%% 
  /* no code here */  
