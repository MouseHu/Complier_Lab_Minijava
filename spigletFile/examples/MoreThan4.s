	.text
	.globl main
main:
	move $fp, $sp
	subu $sp, $sp, 24
	sw $ra, -4($fp)
	li $a0 8
	jal _halloc
	move $t0 $v0
	move $t1 $t0
	la $t0 MT4_Start
	sw $t0, 0($t1)
	la $t0 MT4_Change
	sw $t0, 4($t1)
	li $a0 4
	jal _halloc
	move $t0 $v0
	move $t2 $t0
	sw $t1, 0($t2)
	move $t0 $t2
	lw $t1 0($t0)
	lw $t2 0($t1)
	li $t1 4
	mul $t3, $t1, 6
	move $a0 $t3
	jal _halloc
	move $t1 $v0
	move $t3 $t1
	li $t1 1
	sw $t1, 0($t3)
	li $t1 2
	sw $t1, 4($t3)
	li $t1 3
	sw $t1, 8($t3)
	li $t1 4
	sw $t1, 12($t3)
	li $t1 5
	sw $t1, 16($t3)
	li $t1 6
	sw $t1, 20($t3)
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
	.globl MT4_Start
MT4_Start:
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
	lw $t3 4($t0)
	lw $t4 8($t0)
	lw $t5 12($t0)
	lw $t6 16($t0)
	lw $t7 20($t0)
	move $a0 $t2
	jal _print
	move $a0 $t3
	jal _print
	move $a0 $t4
	jal _print
	move $a0 $t5
	jal _print
	move $a0 $t6
	jal _print
	move $a0 $t7
	jal _print
	move $t0 $t1
	lw $t1 0($t0)
	lw $s0 4($t1)
	li $t1 4
	mul $s1, $t1, 6
	move $a0 $s1
	jal _halloc
	move $t1 $v0
	move $s1 $t1
	sw $t7, 0($s1)
	sw $t6, 4($s1)
	sw $t5, 8($s1)
	sw $t4, 12($s1)
	sw $t3, 16($s1)
	sw $t2, 20($s1)
	move $a0 $t0
	move $a1 $s1
	jalr $s0
	move $t1 $v0
	move $t0 $t1
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
	.globl MT4_Change
MT4_Change:
	sw $fp, -8($sp)
	move $fp, $sp
	subu $sp, $sp, 36
	sw $ra, -4($fp)
	sw $t0, 0($fp)
	sw $t1, 4($sp)
	sw $t2, 8($sp)
	sw $t3, 12($sp)
	sw $t4, 16($sp)
	sw $t5, 20($sp)
	sw $t6, 24($sp)
	move $a0 $a0
	move $t0 $a1
	lw $t1 0($t0)
	lw $t2 4($t0)
	lw $t3 8($t0)
	lw $t4 12($t0)
	lw $t5 16($t0)
	lw $t6 20($t0)
	move $a0 $t1
	jal _print
	move $a0 $t2
	jal _print
	move $a0 $t3
	jal _print
	move $a0 $t4
	jal _print
	move $a0 $t5
	jal _print
	move $a0 $t6
	jal _print
	li $v0 0
	lw $t0,0($fp)
	lw $t1,4($sp)
	lw $t2,8($sp)
	lw $t3,12($sp)
	lw $t4,16($sp)
	lw $t5,20($sp)
	lw $t6,24($sp)
	lw $ra, -4($fp)
	lw $fp, -8($fp)
	addu $sp, $sp, 36
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

