	.text
	.globl main
main:
	move $fp, $sp
	subu $sp, $sp, 24
	sw $ra, -4($fp)
	li $a0 4
	jal _halloc
	move $t0 $v0
	move $t1 $t0
	la $t0 LL_Start
	sw $t0, 0($t1)
	li $a0 4
	jal _halloc
	move $t0 $v0
	move $t2 $t0
	sw $t1, 0($t2)
	move $t0 $t2
	lw $t1 0($t0)
	lw $t2 0($t1)
	li $t1 4
	mul $t3, $t1, 0
	move $a0 $t3
	jal _halloc
	move $t1 $v0
	move $t3 $t1
	move $a0 $t0
	move $a1 $t3
	jalr $t2
	move $t1 $v0
	move $a0 $t1
	jal _print
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 24
	j $ra
	
	.text
	.globl Element_Init
Element_Init:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 28
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	lw $t3 4($t0)
	lw $t4 8($t0)
	sw $t2, 4($t1)
	sw $t3, 8($t1)
	sw $t4, 12($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 28
	j $ra
	
	.text
	.globl Element_GetAge
Element_GetAge:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 4($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl Element_GetSalary
Element_GetSalary:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 8($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl Element_GetMarried
Element_GetMarried:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 12($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl Element_Equal
Element_Equal:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 40
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	sw $t7, 28($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	li $t0 1
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 4($t4)
	li $t4 4
	mul $t6, $t4, 0
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t3 $t4
	move $t4 $t1
	lw $t5 0($t4)
	lw $t6 20($t5)
	li $t5 4
	mul $t7, $t5, 2
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	sw $t3, 0($t7)
	lw $t3 4($t1)
	sw $t3, 4($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t3 $v0
	slt $t4, $t3, 1
	move $t3 $t4
	beqz $t3 Element_Equal_L0
	li $t0 0
	j Element_Equal_L1
Element_Equal_L0:	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 8($t4)
	li $t4 4
	mul $t6, $t4, 0
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t3 $t4
	move $t4 $t1
	lw $t5 0($t4)
	lw $t6 20($t5)
	li $t5 4
	mul $t7, $t5, 2
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	sw $t3, 0($t7)
	lw $t3 8($t1)
	sw $t3, 4($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t3 $v0
	slt $t4, $t3, 1
	move $t3 $t4
	beqz $t3 Element_Equal_L2
	li $t0 0
	j Element_Equal_L3
Element_Equal_L2:	lw $t3 12($t1)
	move $t1 $t3
	beqz $t1 Element_Equal_L4
	move $t1 $t2
	lw $t3 0($t1)
	lw $t4 12($t3)
	li $t3 4
	mul $t5, $t3, 0
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $a0 $t1
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	slt $t1, $t3, 1
	move $t3 $t1
	beqz $t3 Element_Equal_L5
	li $t0 0
	j Element_Equal_L6
Element_Equal_L5:	li $t1 0
Element_Equal_L6:	nop
	j Element_Equal_L7
Element_Equal_L4:	move $t3 $t2
	lw $t2 0($t3)
	lw $t4 12($t2)
	li $t2 4
	mul $t5, $t2, 0
	move $a0 $t5
	jal _halloc
	move $t2 $v0
	move $t5 $t2
	move $a0 $t3
	move $a1 $t5
	jalr $t4
	move $t2 $v0
	move $t3 $t2
	beqz $t3 Element_Equal_L8
	li $t0 0
	j Element_Equal_L9
Element_Equal_L8:	li $t1 0
Element_Equal_L9:	nop
Element_Equal_L7:	nop
Element_Equal_L3:	nop
Element_Equal_L1:	nop
	move $v0 $t0
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $t6,24($sp)
	lw $t7,28($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 40
	j $ra
	
	.text
	.globl Element_Compare
Element_Compare:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 28
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	move $a0 $a0
	move $t0 $a1
	lw $t1 0($t0)
	lw $t2 4($t0)
	li $t0 0
	add $t3, $t2, 1
	move $t4 $t3
	slt $t3, $t1, $t2
	move $t2 $t3
	beqz $t2 Element_Compare_L10
	li $t0 0
	j Element_Compare_L11
Element_Compare_L10:	slt $t2, $t1, $t4
	slt $t1, $t2, 1
	move $t2 $t1
	beqz $t2 Element_Compare_L12
	li $t0 0
	j Element_Compare_L13
Element_Compare_L12:	li $t0 1
Element_Compare_L13:	nop
Element_Compare_L11:	nop
	move $v0 $t0
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 28
	j $ra
	
	.text
	.globl List_Init
List_Init:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	li $t1 1
	sw $t1, 12($t0)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl List_InitNew
List_InitNew:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 28
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	lw $t3 4($t0)
	lw $t4 8($t0)
	sw $t4, 12($t1)
	sw $t2, 4($t1)
	sw $t3, 8($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 28
	j $ra
	
	.text
	.globl List_Insert
List_Insert:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 36
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	move $t0 $t1
	li $a0 40
	jal _halloc
	move $t1 $v0
	move $t3 $t1
	la $t1 List_Delete
	sw $t1, 16($t3)
	la $t1 List_Print
	sw $t1, 36($t3)
	la $t1 List_Init
	sw $t1, 0($t3)
	la $t1 List_GetElem
	sw $t1, 28($t3)
	la $t1 List_GetNext
	sw $t1, 32($t3)
	la $t1 List_SetNext
	sw $t1, 12($t3)
	la $t1 List_Search
	sw $t1, 20($t3)
	la $t1 List_GetEnd
	sw $t1, 24($t3)
	la $t1 List_InitNew
	sw $t1, 4($t3)
	la $t1 List_Insert
	sw $t1, 8($t3)
	li $a0 16
	jal _halloc
	move $t1 $v0
	move $t4 $t1
	li $t1 0
	sw $t1, 4($t4)
	li $t1 0
	sw $t1, 8($t4)
	li $t1 0
	sw $t1, 12($t4)
	sw $t3, 0($t4)
	move $t1 $t4
	move $t3 $t1
	lw $t4 0($t3)
	lw $t5 4($t4)
	li $t4 4
	mul $t6, $t4, 3
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	sw $t2, 0($t6)
	sw $t0, 4($t6)
	li $t0 0
	sw $t0, 8($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t0 $v0
	move $t2 $t0
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $t6,24($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 36
	j $ra
	
	.text
	.globl List_SetNext
List_SetNext:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 20
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	sw $t2, 8($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 20
	j $ra
	
	.text
	.globl List_Delete
List_Delete:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 72
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	sw $t7, 28($sp)
	sw $s0, 32($sp)
	sw $s1, 36($sp)
	sw $s2, 40($sp)
	sw $s3, 44($sp)
	sw $s4, 48($sp)
	sw $s5, 52($sp)
	sw $s6, 56($sp)
	sw $s7, 60($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	move $t0 $t1
	li $t3 0
	li $t4 0
	sub $t5, $t4, 1
	move $t4 $t5
	move $t5 $t1
	move $t6 $t1
	lw $t7 12($t1)
	move $s0 $t7
	lw $t7 4($t1)
	move $t1 $t7
	j List_Delete_L14
List_Delete_L14:	li $t7 0
	slt $s1, $s0, 1
	slt $s2, $t7, $s1
	move $s1 $s2
	beqz $s1 List_Delete_L15
	li $s1 0
	slt $s2, $t3, 1
	slt $s3, $s1, $s2
	move $s1 $s3
	beqz $s1 List_Delete_L15
	li $s1 1
	j List_Delete_L16
List_Delete_L15:	li $s1 0
List_Delete_L16:	nop
	move $s2 $s1
	beqz $s2 List_Delete_L17
	move $s1 $t2
	lw $s2 0($s1)
	lw $s3 16($s2)
	li $s2 4
	mul $s4, $s2, 1
	move $a0 $s4
	jal _halloc
	move $s2 $v0
	move $s4 $s2
	sw $t1, 0($s4)
	move $a0 $s1
	move $a1 $s4
	jalr $s3
	move $s2 $v0
	move $s1 $s2
	beqz $s1 List_Delete_L18
	li $t3 1
	slt $s1, $t4, 0
	move $s2 $s1
	beqz $s2 List_Delete_L19
	move $s1 $t5
	lw $s2 0($s1)
	lw $s3 32($s2)
	li $s2 4
	mul $s4, $s2, 0
	move $a0 $s4
	jal _halloc
	move $s2 $v0
	move $s4 $s2
	move $a0 $s1
	move $a1 $s4
	jalr $s3
	move $s2 $v0
	move $t0 $s2
	j List_Delete_L20
List_Delete_L19:	li $s1 0
	sub $s2, $s1, 555
	move $a0 $s2
	jal _print
	move $s1 $t6
	lw $s2 0($s1)
	lw $s3 12($s2)
	li $s2 4
	mul $s4, $s2, 1
	move $a0 $s4
	jal _halloc
	move $s2 $v0
	move $s4 $s2
	move $s2 $t5
	lw $s5 0($s2)
	lw $s6 32($s5)
	li $s5 4
	mul $s7, $s5, 0
	move $a0 $s7
	jal _halloc
	move $s5 $v0
	move $s7 $s5
	move $a0 $s2
	move $a1 $s7
	jalr $s6
	move $s5 $v0
	sw $s5, 0($s4)
	move $a0 $s1
	move $a1 $s4
	jalr $s3
	move $s2 $v0
	move $s1 $s2
	li $s1 0
	sub $s2, $s1, 555
	move $a0 $s2
	jal _print
List_Delete_L20:	nop
	j List_Delete_L21
List_Delete_L18:	li $s1 0
List_Delete_L21:	nop
	slt $s2, $t3, 1
	move $s3 $s2
	beqz $s3 List_Delete_L22
	move $t6 $t5
	move $s2 $t5
	lw $s3 0($s2)
	lw $s4 32($s3)
	li $s3 4
	mul $s5, $s3, 0
	move $a0 $s5
	jal _halloc
	move $s3 $v0
	move $s5 $s3
	move $a0 $s2
	move $a1 $s5
	jalr $s4
	move $s3 $v0
	move $t5 $s3
	move $s2 $t5
	lw $s3 0($s2)
	lw $s4 24($s3)
	li $s3 4
	mul $s5, $s3, 0
	move $a0 $s5
	jal _halloc
	move $s3 $v0
	move $s5 $s3
	move $a0 $s2
	move $a1 $s5
	jalr $s4
	move $s3 $v0
	move $s0 $s3
	move $s2 $t5
	lw $s3 0($s2)
	lw $s4 28($s3)
	li $s3 4
	mul $s5, $s3, 0
	move $a0 $s5
	jal _halloc
	move $s3 $v0
	move $s5 $s3
	move $a0 $s2
	move $a1 $s5
	jalr $s4
	move $s3 $v0
	move $t1 $s3
	li $t4 1
	j List_Delete_L23
List_Delete_L22:	li $s1 0
List_Delete_L23:	nop
	j List_Delete_L14
List_Delete_L17:	nop
	move $v0 $t0
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $t6,24($sp)
	lw $t7,28($sp)
	lw $s0,32($sp)
	lw $s1,36($sp)
	lw $s2,40($sp)
	lw $s3,44($sp)
	lw $s4,48($sp)
	lw $s5,52($sp)
	lw $s6,56($sp)
	lw $s7,60($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 72
	j $ra
	
	.text
	.globl List_Search
List_Search:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 48
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	sw $t7, 28($sp)
	sw $s0, 32($sp)
	sw $s1, 36($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	li $t0 0
	move $t3 $t1
	lw $t4 12($t1)
	move $t5 $t4
	lw $t4 4($t1)
	move $t1 $t4
	j List_Search_L24
List_Search_L24:	slt $t4, $t5, 1
	move $t6 $t4
	beqz $t6 List_Search_L25
	move $t6 $t2
	lw $t7 0($t6)
	lw $s0 16($t7)
	li $t7 4
	mul $s1, $t7, 1
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	sw $t1, 0($s1)
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t6 $t7
	beqz $t6 List_Search_L26
	li $t0 1
	j List_Search_L27
List_Search_L26:	li $t6 0
List_Search_L27:	nop
	move $t6 $t3
	lw $t7 0($t6)
	lw $s0 32($t7)
	li $t7 4
	mul $s1, $t7, 0
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t3 $t7
	move $t6 $t3
	lw $t7 0($t6)
	lw $s0 24($t7)
	li $t7 4
	mul $s1, $t7, 0
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t5 $t7
	move $t6 $t3
	lw $t7 0($t6)
	lw $s0 28($t7)
	li $t7 4
	mul $s1, $t7, 0
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t1 $t7
	j List_Search_L24
List_Search_L25:	nop
	move $v0 $t0
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $t6,24($sp)
	lw $t7,28($sp)
	lw $s0,32($sp)
	lw $s1,36($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 48
	j $ra
	
	.text
	.globl List_GetEnd
List_GetEnd:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 12($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl List_GetElem
List_GetElem:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 4($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl List_GetNext
List_GetNext:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 8($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl List_Print
List_Print:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 40
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	sw $t7, 28($sp)
	move $t0 $a0
	move $a1 $a1
	move $t1 $t0
	lw $t2 12($t0)
	move $t3 $t2
	lw $t2 4($t0)
	move $t0 $t2
	j List_Print_L28
List_Print_L28:	slt $t2, $t3, 1
	move $t4 $t2
	beqz $t4 List_Print_L29
	move $t4 $t0
	lw $t5 0($t4)
	lw $t6 4($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $a0 $t5
	jal _print
	move $t4 $t1
	lw $t5 0($t4)
	lw $t6 32($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t1 $t5
	move $t4 $t1
	lw $t5 0($t4)
	lw $t6 24($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t3 $t5
	move $t4 $t1
	lw $t5 0($t4)
	lw $t6 28($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t0 $t5
	j List_Print_L28
List_Print_L29:	nop
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $t6,24($sp)
	lw $t7,28($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 40
	j $ra
	
	.text
	.globl LL_Start
LL_Start:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 44
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	sw $t7, 28($sp)
	sw $s0, 32($sp)
	move $a0 $a0
	move $a1 $a1
	li $a0 40
	jal _halloc
	move $t0 $v0
	move $t1 $t0
	la $t0 List_Delete
	sw $t0, 16($t1)
	la $t0 List_Print
	sw $t0, 36($t1)
	la $t0 List_Init
	sw $t0, 0($t1)
	la $t0 List_GetElem
	sw $t0, 28($t1)
	la $t0 List_GetNext
	sw $t0, 32($t1)
	la $t0 List_SetNext
	sw $t0, 12($t1)
	la $t0 List_Search
	sw $t0, 20($t1)
	la $t0 List_GetEnd
	sw $t0, 24($t1)
	la $t0 List_InitNew
	sw $t0, 4($t1)
	la $t0 List_Insert
	sw $t0, 8($t1)
	li $a0 16
	jal _halloc
	move $t0 $v0
	move $t2 $t0
	li $t0 0
	sw $t0, 4($t2)
	li $t0 0
	sw $t0, 8($t2)
	li $t0 0
	sw $t0, 12($t2)
	sw $t1, 0($t2)
	move $t0 $t2
	move $t1 $t0
	lw $t2 0($t1)
	lw $t3 0($t2)
	li $t2 4
	mul $t4, $t2, 0
	move $a0 $t4
	jal _halloc
	move $t2 $v0
	move $t4 $t2
	move $a0 $t1
	move $a1 $t4
	jalr $t3
	move $t2 $v0
	move $t1 $t2
	move $t2 $t0
	move $t0 $t2
	lw $t3 0($t0)
	lw $t4 0($t3)
	li $t3 4
	mul $t5, $t3, 0
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $a0 $t0
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t0 $t2
	lw $t3 0($t0)
	lw $t4 36($t3)
	li $t3 4
	mul $t5, $t3, 0
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $a0 $t0
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	li $a0 24
	jal _halloc
	move $t0 $v0
	move $t3 $t0
	la $t0 Element_GetSalary
	sw $t0, 8($t3)
	la $t0 Element_Init
	sw $t0, 0($t3)
	la $t0 Element_GetAge
	sw $t0, 4($t3)
	la $t0 Element_Equal
	sw $t0, 16($t3)
	la $t0 Element_GetMarried
	sw $t0, 12($t3)
	la $t0 Element_Compare
	sw $t0, 20($t3)
	li $a0 16
	jal _halloc
	move $t0 $v0
	move $t4 $t0
	li $t0 0
	sw $t0, 8($t4)
	li $t0 0
	sw $t0, 12($t4)
	li $t0 0
	sw $t0, 4($t4)
	sw $t3, 0($t4)
	move $t0 $t4
	move $t3 $t0
	lw $t4 0($t3)
	lw $t5 0($t4)
	li $t4 4
	mul $t6, $t4, 3
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	li $t4 25
	sw $t4, 0($t6)
	li $t4 37000
	sw $t4, 4($t6)
	li $t4 0
	sw $t4, 8($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t1 $t4
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 8($t4)
	li $t4 4
	mul $t6, $t4, 1
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	sw $t0, 0($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t2 $t4
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 36($t4)
	li $t4 4
	mul $t6, $t4, 0
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t1 $t4
	li $a0 10000000
	jal _print
	li $a0 24
	jal _halloc
	move $t3 $v0
	move $t4 $t3
	la $t3 Element_GetSalary
	sw $t3, 8($t4)
	la $t3 Element_Init
	sw $t3, 0($t4)
	la $t3 Element_GetAge
	sw $t3, 4($t4)
	la $t3 Element_Equal
	sw $t3, 16($t4)
	la $t3 Element_GetMarried
	sw $t3, 12($t4)
	la $t3 Element_Compare
	sw $t3, 20($t4)
	li $a0 16
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 0
	sw $t3, 8($t5)
	li $t3 0
	sw $t3, 12($t5)
	li $t3 0
	sw $t3, 4($t5)
	sw $t4, 0($t5)
	move $t0 $t5
	move $t3 $t0
	lw $t4 0($t3)
	lw $t5 0($t4)
	li $t4 4
	mul $t6, $t4, 3
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	li $t4 39
	sw $t4, 0($t6)
	li $t4 42000
	sw $t4, 4($t6)
	li $t4 1
	sw $t4, 8($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t1 $t4
	move $t3 $t0
	move $t4 $t2
	lw $t5 0($t4)
	lw $t6 8($t5)
	li $t5 4
	mul $t7, $t5, 1
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	sw $t0, 0($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t2 $t5
	move $t4 $t2
	lw $t5 0($t4)
	lw $t6 36($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t1 $t5
	li $a0 10000000
	jal _print
	li $a0 24
	jal _halloc
	move $t4 $v0
	move $t5 $t4
	la $t4 Element_GetSalary
	sw $t4, 8($t5)
	la $t4 Element_Init
	sw $t4, 0($t5)
	la $t4 Element_GetAge
	sw $t4, 4($t5)
	la $t4 Element_Equal
	sw $t4, 16($t5)
	la $t4 Element_GetMarried
	sw $t4, 12($t5)
	la $t4 Element_Compare
	sw $t4, 20($t5)
	li $a0 16
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	li $t4 0
	sw $t4, 8($t6)
	li $t4 0
	sw $t4, 12($t6)
	li $t4 0
	sw $t4, 4($t6)
	sw $t5, 0($t6)
	move $t0 $t6
	move $t4 $t0
	lw $t5 0($t4)
	lw $t6 0($t5)
	li $t5 4
	mul $t7, $t5, 3
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	li $t5 22
	sw $t5, 0($t7)
	li $t5 34000
	sw $t5, 4($t7)
	li $t5 0
	sw $t5, 8($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t1 $t5
	move $t4 $t2
	lw $t5 0($t4)
	lw $t6 8($t5)
	li $t5 4
	mul $t7, $t5, 1
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	sw $t0, 0($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t2 $t5
	move $t4 $t2
	lw $t5 0($t4)
	lw $t6 36($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t1 $t5
	li $a0 24
	jal _halloc
	move $t4 $v0
	move $t5 $t4
	la $t4 Element_GetSalary
	sw $t4, 8($t5)
	la $t4 Element_Init
	sw $t4, 0($t5)
	la $t4 Element_GetAge
	sw $t4, 4($t5)
	la $t4 Element_Equal
	sw $t4, 16($t5)
	la $t4 Element_GetMarried
	sw $t4, 12($t5)
	la $t4 Element_Compare
	sw $t4, 20($t5)
	li $a0 16
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	li $t4 0
	sw $t4, 8($t6)
	li $t4 0
	sw $t4, 12($t6)
	li $t4 0
	sw $t4, 4($t6)
	sw $t5, 0($t6)
	move $t4 $t6
	move $t5 $t4
	lw $t6 0($t5)
	lw $t7 0($t6)
	li $t6 4
	mul $s0, $t6, 3
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	li $t6 27
	sw $t6, 0($s0)
	li $t6 34000
	sw $t6, 4($s0)
	li $t6 0
	sw $t6, 8($s0)
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	move $t1 $t6
	move $t5 $t2
	lw $t6 0($t5)
	lw $t7 20($t6)
	li $t6 4
	mul $s0, $t6, 1
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	sw $t3, 0($s0)
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	move $a0 $t6
	jal _print
	move $t5 $t2
	lw $t6 0($t5)
	lw $t7 20($t6)
	li $t6 4
	mul $s0, $t6, 1
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	sw $t4, 0($s0)
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t4 $v0
	move $a0 $t4
	jal _print
	li $a0 10000000
	jal _print
	li $a0 24
	jal _halloc
	move $t4 $v0
	move $t5 $t4
	la $t4 Element_GetSalary
	sw $t4, 8($t5)
	la $t4 Element_Init
	sw $t4, 0($t5)
	la $t4 Element_GetAge
	sw $t4, 4($t5)
	la $t4 Element_Equal
	sw $t4, 16($t5)
	la $t4 Element_GetMarried
	sw $t4, 12($t5)
	la $t4 Element_Compare
	sw $t4, 20($t5)
	li $a0 16
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	li $t4 0
	sw $t4, 8($t6)
	li $t4 0
	sw $t4, 12($t6)
	li $t4 0
	sw $t4, 4($t6)
	sw $t5, 0($t6)
	move $t0 $t6
	move $t4 $t0
	lw $t5 0($t4)
	lw $t6 0($t5)
	li $t5 4
	mul $t7, $t5, 3
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	li $t5 28
	sw $t5, 0($t7)
	li $t5 35000
	sw $t5, 4($t7)
	li $t5 0
	sw $t5, 8($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t1 $t5
	move $t4 $t2
	lw $t5 0($t4)
	lw $t6 8($t5)
	li $t5 4
	mul $t7, $t5, 1
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	sw $t0, 0($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t2 $t5
	move $t4 $t2
	lw $t5 0($t4)
	lw $t6 36($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t1 $t5
	li $a0 2220000
	jal _print
	move $t4 $t2
	lw $t5 0($t4)
	lw $t6 16($t5)
	li $t5 4
	mul $t7, $t5, 1
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	sw $t3, 0($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t3 $v0
	move $t2 $t3
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 36($t4)
	li $t4 4
	mul $t6, $t4, 0
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t1 $t4
	li $a0 33300000
	jal _print
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 16($t4)
	li $t4 4
	mul $t6, $t4, 1
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	sw $t0, 0($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t0 $v0
	move $t2 $t0
	move $t0 $t2
	lw $t2 0($t0)
	lw $t3 36($t2)
	li $t2 4
	mul $t4, $t2, 0
	move $a0 $t4
	jal _halloc
	move $t2 $v0
	move $t4 $t2
	move $a0 $t0
	move $a1 $t4
	jalr $t3
	move $t2 $v0
	move $t1 $t2
	li $a0 44440000
	jal _print
	li $v0 0
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $t6,24($sp)
	lw $t7,28($sp)
	lw $s0,32($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 44
	j $ra
	
         .text            
         .globl _halloc  
_halloc:                 
         li $v0, 9        
         syscall          
         j $ra            
                          
         .text            
         .globl _print 
_print:                
         li $v0, 1        
         syscall          
         la $a0, newl     
         li $v0, 4        
         syscall          
         j $ra            
                          
         .data            
         .align   0       
newl:    .asciiz "\n"  
         .data            
         .align   0       
str_er:  .asciiz " ERROR: abnormal termination\n"                           
         .text            
         .globl _error    
_error:                   
         li $v0, 4        
         la $a0, str_er   
         syscall          
         li $v0, 10       
         syscall          

