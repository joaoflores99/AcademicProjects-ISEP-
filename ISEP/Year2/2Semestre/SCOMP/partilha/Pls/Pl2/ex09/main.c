#include "func.h"
#define SIZE 50000

int main(void){

	sales allSales[SIZE];	
	int produtos[SIZE];
	int i;
	for(i=0;i<SIZE;i++){
		 allSales[i].customer_code=1;
		 allSales[i].product_code=i+1;
		 allSales[i].quantity=i;
		}
	func(allSales,produtos);	
	return 0;
	}
