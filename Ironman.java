import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ironman Class
 * 
 * Acts as a typical car that also shoots lasers
 * every so often. Also has a special interaction
 * with Captain America, specified in the Captain class.
 * 
 * @Owen Zhu
 */

public class Ironman extends Vehicle{
    // Instance variables
    private int offset;
    private int spawnLaserRate = 29;
    private boolean caughtByCaptain = false, goUp = true, goRight = true;
    private int count = 0;
    private Pedestrian cap, p;
    
    public Ironman (){
        speed = 4;
    }
    
    /**
     * Every loop of act(), the vehicle drives,
     * checks to see if it's still onscreen, it checks for collision with a Pedestrian
     * and it also sees if its time to fire a laser
     */
    public void act(){
        if(checkEdges()||getY()<5){
            getWorld().removeObject(this);
            return;
        }else{
            spawnLaserRate++;
            if(spawnLaserRate==50){
                spawnLaserRate=0;
                getWorld().addObject(new Laser(), this.getX()+35, this.getY()-5);
            }
        }
            
        // This is so that Ironman wobbles around Captain America, but doesn't get too far away
        if(caughtByCaptain){
            if(cap.getWorld() == null || !cap.getState()){
                caughtByCaptain = false;
                return;
            }    
            
            if(getY()<cap.getY()-4)
                goUp = false;
            else if(getY()>cap.getY()+4)
                goUp = true;
            
            
            if(getX()<cap.getX()-4)
                goRight = true;
            else if(getX()>cap.getX()+4)
                goRight = false;
            
            
            if(goUp)
                setLocation (getX(), getY() - 1);
            else if(getY()+5>=cap.getY())
                setLocation (getX(), getY() + 1);
            
            
            if(goRight)
                setLocation (getX() + 1, getY());
            else
                setLocation (getX() - 1, getY());
            
            return;
        }else{
            drive();
            checkHitPedestrian ();
        }
    }
    
    /**
     * A method to handle the interactions
     * between different Pedestrians
     */
    public void checkHitPedestrian (){
        // Check collision for a pedestrian one pixel ahead
        // of the Vehicle
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, Pedestrian.class);
        
        if (p != null && p instanceof Thanos){ // If the pedestrian is Thanos
            getWorld().addObject(new Boom(), this.getX()+30, this.getY());
            getWorld().removeObject(this);
        }else if (p != null && !(p instanceof Captain)){ // If the pedestrian isn't Captain America
            p.setCurVeh(this);
            p.knockMeOver();
        }else if(p != null && p instanceof Captain && p.getState()){ // If the pedestrian is Captain America and isn't knocked over
            cap = p;
            caughtByCaptain = true;
            Captain c = (Captain)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, Captain.class);
            c.caught();
        }
    }
}
