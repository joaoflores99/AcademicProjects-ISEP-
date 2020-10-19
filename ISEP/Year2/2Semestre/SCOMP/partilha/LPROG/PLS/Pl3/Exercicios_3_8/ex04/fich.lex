%{
 int letterCount=0; 
 int algCont=0;
 int linesCount=0;
 int spacesCount=0;
 int nothingCount=0;
%} 
%option nounput  
%%
[A-z] letterCount++; 
[0-9] algCont++;
[\n]  linesCount++;
[ \t] spacesCount++;
[&\*$#@\+\=\^\-\<\>\?\;\:\'\"\[\]\{\}\`\~\(\)] nothingCount++;
%%
             int main()
             {
              yylex( ) ;
              printf("Number of Letters : %d\n" , letterCount);
              printf("Number of Algarisms : %d\n" , algCont);
              printf("Number of lines : %d\n" , linesCount);
              printf("Number of spsces : %d\n" , spacesCount);
              printf("Number of nothing : %d\n" , nothingCount);
	      return 0;  
	      }
