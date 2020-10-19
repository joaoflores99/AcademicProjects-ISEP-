%{  /* demo06/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
%} 
%token INTEGER SUM MUL 
%%
   S   : S LINE  
	 | ; 
   LINE :  SUM INTEGER INTEGER '\n' { printf("Sum: %d\n", $2+$3) ; }
	 | MUL INTEGER INTEGER '\n' { printf("Mul: %d\n", $2*$3) ; }
	; 	      
%%
int main() {
      return yyparse();
}
void  yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
