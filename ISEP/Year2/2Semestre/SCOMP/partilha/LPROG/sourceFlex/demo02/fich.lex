%{
 int aCount=0; 
%} 
%option nounput  
%%
a  { aCount++; printf("->%s<-",yytext); } 
%%
             int main()
             {
              yylex( ) ;
              printf("Number of 'a's: %d\n" , aCount);
	      return 0;  
	      }
