PAT2 FEUP
PAT3 ISEP
PAT4 2007
PAT5 2008
%option nounput
%%
{PAT2} + printf("ISEP");
{PAT4} + printf("2008");
{PAT3} + printf("FEUP");
{PAT5} + printf("2007");

%%


int main() {
     yylex( ) ;
     return 0;
     }
