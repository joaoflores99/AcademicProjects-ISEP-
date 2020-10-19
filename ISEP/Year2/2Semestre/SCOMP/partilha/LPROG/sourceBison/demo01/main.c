#include <stdio.h> 
#include "defs.h"    /* demo01/main.c  */
int yylval;  int yylex(); 
int main() 
  { 
    token tok; 	
    do { 
	     tok=yylex();  /*  call flex  */	
	     switch(tok) 
	     {     
		case INTEGER: 
	 	 printf("received the int: %d\n",yylval); break; 
		case PLUS: 
		 printf("received the plus token\n"); 
		default:  break; 	
	     } 
	} 
        while (tok!=0); 			      
	return 0; 
  } 	  
