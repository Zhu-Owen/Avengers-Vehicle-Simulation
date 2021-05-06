import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Drstrange Class
 * 
 * Spawns Dr. Strange, who reverses his own time, in order to dodge vehicles.
 * He cannot be knocked down because he will always travel "back in time"
 * to avoid the accident.
 * 
 * @Owen Zhu
 */

public class Drstrange extends Pedestrian{
    public Drstrange(){
        // figure out own width (related to checking if at world's edge)
        GreenfootImage g = this.getImage();
        myWidth = g.getWidth();
        // Set current healthy status to true (alive and moving)
        healthy = true;
        // Set initial speed
        speed = 1;
    }

    // act() method - called by Greenfoot at every
    // "act" or step of execution
    public void act(){
        // Removing object, if out of the simulated zone
        if (atWorldEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        //Move Dr. Strange up
        setLocation (getX(), getY() - speed);
    }

    /**
     * Method causes Dr. Strange to travel back in time
     * when in contact with any vehicles
     */
    public void knockMeOver(){
        // Display a cool rune image
        getWorld().addObject(new Rune(), this.getX(), this.getY());
        setLocation (getX(), getY() + 60); //Teleport Dr. Strange back
    }
}
