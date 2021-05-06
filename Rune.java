import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rune Class
 * 
 * An animation to appear when Dr. Strange
 * travels back in time to a location he was at before
 * 
 * @Owen Zhu
 */
public class Rune extends Actor{
    // Instance variable
    private int count = 20;// Setting a duration of the image
    
    public void act(){
        count--;
        // After the counter reaches 0, delete the image
        if(count==0) getWorld().removeObject(this);
    }    
}
