#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
 
int main(void){
    pid_t p1=fork();
    wait();
    if(p1==-1){
        exit(-1);
    }
    if(p1==0){
        printf("I'm..\n");
    }else{
        printf("I'll never join you!\n");
        pid_t p2=fork();
        wait();
        if(p2==-1){
            exit(-1);
        }
        if(p2==0){
         printf("the..\n");     
        }else{
            printf("I'll never join you!\n");
            pid_t p3=fork();
                wait();
            if(p3==-1){
                exit(-1);
            }
            if(p3==0){
                printf("father.\n");
            }else{
                printf("I'll never join you!\n");
                wait(); 
            }
        }
    }
    return 0;
}
