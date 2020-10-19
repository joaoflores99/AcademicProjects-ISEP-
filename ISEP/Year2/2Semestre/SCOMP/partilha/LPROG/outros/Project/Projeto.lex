%{    /*Projeto.fich.lex  */  
   #include "Projeto.tab.h"
%}

%option noinput 
%option nounput 
%option header-file="lex.yy.h" 
%%

(V8)|(V10)|(Anti-Rábica)|(Giardíase)|(Tosse)|(Leptospirose)				{ yylval.str = strdup(yytext); return VACINA;}
[A-Z][0-9]{4}         													{ yylval.str = strdup(yytext); return CODIGO;}
[A-Z][A-Za-z]*         													{ yylval.str = strdup(yytext); return NOME;}  
[1-9][0-9]*\.[0-9]														{ yylval.val = atof(yytext); return PESO;}  
(20[0-9][0-9])\/((0[1-9])|(1[0-2]))\/((0[1-9])|([1-2][0-9])|(3[0-1]))	{ yylval.str = strdup(yytext); return DATA;}  
(banho)|(massagem)|(tosquia)	 										{ yylval.str = strdup(yytext); return SERVICO;}  
[1-9][0-9]*\.[0-9]{2}													{ yylval.val = atof(yytext); return VALOR;}  
[1-9]																	{ yylval.qtd = atoi(yytext); return QUANTIDADE;} 
" "  																	{ yylval.str = strdup(yytext); return ESPACO;}
","  																	{ yylval.str = strdup(yytext); return VIRGULA;}
\n  																	{ yylval.str = strdup(yytext); return MUDA_LINHA;}
.              															{ printf("ERRO LEXICO SIMBOLO DESCONHECIDO (%s) \n" , yytext); }
%% 
  /* no code here */  
