%{  /* demo07/expr.y  */   
     #include "lex.yy.h" 
     void yyerror( char * s ); 
%} 
%token INTEGER SUM MUL  
%%
S : TOTAL  { printf("Total: %d\n",$1); }
      ; 
TOTAL : TOTAL LINE { $$=$1+$2;}  
	 |   {$$=0;} ; 
LINE : SUM INTEGER INTEGER '\n' { printf("Sum: %d\n", $2+$3);
							$$=$2+$3;}
    | MUL INTEGER INTEGER '\n' { printf("Mul: %d\n", $2*$3); 
							$$=$2*$3;}
	; 	      
%%
int main() {
      return yyparse();
}
void  yyerror(char *s){
	printf("Syntactic/semantic error: %s\n",s);
}
