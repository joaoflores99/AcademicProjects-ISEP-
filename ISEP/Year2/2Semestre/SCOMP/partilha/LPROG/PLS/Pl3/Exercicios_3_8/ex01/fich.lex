%{
 int aCount=0; 
%} 
%option nounput  
%%
[A-z] aCount++; 
%%
             int main()
             {
              yylex( ) ;
              printf("Number of 'a's: %d\n" , aCount);
	      return 0;  
	      }
