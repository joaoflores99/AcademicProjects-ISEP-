%{  /* demo04/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
%} 
%union { 
         int number; 
         char op; 
       } 
%token <number> INTEGER 
%token <op> OPERATOR 
%%
   S   : S EXPR  
	 | 
         ; 
   EXPR :  INTEGER  { printf("Found an int: %d\n", $1) ; }
	   |  OPERATOR   { printf("Found an op: %c\n",$1);}  
	; 	      
%%
int main() {
      return yyparse();
}
void  yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
