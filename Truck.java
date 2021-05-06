import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Truck Class
 * 
 * Functions like a reversed Ambulance who picks up dead bodies
 * instead of healing them.
 * 
 * @Owen Zhu
 */

public class Truck extends Vehicle{    
    public Truck (){
        speed = 3; // Sets the speed of the vehicle
    }
    
    /**
     * Every loop of act(), the vehicle drives,
     * checks to see if it's still onscreen,
     * and then it checks for collision with a Pedestrian
     */
    public void act(){
        drive();
        checkEdges();
        checkHitPedestrian ();
    }
    
    /**
     * A method to handle the interactions
     * between different Pedestrians
     */
    public void checkHitPedestrian (){
        // Check collision for a pedestrian one pixel ahead of the Vehicle
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, Pedestrian.class);
        
        if (p != null && p instanceof Thanos){ // If the pedestrian has been already knocked over
            getWorld().addObject(new Boom(), this.getX()+30, this.getY());
            getWorld().removeObject(this);
        }else if (p != null && !p.getState()){
            p.setCurVeh(this);
            p.pickMeUp();
        }else if(p != null && p.getState()){
            p.setCurVeh(this);
            p.knockMeOver();
        }
    }
}
