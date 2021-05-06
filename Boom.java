import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boom class
 * 
 * An animation to appear when Thanos
 * runs into an Vehicle objects and destorys them
 *
 * @Owen Zhu
 */
public class Boom extends Actor{
    // Instance variable
    private int count = 20; // Setting a duration of the image
    
    public void act(){
        count--;
        // After the counter reaches 0, delete the image
        if(count==0) getWorld().removeObject(this);
    }    
}
