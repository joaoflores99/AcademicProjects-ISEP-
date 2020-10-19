%{  /* demo02/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
%}
%token INTEGER PLUS 
%%
   S   : S EXPR  
	 | 
         ; 
   EXPR :  INTEGER  { printf("Found an int: %d\n", $1) ; }
	   |  PLUS  { printf("Found a plus\n");}  
	; 	      
%%
int main() {
      return yyparse();
}
void  yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
