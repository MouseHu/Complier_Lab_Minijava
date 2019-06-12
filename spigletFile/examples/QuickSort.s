	.text
	.globl main
main:
	move $fp, $sp
	subu $sp, $sp, 24
	sw $ra, -4($fp)
	li $a0 16
	jal _halloc
	move $t0 $v0
	move $t1 $t0
	la $t0 QS_Print
	sw $t0, 8($t1)
	la $t0 QS_Init
	sw $t0, 12($t1)
	la $t0 QS_Start
	sw $t0, 0($t1)
	la $t0 QS_Sort
	sw $t0, 4($t1)
	li $a0 12
	jal _halloc
	move $t0 $v0
	move $t2 $t0
	li $t0 0
	sw $t0, 4($t2)
	li $t0 0
	sw $t0, 8($t2)
	sw $t1, 0($t2)
	move $t0 $t2
	lw $t1 0($t0)
	lw $t2 0($t1)
	li $t1 4
	mul $t3, $t1, 1
	move $a0 $t3
	jal _halloc
	move $t1 $v0
	move $t3 $t1
	li $t1 10
	sw $t1, 0($t3)
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
	.globl QS_Start
QS_Start:
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
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	move $t0 $t1
	lw $t3 0($t0)
	lw $t4 12($t3)
	li $t3 4
	mul $t5, $t3, 1
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	sw $t2, 0($t5)
	move $a0 $t0
	move $a1 $t5
	jalr $t4
	move $t2 $v0
	move $t0 $t2
	move $t2 $t1
	lw $t3 0($t2)
	lw $t4 8($t3)
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
	move $t0 $t3
	li $a0 9999
	jal _print
	lw $t2 8($t1)
	sub $t3, $t2, 1
	move $t0 $t3
	move $t2 $t1
	lw $t3 0($t2)
	lw $t4 4($t3)
	li $t3 4
	mul $t5, $t3, 2
	move $a0 $t5
	jal _halloc
	move $t3 $v0
	move $t5 $t3
	li $t3 0
	sw $t3, 0($t5)
	sw $t0, 4($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t3 $v0
	move $t0 $t3
	move $t2 $t1
	lw $t1 0($t2)
	lw $t3 8($t1)
	li $t1 4
	mul $t4, $t1, 0
	move $a0 $t4
	jal _halloc
	move $t1 $v0
	move $t4 $t1
	move $a0 $t2
	move $a1 $t4
	jalr $t3
	move $t1 $v0
	move $t0 $t1
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
	.globl QS_Sort
QS_Sort:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 76
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
	sw $t8, 64($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	lw $t3 4($t0)
	li $t0 0
	slt $t4, $t2, $t3
	move $t5 $t4
	beqz $t5 QS_Sort_L0
	lw $t4 4($t1)
	move $t5 $t4
	move $t4 $t3
	li $t6 4
	li $t7 1
	add $s0, $t7, $t4
	mul $t4, $t6, $s0
	add $t6, $t5, $t4
	move $t5 $t6
	lw $t4 0($t5)
	move $t5 $t4
	sub $t4, $t2, 1
	move $t6 $t4
	move $t4 $t3
	li $t7 1
	j QS_Sort_L1
QS_Sort_L1:	move $s0 $t7
	beqz $s0 QS_Sort_L2
	li $s1 1
	j QS_Sort_L3
QS_Sort_L3:	move $s2 $s1
	beqz $s2 QS_Sort_L4
	add $s3, $t6, 1
	move $t6 $s3
	lw $s3 4($t1)
	move $s4 $s3
	move $s3 $t6
	li $s5 4
	li $s6 1
	add $s7, $s6, $s3
	mul $s3, $s5, $s7
	add $s5, $s4, $s3
	move $s4 $s5
	lw $s3 0($s4)
	move $s4 $s3
	slt $s3, $s4, $t5
	slt $s5, $s3, 1
	move $s3 $s5
	beqz $s3 QS_Sort_L5
	li $s1 0
	j QS_Sort_L6
QS_Sort_L5:	li $s1 1
QS_Sort_L6:	nop
	j QS_Sort_L3
QS_Sort_L4:	nop
	li $s1 1
	j QS_Sort_L7
QS_Sort_L7:	move $s2 $s1
	beqz $s2 QS_Sort_L8
	sub $s3, $t4, 1
	move $t4 $s3
	lw $s3 4($t1)
	move $s5 $s3
	move $s3 $t4
	li $s6 4
	li $s7 1
	add $t8, $s7, $s3
	mul $s3, $s6, $t8
	add $s6, $s5, $s3
	move $s5 $s6
	lw $s3 0($s5)
	move $s4 $s3
	slt $s3, $t5, $s4
	slt $s5, $s3, 1
	move $s3 $s5
	beqz $s3 QS_Sort_L9
	li $s1 0
	j QS_Sort_L10
QS_Sort_L9:	li $s1 1
QS_Sort_L10:	nop
	j QS_Sort_L7
QS_Sort_L8:	nop
	lw $s1 4($t1)
	move $s2 $s1
	move $s1 $t6
	li $s3 4
	li $s4 1
	add $s5, $s4, $s1
	mul $s1, $s3, $s5
	add $s3, $s2, $s1
	move $s2 $s3
	lw $s1 0($s2)
	move $t0 $s1
	lw $s1 4($t1)
	move $s2 $t6
	li $s3 4
	li $s4 1
	add $s5, $s4, $s2
	mul $s2, $s3, $s5
	add $s3, $s1, $s2
	lw $s1 4($t1)
	move $s2 $s1
	move $s1 $t4
	li $s4 4
	li $s5 1
	add $s6, $s5, $s1
	mul $s1, $s4, $s6
	add $s4, $s2, $s1
	move $s2 $s4
	lw $s1 0($s2)
	sw $s1, 0($s3)
	lw $s1 4($t1)
	move $s2 $t4
	li $s3 4
	li $s4 1
	add $s5, $s4, $s2
	mul $s2, $s3, $s5
	add $s3, $s1, $s2
	sw $t0, 0($s3)
	add $s1, $t6, 1
	slt $s2, $t4, $s1
	move $s1 $s2
	beqz $s1 QS_Sort_L11
	li $t7 0
	j QS_Sort_L12
QS_Sort_L11:	li $t7 1
QS_Sort_L12:	nop
	j QS_Sort_L1
QS_Sort_L2:	nop
	lw $t5 4($t1)
	move $t7 $t4
	li $t4 4
	li $s0 1
	add $s1, $s0, $t7
	mul $t7, $t4, $s1
	add $t4, $t5, $t7
	lw $t5 4($t1)
	move $t7 $t5
	move $t5 $t6
	li $s0 4
	li $s1 1
	add $s2, $s1, $t5
	mul $t5, $s0, $s2
	add $s0, $t7, $t5
	move $t7 $s0
	lw $t5 0($t7)
	sw $t5, 0($t4)
	lw $t4 4($t1)
	move $t5 $t6
	li $t7 4
	li $s0 1
	add $s1, $s0, $t5
	mul $t5, $t7, $s1
	add $t7, $t4, $t5
	lw $t4 4($t1)
	move $t5 $t4
	move $t4 $t3
	li $s0 4
	li $s1 1
	add $s2, $s1, $t4
	mul $t4, $s0, $s2
	add $s0, $t5, $t4
	move $t5 $s0
	lw $t4 0($t5)
	sw $t4, 0($t7)
	lw $t4 4($t1)
	move $t5 $t3
	li $t7 4
	li $s0 1
	add $s1, $s0, $t5
	mul $t5, $t7, $s1
	add $t7, $t4, $t5
	sw $t0, 0($t7)
	move $t0 $t1
	lw $t4 0($t0)
	lw $t5 4($t4)
	li $t4 4
	mul $t7, $t4, 2
	move $a0 $t7
	jal _halloc
	move $t4 $v0
	move $t7 $t4
	sw $t2, 0($t7)
	sub $t2, $t6, 1
	sw $t2, 4($t7)
	move $a0 $t0
	move $a1 $t7
	jalr $t5
	move $t2 $v0
	move $t0 $t2
	move $t2 $t1
	lw $t1 0($t2)
	lw $t4 4($t1)
	li $t1 4
	mul $t5, $t1, 2
	move $a0 $t5
	jal _halloc
	move $t1 $v0
	move $t5 $t1
	add $t1, $t6, 1
	sw $t1, 0($t5)
	sw $t3, 4($t5)
	move $a0 $t2
	move $a1 $t5
	jalr $t4
	move $t1 $v0
	move $t0 $t1
	j QS_Sort_L13
QS_Sort_L0:	li $t0 0
QS_Sort_L13:	nop
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
	lw $s1,36($sp)
	lw $s2,40($sp)
	lw $s3,44($sp)
	lw $s4,48($sp)
	lw $s5,52($sp)
	lw $s6,56($sp)
	lw $s7,60($sp)
	lw $t8,64($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 76
	j $ra
	
	.text
	.globl QS_Print
QS_Print:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 40
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	sw $t7, 28($sp)
	move $t0 $a0
	move $a1 $a1
	li $t1 0
	j QS_Print_L14
QS_Print_L14:	lw $t2 8($t0)
	slt $t3, $t1, $t2
	move $t4 $t3
	beqz $t4 QS_Print_L15
	lw $t3 4($t0)
	move $t4 $t3
	move $t3 $t1
	li $t5 4
	li $t6 1
	add $t7, $t6, $t3
	mul $t3, $t5, $t7
	add $t5, $t4, $t3
	move $t4 $t5
	lw $t3 0($t4)
	move $a0 $t3
	jal _print
	add $t3, $t1, 1
	move $t1 $t3
	j QS_Print_L14
QS_Print_L15:	nop
	li $v0 0
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
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
	.globl QS_Init
QS_Init:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 32
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	move $t1 $a0
	move $t0 $a1
	lw $t2 0($t0)
	sw $t2, 8($t1)
	move $t0 $t2
	li $t2 4
	li $t3 1
	add $t4, $t3, $t0
	mul $t3, $t2, $t4
	move $a0 $t3
	jal _halloc
	move $t2 $v0
	move $t3 $t2
	sw $t0, 0($t3)
	sw $t3, 4($t1)
	lw $t0 4($t1)
	li $t2 0
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 20
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 1
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 7
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 2
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 12
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 3
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 18
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 4
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 2
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 5
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 11
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 6
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 6
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 7
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 9
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t2 8
	li $t3 4
	li $t4 1
	add $t5, $t4, $t2
	mul $t2, $t3, $t5
	add $t3, $t0, $t2
	li $t0 19
	sw $t0, 0($t3)
	lw $t0 4($t1)
	li $t1 9
	li $t2 4
	li $t3 1
	add $t4, $t3, $t1
	mul $t1, $t2, $t4
	add $t2, $t0, $t1
	li $t0 5
	sw $t0, 0($t2)
	li $v0 0
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 32
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

