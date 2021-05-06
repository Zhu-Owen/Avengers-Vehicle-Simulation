import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract Pedestrian Class
 * 
 * An abstract class that is
 * used to create: Captain America
 * Dr. Strange, and Thanos
 * 
 * @Owen Zhu
 */

public abstract class Pedestrian extends Actor{
    // Instance variables
    protected int myWidth;
    protected boolean healthy;
    protected int speed;
    
    // Variables to avoid pedestrains from being 
    // knocked over and healed by the same ambulance
    // or knocked over and picked up by the same truck
    protected Vehicle prev,cur;       
    
    /**
     * Abstract method for the interaction b/w 
     * vehicles and pedestrians
     */
    public abstract void knockMeOver();

    /**
     * Method causes this pedestrian to "heal" - regain
     * upright position and start moving again
     */
    public void healMe (){
        // If the current ambulance to collide with the 
        // Pedestrian isn't the same one that knocked them over
        if(cur!=prev){ 
            speed = 1;
            setRotation (0);
            healthy = true;
            prev = cur; // Set the cur ambulance object to prev
        }
    }

    /**
     * Method that sets the current vehicle that the pedestrians collide with
     */
    public void setCurVeh(Vehicle a){
        cur = a;
    }

    /**
     * Method causes that gets the current state of the pedestrian
     */
    public boolean getState(){
        return healthy;
    }

    /**
     * Method causes the pedestrian to get picked up by the bus or truck
     */
    public void pickMeUp(){
        if(cur!=prev){ 
            prev = cur;
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Handy method that checks if this object is at the edge
     * of the World
     * 
     * @return boolean - true if at or past the edge of the World, otherwise false
     */
    public boolean atWorldEdge(){
        if (getX() < -(myWidth / 2) || getX() > getWorld().getWidth() + (myWidth / 2))
            return true;
        else if (getY() - 5 < -(myWidth / 2) || getY () > getWorld().getHeight() + (myWidth / 2))
            return true;
        else
            return false;
    }
}
