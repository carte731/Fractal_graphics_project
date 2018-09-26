import java.lang.reflect.Array;
import java.util.*;
import java.awt.*;

/*

    Corey Carter
    09-23-18
    CSCI-1933
    Project One: Object-oriented geometry and fractals
    Main Class: Holds Main function and class

 */

public class Main { // Main Class

    private Canvas drawing = null; // Creates a private canvas object to draw on
    private double totalArea = 0; // Holds the total area for objects created
    private Color[] colorArray = new Color[]{ // An array that holds all the Colors for shapes

            Color.BLACK,
            Color.LIGHT_GRAY,
            Color.BLUE,
            Color.MAGENTA,
            Color.CYAN,
            Color.ORANGE,
            Color.DARK_GRAY,
            Color.PINK,
            Color.GRAY,
            Color.RED,
            Color.GREEN,
            Color.YELLOW

    };

    public Main(){
        // Assigns the values to the Canvas object after the user inputs what shape they want
        drawing = new Canvas(1250,1250);
    }

    public Color randomColor(){
        // Picks and returns a random color from Colors Array
        return(colorArray[new Random().nextInt(12)]);
    }

    public void triFracing(int depth, double height, double width, double x, double y){
        // Creates fractal drawing of Triangle objects
        Triangle baseTri = new Triangle(x, y, width, height); // Creates Triangle
        totalArea += baseTri.calculateArea(); // Saves it area to the private value
        baseTri.setColor(randomColor()); // Grabs and sets a random color from the Colors Array
        drawing.drawShape(baseTri); // Draws the shape
        if(depth > 0) { // Checks the depth level
            --depth; // removes 1 from the depth counter
            triFracing(depth, height/2, width/2, x + width, y); // The recursive call, right triangle
            triFracing(depth, height/2, width/2, x - (width/2), y); // Left triangle
            triFracing(depth, height/2, width/2, x + (width/4), y-height); // Top triangle
        }
    }

    public void circleFracing(int depth, double radius, double x, double y){
        // Creates fractal drawing of Circle objects
        // Operates same as the Triangle fractal method
        Circle baseCircle = new Circle(x, y, radius);
        totalArea += baseCircle.calculateArea();
        baseCircle.setColor(randomColor());
        drawing.drawShape(baseCircle);
        if(depth > 0) {
            --depth;
            circleFracing(depth, radius/2, x + (radius/20), y - (radius * 1.5)); // Top circle
            circleFracing(depth, radius/2, x + (radius * 1.5), y); // Right circle
            circleFracing(depth, radius/2, x - (radius * 1.5), y); // Left circle
        }
    }

    public void retangleFracing(int depth, double height, double width, double x, double y){
        // Creates fractal drawing of Rectangle objects
        // Operates same as the Triangle and Circle fractal methods
        Rectangle baseRectangle = new Rectangle(x, y, width, height);
        totalArea += baseRectangle.calculateArea();
        baseRectangle.setColor(randomColor());
        drawing.drawShape(baseRectangle);
        if(depth > 0) {
            --depth;
            retangleFracing(depth, height/2, width/2, x + width, y + height); // Bottom-right circle
            retangleFracing(depth, height/2, width/2, x - width/2, y + height); // Bottom-left circle
            retangleFracing(depth, height/2, width/2, x + width, y - height/2); // Top-right circle
            retangleFracing(depth, height/2, width/2, x - width/2, y - height/2); // Top-left circle
        }
    }

    public void abstractTri(int depth, double height, double width, double x, double y, Color upperColor){

        // SOMETIMES YOU HAVE TO RE-SIZE TO SEE FULL PATTERN!

        Triangle baseTri = new Triangle(x, y, width, height); // Creates Triangle
        totalArea += baseTri.calculateArea(); // Saves it area to the private valuvariable
        baseTri.setColor(randomColor()); // Grabs and sets a random color from the Colors Array
        if(upperColor == baseTri.getColor()){ // Makes sure that each level of the inner triangle has a different color
            baseTri.setColor(randomColor());
        }
        drawing.drawShape(baseTri); // Draws the shape
        if(depth > 0) { // Checks the depth level
            --depth; // removes 1 from the depth counter
            abstractTri(depth, -height, width, x + (width/2), y - height, baseTri.getColor()); // Triangle pattern
            abstractTri(depth, height, width, (x + width) + width, y, baseTri.getColor()); // Triangle pattern 2
            // Embedded triangle
            abstractSub(depth, height/2, width/2, x + (width/4), y - (height/6), baseTri.getColor()); // Inside triangle recursion
        }
    }

