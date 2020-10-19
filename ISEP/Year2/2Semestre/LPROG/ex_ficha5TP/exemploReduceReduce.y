%{


%}

%token TOKEN

%%

sequencia: /* vazio */ { printf ("palavra vazio\n"); }
	  | talvez_palavra
	  | sequencia palavra { printf ("adicionada palavra %s\n", $2); }
	  ;

talvez_palavra: /* vazio */ { printf ("talvez_palavra vazio\n"); }
                | palavra { printf ("palavra simples %s\n", $1); }
		;

palavra: TOKEN ;


%%


void main()
{
  yyparse();
  return yynerrs;
}

int yyerror(char *s)
{
  printf("Erro sintactico/semantico: %s\n",n);
}
