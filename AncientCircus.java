// required imports
import java.util.Scanner;

/**
 *  Polygon class
 *  - helps us to calculate the smallest possible area 
 *    of the regular polygon formed by given three points
 */
class Polygon {
	
	private double x1, x2, x3, y1, y2, y3; // to store 3 points (xi, yi)
	
    /**
     *  Parameterized constructor
     * @param points : double[][] : inputed 2D points
     * 
     */
	Polygon (double[][] points){
		
        // initializing the point(xi, yi) values
		x1 = points[0][0]; y1 = points[0][1];
		x2 = points[1][0]; y2 = points[1][1];
		x3 = points[2][0]; y3 = points[2][1];
		
	}
	
    /**
     *  to calculate the smallest possible area 
     *  of the regular polygon formed by given three points
     * 
     * @return : double : smallest possible area
     */
	public double minArea () {
		
        // Calculate 3 sides of the triangle (formed by the points)
		double sideA = triangleSide(x2, y2, x3, y3);
		double sideB = triangleSide(x1, y1, x3, y3);
		double sideC = triangleSide(x1, y1, x2, y2);

		
		// Calculate 3 angles of the triangle (formed by the points)
		double angleA = triangleAngle(sideA, sideB, sideC);
		double angleB = triangleAngle(sideB, sideA, sideC);
		double angleC = triangleAngle(sideC, sideA, sideB);

		
        // Calculate area of the triangle (formed by the points)
        double areaOfTriangle = triangleArea(sideA, sideB, sideC);

		
        // Calculate circumradius  of the triangle  (formed by the points)
		double circumRadius = (sideA * sideB * sideC) / (4 * areaOfTriangle);


        // Calculate total number of sides of the regular polygon
        int totalSide = (int) (Math.PI / fGCD( fGCD(angleA, angleB), angleC));


        // Calculate minimum area possible of the regular polygon
        double minArea = ( totalSide * Math.pow(circumRadius, 2) * Math.sin((2 * Math.PI)/totalSide)) / 2;

        
        return minArea;
		
	}

    /**
     * 
     *  to calculate length of a side of any triangle
     * 
     * @param x1 : double : x coordinate of 1st point
     * @param y1 : double : y coordinate of 1st point
     * @param x2 : double : x coordinate of 2nd point
     * @param y2 : double : y coordinate of 2nd point
     * @return : double : length of a side of a triangle
     */
    private double triangleSide(double x1, double y1, double x2, double y2){

        // returns the distance between two points
        return Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) );

    }

    /**
     *  to calculate a angle of any triangle
     * 
     * @param sideA : double : 1st side of a triagle
     * @param sideB : double : 2nd side of a triagle
     * @param sideC : double : 3rd side of a triagle
     * @return : double : angle of a triangle ( opposite to sideA and created by sideB and sideC)
     */
    private double triangleAngle(double sideA, double sideB, double sideC){

        return Math.acos( ( Math.pow(sideB, 2) + Math.pow(sideC, 2) - Math.pow(sideA, 2)) / (2 * sideB * sideC) );
    }

    /**
     *  to calculate area of a triange (using Heron's Formula)
     * 
     * @param sideA : double : 1st side of a triagle
     * @param sideB : double : 2nd side of a triagle
     * @param sideC : double : 3rd side of a triagle
     * @return : double : area of the triangle
     */
    private double triangleArea(double sideA, double sideB, double sideC){
        
        double perimeter = sideA + sideB + sideC; // perimeter of the triangle

        double semiperi = perimeter / 2; // semiperimeter of the triangle

        double area = Math.sqrt( semiperi * (semiperi - sideA) * (semiperi - sideB) * (semiperi - sideC)); // total area of the triangle

        return area;
    }

    /**
     *  to calculate gcd of two floating point number (double type)
     *  
     *  ** Euclidean Algorithm
     * 
     * @param a : double : 1st number(dividend)
     * @param b : double : 2nd number(divisor)
     * @return : double : gcd of a and b
     */
    private double fGCD(double a, double b){

        // base case
        if( Math.abs(b) < 1e-4) return a; // almost equals to zero

        // calculate reminder of a divided by b

        int quotient = (int) (a / b);

        double reminder = a - quotient * b;


        return fGCD(b, reminder);

    }
	
}



public class AncientCircus {
	
	public static void main (String[] args){

        Scanner scanner = new Scanner(System.in); // to take inputs
		
		double[][] points = new double[3][2]; // stores three 2D points

        // take inputs
        // 3 line with 2 double type number

        for(int i = 0; i < 3; i++){
            points[i][0] = scanner.nextDouble();
            points[i][1] = scanner.nextDouble();
        }
		
		Polygon polygon = new Polygon(points); // Polygon object intialization with the given 3 points
		

        // Output : smallest possible area of the regular polygon (8 decimal places)
		System.out.printf("%.8f", polygon.minArea());

        scanner.close();
	}
	  
}