    public void abstractSub(int depth, double height, double width, double x, double y, Color lastColor){ // Inner triangle pattern
        Triangle baseTri = new Triangle(x, y, width, height); // Creates Triangle
        totalArea += baseTri.calculateArea(); // Saves it area to the private variable
        baseTri.setColor(randomColor()); // Grabs and sets a random color from the Colors Array
        if(lastColor == baseTri.getColor()){ // Makes sure that each level of the inner triangle has a different color
            baseTri.setColor(randomColor());
        }
        drawing.drawShape(baseTri); // Draws the shape
        if(depth > 0) { // Checks the depth level
            --depth; // removes 1 from the depth counter
            abstractSub(depth, height/2, width/2, x + (width/4), y - (height/6), baseTri.getColor()); // Inside triangle recursion

        }
    }

    public void patricksLeftLeg(int depth, double height, double width, double x, double y) {
        // This draws Patrick's left leg, works like the Rectangle method
        // Draw patrick extra credit draw
        if (depth > 4) {
            Rectangle patLeg = new Rectangle(x, y, width, height);
            totalArea += patLeg.calculateArea();
            patLeg.setColor(Color.GREEN);
            drawing.drawShape(patLeg);
            --depth;
            patricksLeftLeg(depth, height / 2, width / 2, x, y + height);
        } else if ((depth > 0) && (depth <= 4)){
            Rectangle baseCircle = new Rectangle(x, y, width, height);
            baseCircle.setColor(Color.PINK);
            drawing.drawShape(baseCircle);
            --depth;
            patricksLeftLeg(depth, height / 2, width / 2, x, y + height);
        }
    }

    public void patricksRightLeg(int depth, double height, double width, double x, double y){
        // This draws Patrick's right leg, works like the Rectangle method
        // Draw patrick extra credit draw
        if (depth > 4) {
            Rectangle patLeg = new Rectangle(x, y, width, height);
            totalArea += patLeg.calculateArea();
            patLeg.setColor(Color.GREEN);
            drawing.drawShape(patLeg);
            --depth;
            patricksRightLeg(depth, height/2, width/2, x + width/2, y + height);
        } else if ((depth > 0) && (depth <= 4)){
            Rectangle baseCircle = new Rectangle(x, y, width, height);
            baseCircle.setColor(Color.PINK);
            drawing.drawShape(baseCircle);
            --depth;
            patricksRightLeg(depth, height/2, width/2, x + width/2, y + height);
        }
    }

    public void patricksRightArm(int depth, double height, double width, double x, double y){
        // This draws Patrick's right arm, works like the Rectangle method
        // Draw patrick extra credit draw
        if (depth > 0) {
            Rectangle patArm = new Rectangle(x, y, width, height);
            totalArea += patArm.calculateArea();
            patArm.setColor(Color.PINK);
            drawing.drawShape(patArm);
            --depth;
            patricksRightArm(depth, height/2, width/2, x + width/2, y + height);
        }
    }

    public void patricksLeftArm(int depth, double height, double width, double x, double y){
        // This draws Patrick's left arm, works like the Rectangle method
        // Draw patrick extra credit draw
        if (depth > 0) {
            Rectangle patArm = new Rectangle(x, y, width, height);
            totalArea += patArm.calculateArea();
            patArm.setColor(Color.PINK);
            drawing.drawShape(patArm);
            --depth;
            patricksLeftArm(depth, height/2, width/2, x, y + height);
        }
    }

    public void patricksHead(int depth, double height, double width, double x, double y){
        // This draws Patrick's head, uses a triangle
        // Draw patrick extra credit draw
        Triangle patricksHead = new Triangle(x, y, width, height);
        totalArea += patricksHead.calculateArea();
        patricksHead.setColor(Color.PINK);
        drawing.drawShape(patricksHead);
    }

