PAT1 aaa
PAT2 [a-z][a-z][a-z]
%option nounput  
%%
{PAT2}   printf("Pat2"); 
{PAT1}   printf("Pat1"); 
%%
             int main()
             {
              yylex( ) ;
	      return 0;  
	      }
