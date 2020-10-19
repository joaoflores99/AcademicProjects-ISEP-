%{
	char str2[]="valido";
	char str4[]="invalido";
%}

dd [A-Z][A-Z]
ll [0-9][0-9]

%option nounput
 
%%
{ll}-{ll}-{dd} {printf("%s \n",str2);}
{ll}-{dd}-{ll} {printf("%s \n",str2);}
{dd}-{ll}-{ll} {printf("%s \n",str2);}
%%

int main(){
	yylex( ) ;
	return 0;       
}
