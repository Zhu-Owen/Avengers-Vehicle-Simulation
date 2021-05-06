import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Captain Class
 * 
 * Spawns Captain America, who has a special interaction with Ironman.
 * He cannot be knocked down by his laser because of his shield and
 * when he is in contact with Ironman, they "battle" it out.
 * 
 * @Owen Zhu
 */

public class Captain extends Pedestrian{
    // Instance variables
    private boolean caughtIronman = false, goUp = true, goRight = true;

    public Captain(){
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
        
        // If Captain America gets hit by a vehicle thats not Ironman
        if(!this.getState()) caughtIronman = false;

        // When "fighting" with Ironman, make Captain America
        // move randomly while Ironman follows him
        if(caughtIronman){
            int struggleSpeed = Greenfoot.getRandomNumber(5); // Set a random speed
            int direction = Greenfoot.getRandomNumber(2); // Set a random direction
            
            if(direction == 0) // Go left
                setLocation (getX() - struggleSpeed, getY()-1);
            else // Go right
                setLocation (getX() + struggleSpeed, getY()-1);
            return;
        }else{
            // Regular walking when there is not interaction with Ironman
            setLocation (getX(), getY() - speed);
        }
    }

    public void caught(){
        caughtIronman = true;
    }
    
    /**
     * Method causes Captain America to "heal" - regain
     * upright position and start moving again
     */
    public void knockMeOver (){
        // Check if the current vehicle to collide with the 
        // Pedestrian isn't the same one that healed them
        if(cur!=prev){
            speed = 0;
            setRotation (90);
            healthy = false;
            prev = cur; // Set the cur vehicle object to prev
        }
    }
}
