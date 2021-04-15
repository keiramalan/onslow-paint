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
    
    /**      */
    public paint(){
    UI.initialise();
    UI.addButton("Quit", UI::quit);
    }

    /**
     * Draw a press and release line
     */
    
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")) {
            // set starting position
            this.startX = x;
            this.startY = y;
        } 
        else if (action.equals("released")) {
            UI.drawLine(this.startX, this.startY, x, y);
        }
    }
    
    /**
     * Slider for line width from 1-20
     */
    public void chooseWidth() {
        
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
        // get input for line width
        UI.setMouseListener(obj::doMouse);
        UI.addButton("Color", obj::doChooseColor);
    }

}

