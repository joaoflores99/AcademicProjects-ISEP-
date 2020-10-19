.section .data
	.equ CHAR_OFFSET,4
	
.section .text
	.global approved_semester # int approved_semester(group *g)
	
	
	approved_semester:
		# prologue
		pushl %ebp      # save previous stack frame pointer
		movl  %esp,%ebp # the stack frame pointer for sum function
		pushl %ebx		# pushes ebx to the stack
		pushl %esi		# pushes esi to the stack
		
		movl $0, %ebx			# places value 0 in ebx
		movl $0, %esi			# places value 0 in esi
		movl $0, %eax			# places value 0 in eax
		movl 8(%ebp),%edx		# moves the number of the students to edx
		movl (%edx), %ecx		# places the number in ecx
		addl $CHAR_OFFSET, %edx	# adds the char offset to the pointer of pointer of char vector
		movl (%edx), %edx		# places pointed pointer in edx
		
		loop_alunos:
			pushl %ecx				# pushes ecx to the stack
			movl $8, %ecx			# places value 8 in ecx
			movzbl (%edx),%ebx		# places value pointed by edx in ebx
			
			loop_bits:	
				test $1, %ebx		# tests to see if last bit equals zero
				jz no_increment		# if it equals zero, jump to no_increment
				incl %eax			# increments eax
				
			no_increment:
				shrl %ebx			# shifts value of ebx to the right
				loop loop_bits		# loops loop_bits
			
			end_loop_bits:
				cmpl $4, %eax		# compares value 4 with eax
				jle not_approved	# jumps if less or equal to not_approved
				incl %esi			# increments esi
	
			not_approved:	
				incl %edx			# increments edx
				popl %ecx			# restores the previous value of ecx from the stack
				loop loop_alunos	# loops loop_alunos
			
		end:
			movl %esi, %eax			# places value of esi in eax (number of students that are approved)
			
			#epilogue	
			popl %esi	   # restores the previous value of esi from the stack	
			popl %ebx	   # restores the previous value of ebx from the stack
			movl %ebp,%esp # restore the previous stack pointer ("clear" the stack)
			popl %ebp      # restore the previous stack frame pointer
			ret			   # return from the fucntion
