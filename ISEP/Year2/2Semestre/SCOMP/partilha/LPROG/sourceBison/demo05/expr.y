%{  /* demo05/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
%}
%token INTEGER 
%%
   S   : S EXPR  
	 | ; 
   EXPR :  INTEGER  { printf("Found an int: %d\n", $1) ; }
	   | '+'   { printf("Found a plus\n");}  
	   | '-'   { printf("Found a minus\n");}  
	   | '*'   { printf("Found a multiply\n");}  
	   | '/'   { printf("Found a divide\n");}  
	; 	      
%%
int main() {
      return yyparse();
}
void  yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
