import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bus Class
 * 
 * Picks up any pedestrian that comes in its way
 * 
 * @Owen Zhu
 */

public class Bus extends Vehicle{
    public Bus (){
        speed = 3;
    }

    /**
     * Every loop of act(), the vehicle drives,
     * checks to see if it's still onscreen,
     * and then it checks for collision with a Pedestrian
     */
    public void act (){
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
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((this.getImage().getWidth() / 2) + speed, 0, Pedestrian.class);

        if (p != null && p instanceof Thanos){ // If the pedestrian is Thanos
            getWorld().addObject(new Boom(), this.getX()+30, this.getY());
            getWorld().removeObject(this);
        }else if (p != null && p.getState()){ // If the pedestrian isn't knocked over, pick them up
            p.setCurVeh(this);
            p.pickMeUp();
        }
    }
}
