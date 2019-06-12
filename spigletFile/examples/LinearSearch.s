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
	la $t0 LS_Print
	sw $t0, 4($t1)
	la $t0 LS_Init
	sw $t0, 12($t1)
	la $t0 LS_Start
	sw $t0, 0($t1)
	la $t0 LS_Search
	sw $t0, 8($t1)
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
	.globl LS_Start
LS_Start:
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
	move $t0 $t1
	lw $t2 0($t0)
	lw $t3 4($t2)
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
	move $t0 $t2
	li $a0 9999
	jal _print
	move $t0 $t1
	lw $t2 0($t0)
	lw $t3 8($t2)
	li $t2 4
	mul $t4, $t2, 1
	move $a0 $t4
	jal _halloc
	move $t2 $v0
	move $t4 $t2
	li $t2 8
	sw $t2, 0($t4)
	move $a0 $t0
	move $a1 $t4
	jalr $t3
	move $t2 $v0
	move $a0 $t2
	jal _print
	move $t0 $t1
	lw $t2 0($t0)
	lw $t3 8($t2)
	li $t2 4
	mul $t4, $t2, 1
	move $a0 $t4
	jal _halloc
	move $t2 $v0
	move $t4 $t2
	li $t2 12
	sw $t2, 0($t4)
	move $a0 $t0
	move $a1 $t4
	jalr $t3
	move $t2 $v0
	move $a0 $t2
	jal _print
	move $t0 $t1
	lw $t2 0($t0)
	lw $t3 8($t2)
	li $t2 4
	mul $t4, $t2, 1
	move $a0 $t4
	jal _halloc
	move $t2 $v0
	move $t4 $t2
	li $t2 17
	sw $t2, 0($t4)
	move $a0 $t0
	move $a1 $t4
	jalr $t3
	move $t2 $v0
	move $a0 $t2
	jal _print
	move $t0 $t1
	lw $t1 0($t0)
	lw $t2 8($t1)
	li $t1 4
	mul $t3, $t1, 1
	move $a0 $t3
	jal _halloc
	move $t1 $v0
	move $t3 $t1
	li $t1 50
	sw $t1, 0($t3)
	move $a0 $t0
	move $a1 $t3
	jalr $t2
	move $t1 $v0
	move $a0 $t1
	jal _print
	li $v0 55
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
	.globl LS_Print
LS_Print:
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
	li $t1 1
	j LS_Print_L0
LS_Print_L0:	lw $t2 8($t0)
	slt $t3, $t1, $t2
	move $t4 $t3
	beqz $t4 LS_Print_L1
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
	j LS_Print_L0
LS_Print_L1:	nop
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
	.globl LS_Search
LS_Search:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 52
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
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
	li $t0 1
	li $t3 0
	li $t4 0
	j LS_Search_L2
LS_Search_L2:	lw $t5 8($t1)
	slt $t6, $t0, $t5
	move $t7 $t6
	beqz $t7 LS_Search_L3
	lw $t6 4($t1)
	move $t7 $t6
	move $t6 $t0
	li $s0 4
	li $s1 1
	add $s2, $s1, $t6
	mul $t6, $s0, $s2
	add $s0, $t7, $t6
	move $t7 $s0
	lw $t6 0($t7)
	move $t7 $t6
	add $t6, $t2, 1
	move $s0 $t6
	slt $t6, $t7, $t2
	move $s1 $t6
	beqz $s1 LS_Search_L4
	li $t6 0
	j LS_Search_L5
LS_Search_L4:	slt $s1, $t7, $s0
	slt $t7, $s1, 1
	move $s0 $t7
	beqz $s0 LS_Search_L6
	li $t6 0
	j LS_Search_L7
LS_Search_L6:	li $t3 1
	li $t4 1
	lw $t6 8($t1)
	move $t0 $t6
LS_Search_L7:	nop
LS_Search_L5:	nop
	add $t6, $t0, 1
	move $t0 $t6
	j LS_Search_L2
LS_Search_L3:	nop
	move $v0 $t4
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
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
	.globl LS_Init
LS_Init:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 52
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
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
	li $t0 1
	lw $t2 8($t1)
	add $t3, $t2, 1
	move $t2 $t3
	j LS_Init_L8
LS_Init_L8:	lw $t3 8($t1)
	slt $t4, $t0, $t3
	move $t5 $t4
	beqz $t5 LS_Init_L9
	li $t4 2
	mul $t5, $t4, $t0
	move $t4 $t5
	sub $t5, $t2, 3
	move $t6 $t5
	lw $t5 4($t1)
	move $t7 $t0
	li $s0 4
	li $s1 1
	add $s2, $s1, $t7
	mul $t7, $s0, $s2
	add $s0, $t5, $t7
	add $t5, $t4, $t6
	sw $t5, 0($s0)
	add $t4, $t0, 1
	move $t0 $t4
	sub $t4, $t2, 1
	move $t2 $t4
	j LS_Init_L8
LS_Init_L9:	nop
	li $v0 0
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
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

