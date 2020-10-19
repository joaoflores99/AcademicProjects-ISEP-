%{
 int aCount=0; 
%} 
%option nounput  
%%
a  { aCount++; printf("->%s<-",yytext); }  
.|\n  ;     /* any other char including newline will  disappear */ 
%%
             int main()
             {
              yylex( ) ;
              printf("Number of 'a's: %d\n" , aCount);
	      return 0;  
	      }
