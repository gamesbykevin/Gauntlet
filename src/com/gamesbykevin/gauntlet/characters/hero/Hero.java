package com.gamesbykevin.gauntlet.characters.hero;

import com.gamesbykevin.framework.util.Timer;
import com.gamesbykevin.framework.util.Timers;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.shared.Shared;

/**
 * The Hero class
 * @author GOD
 */
public abstract class Hero extends Character
{
    /**
     * The number of keys we have
     */
    private int keys = 0;
    
    //the number of potions we have
    private int potions = 0;
    
    //the score
    private int score = 0;
    
    //the timer for the reflective shot
    private Timer timerReflective;
    
    //the timer for the power shot
    private Timer timerPower;
    
    /**
     * Default health
     */
    private static final int DEFAULT_HEALTH = 2500;
    
    /**
     * How long the reflective ability is enabled
     */
    private static final long DELAY_REFLECTIVE = Timers.toNanoSeconds(60000L);
    
    /**
     * How long the power ability is enabled
     */
    private static final long DELAY_POWER = Timers.toNanoSeconds(60000L);
    
    /**
     * The distance from the center of the exit tile, before we can say the hero has completed the level
     */
    protected static final double EXIT_DISTANCE_CHECK = .35;
    
    /**
     * Normal damage
     */
    protected static final int DAMAGE_NORMAL = 2;
    
    /**
     * Extra damage
     */
    protected static final int DAMAGE_EXTRA = 3;
    
    /**
     * We will deduct 1 health away every time this timer completes
     */
    private Timer timerHealth;
    
    //our player stats
    private Status status;
    
    /**
     * Create a new hero of specified type
     * @param type The type of player we want
     * @throws Exception If the type of player is not setup
     */
    protected Hero(final Character.Type type) throws Exception
    {
        super(type);
        
        //create the timer and make it expire for now
        this.timerReflective = new Timer(DELAY_REFLECTIVE);
        this.timerReflective.setRemaining(DELAY_NONE);
        
        //create the timer and make it expire for now
        this.timerPower = new Timer(DELAY_POWER);
        this.timerPower.setRemaining(DELAY_NONE);
        
        //set the default health
        super.setHealth(DEFAULT_HEALTH);
        
        //the timer will be 1 second in length
        this.timerHealth = new Timer(Timers.NANO_SECONDS_PER_SECOND);
        
        this.status = new Status(Shared.ORIGINAL_WIDTH, 20);
        this.status.render();
        
        /**
         * Assign different attributes to make each hero unique such as
         * 1. Character movement speed
         * 2. Projectile limit
         * 3. Projectile speed ratio
         * 4. Time delay between each projectile fired
         * 4. Damage
         */
        switch (getType())
        {
            case Wizard:
                super.setSpeed(FASTER_SPEED);
                super.setProjectileLimit(DEFAULT_PROJECTILE_LIMIT);
                super.setProjectileSpeedRatio(PROJECTILE_SPEED_RATIO_FASTER);
                super.getTimerProjectile().setReset(DELAY_PROJECTILE);
                super.setDamage(DAMAGE_NORMAL);
                break;

            case Elf:
                super.setSpeed(DEFAULT_SPEED);
                super.setProjectileLimit(DEFAULT_PROJECTILE_LIMIT + 1);
                super.setProjectileSpeedRatio(PROJECTILE_SPEED_RATIO_FASTEST);
                super.getTimerProjectile().setReset(DELAY_PROJECTILE);
                super.setDamage(DAMAGE_DEFAULT);
                break;

            case Valkyrie:
                super.setSpeed(FASTEST_SPEED);
                super.setProjectileLimit(DEFAULT_PROJECTILE_LIMIT + 2);
                super.setProjectileSpeedRatio(PROJECTILE_SPEED_RATIO_FASTER);
                super.getTimerProjectile().setReset(DELAY_PROJECTILE_SLOW);
                super.setDamage(DAMAGE_NORMAL);
                break;
                
            case Warrior:
                super.setSpeed(SLOW_SPEED);
                super.setProjectileLimit(1);
                super.setProjectileSpeedRatio(PROJECTILE_SPEED_RATIO);
                super.getTimerProjectile().setReset(DELAY_PROJECTILE_SLOWEST);
                super.setDamage(DAMAGE_EXTRA);
                break;
                
            default:
                throw new Exception("Type not setup here " + getType().toString());
        }
    }
    
    protected Status getStatus()
    {
        return this.status;
    }
    
    @Override
    public void setHealth(final int health)
    {
        super.setHealth(health);
        
        //assign health
        getStatus().setHealth(health);
        
        //render a new image
        getStatus().render();
    }
    
    /**
     * Get the health timer
     * @return The timer that deducts health every time it finishes
     */
    protected Timer getTimerHealth()
    {
        return this.timerHealth;
    }
    
    /**
     * Does this hero have reflective shot?
     * @return true if the reflective shot timer has not run out, false otherwise
     */
    protected final boolean hasReflectiveShot()
    {
        return (!getTimerReflectiveShot().hasTimePassed());
    }
    
    /**
     * Does this hero have power shot?
     * @return true if the power shot timer has not run out, false otherwise
     */
    protected final boolean hasPowerShot()
    {
        return (!getTimerPowerShot().hasTimePassed());
    }
    
    /**
     * Get the timer for reflective shot
     * @return The timer for how long the reflective shot is active
     */
    public final Timer getTimerReflectiveShot()
    {
        return this.timerReflective;
    }
    
    /**
     * Get the timer for power shot
     * @return The timer for how long the power shot is active
     */
    public final Timer getTimerPowerShot()
    {
        return this.timerPower;
    }
    
    /**
     * Add the specified score to the total
     * @param score The score we want to add
     */
    public void addScore(final int score)
    {
        this.score += score;
        
        //assign value
        getStatus().setScore(this.score);
        
        //render a new image
        getStatus().render();
    }
    
    /**
     * Get the score
     * @return The heroes score
     */
    public int getScore()
    {
        return this.score;
    }
    
    /**
     * Add a potion to our total
     */
    public void addPotion()
    {
        this.potions++;
        
        //assign value
        getStatus().setPotions(potions);
        
        //render a new image
        getStatus().render();
    }
    
    /**
     * Does this hero have a potion?
     * @return true if the potions count is greater than 0, otherwise false
     */
    public boolean hasPotion()
    {
        return (this.potions > 0);
    }
    
    /**
     * Remove a potion from our inventory
     */
    public void removePotion()
    {
        this.potions--;
        
        //just make sure we don't have less than 0
        if (this.potions < 0)
            this.potions = 0;
        
        //assign value
        getStatus().setPotions(potions);

        //render a new image
        getStatus().render();
    }
    
    /**
     * Add a key to our total
     */
    public void addKey()
    {
        this.keys++;
        
        //assign value
        getStatus().setKeys(keys);
        
        //render a new image
        getStatus().render();
    }
    
    /**
     * Does this hero have a key?
     * @return true if the keys count is greater than 0, otherwise false
     */
    public boolean hasKey()
    {
        return (this.keys > 0);
    }
    
    /**
     * Remove a key from our inventory
     */
    public void removeKey()
    {
        this.keys--;
        
        //just make sure we don't have less than 0
        if (this.keys < 0)
            this.keys = 0;
        
        //assign value
        getStatus().setKeys(keys);
        
        //render a new image
        getStatus().render();
    }
}