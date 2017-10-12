/*
Brian Ryan 03/03/2016. Data Structures Assignment 1
 */

public class Percolation 
{
  //Declare Variables
  private final int [] grid;
  private final WeightedQuickUnionUF UF;
  private final int edge;
  
        //Create N-by-N grid, with all the sites closed
    	public Percolation (int N)
        {
            edge = N;
            
            //Add 2 to the N * N grid for virtual top and bottom
            UF = new WeightedQuickUnionUF(N*N+2);      
            grid = new int[N*N+2];
            
            //Loop through array setting sites to closed
            for(int position = 0; position<N*N; position++)
            {
                grid[position] = 0;
            }
                        	
	}
         
        //Union method
        private void union(int x, int y)
        {
            if (!UF.connected(x,y))
            {
                UF.union(x,y);
            }
        }
        
        //Checking position of site in real grid 
        private int gridPosition(int row, int column)
        {
            return 
                (edge*(row - 1)) + column - 1;
        }
        
        //Checking that the site is part of the grid 
        private void checkGrid(int i, int j)
        {
            if (i<=0||j<=0||i>edge||j>edge) 
                throw new IndexOutOfBoundsException();
        }

        //Open cell (row i, column j) if it is not already
	public void open (int i, int j)
        {
            checkGrid(i,j);//Checking the site is in the grid
            if(isOpen(i,j))return;//Checking site not already open    
       
            //Declaring variable
            int site = gridPosition(i,j);
            grid[site] = 1;//Setting varaible to 1 = open
                   
            //Checking its not open on the right side
            if(j!=edge && isOpen (i,j+1))
            {
                union(gridPosition(i,j+1),site);//open j+1 to the right
            }
            
            //Checking its not open on the left side
            if(j!=1 && isOpen (i,j-1))
            {
                union(gridPosition(i,j-1),site);//open j-1 to the left
            }   
                      
            //Checking its not open on the bottom row
            if(i!=edge && isOpen (i+1,j))
            {       
                union(gridPosition(i+1,j),site);//open i+1 checking below
            }
       
            else if (i == edge)
            {
                //Connecting to virtual bottom
                union(edge*edge+1,site);
            }
            
            //Checking its not open on the top row
            if(i!=1 && isOpen (i-1,j))
            {
                union(gridPosition(i-1,j),site);//open i-1 checking below
            }
       
            else if(i == 1)
            {
                //Connecting to virtual top
                union(edge*edge,site);
            }
	}
	
        //Checking is the site open
	public boolean isOpen (int i, int j)
        {
            checkGrid(i,j);//Checking the site is in the grid
                return 
                    grid[gridPosition(i,j)] ==1;
	}
	
        //Checking is the site full
	public boolean isFull (int i, int j)
        {
	    checkGrid(i,j);//Checking the site is in the grid
                return 
                    UF.connected(edge*edge,gridPosition(i,j));
	}
	
        //Is the system percolating
	public boolean percolates()
        {
            return 
                UF.connected(edge*edge,edge*edge+1);
	}
}       
