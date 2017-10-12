/*
Brian Ryan 21/05/2016. Data Structures Assignment 2
 */

import java.util.Comparator;

public class Point implements Comparable<Point> 
{
     // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() 
    {
        @Override
        public int compare(Point p1, Point p2) 
        {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            if (slope1 < slope2) 
            {
                return -1;
            } 
            else if (slope1 > slope2) 
            {
                return 1;
            } 
            else 
            {
                return 0;
            }
        }
    };
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) 
    {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // slope between this point and that point
    public double slopeTo(Point that) 
    {
        if (that == null) 
        {
            throw new java.lang.NullPointerException();
        }
        if (this.x != that.x) 
        {
            if (this.y != that.y) 
            {
                return (double) (this.y - that.y)/(this.x - that.x);
            } 
            else 
            {
                return 0.0;
            }
        }
        else if (this.y != that.y) 
        {
            return Double.POSITIVE_INFINITY;
        } 
        else 
        {
            return Double.NEGATIVE_INFINITY;
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) 
    {
        if (that == null)
        {
            throw new java.lang.NullPointerException();
        }
        if (this.y < that.y) 
        {
            return -1;
        } 
        else if (this.y > that.y) 
        {
            return 1;
        } 
        else if (this.x < that.x) 
        {
            return -1;
        } 
        else if (this.x > that.x) 
        {
            return 1;
        } 
        else 
        {
            return 0;
        }
    }
    
    // plot this point to standard drawing
    public void draw() 
    {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) 
    {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // return string representation of this point
    public String toString() 
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    // unit test
    public static void main(String[] args) 
    {
        /* YOUR CODE HERE */
    }
  
}
