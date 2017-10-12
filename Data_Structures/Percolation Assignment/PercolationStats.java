/*
Brian Ryan 03/03/2016. Data Structures Assignment
 */

public class PercolationStats 
{
    //Declare variable Array of doubles to store results (T)
    private double [] testResults;
        
    	public PercolationStats (int N, int T)
        {
          //N or T can not be less than or equal to zero, must have positive values
          if (N<=0||T<=0) 
              throw new IllegalArgumentException();
                testResults = new double [T];//Initialising T to array (T amount of tests)
    
           //For T amount of tests: SEE NEXT COMMENT below 
          for(int i=0;i<T;i++)
          {
              //Create instance of percolation each T
              Percolation test = new Percolation(N);
              int openCellCount = 0;//Setting open cell counter to zero
      
            while(!test.percolates())
            {
                int column = StdRandom.uniform(N)+1;//Generate randon numbers for column
                int row = StdRandom.uniform(N)+1;//Generate randon numbers for row
                
                //Open cells if not open
                if(!test.isOpen(column,row))
                {
                    test.open(column,row);
                    openCellCount++;//Increment Open cell counter 
                }
            }
                //Calculate and return % of sites that were opened
                testResults[i] = (double)openCellCount/(N*N);
          }    
        }
        
        //Mean of percolation
	public double mean()
        {
            return 
                StdStats.mean(testResults);
	}
	
        //Standard deviation of percolation
	public double stddev()
        {
            return 
                StdStats.stddev(testResults);
	}
	
        //Lower bound of the 95% confidence interval
	public double confidenceLo()
        {
            /*The value of 1.96 is based on the fact that 95% of the area of a normal 
            distribution is within 1.96 standard deviations of the mean*/
            return 
                mean() - ((1.96*stddev()) / Math.sqrt(testResults.length));
	}
	
        //Upper bound of the 95% confidence interval
	public double confidenceHi()
        {
            /*The value of 1.96 is based on the fact that 95% of the area of a normal 
            distribution is within 1.96 standard deviations of the mean*/
            return 
                mean() + ((1.96*stddev()) / Math.sqrt(testResults.length));
	}
	
        //Main to run program
	public static void main(String [] args)
        {
                /*Take in Arguments*/
                 int N = Integer.parseInt( args[0]);
		 int T = Integer.parseInt( args[1]);
            
            //Create new stats (N, T) N = size of grid, T = number of times to run percolation
            PercolationStats ps = new PercolationStats(N,T);//n,t//(300,1000)
            
            //Output
            System.out.println( "Mean: " + ps.mean());
            System.out.println( "Std Dev: " + ps.stddev());
            System.out.println("95% confidence interval: " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}   
}
