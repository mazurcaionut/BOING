import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Triangle is a shape that can be drawn to the screen, either
 * filled with colour or opaque.
 * Its position is determined by the left point
 */

public class Triangle extends ClosedShape{
    //Array needed for the x points.
    private double[] xPoints=new double[3];
    //Array needed for the y points.
    private double[] yPoints=new double[3];
    //The side of the triangle.
    private int side;
    //The initial side of the triangle.
    private int initialSide;

    /**
     * Creates a triangle.
     * @param shape The shape type.
     * @param insertionTime The insertion time.
     * @param x1 The display component's x position.
     * @param y1 The display component's y position.
     * @param vx The display component's x velocity.
     * @param vy The display component's y velocity.
     * @param side The side of the triangle.
     * @param colour The line colour or fill colour.
     * @param isFilled True if the triangle is filled with colour, false if opaque.
     * @param isPulsing True if the triangle is pulsing, false otherwise.
     */
    public Triangle (String shape, int insertionTime, int x1, int y1, int vx, int vy, int side, Color colour, boolean isFilled, boolean isPulsing) {
        super (shape, insertionTime, x1, y1, vx, vy, colour, isFilled, isPulsing);
        setSide(side);
        setInitialSide();
        setCoordinates(x1,y1,side);
    }

    /**
     * Sets the coordinates of the first point of the triangle and also the side .
     * @param x1 The x coordinate of the first point.
     * @param y1 The y coordinate of the first point.
     * @param side The side of the triangle.
     */
    public void setCoordinates(double x1, double y1, int side){
        //Sets the triangle side.
        setSide(side);

        //Set the x and y coordinates for the first point.
        this.xPoints[0]=x1;
        this.yPoints[0]=y1;

        //Set the x and y coordinates for the second point.
        this.yPoints[1]=this.yPoints[0];
        this.xPoints[1]=side+this.xPoints[0];

        //Compute the x and y coordinates for the third point.
        this.xPoints[2]=(this.xPoints[0]+this.xPoints[1])/2;
        this.yPoints[2]=this.yPoints[0]-Math.sqrt(Math.pow(side,2)-Math.pow((this.xPoints[2]-this.xPoints[0]),2));
    }


    /**
     * @param side The side of the triangle.
     */
    public void setSide(int side){
        this.side=side;
    }


    /**
     * @return The side of the triangle.
     */
    public int getSide(){
        return this.side;
    }

    /**
     * Method to convert a triangle to a string.
     */
    public String toString () {
        String result = "This is an equilateral triangle\n";
        result += super.toString ();
        result += "Its side is " + this.side + "\n";
        return result;
    }

    /**
     * @return The width of the triangle.
     */
    public int getWidth(){
        return side;
    }

    /**
     * @return The height of the triangle.
     */
    public int getHeight(){
        return side;
    }

    /**
     * @return The shape type.
     */
    public String getShape(){
        return this.shape;
    }

    /**
     * Puts the shape back in bounds in X.
     */
    @Override
    public void putInBoundsX (double winX) {
        //For the left margin the first point is the first one to go out.
        if (this.xPoints[0] < 0){
            xPoints[0] = 0;
            setCoordinates(xPoints[0],yPoints[0],side);
        }
        //For the right margin the second point is the first one to go out.
        if (this.xPoints[1] > winX) {
            this.xPoints[0] = (int) (winX - Math.ceil (this.side)) ;
            setCoordinates(xPoints[0],yPoints[0], side);
        }
    }

    /**
     * Puts the shape back in bounds in Y.
     */
    @Override
    public void putInBoundsY (double winY) {
        //For the upper margin the third point is the first one to go out.
        if (this.yPoints[2] < 0){
            this.yPoints[2] = 0;
        }
        //For the lower margin the first and the second point are the first one that go out
        if (this.yPoints[0] > winY) {
            this.yPoints[0] = winY;
            setCoordinates(xPoints[0],yPoints[0],side);
        }
    }

    /**
     * Sets the initial side.
     */
    public void setInitialSide(){
        this.initialSide=side;
    }

    /**
     * @return The initial side.
     */
    public int getInitialSide(){
        return this.initialSide;
    }

    /**
     * Returns true if the shapes have gone out of bounds in X.
     */
    @Override
    public boolean outOfBoundsX (double winX) {
        return (this.xPoints[1]> winX) || (this.xPoints[0] < 0);
    }

    /**
     * Returns true if the shapes have gone out of bounds in Y.
     */
    @Override
    public boolean outOfBoundsY (double winY) {
        return (this.yPoints[0] > winY) || (this.yPoints[2] < 0);
    }

    /**
     * Takes in a direction and a velocity and moves the shape
     * in that direction on unit by increasing every the coordinates
     * of every point of the triangle.
     */
    @Override
    public void move () {
        for(int i=0;i<3;i++){
            this.xPoints[i]+=xVec;
            this.yPoints[i]+=yVec;
        }
    }

    /**
     * Draw the triangle on the screen.
     * @param g The graphics object of the scene component.
     */
    public void draw (GraphicsContext g) {
        g.setFill( colour );
        g.setStroke( colour );
        if (isFilled) {
            g.fillPolygon(xPoints,yPoints,3);
        }
        else {
            g.strokePolygon(xPoints,yPoints,3);
        }
    }
}
