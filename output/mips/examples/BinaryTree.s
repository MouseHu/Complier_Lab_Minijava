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
	la $t0 BT_Start
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
	.globl BT_Start
BT_Start:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 32
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	move $a0 $a0
	move $a1 $a1
	li $a0 80
	jal _halloc
	move $t0 $v0
	move $t1 $t0
	la $t0 Tree_Delete
	sw $t0, 52($t1)
	la $t0 Tree_SetHas_Left
	sw $t0, 36($t1)
	la $t0 Tree_RemoveLeft
	sw $t0, 64($t1)
	la $t0 Tree_GetKey
	sw $t0, 20($t1)
	la $t0 Tree_SetRight
	sw $t0, 4($t1)
	la $t0 Tree_GetLeft
	sw $t0, 16($t1)
	la $t0 Tree_GetRight
	sw $t0, 12($t1)
	la $t0 Tree_Remove
	sw $t0, 56($t1)
	la $t0 Tree_SetLeft
	sw $t0, 8($t1)
	la $t0 Tree_Insert
	sw $t0, 48($t1)
	la $t0 Tree_Print
	sw $t0, 72($t1)
	la $t0 Tree_Init
	sw $t0, 0($t1)
	la $t0 Tree_GetHas_Right
	sw $t0, 28($t1)
	la $t0 Tree_GetHas_Left
	sw $t0, 32($t1)
	la $t0 Tree_RemoveRight
	sw $t0, 60($t1)
	la $t0 Tree_Search
	sw $t0, 68($t1)
	la $t0 Tree_SetKey
	sw $t0, 24($t1)
	la $t0 Tree_Compare
	sw $t0, 44($t1)
	la $t0 Tree_SetHas_Right
	sw $t0, 40($t1)
	la $t0 Tree_RecPrint
	sw $t0, 76($t1)
	li $a0 28
	jal _halloc
	move $t0 $v0
	move $t2 $t0
	li $t0 0
	sw $t0, 4($t2)
	li $t0 0
	sw $t0, 20($t2)
	li $t0 0
	sw $t0, 24($t2)
	li $t0 0
	sw $t0, 8($t2)
	li $t0 0
	sw $t0, 16($t2)
	li $t0 0
	sw $t0, 12($t2)
	sw $t1, 0($t2)
	move $t0 $t2
	move $t1 $t0
	lw $t2 0($t1)
	lw $t3 0($t2)
	li $t2 4
	mul $t4, $t2, 1
	move $a0 $t4
	jal _halloc
	move $t2 $v0
	move $t4 $t2
	li $t2 16
	sw $t2, 0($t4)
	move $a0 $t1
	move $a1 $t4
	jalr $t3
	move $t2 $v0
	move $t1 $t2
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 72($t3)
	li $t3 4
	mul $t5, $t3, 0
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	li $a0 100000000
	jal _print
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 48($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 8
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 72($t3)
	li $t3 4
	mul $t5, $t3, 0
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 48($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 24
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 48($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 4
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 48($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 12
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 48($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 20
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 48($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 28
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 48($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 14
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 72($t3)
	li $t3 4
	mul $t5, $t3, 0
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 68($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 24
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $a0 $t3
	jal _print
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 68($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 12
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $a0 $t3
	jal _print
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 68($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 16
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $a0 $t3
	jal _print
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 68($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 50
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $a0 $t3
	jal _print
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 68($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 12
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $a0 $t3
	jal _print
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 52($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 12
	sw $t3, 0($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t2 $t0
	lw $t3 0($t2)
	lw $t4 72($t3)
	li $t3 4
	mul $t5, $t3, 0
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t1 $t3
	move $t1 $t0
	lw $t0 0($t1)
	lw $t2 68($t0)
	li $t0 4
	mul $t3, $t0, 1
	move $a0 $t3
	jal _halloc
	move $t0 $v0
	move $t3 $t0
	li $t0 12
	sw $t0, 0($t3)
	move $a0 $t1
	move $a1 $t3
	jalr $t2
	move $t0 $v0
	move $a0 $t0
	jal _print
	li $v0 0
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 32
	j $ra
	
	.text
	.globl Tree_Init
Tree_Init:
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
	sw $t2, 12($t1)
	li $t0 0
	sw $t0, 16($t1)
	li $t0 0
	sw $t0, 20($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 20
	j $ra
	
	.text
	.globl Tree_SetRight
Tree_SetRight:
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
	.globl Tree_SetLeft
Tree_SetLeft:
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
	sw $t2, 4($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 20
	j $ra
	
	.text
	.globl Tree_GetRight
Tree_GetRight:
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
	.globl Tree_GetLeft
Tree_GetLeft:
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
	.globl Tree_GetKey
Tree_GetKey:
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
	.globl Tree_SetKey
Tree_SetKey:
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
	sw $t2, 12($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 20
	j $ra
	
	.text
	.globl Tree_GetHas_Right
Tree_GetHas_Right:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 20($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl Tree_GetHas_Left
Tree_GetHas_Left:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 16
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 16($t0)
	move $v0 $t1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 16
	j $ra
	
	.text
	.globl Tree_SetHas_Left
Tree_SetHas_Left:
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
	sw $t2, 16($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 20
	j $ra
	
	.text
	.globl Tree_SetHas_Right
Tree_SetHas_Right:
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
	sw $t2, 20($t1)
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 20
	j $ra
	
	.text
	.globl Tree_Compare
Tree_Compare:
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
	beqz $t2 Tree_Compare_L0
	li $t0 0
	j Tree_Compare_L1
Tree_Compare_L0:	slt $t2, $t1, $t4
	slt $t1, $t2, 1
	move $t2 $t1
	beqz $t2 Tree_Compare_L2
	li $t0 0
	j Tree_Compare_L3
Tree_Compare_L2:	li $t0 1
Tree_Compare_L3:	nop
Tree_Compare_L1:	nop
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
	.globl Tree_Insert
Tree_Insert:
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
	li $a0 80
	jal _halloc
	move $t0 $v0
	move $t3 $t0
	la $t0 Tree_Delete
	sw $t0, 52($t3)
	la $t0 Tree_SetHas_Left
	sw $t0, 36($t3)
	la $t0 Tree_RemoveLeft
	sw $t0, 64($t3)
	la $t0 Tree_GetKey
	sw $t0, 20($t3)
	la $t0 Tree_SetRight
	sw $t0, 4($t3)
	la $t0 Tree_GetLeft
	sw $t0, 16($t3)
	la $t0 Tree_GetRight
	sw $t0, 12($t3)
	la $t0 Tree_Remove
	sw $t0, 56($t3)
	la $t0 Tree_SetLeft
	sw $t0, 8($t3)
	la $t0 Tree_Insert
	sw $t0, 48($t3)
	la $t0 Tree_Print
	sw $t0, 72($t3)
	la $t0 Tree_Init
	sw $t0, 0($t3)
	la $t0 Tree_GetHas_Right
	sw $t0, 28($t3)
	la $t0 Tree_GetHas_Left
	sw $t0, 32($t3)
	la $t0 Tree_RemoveRight
	sw $t0, 60($t3)
	la $t0 Tree_Search
	sw $t0, 68($t3)
	la $t0 Tree_SetKey
	sw $t0, 24($t3)
	la $t0 Tree_Compare
	sw $t0, 44($t3)
	la $t0 Tree_SetHas_Right
	sw $t0, 40($t3)
	la $t0 Tree_RecPrint
	sw $t0, 76($t3)
	li $a0 28
	jal _halloc
	move $t0 $v0
	move $t4 $t0
	li $t0 0
	sw $t0, 4($t4)
	li $t0 0
	sw $t0, 20($t4)
	li $t0 0
	sw $t0, 24($t4)
	li $t0 0
	sw $t0, 8($t4)
	li $t0 0
	sw $t0, 16($t4)
	li $t0 0
	sw $t0, 12($t4)
	sw $t3, 0($t4)
	move $t0 $t4
	move $t3 $t0
	lw $t4 0($t3)
	lw $t5 0($t4)
	li $t4 4
	mul $t6, $t4, 1
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	sw $t2, 0($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t3 $t4
	move $t4 $t1
	li $t1 1
	j Tree_Insert_L4
Tree_Insert_L4:	move $t5 $t1
	beqz $t5 Tree_Insert_L5
	move $t6 $t4
	lw $t7 0($t6)
	lw $s0 20($t7)
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
	move $t6 $t7
	slt $t7, $t2, $t6
	move $t6 $t7
	beqz $t6 Tree_Insert_L6
	move $t6 $t4
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
	move $t6 $t7
	beqz $t6 Tree_Insert_L7
	move $t6 $t4
	lw $t7 0($t6)
	lw $s0 16($t7)
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
	move $t4 $t7
	j Tree_Insert_L8
Tree_Insert_L7:	li $t1 0
	move $t6 $t4
	lw $t7 0($t6)
	lw $s0 36($t7)
	li $t7 4
	mul $s1, $t7, 1
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	li $t7 1
	sw $t7, 0($s1)
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t3 $t7
	move $t6 $t4
	lw $t7 0($t6)
	lw $s0 8($t7)
	li $t7 4
	mul $s1, $t7, 1
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	sw $t0, 0($s1)
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t3 $t7
Tree_Insert_L8:	nop
	j Tree_Insert_L9
Tree_Insert_L6:	move $t6 $t4
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
	move $t6 $t7
	beqz $t6 Tree_Insert_L10
	move $t6 $t4
	lw $t7 0($t6)
	lw $s0 12($t7)
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
	move $t4 $t7
	j Tree_Insert_L11
Tree_Insert_L10:	li $t1 0
	move $t6 $t4
	lw $t7 0($t6)
	lw $s0 40($t7)
	li $t7 4
	mul $s1, $t7, 1
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	li $t7 1
	sw $t7, 0($s1)
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t3 $t7
	move $t6 $t4
	lw $t7 0($t6)
	lw $s0 4($t7)
	li $t7 4
	mul $s1, $t7, 1
	move $a0 $s1
	jal _halloc
	move $t7 $v0
	move $s1 $t7
	sw $t0, 0($s1)
	move $a0 $t6
	move $a1 $s1
	jalr $s0
	move $t7 $v0
	move $t3 $t7
Tree_Insert_L11:	nop
Tree_Insert_L9:	nop
	j Tree_Insert_L4
Tree_Insert_L5:	nop
	li $v0 1
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
	.globl Tree_Delete
Tree_Delete:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 60
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
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	move $t0 $t1
	move $t3 $t1
	li $t4 1
	li $t5 0
	li $t6 1
	j Tree_Delete_L12
Tree_Delete_L12:	move $t7 $t4
	beqz $t7 Tree_Delete_L13
	move $s0 $t0
	lw $s1 0($s0)
	lw $s2 20($s1)
	li $s1 4
	mul $s3, $s1, 0
	move $a0 $s3
	jal _halloc
	move $s1 $v0
	move $s3 $s1
	move $a0 $s0
	move $a1 $s3
	jalr $s2
	move $s1 $v0
	move $s0 $s1
	slt $s1, $t2, $s0
	move $s2 $s1
	beqz $s2 Tree_Delete_L14
	move $s1 $t0
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
	move $s1 $s2
	beqz $s1 Tree_Delete_L15
	move $t3 $t0
	move $s1 $t0
	lw $s2 0($s1)
	lw $s3 16($s2)
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
	j Tree_Delete_L16
Tree_Delete_L15:	li $t4 0
Tree_Delete_L16:	nop
	j Tree_Delete_L17
Tree_Delete_L14:	slt $s1, $s0, $t2
	move $s0 $s1
	beqz $s0 Tree_Delete_L18
	move $s0 $t0
	lw $s1 0($s0)
	lw $s2 28($s1)
	li $s1 4
	mul $s3, $s1, 0
	move $a0 $s3
	jal _halloc
	move $s1 $v0
	move $s3 $s1
	move $a0 $s0
	move $a1 $s3
	jalr $s2
	move $s1 $v0
	move $s0 $s1
	beqz $s0 Tree_Delete_L19
	move $t3 $t0
	move $s0 $t0
	lw $s1 0($s0)
	lw $s2 12($s1)
	li $s1 4
	mul $s3, $s1, 0
	move $a0 $s3
	jal _halloc
	move $s1 $v0
	move $s3 $s1
	move $a0 $s0
	move $a1 $s3
	jalr $s2
	move $s1 $v0
	move $t0 $s1
	j Tree_Delete_L20
Tree_Delete_L19:	li $t4 0
Tree_Delete_L20:	nop
	j Tree_Delete_L21
Tree_Delete_L18:	move $s0 $t6
	beqz $s0 Tree_Delete_L22
	li $s0 0
	move $s1 $t0
	lw $s2 0($s1)
	lw $s3 28($s2)
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
	slt $s1, $s2, 1
	slt $s2, $s0, $s1
	move $s0 $s2
	beqz $s0 Tree_Delete_L23
	li $s0 0
	move $s1 $t0
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
	slt $s1, $s2, 1
	slt $s2, $s0, $s1
	move $s0 $s2
	beqz $s0 Tree_Delete_L23
	li $s0 1
	j Tree_Delete_L24
Tree_Delete_L23:	li $s0 0
Tree_Delete_L24:	nop
	move $s1 $s0
	beqz $s1 Tree_Delete_L25
	li $s0 1
	j Tree_Delete_L26
Tree_Delete_L25:	move $s1 $t1
	lw $s2 0($s1)
	lw $s3 56($s2)
	li $s2 4
	mul $s4, $s2, 2
	move $a0 $s4
	jal _halloc
	move $s2 $v0
	move $s4 $s2
	sw $t3, 0($s4)
	sw $t0, 4($s4)
	move $a0 $s1
	move $a1 $s4
	jalr $s3
	move $s2 $v0
	move $s0 $s2
Tree_Delete_L26:	nop
	j Tree_Delete_L27
Tree_Delete_L22:	move $s1 $t1
	lw $s2 0($s1)
	lw $s3 56($s2)
	li $s2 4
	mul $s4, $s2, 2
	move $a0 $s4
	jal _halloc
	move $s2 $v0
	move $s4 $s2
	sw $t3, 0($s4)
	sw $t0, 4($s4)
	move $a0 $s1
	move $a1 $s4
	jalr $s3
	move $s2 $v0
	move $s0 $s2
Tree_Delete_L27:	nop
	li $t5 1
	li $t4 0
Tree_Delete_L21:	nop
Tree_Delete_L17:	nop
	li $t6 0
	j Tree_Delete_L12
Tree_Delete_L13:	nop
	move $v0 $t5
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
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 60
	j $ra
	
	.text
	.globl Tree_Remove
Tree_Remove:
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
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	lw $t3 4($t0)
	move $t0 $t3
	lw $t4 0($t0)
	lw $t5 32($t4)
	li $t4 4
	mul $t6, $t4, 0
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	move $a0 $t0
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t0 $t4
	beqz $t0 Tree_Remove_L28
	move $t0 $t1
	lw $t4 0($t0)
	lw $t5 64($t4)
	li $t4 4
	mul $t6, $t4, 2
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	sw $t2, 0($t6)
	sw $t3, 4($t6)
	move $a0 $t0
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t0 $t4
	j Tree_Remove_L29
Tree_Remove_L28:	move $t4 $t3
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
	move $t4 $t5
	beqz $t4 Tree_Remove_L30
	move $t4 $t1
	lw $t5 0($t4)
	lw $t6 60($t5)
	li $t5 4
	mul $t7, $t5, 2
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	sw $t2, 0($t7)
	sw $t3, 4($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t0 $t5
	j Tree_Remove_L31
Tree_Remove_L30:	move $t4 $t3
	lw $t3 0($t4)
	lw $t5 20($t3)
	li $t3 4
	mul $t6, $t3, 0
	move $a0 $t6
	jal _halloc
	move $t3 $v0
	move $t6 $t3
	move $a0 $t4
	move $a1 $t6
	jalr $t5
	move $t3 $v0
	move $t4 $t3
	move $t3 $t2
	lw $t5 0($t3)
	lw $t6 16($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t3
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t3 $t5
	lw $t5 0($t3)
	lw $t6 20($t5)
	li $t5 4
	mul $t7, $t5, 0
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $a0 $t3
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t3 $t5
	move $t5 $t1
	lw $t6 0($t5)
	lw $t7 44($t6)
	li $t6 4
	mul $s0, $t6, 2
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	sw $t4, 0($s0)
	sw $t3, 4($s0)
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t3 $v0
	move $t4 $t3
	beqz $t4 Tree_Remove_L32
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 8($t4)
	li $t4 4
	mul $t6, $t4, 1
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	lw $t4 24($t1)
	sw $t4, 0($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t0 $t4
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 36($t4)
	li $t4 4
	mul $t6, $t4, 1
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	li $t4 0
	sw $t4, 0($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t0 $t4
	j Tree_Remove_L33
Tree_Remove_L32:	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 4($t4)
	li $t4 4
	mul $t6, $t4, 1
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	lw $t4 24($t1)
	sw $t4, 0($t6)
	move $a0 $t3
	move $a1 $t6
	jalr $t5
	move $t1 $v0
	move $t0 $t1
	move $t1 $t2
	lw $t2 0($t1)
	lw $t3 40($t2)
	li $t2 4
	mul $t4, $t2, 1
	move $a0 $t4
	jal _halloc
	move $t2 $v0
	move $t4 $t2
	li $t2 0
	sw $t2, 0($t4)
	move $a0 $t1
	move $a1 $t4
	jalr $t3
	move $t2 $v0
	move $t0 $t2
Tree_Remove_L33:	nop
Tree_Remove_L31:	nop
Tree_Remove_L29:	nop
	li $v0 1
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
	.globl Tree_RemoveRight
Tree_RemoveRight:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 52
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
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	lw $t3 4($t0)
	j Tree_RemoveRight_L34
Tree_RemoveRight_L34:	move $t0 $t3
	lw $t4 0($t0)
	lw $t5 28($t4)
	li $t4 4
	mul $t6, $t4, 0
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	move $a0 $t0
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t5 $t4
	beqz $t5 Tree_RemoveRight_L35
	move $t4 $t3
	lw $t5 0($t4)
	lw $t6 24($t5)
	li $t5 4
	mul $t7, $t5, 1
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $t5 $t3
	lw $s0 0($t5)
	lw $s1 12($s0)
	li $s0 4
	mul $s2, $s0, 0
	move $a0 $s2
	jal _halloc
	move $s0 $v0
	move $s2 $s0
	move $a0 $t5
	move $a1 $s2
	jalr $s1
	move $s0 $v0
	move $t5 $s0
	lw $s0 0($t5)
	lw $s1 20($s0)
	li $s0 4
	mul $s2, $s0, 0
	move $a0 $s2
	jal _halloc
	move $s0 $v0
	move $s2 $s0
	move $a0 $t5
	move $a1 $s2
	jalr $s1
	move $s0 $v0
	sw $s0, 0($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t4 $t5
	move $t2 $t3
	move $t5 $t3
	lw $t6 0($t5)
	lw $t7 12($t6)
	li $t6 4
	mul $s0, $t6, 0
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	move $t3 $t6
	j Tree_RemoveRight_L34
Tree_RemoveRight_L35:	nop
	move $t0 $t2
	lw $t3 0($t0)
	lw $t5 4($t3)
	li $t3 4
	mul $t6, $t3, 1
	move $a0 $t6
	jal _halloc
	move $t3 $v0
	move $t6 $t3
	lw $t3 24($t1)
	sw $t3, 0($t6)
	move $a0 $t0
	move $a1 $t6
	jalr $t5
	move $t1 $v0
	move $t4 $t1
	move $t0 $t2
	lw $t1 0($t0)
	lw $t2 40($t1)
	li $t1 4
	mul $t3, $t1, 1
	move $a0 $t3
	jal _halloc
	move $t1 $v0
	move $t3 $t1
	li $t1 0
	sw $t1, 0($t3)
	move $a0 $t0
	move $a1 $t3
	jalr $t2
	move $t1 $v0
	move $t4 $t1
	li $v0 1
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
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 52
	j $ra
	
	.text
	.globl Tree_RemoveLeft
Tree_RemoveLeft:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 52
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
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	lw $t3 4($t0)
	j Tree_RemoveLeft_L36
Tree_RemoveLeft_L36:	move $t0 $t3
	lw $t4 0($t0)
	lw $t5 32($t4)
	li $t4 4
	mul $t6, $t4, 0
	move $a0 $t6
	jal _halloc
	move $t4 $v0
	move $t6 $t4
	move $a0 $t0
	move $a1 $t6
	jalr $t5
	move $t4 $v0
	move $t5 $t4
	beqz $t5 Tree_RemoveLeft_L37
	move $t4 $t3
	lw $t5 0($t4)
	lw $t6 24($t5)
	li $t5 4
	mul $t7, $t5, 1
	move $a0 $t7
	jal _halloc
	move $t5 $v0
	move $t7 $t5
	move $t5 $t3
	lw $s0 0($t5)
	lw $s1 16($s0)
	li $s0 4
	mul $s2, $s0, 0
	move $a0 $s2
	jal _halloc
	move $s0 $v0
	move $s2 $s0
	move $a0 $t5
	move $a1 $s2
	jalr $s1
	move $s0 $v0
	move $t5 $s0
	lw $s0 0($t5)
	lw $s1 20($s0)
	li $s0 4
	mul $s2, $s0, 0
	move $a0 $s2
	jal _halloc
	move $s0 $v0
	move $s2 $s0
	move $a0 $t5
	move $a1 $s2
	jalr $s1
	move $s0 $v0
	sw $s0, 0($t7)
	move $a0 $t4
	move $a1 $t7
	jalr $t6
	move $t5 $v0
	move $t4 $t5
	move $t2 $t3
	move $t5 $t3
	lw $t6 0($t5)
	lw $t7 16($t6)
	li $t6 4
	mul $s0, $t6, 0
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	move $t3 $t6
	j Tree_RemoveLeft_L36
Tree_RemoveLeft_L37:	nop
	move $t0 $t2
	lw $t3 0($t0)
	lw $t5 8($t3)
	li $t3 4
	mul $t6, $t3, 1
	move $a0 $t6
	jal _halloc
	move $t3 $v0
	move $t6 $t3
	lw $t3 24($t1)
	sw $t3, 0($t6)
	move $a0 $t0
	move $a1 $t6
	jalr $t5
	move $t1 $v0
	move $t4 $t1
	move $t0 $t2
	lw $t1 0($t0)
	lw $t2 36($t1)
	li $t1 4
	mul $t3, $t1, 1
	move $a0 $t3
	jal _halloc
	move $t1 $v0
	move $t3 $t1
	li $t1 0
	sw $t1, 0($t3)
	move $a0 $t0
	move $a1 $t3
	jalr $t2
	move $t1 $v0
	move $t4 $t1
	li $v0 1
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
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 52
	j $ra
	
	.text
	.globl Tree_Search
Tree_Search:
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
	move $t0 $t1
	li $t1 1
	li $t3 0
	j Tree_Search_L38
Tree_Search_L38:	move $t4 $t1
	beqz $t4 Tree_Search_L39
	move $t5 $t0
	lw $t6 0($t5)
	lw $t7 20($t6)
	li $t6 4
	mul $s0, $t6, 0
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	move $t5 $t6
	slt $t6, $t2, $t5
	move $t7 $t6
	beqz $t7 Tree_Search_L40
	move $t6 $t0
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
	move $t6 $t7
	beqz $t6 Tree_Search_L41
	move $t6 $t0
	lw $t7 0($t6)
	lw $s0 16($t7)
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
	move $t0 $t7
	j Tree_Search_L42
Tree_Search_L41:	li $t1 0
Tree_Search_L42:	nop
	j Tree_Search_L43
Tree_Search_L40:	slt $t6, $t5, $t2
	move $t5 $t6
	beqz $t5 Tree_Search_L44
	move $t5 $t0
	lw $t6 0($t5)
	lw $t7 28($t6)
	li $t6 4
	mul $s0, $t6, 0
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	move $t5 $t6
	beqz $t5 Tree_Search_L45
	move $t5 $t0
	lw $t6 0($t5)
	lw $t7 12($t6)
	li $t6 4
	mul $s0, $t6, 0
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	move $a0 $t5
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	move $t0 $t6
	j Tree_Search_L46
Tree_Search_L45:	li $t1 0
Tree_Search_L46:	nop
	j Tree_Search_L47
Tree_Search_L44:	li $t3 1
	li $t1 0
Tree_Search_L47:	nop
Tree_Search_L43:	nop
	j Tree_Search_L38
Tree_Search_L39:	nop
	move $v0 $t3
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
	.globl Tree_Print
Tree_Print:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 28
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($fp)
	sw $t2, 8($fp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	move $t0 $a0
	move $a1 $a1
	move $t1 $t0
	move $t2 $t0
	lw $t0 0($t2)
	lw $t3 76($t0)
	li $t0 4
	mul $t4, $t0, 1
	move $a0 $t4
	jal _halloc
	move $t0 $v0
	move $t4 $t0
	sw $t1, 0($t4)
	move $a0 $t2
	move $a1 $t4
	jalr $t3
	move $t0 $v0
	move $t1 $t0
	li $v0 1
	lw $t0,0($fp)
	lw $t1,4($fp)
	lw $t2,8($fp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 28
	j $ra
	
	.text
	.globl Tree_RecPrint
Tree_RecPrint:
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
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	move $t0 $t2
	lw $t3 0($t0)
	lw $t4 32($t3)
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
	move $t0 $t3
	beqz $t0 Tree_RecPrint_L48
	move $t0 $t1
	lw $t3 0($t0)
	lw $t4 76($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	move $t3 $t2
	lw $t6 0($t3)
	lw $t7 16($t6)
	li $t6 4
	mul $s0, $t6, 0
	move $a0 $s0
	jal _halloc
	move $t6 $v0
	move $s0 $t6
	move $a0 $t3
	move $a1 $s0
	jalr $t7
	move $t6 $v0
	sw $t6, 0($t5)
	move $a0 $t0
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t0 $t3
	j Tree_RecPrint_L49
Tree_RecPrint_L48:	li $t0 1
Tree_RecPrint_L49:	nop
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 20($t4)
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
	move $a0 $t4
	jal _print
	move $t3 $t2
	lw $t4 0($t3)
	lw $t5 28($t4)
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
	beqz $t3 Tree_RecPrint_L50
	move $t3 $t1
	lw $t1 0($t3)
	lw $t4 76($t1)
	li $t1 4
	mul $t5, $t1, 1
	move $a0 $t5
	jal _halloc
	move $t1 $v0
	move $t5 $t1
	move $t1 $t2
	lw $t2 0($t1)
	lw $t6 12($t2)
	li $t2 4
	mul $t7, $t2, 0
	move $a0 $t7
	jal _halloc
	move $t2 $v0
	move $t7 $t2
	move $a0 $t1
	move $a1 $t7
	jalr $t6
	move $t2 $v0
	sw $t2, 0($t5)
	move $a0 $t3
	move $a1 $t5
	jalr $t4
	move $t1 $v0
	move $t0 $t1
	j Tree_RecPrint_L51
Tree_RecPrint_L50:	li $t0 1
Tree_RecPrint_L51:	nop
	li $v0 1
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

