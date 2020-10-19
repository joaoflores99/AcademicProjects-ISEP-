%{  /* demo10/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
%}
%token INTEGER  
%define  parse.trace 
%%
   LIST   : INTEGER   
	 | INTEGER ',' LIST 
         ; 
%%
int main() {
      yydebug=1; 
      return yyparse();
}
void  yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
