%{
	#include"miaumiau.tab.h"
	extern int numErros;
%}

%%

[A-Z][0-9]{4}													  yylval.string = strdup(yytext);return CODIGO;
[A-Z][a-zA-Z]* 													  yylval.string = strdup(yytext);return NOME;
[0-9]+\.[0-9] 													  yylval.real=atof(yytext);return PESO;
20[0-9]{2}\/((0[1-9])|(1[0-2]))\/((0[1-9])|([1-2][0-9])|(3[0-1])) yylval.string = strdup(yytext);return DATA;
banho|massagem|tosquia										      yylval.string = strdup(yytext);return SERVICO;
[0-9]+\.[0-9]{2}												  yylval.real=atof(yytext);return VALOR;
V8|V10|Anti-Rábica|Giardíase|Tosse|Leptospirose					  yylval.string = strdup(yytext);return VACINA;
[1-9]															  yylval.quantidade=atoi(yytext);return QUANTIDADE;
, return VIRGULA;
[ \t]

.                        printf("Erro lexico: simbolo desconhecido %s\n",yytext); numErros++;

\n                       return 0;
<<EOF>>                  return 0;

%%
