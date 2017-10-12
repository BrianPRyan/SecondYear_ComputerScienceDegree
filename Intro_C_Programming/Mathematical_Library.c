/*Brian Ryan 11/11/2015. Section A. Mathematical Library*/

#include <stdio.h>       //include standard input/output library header file
#include <math.h>        //include math library header file
#define PI 3.14159265    //define value of PI 

     main()
     {
                 
        int selection=0; //setting variable that will store the user choice
                 
       	         do	//loop to create menu for user
                {
                 printf("\nThese functions can help with Maths related Game Development probelms");
                 printf("\n*********************************************************************");
                 printf("\n1 - Modulus-Return the remainder of x divided by y");
                 printf("\n2 - Square Root-Return the square root of x");
                 printf("\n3 - Power-Return the result of raising x to the power of y");
                 printf("\n4 - Log-Returns the natural logarithm of x");
                 printf("\n5 - Cosine-Returns the cosine of x in degress");
                 printf("\n*********************************************************************");
                 printf("\nChoose the function you want to use, please enter a number from 1-5");
                 printf("\nOr please Enter 0 to Exit");
                 printf("\n*********************************************************************\n");
                 
                 scanf("%d",&selection); //scanning user choice and storing to the selection variable
                 
                 switch(selection) //switch statement to choose which function to select
                 {
                                   	
	             case 1:mod(); //if number 1 selected mod function is called
                 break;
                 
                 case 2:squareR(); //if number 2 selected square root function is called
                 break;
                 
                 case 3:power();   //if number 3 selected Raise to the power function is called
                 break;
                 
                 case 4:logBaseE(); //if number 4 selected log base e function is called
                 break;
                 
                 case 5:cosine(); //if number 5 selected cosine of x function is called
                 break;
                 
                 }
                }
                while (selection != 0);  //continue loop until user enters 0 to quit      
         
              getch();
              return(0);        
     }
        mod() //function block of code               
        {
              float x=0; //declare variables
              float y=0; 
                                      
              printf("\nPlease enter x value\n");    //ask for values
              scanf("%f",&x);                        //take in values
              
              printf("\nPlease enter y value\n");
              scanf("%f",&y);
              
              printf("\nModulus of %.2f / %.2f is %.2f\n",x,y,fmod(x,y)); //print out results of calculation and perform function inline
              printf("\n*********************************************************************"); 
        }
        squareR()//function block of code
        {
              float x=0;//declare variable 
                                     
              printf("\nPlease enter x value\n");//ask for value
              scanf("%f",&x);//take in values
              
              printf("\nSquare Root of %.2f is %.2f\n",x,sqrt(x));//print out results of calculation and perform function inline
              printf("\n*********************************************************************");  
        }
        power()//function block of code
        {
              float x=0;//declare variables
              float y=0; 
              float ans=0; 
                                       
              printf("\nPlease enter x value\n");//ask for values
              scanf("%f",&x);//take in values
              
              printf("\nPlease enter the value you want to raise x to the power of\n");
              scanf("%f",&y);
              
              ans=pow(x,y);//performing power function
              
              if(ans==HUGE_VAL)//if else statement to control macro defined in the math library. This macro is used when the result of a function may not be representable as a floating point number. 
              {
                        puts("Sorry the numbers are to large, please try again"); //This macro is used when the result of a function may not be representable as a floating point number.      
              }
              else
              {
                        printf("\n%.2f raised to the power of %.2f is %.2f\n",x,y,ans);//print out results of calculation and perform function inline
              }
              printf("\n*********************************************************************");                        
        }
        logBaseE()//function block of code
        {
              float x=0;//declare variable
                                        
              printf("\nPlease enter x value\n");//ask for value
              scanf("%f",&x);//take in values
              
              printf("\nLog(%.2f) = %f\n",x,log(x));//print out results of calculation and perform function inline
              printf("\n*********************************************************************");                        
        } 
        cosine()//function block of code
        {
              float x=0;//declare variables
              float v=0;
              float r=0;
              
              v = PI / 180.0; //conversion to degrees from radians 
                                     
              printf("\nPlease enter x value\n");//ask for value
              scanf("%f",&x);//take in values
              
              r = cos(x*v);//finding the cosine of x 
              
              printf("\nThe cosine of %.2f is %.2f degrees",x,r);//print out results of calculation
              printf("\n*********************************************************************");                        
        }

