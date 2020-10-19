PAT1 aaa
PAT2 [a-z][a-z][a-z]
%option nounput  
%%
{PAT1}   printf("Pat1"); 
{PAT2}   printf("Pat2"); 
%%
             int main()
             {
              yylex( ) ;
	      return 0;  
	      }
