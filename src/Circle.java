import java.util.*;
import java.awt.*;

public class Circle{

/*

    Corey Carter
    09-23-18
    CSCI-1933
    Project One: Object-oriented geometry and fractals
    Circle Class: Holds Circle object data

*/

    // private variables for Circle class
    private double thisX = 0;
    private double thisY = 0;
    private double thisRadius = 0;
    private Color thisColor = null;
    private static final double PI = 3.14159265; // Uppercase because it's a global variable for the Circle class.

    // Circle constructor method

    public Circle(double x, double y, double radius){
        // default constructor for Circle class
        this.thisX = x;
        this.thisY = y;
        this.thisRadius = radius;
    }

    // Calculate Perimeter and Area of Circle class instance

    public double calculatePerimeter(){
        // calculates shapes perimeter
        return(2 * PI * this.getRadius());
    }

    public double calculateArea(){
        // calculates shapes area
        return(PI * Math.pow(this.getRadius(), 2));
    }

    // Setter Methods

    public void setColor(Color shapeColor){
        // Sets shapes color
        this.thisColor = shapeColor;
    }

    public void setPos(double x, double y){
        // Sets 'X' and 'Y' positions
        this.thisX = x;
        this.thisY = y;
    }

    public void setRadius(double radius){
        // Sets shapes radius
        this.thisRadius = radius;
    }

    // Getter Methods

    public Color getColor(){
        //getter method for shape Color
        return(this.thisColor);
    }

    public double getXPos(){
        // Getter for shapes 'X' position
        return(this.thisX);
    }

    public double getYPos(){
        // Getter for shapes 'Y' position
        return(this.thisY);
    }

    public double getRadius(){
        // Getter for shapes radius
        return(this.thisRadius);
    }

}
