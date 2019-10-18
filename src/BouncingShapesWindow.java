




import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 *
 * @author Dr D. Archambault, Modified  for JAVAFX by Dr J. L. Jones
 *
 */
public class BouncingShapesWindow {

    private static final int ANIMATION_DELAY = 10;
    private static final String FRAME_TITLE = "Shape Booooiiinggg Frame";

    private GraphicsContext gc;
    private Queue<ClosedShape> shapesToAdd;
    private ArrayList<ClosedShape> activeShapes;
    private int currentTime = 0;
    private boolean flag=true;
    private final int CONSTANT=2; // constant used to increase the shape size
    private String filename;


    public BouncingShapesWindow(GraphicsContext gc,String filename) {
        this.gc=gc;

        activeShapes=new ArrayList<ClosedShape>();
        this.initShapes(filename);
        this.insertShapes ();
        drawClosedShapes();
        actionPerformed();
    }

    private void drawClosedShapes () {
        for (ClosedShape s : activeShapes)
        {
            s.draw(gc);
        }
    }

    private void initShapes (String filename) {
        shapesToAdd = ReadShapeFile.readDataFile(filename);
    }

    private void insertShapes() {
        //no more shapes to add, we are done
        if (shapesToAdd.isEmpty ()) {
            return;
        }

        //add shapes if needed
        ClosedShape current = shapesToAdd.peek ();
        while (!shapesToAdd.isEmpty () && (current.getInsertionTime() <= currentTime*ANIMATION_DELAY)) {
            activeShapes.add(current);
            shapesToAdd.dequeue();
            if (!shapesToAdd.isEmpty ()) {
                current = shapesToAdd.peek();
            }
        }
    }

    public void actionPerformed()
    {

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5),ae -> onTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void onTime() {
        currentTime++;
        double h =gc.getCanvas().getHeight();
        double w = gc.getCanvas().getWidth();
        gc.clearRect(0, 0, w, h);
        moveShapes();
        insertShapes ();
        drawClosedShapes();
    }

    /**
     * Method needed for the Stage 5 that moves the shapes around the window
     */
    public void moveShapes()
    {
        double  dimsY = gc.getCanvas().getHeight() ;
        double  dimsX = gc.getCanvas().getWidth() ;
        for (ClosedShape s : activeShapes)
        {
            s.move();
            //We check to see if the object is pulsing or not
            //than we check what type of object it is
            if(s.isPulsing()){
                if(s.getShape().equals("circle")){
                    Circle circle=(Circle)s;
                    //The first if condition is used to increase the circle diameter
                    // to a certain size
                    if(circle.getDiameter()<CONSTANT*circle.getInitialDiameter() && circle.getMoving()) {
                        circle.setDiameter(circle.getDiameter() + 1);
                    }
                    //This else if condition is used to decrease the circle diameter
                    // till it reaches the initial size
                    else if(circle.getDiameter()>circle.getInitialDiameter()) {
                        circle.setDiameter(circle.getDiameter() - 1);
                        circle.setMoving(false);
                    }
                    //When the circle diameter reaches the initial size we set the moving
                    //variable to be true so the circle diameter can increase again in size
                    else{
                        circle.setMoving(true);
                    }
                }
                else if(s.getShape().equals("rect")){
                    Rect rect=(Rect)s;
                    //The first if condition is used to increase the square height and width
                    // to a certain size
                    if(rect.getHeight()*rect.getWidth()<CONSTANT*rect.getInitialHeight()*rect.getWidth() && rect.getMoving()){
                        rect.setHeight(rect.getHeight()+1);
                        rect.setWidth(rect.getWidth()+1);
                    }
                    //This else if condition is used to decrease the square height and width
                    // till they reach the initial size
                    else if(rect.getHeight()*rect.getWidth()>rect.getInitialHeight()*rect.getWidth()) {
                        rect.setHeight(rect.getHeight() - 1);
                        rect.setWidth(rect.getWidth()-1);
                        rect.setMoving(false);
                    }
                    //When the rect height and width reach the initial size we set the moving
                    //variable to be true so the rect height and width can increase again in size
                    else{
                        rect.setMoving(true);
                    }
                }
                else if(s.getShape().equals("oval")){
                    Oval oval=(Oval) s;
                    //The first if condition is used to increase the oval height and width
                    // to a certain size
                   if(oval.getHeight()*oval.getWidth()<CONSTANT*oval.getInitialHeight()*oval.getInitialWidth() && oval.getMoving()){
                       oval.setHeight(oval.getHeight()+1);
                       oval.setWidth(oval.getWidth()+1);
                   }
                   //This else if condition is used to decrease the oval height and width
                   // till they reach the initial size
                   else if(oval.getHeight()*oval.getWidth()>oval.getInitialHeight()*oval.getInitialWidth()){
                        oval.setHeight(oval.getHeight()-1);
                        oval.setWidth(oval.getWidth()-1);
                        oval.setMoving(false);
                   }
                   //When the oval height and width reach the initial size we set the moving
                   //variable to be true so the oval height and width can increase again in size
                   else{
                       oval.setMoving(true);
                   }
                }
                else if(s.getShape().equals("triangle")){
                    Triangle triangle=(Triangle) s;
                    //The first if condition is used to increase the triangle side
                    // to a certain size
                    if(triangle.getSide()<CONSTANT*triangle.getInitialSide() && triangle.getMoving()) {
                        triangle.setSide(triangle.getSide() + 1);
                    }
                    //This else if condition is used to decrease the triangle side
                    // till it reaches the initial size
                    else if(triangle.getSide()>triangle.getInitialSide()){
                        triangle.setSide(triangle.getSide()-1);
                        triangle.setMoving(false);
                    }
                    //When the triangle side reaches the initial size we set the moving
                    //variable to be true so the triangle side can increase again in size
                    else{
                        triangle.setMoving(true);
                    }
                }
                else if(s.getShape().equals("square")){
                    Square square=(Square) s;
                    //The first if condition is used to increase the square side
                    // to a certain size
                    if(square.getSide()<CONSTANT*square.getInitialSide() && square.getMoving()) {
                        square.setSide(square.getSide() + 1);
                    }
                    //This else if condition is used to decrease the square side
                    // till it reaches the initial size
                    else if(square.getSide()>square.getInitialSide()){
                        square.setSide(square.getSide()-1);
                        square.setMoving(false);
                    }
                    //When the square side reaches the initial size we set the moving
                    //variable to be true so the square side can increase again in size
                    else{
                        square.setMoving(true);
                    }
                }
            }

            // Move us back in and bounce if we went outside the drawing area.
            if (s.outOfBoundsX(dimsX))
            {
                s.putInBoundsX(dimsX);
                s.bounceX();
            }

            if (s.outOfBoundsY(dimsY))
            {
                s.putInBoundsY(dimsY);
                s.bounceY();
            }

        }
    }

}

