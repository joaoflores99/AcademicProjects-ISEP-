.section .text
.global count_odd_matrix
	count_odd_matrix:
			#Prologue
				pushl %ebp
				movl %esp,%ebp

			#Body
				pushl %esi
				pushl %edi
				pushl %ebx
				 
				movl $0 , %eax
				movl $0 , %ebx

				movl 8(%ebp) , %esi
				movl 12(%ebp) , %ecx
				movl 16(%ebp) , %edx
				

				cmpl $0 , %ecx
				je end
				cmpl $0 , %edx
				je end
			 
			linhas:
			     cmpl %ebx , %ecx
			     je end
			     movl $0 , %edi
			     pushl %ecx
			     movl (%esi) , %ecx

			colunas:
			     cmpl %edi , %edx
			     je inc_linhas
			     pushl %edx
			     movl (%ecx , %edi , 4) , %edx
			     ANDL $1 , %edx
			     cmpl $1 , %edx
			     popl %edx
			     jne inc_colunas
		             incl %eax

			inc_colunas:
			     incl %edi
			     jmp colunas

			inc_linhas:
			     popl %ecx
			     addl $4 , %esi
			     incl %ebx
			     jmp linhas
			 
			 
			end:
							 
			# epilogue
			    popl %ebx
			    popl %edi
			    popl %esi
			    movl %ebp, %esp 
			    popl %ebp 
			    ret

				