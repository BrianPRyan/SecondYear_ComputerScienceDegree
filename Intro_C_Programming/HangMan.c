/*Brian Ryan 01/12/2015. 
Section B. Hangman Game*/

#include <stdio.h>       //include standard input/output library header file
#include <stdlib.h>      //include general purpose standard library of C
#include <string.h>      //include string library header file to enable manipulation of strings

int main()
{  
      
   //calling main game logo function
   printLogo();
    
   //setting variable that will store the user choice 
   int selection=0; 
   
   //do while loop to create menu for user              
   do	
   {
   printf("\nWelcome to Hangman! by Brian Ryan");
   printf("\n*********************************************************************");
   printf("\n1 - PLAY HANGMAN");
   printf("\n2 - ENTER NEW PLAYER DETAILS");
   printf("\n3 - VIEW PAST PLAYER DETAILS");
   printf("\n*********************************************************************");
   printf("\nChoose the Menu Option of your choice, please enter a number from 1-3");
   printf("\nOr please Enter 0 to Exit");
   printf("\n*********************************************************************\n");
    
   //scanning user choice and storing to the selection variable             
   scanf("%d",&selection);
    
   //switch statement to choose which function to select             
   switch(selection) 
   {
   
   //if number 1 selected play game                                	
   case 1:gameLoop(); 
   break;
    
   //if number 2 selected enter player details             
   case 2:fileWrite(); 
   break;
   
   //if number 3 selected view player details             
   case 3:fileRead(); 
   break;       
   }
   
   //continue loop until user enters 0 to quit
   }while (selection != 0);  
    
    getchar();
	return(0);
	
}//end of main

  //Print main game logo function
  printLogo()
  {
  printf("--------------------------------------------\n");
  printf("| #  #   #   #   #  #### #   #   #   #   # |\n");
  printf("| #  #  # #  ##  # #     ## ##  # #  ##  # |\n");
  printf("| #### ##### # # # #  ## # # # ##### # # # |\n");
  printf("| #  # #   # #  ## #   # #   # #   # #  ## |\n");
  printf("| #  # #   # #   #  ###  #   # #   # #   # |\n");
  printf("--------------------------------------------\n\n");
  }//end of print logo function
  
  //Game logic function
  gameLoop()
	{
        //ensuring a diff random number is generated every time
		srand(time(NULL));
		
		//generating random number
		int NumberGenerator = rand() % 50;
        
        //list of 50 words for use in game    
		char ListOfWords[][25] = {"pasta","door","planet","steel","manchester","person","super","computer","science","game","hangman","winner","looser","college","ireland","england",
        "beef","table","building","pork","walking","early","unreal","lazy","database","chip","football","basketball","tennis","hurling","spain","france",
        "yard","window","road","timber","sprinting","middle","false","true","design","car","truck","van","caravan","university","germany","holland","japan","russia"};                                                                                                                                                           

        //setting the length of the word to the chosen word
        int WordLength = strlen(ListOfWords[NumberGenerator]);
     
        //setting all the guessed chars to NULL before we start.This is incase someone enters lots of letters. We use the first char later
	    char LettersGuessed[25] = {'\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0',};
	    
	    /*this line was used for error checking whilst creating the game. if you take out the comment marks, this line will print the word you have to guess to screen, it will also 
        print where in the index the word lies and the length of the word*/
        //printf("ListOfWords : %s     NumberGenerator : %d     WordLength : %d\n", ListOfWords[NumberGenerator], NumberGenerator, WordLength);
	
	    //variables used throughout the game logic
	    char PlayerGuess[25];
		char PlayerGuess1;
		int CorrectGuesses = 0;
		int NumberLoop = 0;
		int GiveUp = 0;
		int ChancesLeft = 5;
		
	//Every player guess starts here. while guesses are less than the random word length
	while(CorrectGuesses < WordLength)
	{
        //enter X to quit statement
        printf("Enter X at any stage to quit\n");
        
        //letting the player know how many blank letters are in the word
		printf("Word You need to Guess: ");
		
		for(NumberLoop = 0;NumberLoop < WordLength; NumberLoop++)
		{
            // this validates the above loop. Whether or not a letter has already been guessed correctly before
            if(LettersGuessed[NumberLoop] != '\0')
			{
				printf(" %c ", LettersGuessed[NumberLoop]);
			}
			else
			{
				printf(" _ ");
			}//end of validation loop
		}
		printf("\n");
		printf("\n");//allowing some space
		
		//how many correct guesse has the player had
		printf("CorrectGuesses : %d\n", CorrectGuesses);
		
		//enter guess letter
		printf("Enter PlayerGuess Letter : \n");
		
		//putting guess letter into PlayerGuess variable
		fgets(PlayerGuess, 16, stdin);
		
		//setting variable to enable compare guess to string
		int Compare = 0;
		
		//checking if X has been entered
		if(strncmp(PlayerGuess, "X", 1) == 0)
		{
            //if X is entered break from loop
			GiveUp = 1;
			break;
		}
		
		//dealing with the scanned in letter
		PlayerGuess1 = PlayerGuess[0];
		
		//Validating what we have as our letter
		printf("PlayerGuess1 : %c\n", PlayerGuess1);
		
		//setting compare to the remaining guesses
		Compare = CorrectGuesses;
		
		//this loop checks each letter in the word and compares it to your letter
		for(NumberLoop = 0; NumberLoop < WordLength; NumberLoop++)
		{
            //our letter will be compared with all letter in player guessed
			if(LettersGuessed[NumberLoop] == PlayerGuess1)
			{
                // Continue will break out of our of main loop if we have already entered this letter successfully before
				continue;
			}
			
			if(PlayerGuess1 == ListOfWords[NumberGenerator][NumberLoop])
			{
                
               	//checking every letter in the word
				LettersGuessed[NumberLoop] = PlayerGuess1;
				
				int i = 0;
				
				for(i = 0; i < 16; i++) 
				{
        			printf("%c", LettersGuessed[i]);
    				
				}
				printf("\n");
				printf("\n");//allowing some space
				
				//adding 1 to correct num of guesses
				CorrectGuesses++;
			}
				
		}
             
            //checking your score against lives	
			if (Compare == CorrectGuesses)
			{
				ChancesLeft--;
				printf("You loose a life\n");
			}
			else
			{
				printf("Well Done\n");
			}
			if(ChancesLeft<1)
			{
				break;
			}
		
	}
	    
	    //checking if you quit
		if(GiveUp == 1 )
		{
		printf("Giving up is for Loosers!\n");
		}
		
		//if less than 1 life.......
		else if (ChancesLeft<1)
		{
		printf("Game Over. The correct word was  : %s\n", ListOfWords[NumberGenerator]);
		}
		
		//winner statement
		else
		{
			printf("Well Done - You're a Winner!\n");
		}
		
	}//end of game loop function 

  //Write player details to a file
  fileWrite()
  {
   
   //creating struct called player 
   typedef struct 
   {
      char name[15];
      int age;
   }player;         
             
             //pointing pointer to file
             FILE *details;
             //opening/creating txt file
             details = fopen("pDetails.txt","a");
             
             //error handling incase file cant be found
             if(details==NULL)
                         {
                  
                         printf("\nERROR - Cannot Open Designated File");      
                  
                         }
      
            else
                { 
                
                //create instance of struct called p1
                player p1;
             
                //ask for details
                printf("Please Enter your Name\n");
                scanf("%s",&p1.name);
            
                printf("Please Enter your Age\n");
                scanf("%d",&p1.age); 
             
                //print details to file
                fprintf(details,"%s\t",p1.name);
                fprintf(details,"%d\t\n",p1.age);
                }
                
                //close file
                fclose(details);   
                                    
  }//end of write to file function

  //Read player details from a file
  fileRead()
  {
            //pointing pointer to file
            FILE *details1;
            details1 = fopen("pDetails.txt","r"); 
            
            //line to read
            char singleLine[100];
            
            //while not at end of file. read it all
            while(!feof(details1)) 
                {
                    fgets(singleLine, 100, details1);
                    puts(singleLine);                         
                }  
            
            //close file
            fclose(details1); 
            
  }//end of read from file function
