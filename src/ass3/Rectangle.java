/**
 * 
 */
package ass3;

import java.util.ArrayList;
import java.util.List;
/**
 * @author jessica
 *
 */
public class Rectangle {
	
	private Point upperLeft;
	private double width;
	private double height;

	/**
	 * 
	 */
	// Create a new rectangle with location and width/height.
	   public Rectangle(Point upperLeft, double width, double height) {
		   this.upperLeft = upperLeft;
		   this.width = width;
		   this.height = height;
	   }
	   

	   // Return a (possibly empty) List of intersection points
	   // with the specified line.
	   public List<Point> intersectionPoints(Line line){
		   double x = this.upperLeft.getX();
		   double y = this.upperLeft.getY();
		   if (line.start().getY() >= 645 && line.start().getX() >= 1104 && this.getWidth() == 1200) {
			   System.out.println("vcxd");
		   }
		   List<Point> intersections = new ArrayList<Point>();
		   Line[] sides = new Line[4];
		   sides[0] = new Line (x, y, x + this.width, y);
		   sides[1]= new Line (x, y + this.height, x + this.width, y + this.height);
		   sides[2] = new Line (x, y, x, y + height);
		   sides[3] = new Line (x + this.width, y, x + this.width, y + this.height);
		   
		   for (Line side : sides) {
			   if (line.isIntersecting(side)) {
				   intersections.add(line.intersectionWith(side));
			   }
		   }
		   
		   return intersections;
	   }


	/**
	 * @return the upperLeft
	 */
	public Point getUpperLeft() {
		return upperLeft;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

}
