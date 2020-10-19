PAT2 aa
PAT3 aaa
PAT4 aaaa
%option nounput  
%%
{PAT2}   printf("Pat2"); 
{PAT3}   printf("Pat3"); 
{PAT4}   printf("Pat4"); 
%%
             int main()
             {
              yylex( ) ;
	      return 0;  
	      }
