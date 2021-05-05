/* Based on the ecs 100 template
 * Create MS paint
 * Name:
 * Date:
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import javax.swing.JColorChooser;


/** Create MS Paint
 */
public class paint{
    // fields
    private double startX, startY; // fields to remember press position
    private Color currentColor = Color.black;
    
    // t/f for which mode its in which will be changed by a button
    boolean createOval = true;
    boolean drawLine = false;
    boolean fillShape = false;
    
    /**      */
    public paint(){
    UI.initialise();
    UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Button to change the shape created from a square to an oval
     */
    public void changeOval() {
        // set mode to oval
        createOval = true;
        drawLine = false;
    }
    
    /**
     * Button to change the shape from oval to square
     */
    public void changeRect () {
        //set mode to oval
        createOval = false;
        drawLine = false;
    }
    
    /**
     * Method to set draw mode to line
     */
    public void changeLine() {
        drawLine = true;
    }
    
    /**
     * Draw a press and release square/oval depending on T/F
     */
    
    public void doMouse(String action, double x, double y) {
        
        double shapeWidth, shapeHeight;
        // set starting co-ords
        if (action.equals("pressed")) {
            // set starting position
            this.startX = x;
            this.startY = y;
        } 
        else if (action.equals("released")) {
            // create a line if user pressed line button
            if (drawLine) {
                UI.drawLine(this.startX, this.startY, x, y);
            }
            else {
                // otherwise draw oval/rectangle depending on button input
                // set shape widths
                if ((x-startX >= 0) && (y-startY >= 0)) { // set width and height
                    shapeWidth = x-startX;
                    shapeHeight = y-startY;
                }
                    
                else { // reverse sums to create negative box
                    shapeWidth = startX-x;
                    shapeHeight = startY-x;
                }
                
                if (createOval) {
                    //change to width/height
                    UI.drawOval(this.startX, this.startY, shapeWidth, shapeHeight);
                }
                else {
                    UI.drawRect(this.startX, this.startY, shapeWidth, shapeHeight);
                    //change to width/height
                }
            }
        }
    }
    
    /**
     * Slider for line width from 1-20
     */
    public void chooseWidth(double input) {
        // set input to line width
        UI.setLineWidth(input);
    }
    
    /**
     * Clear graphics pane
     */
    public void clearScreen() {
        UI.clearGraphics();
    }
    
    /**
     * Choose random color
     */
    public void doRandomColor() {
        // select random color
        Color col = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        UI.setColor(col);
    }
    
    /**
     * Method to select fill
     */
    public void selectFill() {
        
    }
    
    /** Method to select outline
     * 
     */
    public void selectOutline() {
        
    }
    
    /**
     * Color Chooser
     */
    
    public void doChooseColor() {
        this.currentColor = JColorChooser.showDialog(null, "Choose Color", this.currentColor);
        UI.setColor(this.currentColor);
    }
    
    public static void main(String[] args){
        paint obj = new paint();
        // set inital line width to 15
        UI.setLineWidth(15);
        
        // get input for line width
        UI.setMouseListener(obj::doMouse);
        
        // buttons to choose or randomly assign color
        UI.addButton("Choose Color", obj::doChooseColor);
        UI.addButton("Choose Random Color", obj::doRandomColor);
        
        // create slider for line width
        UI.addSlider("Line Width", 1, 30, obj::chooseWidth);
        
        // create buttons for rect or oval
        UI.addButton("Oval", obj::changeOval);
        UI.addButton("Rectangle", obj::changeRect);
        
        // create button to draw line
        UI.addButton("Draw Line", obj::changeLine);
        
        // create clear button to wipe screen
        UI.addButton("Clear", UI::clearGraphics);
    }

}

