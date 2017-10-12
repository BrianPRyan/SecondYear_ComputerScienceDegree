#include <stdio.h>
#include <stdlib.h>
//#include <time.h>

int main()
{
    int R=1;
    int P=2;
    int S=3;
    int i;
    int Pscore =0;
    int P2Score =0;
    int choice;
    int P2Choice;
    
    //srand(time(NULL));
    printf("Rock = 1, Paper = 2 and Scissors = 3\n");

    for(i=0;i<5;i++){
        printf("Enter your choice:");
        scanf("%d",&choice);
        printf("Player 2 Enter your choice:");
        scanf("%d",&P2Choice);
            //int computer=rand()%3+1;
            if(choice==1){
                if(P2Choice==1){
                    printf("Draw\n");
                }
                if(P2Choice==2){
                    printf("Player 2 Wins!\n");
                    P2Score= P2Score + 1;
                }
                if(P2Choice==3){
                    printf("Player 1 Wins\n");
                    Pscore = Pscore + 1;
                }
            }
              else  if(choice==2){
                    if(P2Choice==2){
                        printf("Draw\n");
                    }
                    if(P2Choice==3){
                        printf("Player 2 Wins!\n");
                        Pscore = P2Score + 1;
                    }
                    if(P2Choice==1){
                        printf("Player 1 Wins!\n");
                        P2Score= Pscore + 1;
                    }
                }
                else if(choice==3){
                    if(P2Choice==3){
                        printf("Draw\n");
                    }
                    if(P2Choice==2){
                        printf("Player 1 Wins!\n");
                        P2Score= Pscore + 1;
                    }
                    if(P2Choice==1){
                        printf("Player 2 Wins!\n");
                        Pscore = P2Score + 1;

                    }
                }
        else{
            printf("Wrong Answer\n");
        }

            }
            if(P2Score > Pscore ){
                printf("Player 2 wins %d to %d\n",P2Score,Pscore);
            }
            else if(P2Score < Pscore ){
                printf("Player 1 wins %d to %d\n",Pscore,P2Score);
            }
           else if(P2Score = Pscore ){
                printf("No winner it is a draw!\n");
            }
            
            getch();
            return (0);
}
