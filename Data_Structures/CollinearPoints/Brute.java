/*
Brian Ryan 21/05/2016. Data Structures Assignment 2
 */

import java.util.Arrays;
 
public class Brute 
{
    public static void main(String[] args) 
    {
        // Read points from the input file.
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        int n = 0;
        while (!in.isEmpty()) 
        {
            int x = in.readInt();
            int y = in.readInt();
            points[n] = new Point(x, y);
            points[n].draw();
            n++;
        }
 
        Arrays.sort(points);
 
        // Rescale the coordinate system.    
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
 
        // Go each 4 points and check whether they all lie on the same line segment.
        for (int i = 0; i < N; i++) 
        {
            for (int j = i + 1; j < N; j++) 
            {
                double slopeij = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < N; k++) 
                {
                    double slopeik = points[i].slopeTo(points[k]);
                    if (slopeij == slopeik) 
                    {
                        for (int l = k + 1; l < N; l++) 
                        {
                            double slopeil = points[i].slopeTo(points[l]);
                            if (slopeij == slopeil) 
                            {
                                System.out.println(points[i] + " -> " + points[j] + " -> " + points[k] + " -> " + points[l]);
                                points[i].drawTo(points[l]);
                            }
                        }
                    }
                }
            }
        }
    }
}
