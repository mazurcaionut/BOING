

/**
 * Rect.java
 * @version 2.0.0
 * @author Ioan Mazurrca
 */

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * Rect is a rect shape that can be drawn to the screen, either
 * filled with colour or opaque.
 * Its position is determined by the upper left corner of the
 * rect's bounding rectangle
 */
public class Rect extends ClosedShape {
    //The width and height of the rect (major and minor axis).
    private int width, height;
    //The initial width and height of the rect.
    private int initialWidth, initialHeight;

    /**
     * Creates a rect.
     * @param shape The shape type.
     * @param insertionTime The insertion time.
     * @param x The display component's x position.
     * @param y The display component's y position.
     * @param vx The display component's x velocity.
     * @param vy The display component's y velocity.
     * @param width The width of the rect (in pixels).
     * @param height The height of the rect (in pixels).
     * @param colour The line colour or fill colour.
     * @param isFilled True if the rect is filled with colour, false if opaque.
     * @param isPulsing True if the rect is pulsing, false otherwise.
     */
    public Rect (String shape, int insertionTime, int x, int y, int vx, int vy, int width, int height, Color colour, boolean isFilled, boolean isPulsing) {
        super (shape, insertionTime, x, y, vx, vy, colour, isFilled, isPulsing);
        this.width = width;
        this.height = height;
        setInitialParameters();
    }

    /**
     * Method to convert a rect to a string.
     */
    public String toString () {
        String result = "This is a rect\n";
        result += super.toString ();
        result += "Its width is " + this.width + " and its height is " + this.height + "\n";
        return result;
    }


    /**
     * @param width Resets the width.
     */
    public void setWidth (int width) {
        this.width = width;
    }

    /**
     * @param height Resets the height.
     */
    public void setHeight (int height) {
        this.height = height;
    }


    /**
     * @return The width of the rect.
     */
    public int getWidth() {
        return width;
    }


    /**
     * @return The height of the rect.
     */
    public int getHeight() {
        return height;
    }


    /**
     * Sets the initial height and the initial width.
     */
    public void setInitialParameters(){
        this.initialHeight=height;
        this.initialWidth=width;
    }

    /**
     * Returns the initial width.
     * @return The initial width of the rect.
     */
    public int getInitialWidth(){
        return initialWidth;
    }

    /**
     * Returns the initial height.
     * @return The initial height of the rect.
     */
    public int getInitialHeight(){
        return initialHeight;
    }


    /**
     * @return The shape type.
     */
    public String getShape(){
        return this.shape;
    }

    /**
     * Draw the rect.
     * @param g The graphics object of the drawable component.
     */
    public void draw (GraphicsContext g) {
        g.setFill (colour);
        g.setStroke( colour );
        if (isFilled) {
            g.fillRect( x, y, width, height );
        }
        else {
            g.strokeRect( x, y, width, height );
        }
    }
}
