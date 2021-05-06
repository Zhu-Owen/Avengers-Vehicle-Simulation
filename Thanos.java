import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Thanos Class
 * 
 * Spawns Thanos after half of the objects are removed
 * in the MyWorld class. Thanos also cannot be knocked over
 * because he is too powerful.
 * 
 * @Owen Zhu
 */

public class Thanos extends Pedestrian{
    public Thanos(){
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

        //Move Thanos up
        setLocation (getX(), getY() - speed);
    }
    
    // Thanos is too powerful to be knocked over
    public void knockMeOver(){};
}
