import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract Vehicle Class
 * 
 * An abstract class that is used to create: 
 * Ambulance, Bus, Truck, Ironman and his laser
 * Methods such as drive() and checkEdges() are
 * inherited by the child classes as all Vehicles
 * will need to drive and deleted when they reach the edge
 * 
 * @Owen Zhu
 */

public abstract class Vehicle extends Actor{   
    // Variable for all vehicles, because they all move at a constant speed 
    protected int speed;

    /**
     * Abstract method for the interaction b/w 
     * vehicles and pedestrians
     */
    public abstract void checkHitPedestrian ();
    
    /**
     * Detects if the object is off the edge of the screen
     */
    public boolean checkEdges(){
        if (getX() < -getImage().getWidth() || getX() > getWorld().getBackground().getWidth() + getImage().getWidth())
            return true;
        else if(getY() < -getImage().getHeight() || getY() > getWorld().getBackground().getHeight() + getImage().getHeight())
            return true;
        return false;
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
     */
    public void drive(){
        move (speed);
    }   
}





