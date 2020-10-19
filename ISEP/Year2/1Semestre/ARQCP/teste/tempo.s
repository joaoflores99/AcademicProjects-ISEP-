.section .data
		.equ RESULTADOS_OFFSET,6
		.equ NUM_MARATONAS,2
			
.section .text 
	.global calcula_tempo

calcula_tempo:

#prologue
		pushl %ebp        
		movl %esp, %ebp  
		
		pushl %esi
		pushl %edi
		
		movl 8(%ebp), %esi		
		movl N_MARATONAS(%esi), %ecx
		imul $2, %ecx		
		movl RESULTADOS(%esi), %edi		
		
	loop:
		
		movl %edi, %edx 
		movl 4(%esi), %ebx
		subl %ebx,%edx
		cmpl %edx,%eax
		jg next
		movl %edx, %eax
		jump end
		  
		 next:
		 shrl $8(%edi)
		 loop loop  
	

end:
		popl %edi 
		popl %esi
#epilogue
		movl %ebp, %esp # restore the previous stack pointer ("clear" the stack)
		popl %ebp # restore the previous stack frame pointer
		ret
