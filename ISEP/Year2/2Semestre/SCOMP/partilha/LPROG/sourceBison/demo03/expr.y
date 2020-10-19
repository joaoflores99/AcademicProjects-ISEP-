%{  /* demo03/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
%}
%code requires { #define YYSTYPE float 
} 
%token FLOAT PLUS 
%%
   S   : S EXPR  
	 | 
         ; 
   EXPR :  FLOAT  { printf("Found a float: %f\n", $1) ; }
	   |  PLUS  { printf("Found a plus\n");}  
	; 	      
%%
int main() {
      return yyparse();
}
void  yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
