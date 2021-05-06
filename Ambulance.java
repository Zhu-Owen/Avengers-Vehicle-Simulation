import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ambulance Class
 * 
 * Spawns an Ambulance vehicle that knocks and heals Captain America.
 * I added an extra feature from the file that was given in order to
 * knock over and interact with pedestrains that don't need healing.
 * 
 * @Owen Zhu
 */

public class Ambulance extends Vehicle{
    public Ambulance (){
        speed = 3; // Sets the speed of the vehicle
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
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, Pedestrian.class);

        if (p != null && p instanceof Thanos){ // If the pedestrian is Thanos
            getWorld().addObject(new Boom(), this.getX()+30, this.getY());
            getWorld().removeObject(this);
        }else if (p != null && !p.getState()){ // If the pedestrian has been already knocked over
            p.setCurVeh(this);
            p.healMe();
        }else if (p != null && p.getState()){ // If the pedestrian hasn't been already knocked over
            p.setCurVeh (this);
            p.knockMeOver();
        }
    }
}
