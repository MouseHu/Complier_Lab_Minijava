    .text
    .globl main
main:
    sw $fp, -8($sp)
    subu $sp, $sp, 24
    sw $ra, -4($fp)
    li $a0 4 
    jal _halloc
    move $t0  $v0
    move $t1  $t0 
    la $t0  Fac_ComputeFac 
    sw $t1 , 0 ( $t0 )
    li $a0 4 
    jal _halloc
    move $t0  $v0
    move $t2  $t0 
    sw $t2 , 0 ( $t1 )
    move $t0  $t2 
    lw $t1 , $t0 ( 0 )
    lw $t2 , $t1 ( 0 )
    li $t1  4 
    mul $t3 , $t1 , 1 
    move $a0 $t3 
    jal _halloc
    move $t1  $v0
    move $t3  $t1 
    li $t1  10 
    sw $t3 , 0 ( $t1 )
    move $a0  $t0 
    move $a1  $t3 
    jalr $t2 
    move $t1  $v0 
    move $a0 $t1 
    jal _print
    lw $t0 ,0($fp)
    lw $t1 ,4($fp)
    lw $t2 ,8($fp)
    lw $t3 ,12($sp)
    lw $ra, -4($fp)
    lw $fp, -8($fp)
    addu $sp, $sp, 24
    j $ra
Fac_ComputeFac:
    sw $fp, -8($sp)
    subu $sp, $sp, 32
    sw $ra, -4($fp)
    sw $t0 ,0($fp)
    sw $t1 ,4($fp)
    sw $t2 ,8($fp)
    sw $t3 ,12($sp)
    sw $t4 ,16($sp)
    sw $t5 ,20($sp)
    move $t1  $a0 
    move $t0  $a1 
    lw $t2 , $t0 ( 0 )
    slt $t0 , $t2 , 1 
    move $t3  $t0 
    beqz $t3  Fac_ComputeFac_L0 
    li $t0  1 
    j Fac_ComputeFac_L1 
Fac_ComputeFac_L0     move $t3  $t1 
    lw $t1 , $t3 ( 0 )
    lw $t4 , $t1 ( 0 )
    li $t1  4 
    mul $t5 , $t1 , 1 
    move $a0 $t5 
    jal _halloc
    move $t1  $v0
    move $t5  $t1 
    sub $t1 , $t2 , 1 
    sw $t5 , 0 ( $t1 )
    move $a0  $t3 
    move $a1  $t5 
    jalr $t4 
    move $t1  $v0 
    mul $t3 , $t2 , $t1 
    move $t0  $t3 
Fac_ComputeFac_L1     nop
    move $v0  $t0 
    lw $t0 ,0($fp)
    lw $t1 ,4($fp)
    lw $t2 ,8($fp)
    lw $t3 ,12($sp)
    lw $t4 ,16($sp)
    lw $t5 ,20($sp)
    lw $ra, -4($fp)
    lw $fp, -8($fp)
    addu $sp, $sp, 32
    j $ra
         .text            
         .globl _hallocs  
_hallocs:                 
         li $v0, 9        
         syscall          
         j $ra            
                          
         .text            
         .globl _printint 
_printint:                
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

