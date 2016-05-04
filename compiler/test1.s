.data
.comm	a,4,4

.text
	.align 4
.globl  foo
foo:
foo_bb3:
foo_bb4:
	movl	$1, %EAX
foo_bb2:
	ret
.globl  main
main:
main_bb3:
main_bb4:
	movl	$5, %EAX
	movl	%EAX, a(%RIP)
	movl	a(%RIP), %EAX
	movl	$1, %EDI
	addl	%EDI, %EAX
	movl	%EAX, %ESI
	movl	a(%RIP), %EAX
	movl	a(%RIP), %EDI
	movl	$0, %EDX
	idivl	%EDI, %EAX
	movl	$3, %EDI
	imull	%EDI, %EAX
	movl	%EAX, %EDI
	movl	%ESI, %EAX
	subl	%EDI, %EAX
	movl	$4, %EDI
	addl	%EDI, %EAX
	movl	%EAX, a(%RIP)
	call	foo
	movl	%EAX, %ESI
	movl	a(%RIP), %EDI
	movl	$0, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb7
main_bb5:
	movl	$1, %EAX
	movl	%EAX, a(%RIP)
main_bb6:
	movl	$1, %EAX
	cmpl	%EAX, %ESI
	jle	main_bb9
main_bb8:
	movl	$1, %EDI
	movl	%ESI, %EAX
	subl	%EDI, %EAX
	movl	%EAX, %ESI
	movl	$1, %EAX
	cmpl	%EAX, %ESI
	jg	main_bb8
main_bb9:
	movl	$10, %EAX
	movl	$0, %EAX
main_bb2:
	ret
main_bb7:
	movl	$5, %EAX
	movl	%EAX, %ESI
	jmp	main_bb6
