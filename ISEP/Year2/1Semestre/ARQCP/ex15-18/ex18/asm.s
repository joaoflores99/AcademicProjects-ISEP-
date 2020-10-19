.section .data
	.equ STRUCT_OFFSET,28
	.equ UNION_OFFSET,20
.section .text

.global return_unionB_b #char return_unionB_b(structA **matrix, int i, int j)

return_unionB_b:
	# prologue
		pushl %ebp      # save previous stack frame pointer
		movl  %esp,%ebp # the stack frame pointer for sum function
		
		subl $8,%esp	# creates 2 local variables
		pushl %ebx		# saves ebx in the stack
		pushl %esi		# saves esi in the stack
		movl $3,-4(%ebp) # number of columns
		movl $4,-8(%ebp) # number of lines
		movl $0,%eax		# places 0 in eax		
		movl 8(%ebp),%edx # matrix	
		movl 12(%ebp),%ecx # line position
		cmp -8(%ebp),%ecx	# checks if it is a valid line
		jge end				# jumps to end if it is not valid
		cmp $0,%ecx			# checks if it is a valid line
		jl end				# jumps to end if it is not valid
		movl 16(%ebp),%ebx # column position
		cmp -4(%ebp),%ebx	# checks if it is a valid column
		jge end				# jumps to end if it is not valid
		cmp $0,%ebx			# checks if it is a valid column
		jl end				# jumps to end if it is not valid
		
		
		movl (%edx),%esi	# 1st element
		
		imull -4(%ebp),%ecx	# line position*number of columns
		addl %ebx,%ecx		# adds column position
		imull $STRUCT_OFFSET,%ecx	# multiplies by size of the structure
		addl %ecx,%esi				# structA at line position column position
		movb UNION_OFFSET(%esi),%al	#  returns union from the structA at line position column position
		
		end:
			#epilogue
			popl %esi		# restores esi from the stack
			popl %ebx		# restores ebx from the stack
			movl %ebp,%esp # restore the previous stack pointer ("clear" the stack)
			popl %ebp      # restore the previous stack frame pointer
			ret			   # return from the fucntion
