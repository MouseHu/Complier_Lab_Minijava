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
	la $t0 BBS_Print
	sw $t0, 8($t1)
	la $t0 BBS_Init
	sw $t0, 12($t1)
	la $t0 BBS_Start
	sw $t0, 0($t1)
	la $t0 BBS_Sort
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
	.globl BBS_Start
BBS_Start:
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
	li $a0 99999
	jal _print
	move $t2 $t1
	lw $t3 0($t2)
	lw $t4 4($t3)
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
	.globl BBS_Sort
BBS_Sort:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 60
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
	sw $s3, 44($sp)
	sw $s4, 48($sp)
	move $t0 $a0
	move $a1 $a1
	lw $t1 8($t0)
	sub $t2, $t1, 1
	move $t1 $t2
	li $t2 0
	sub $t3, $t2, 1
	move $t2 $t3
	j BBS_Sort_L0
BBS_Sort_L0:	slt $t3, $t2, $t1
	move $t4 $t3
	beqz $t4 BBS_Sort_L1
	li $t4 1
	j BBS_Sort_L2
BBS_Sort_L2:	add $t5, $t1, 1
	slt $t6, $t4, $t5
	move $t7 $t6
	beqz $t7 BBS_Sort_L3
	sub $t6, $t4, 1
	move $t7 $t6
	lw $t6 4($t0)
	move $s0 $t6
	move $t6 $t7
	li $t7 4
	li $s1 1
	add $s2, $s1, $t6
	mul $t6, $t7, $s2
	add $t7, $s0, $t6
	move $s0 $t7
	lw $t6 0($s0)
	move $t7 $t6
	lw $t6 4($t0)
	move $s0 $t6
	move $t6 $t4
	li $s1 4
	li $s2 1
	add $s3, $s2, $t6
	mul $t6, $s1, $s3
	add $s1, $s0, $t6
	move $s0 $s1
	lw $t6 0($s0)
	move $s0 $t6
	slt $t6, $s0, $t7
	move $t7 $t6
	beqz $t7 BBS_Sort_L4
	sub $t6, $t4, 1
	move $t7 $t6
	lw $t6 4($t0)
	move $s0 $t6
	move $t6 $t7
	li $s1 4
	li $s2 1
	add $s3, $s2, $t6
	mul $t6, $s1, $s3
	add $s1, $s0, $t6
	move $s0 $s1
	lw $t6 0($s0)
	move $s0 $t6
	lw $t6 4($t0)
	move $s1 $t7
	li $t7 4
	li $s2 1
	add $s3, $s2, $s1
	mul $s1, $t7, $s3
	add $t7, $t6, $s1
	lw $t6 4($t0)
	move $s1 $t6
	move $t6 $t4
	li $s2 4
	li $s3 1
	add $s4, $s3, $t6
	mul $t6, $s2, $s4
	add $s2, $s1, $t6
	move $s1 $s2
	lw $t6 0($s1)
	sw $t6, 0($t7)
	lw $t6 4($t0)
	move $t7 $t4
	li $s1 4
	li $s2 1
	add $s3, $s2, $t7
	mul $t7, $s1, $s3
	add $s1, $t6, $t7
	sw $s0, 0($s1)
	j BBS_Sort_L5
BBS_Sort_L4:	li $t6 0
BBS_Sort_L5:	nop
	add $t6, $t4, 1
	move $t4 $t6
	j BBS_Sort_L2
BBS_Sort_L3:	nop
	sub $t4, $t1, 1
	move $t1 $t4
	j BBS_Sort_L0
BBS_Sort_L1:	nop
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
	lw $s3,44($sp)
	lw $s4,48($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 60
	j $ra
	
	.text
	.globl BBS_Print
BBS_Print:
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
	j BBS_Print_L6
BBS_Print_L6:	lw $t2 8($t0)
	slt $t3, $t1, $t2
	move $t4 $t3
	beqz $t4 BBS_Print_L7
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
	j BBS_Print_L6
BBS_Print_L7:	nop
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
	.globl BBS_Init
BBS_Init:
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

