.section .data 
		.equ RESULTADOS_OFFSET,6
		.equ NUM_MARATONAS,2
		.equ  ID_CAND ,0
		.equ SIZE_CAND_ESTR, 10
		
.section .text 
		.global qualificados
		
qualificados:


		# prologue
		pushl %ebp        # save previous stack frame pointer
		movl %esp, %ebp   # the stack frame pointer 
		
		pushl %esi 
		pushl %edi 
		pushl %edx 
		
		
		movl  8(%ebp), %edi     #   array com o id dos classificados   
		movl 12(%ebp), %ebx	   # tempo min para ser qualificado
		movl 16(%ebp), %ecx		#num candidatos
		movl 20(%ebp), %esi    # endereco do vetor de candidatos
		
		loop:
			pushl %edi 
			pushl %esi 
			call calcula_tempos
			popl %esi 
			popl %edi 			
			cmpl %eax, %ebx
			jle aprovado
			
		aprovado:
		   movl ID_CAND(%esi), %edi
		   addl SIZE_CAND_ESTR
		   loop loop 	
		
			
end:
		popl
		popl 
		# epilogue
		movl %ebp, %esp # restore the previous stack pointer ("clear" the stack)
		popl %ebp # restore the previous stack frame pointer
		ret
