#include <stdio.h> 
#include "defs.h"   
%%
  int main() {
 yylex( ) ;
 printf("Number of ’a’s: %d\n" , aCount);
 return 0;
 }