    public void patrick(int depth, double height, double width, double x, double y){
        // Patrick root method that calls the sub-recursive methods
        // Draw patrick extra credit draw
        Rectangle patBody = new Rectangle(x, y, width, height); // Patrick's body
        totalArea += patBody.calculateArea();
        patBody.setColor(Color.PINK);
        drawing.drawShape(patBody);
        int faceDepth = 2;
        if(depth > 0) {
            --depth;
            // Calls bodies recursive methods
            patricksLeftLeg(depth, height/2, width/2, x, y + height);
            patricksRightLeg(depth, height/2, width/2, x + width/2, y + height);
            patricksRightArm(depth, height/2, width/2, x + width, y + height/25);
            patricksLeftArm(depth, height/2, width/2, x - width/2, y + height/25);
            patricksHead(faceDepth, height, width, x, y);
        }
    }

    public static void main(String[] args){
        // Main method for script
        System.out.println("\nPlease make a fractal selection, enter: triangle, circle, rectangle, abstract and patrick or exit(e) to quit:");
        Scanner userInput = new Scanner(System.in); // user input object
        String selection = userInput.nextLine(); // Looks for input
        selection = selection.toLowerCase(); // makes it lowercase
        Main myMain = new Main(); // Default constructor for the Main, assigns values to Canvas drawing object
        int depth = 7; // How many recursive calls to makes
        if(selection.equals("triangle")) { // Checks user answers
            double xValue = 450; // Assigns values for recursive function call
            double yValue = 750;
            double height = 300;
            double width = 300;
            myMain.triFracing(depth, height, width, xValue, yValue); // recursive function call
        } else if(selection.equals("circle")) { // Same as above
            double xValue = 600;
            double yValue = 500;
            double radius = 100;
            myMain.circleFracing(depth, radius, xValue, yValue);
        } else if(selection.equals("rectangle")) { // Same as above
            double xValue = 500;
            double yValue = 350;
            double height = 300;
            double width = 300;
            myMain.retangleFracing(depth, height, width, xValue, yValue);
        } else if(selection.equals("patrick")) { // Same as above but with PATRICK!
            double xValue = 500;
            double yValue = 350;
            double height = 300;
            double width = 300;
            double radius = 50;
            System.out.println("You guys talk funny! SAY MORE WORDS!\n");
            myMain.patrick(depth, height/2, width/2, xValue, yValue);
        }else if(selection.equals("abstract")) { // Checks user answers
            double xValue = -200; // Assigns values for recursive function call
            double yValue = 275;
            double height = 300;
            double width = 300;
            depth = 7;
            Color inputColor = Color.WHITE;
            for(int row=0; row <= 5; row++) {
                myMain.abstractTri(depth, height, width, xValue, yValue, inputColor); // recursive function call
                xValue = -200; // Assigns values for recursive function call
                yValue = yValue + height;
                height = 300;
                width = 300;
                depth = 7;
            }
        } else if(selection.equals("exit") || selection.equals("e")){ // Exits out program if they use types 'exit'
            System.out.println("\nThanks for using my script! Bye...\nEXITING...\n");
            System.exit(0);
        } else { // If they don't enter any valid answers that it recursively calls the Main function and restarts
            System.out.println("\nIncorrect entry... Restarting script.\n");
            myMain.drawing = null;
            main(args);
        }

        System.out.println("\nTotal area for " + selection + " is:" + myMain.totalArea + "\n"); // Displays calculate area
        myMain.totalArea = 0; // Zeroes out total area if the user restarts the script

        System.out.println("Would you like to run the script again: yes(y) or no(n)"); // Ask if the user want to run the script again
        Scanner userRestart = new Scanner(System.in);
        String restart = userRestart.nextLine();
        restart = restart.toLowerCase();
        if(restart.equals("yes") || restart.equals("y")){ // If the user wants to play again, recursive call to Main
            System.out.println("restarting script...\n");
            main(args);
        } else if(restart.equals("no") || restart.equals("n")){ // If they don't want to play, it quits the Java environment
            System.out.println("Thank you for using my script.\nBye...\n");
            System.exit(0);
        } else { // If no valid answers are submitted it 'exit's anyway
            System.out.println("No valid entry made, quitting...\n");
            System.exit(0);
        }

    }
}
