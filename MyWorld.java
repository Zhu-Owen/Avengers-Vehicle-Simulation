import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;  // For the list used to store objects in the world

/** 
 * Features:
 * To the overall game:
 * - Added background music
 * - Added a new background image
 * - Added sound effects when Thanos spawns and snaps
 * 
 * To the Vehicles:
 * - Ambulances also knock Pedestrains over
 *      - Added code to prevent the Ambulance from knocking over and healing the same Pedestrian repeatedly
 * - Added a garbage truck that picks up dead bodies
 *      - Same as the Ambulance, I coded it so that the truck doesn't knock over and pick the same Pedestrian up
 * - Added an Ironman vehicle that shoots lasers every so often
 *      - The Ironman vehicle has a special interaction with Captain America
 *      - I simulate a "fight" b/w the two and they randomly move in the X-direction during the fight
 * - Added the laser that Ironman shoots and functions the same as regular "vehicle"
 * 
 * To the Pedestrians:
 * - Added Captain America
 *      - He is invulnerable to Ironman's lasers and the two fight when they collide
 * - Added Dr. Strange
 *      - Strange travels back in time whenever he collides with any vehicle with his Time Stone
 * - Added Thanos
 *      - Thanos spawns with a sound effect and I simulate a "snap" by removing half of all objects on the screen
 *      - Thanos is invincible to any vehicles and they explode when they come in contact with Thanos
 ***************************************************************************************************************** 
 * - Inhertience is very useful when you need to create elements of a project/game that only differ by a few elements
 * - Using inheritence can be very time and resource efficient because it allows you to remove redundant code in your program
 * - For example, when I was coding up my Pedestrians in the simulation. The only difference between Dr. Strange and Thanos was
 *   that Dr. Strange teleported backwards when he hit a vehicle, whereas Thanos destroys and deletes the vehicle
 * - Other variables such as speed, and methods such as healMe(), pickMeUp(), etc. would be shared between all Pedestrians,
 *   and so there would be no need to repeat individual components, because of inheritence
 * 
 *****************************************************************************************************************
 * SOURCES:
 * https://www.teepublic.com/t-shirt/2286917-8-bit-thanos
 * https://www.deviantart.com/creativecc/art/8-Bit-Marvel-Captain-America-365833016
 * https://ya-webdesign.com/image/transparent-laser-8-bit/1514296.html
 * https://www.shutterstock.com/image-vector/vector-illustration-cartoon-truck-pixel-design-1051161728
 * https://www.youtube.com/watch?v=ktZkRDRNhKw
 * https://www.teepublic.com/sticker/1166862-time-traveling-doctors
 * https://www.teepublic.com/fr/autocollant/2719191-8-bit-bomb-explosion
 * https://www.teepublic.com/t-shirt/2286917-8-bit-thanos
 * https://www.greenfoot.org/topics/37450/0
 * https://medea-music.com/portfolio-item/the-avengers-theme-a-silvestri/the-avengers-theme-song/
 * 
 * @author Owen Zhu
 * @version March 2020
 * @since March 2015
 */
public class MyWorld extends World{
    private int randomize;
    /**
     * Spawn Rates:
     * Lower number means more spawns
     */
    private int spawnRate = 200;
    private int pedSpawn = 100;
    private int spawnThanos = 0;
    
    //Sound effects for the simulation
    GreenfootSound thanosSound = new GreenfootSound("sounds/inevitable.mp3");
    GreenfootSound backgroundMusic = new GreenfootSound("sounds/The-Avengers-Theme-Song.mp3");
    
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        //Sets up the Avengers theme sound to play in the background in a loop
        backgroundMusic.setVolume(7);
        backgroundMusic.playLoop();
    }

    public void act (){
        // Run methods to see if any pedestrians or vehicles are going to be spawned this act
        spawnPedestrians();
        spawnVehicles();    
    }

    private void spawnVehicles(){
        // Generate a random number to add a random element to Vehicle spawning
        randomize = Greenfoot.getRandomNumber(spawnRate);

        // Chose a random lane in case a vehicle spawns
        int lane = Greenfoot.getRandomNumber (6);
        int spawnY = getYPosition (lane);

        // spawn vehicles
        if (randomize == 1)        // spawn a Truck
            addObject (new Truck(), 10, spawnY);
        else if (randomize == 2)   // spawn a Bus
            addObject (new Bus(), 10, spawnY);
        else if (randomize == 3)   // spawn Ambulance
            addObject (new Ambulance(), 10, spawnY);
        else if (randomize == 4)   // spawn Ironman 
            addObject (new Ironman(), 10, spawnY);
    }

    private void spawnPedestrians(){
        // spawn pedestrians
        if (Greenfoot.getRandomNumber(pedSpawn) == 1) // Spawns Captain America
            addObject (new Captain(), Greenfoot.getRandomNumber(600), 395);
        else if (Greenfoot.getRandomNumber(pedSpawn) == 2) // Spawns Dr. Strange
            addObject (new Drstrange(), Greenfoot.getRandomNumber(600), 395);
        else if (Greenfoot.getRandomNumber(pedSpawn) == 3){ // "Spawns" Thanos
            spawnThanos++;
            //Using a counter to spawn Thanos in order to decrease the freq. of spawns
            if(spawnThanos==10){
                //Playing sound effect
                //---------------------
                backgroundMusic.pause();
                thanosSound.setVolume(20);
                thanosSound.play();
                Greenfoot.delay(290);
                backgroundMusic.play();
                //---------------------
                spawnThanos = 0;
                int cnt = 0;

                // Using a list to store all the objects in the simulation
                List<Actor> actors = this.getObjects(null); 
                
                for(Actor x: actors){
                    cnt++;
                    //Using a integer to help remove half of all objects in the simulation
                    if(cnt%2==0) this.removeObject(x);
                }
                addObject (new Thanos(), Greenfoot.getRandomNumber(600), 395);
            }
        }
    }

    /**
     * Returns the appropriate y coordinate for a given lane
     */
    private int getYPosition (int inLane){
        // Manually input values based on the background graphic
        switch (inLane){
            case 0: 
            return 79;

            case 1:
            return 127;

            case 2:
            return 175;

            case 3:
            return 222;

            case 4:
            return 272;

            case 5: 
            return 320;

        }  
        // In case an invalid value is passed in
        return 0;
    }
}

