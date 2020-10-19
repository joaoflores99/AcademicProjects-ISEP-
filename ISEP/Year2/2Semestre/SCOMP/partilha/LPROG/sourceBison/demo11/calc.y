%{
#include <stdio.h>     /* C declarations used in actions */
#include <stdlib.h>
// #include <ctype.h>    
#include "lex.yy.h"
int memory[100];
void yyerror (char *s);
%}
%union {int num; char id;}     
%start LINE
%token PRINT
%token EXIT_COMMAND
%token <num> NUMBER
%token <num> IDENTIFIER
%type <num> LINE EXPR TERM  COMMAND 
%type <id> ASSIGNMENT

%%
LINE    : COMMAND ';'
	| LINE COMMAND ';'
	;  

COMMAND : ASSIGNMENT         {;}
        | EXIT_COMMAND       {exit(EXIT_SUCCESS);}
        | PRINT EXPR         {printf("Printing %d\n", $2);}
        ;
ASSIGNMENT : IDENTIFIER '=' EXPR  { memory[$1]=$3; }
                        ;
EXPR     : TERM                  {$$ = $1;}
        | EXPR '+' TERM          {$$ = $1 + $3;}
        | EXPR '-' TERM          {$$ = $1 - $3;}
        ;
TERM    : NUMBER                {$$ = $1;}
        | IDENTIFIER            {$$ = memory[$1];} 
        ;

%%                    
int main (void) { 
        return yyparse ( );
}
void yyerror (char *s) {fprintf (stderr, "%s\n", s);} 


