import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Laser Class
 * 
 * Acts like a fast car that can also knock pedestrians over
 * 
 * @Owen Zhu
 */

public class Laser extends Vehicle{
    public Laser (){
        speed = 10; // Sets the speed of the vehicle
    }
    
    /**
     * Every loop of act(), the vehicle drives,
     * checks to see if it's still onscreen,
     * and then it checks for collision with a Pedestrian
     */
    public void act(){
        move(speed);
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
        
        if (p != null && !(p instanceof Captain) && !(p instanceof Thanos)){
            p.setCurVeh(this);
            p.knockMeOver();
        }else if(p != null || p instanceof Thanos){
            getWorld().removeObject(this);
        }
    }
}
