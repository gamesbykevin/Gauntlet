package com.gamesbykevin.gauntlet.characters;

import com.gamesbykevin.framework.resources.Disposable;
import com.gamesbykevin.framework.util.Timers;

import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.projectile.Projectile;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * A character could either be an enemy or a hero
 * @author GOD
 */
public abstract class Character extends Entity implements Disposable
{
    /**
     * Types of characters available
     */
    public enum Type
    {
        Wizard, Elf, Valkyrie, Warrior,
        Ghost, Demon, Blob, Grunt, Sorcerer, Lobber, It, SuperSorcerer, Death, 
        EnemyGenerator1, EnemyGenerator2, 
    }
    
    /**
     * The type of character
     */
    private final Type type;
    
    /**
     * The default dimension (width, height) of each character animation
     */
    public static final int DEFAULT_DIMENSION = 16;
    
    /**
     * Default time delay for the animations
     */
    public static final long DEFAULT_DELAY = Timers.toNanoSeconds(250L);
    
    /**
     * The number of projectiles allowed at once
     */
    public static final int DEFAULT_PROJECTILE_LIMIT = 3;
    
    //the number of projectiles allowed
    private int projectileLimit = DEFAULT_PROJECTILE_LIMIT;
    
    /**
     * The scale ratio
     */
    public static final double SIZE_RATIO = 1.5;
    
    /**
     * The projectile speed with be a % of the character speed
     */
    protected static final double PROJECTILE_SPEED_RATIO = 2.0;
    
    /**
     * Assign the speed of the projectile
     */
    private double projectileSpeedRatio = PROJECTILE_SPEED_RATIO;
    
    /**
     * List of projectiles
     */
    private List<Projectile> projectiles;
    
    /**
     * The speed at which the character can move
     */
    public static final double DEFAULT_SPEED = 0.10000;
    
    //assign the default speed
    private double speed = DEFAULT_SPEED;
    
    /**
     * Each direction may have an animation
     */
    public enum Directions
    {
        N, NE, E, SE, S, SW, W, NW
    }
    
    /**
     * Create a character of specified type
     * @param type Type of character desired
     * @throws Exception 
     */
    protected Character(final Type type) throws Exception
    {
        super();
        
        //assign the character type
        this.type = type;
        
        //create a new list for the projectiles
        this.projectiles = new ArrayList<>();
    }
    
    /**
     * Get the type
     * @return The type of character this is
     */
    public final Type getType()
    {
        return this.type;
    }
    
    @Override
    public void dispose()
    {
        super.dispose();
        
        if (projectiles != null)
        {
            for (int index = 0; index < projectiles.size(); index++)
            {
                projectiles.get(index).dispose();
                projectiles.set(index, null);
            }

            projectiles.clear();
            projectiles = null;
        }
    }
    
    /**
     * Each child will need logic to update the state
     * @param engine Object containing all game elements
     * @throws Exception 
     */
    public abstract void update(final Engine engine) throws Exception;
    
    /**
     * Update the projectiles accordingly
     * @param engine Object containing all game elements
     * @throws Exception 
     */
    private void updateProjectiles(final Engine engine) throws Exception
    {
        //update the projectiles
        for (int index = 0; index < projectiles.size(); index++)
        {
            //update the projectile
            projectiles.get(index).update(engine);
            
            //if the projectile is dead
            if (projectiles.get(index).isDead())
            {
                //remove it
                projectiles.remove(index);
                
                //move index position back
                index--;
            }
        }
    }
    
    /**
     * Set the projectile speed ratio.<br>
     * This will determine the speed of the projectile
     * @param projectileSpeedRatio Ratio of the characters movement speed
     */
    protected void setProjectileSpeedRatio(final double projectileSpeedRatio)
    {
        this.projectileSpeedRatio = projectileSpeedRatio;
    }
    
    /**
     * Get the projectile speed ratio
     * @return The rate at which the projectile speed should be
     */
    private double getProjectileSpeedRatio()
    {
        return this.projectileSpeedRatio;
    }
    
    /**
     * Get the movement speed of the character
     * @return The rate at which we can move between tiles
     */
    public double getSpeed()
    {
        return this.speed;
    }
    
    /**
     * Assign the movement speed of the character
     * @param speed The rate at which we can move between tiles
     */
    public void setSpeed(final double speed)
    {
        this.speed = speed;
    }
    
    /**
     * Is the character dead?
     * @return true if the health is less than or equal to 0, false otherwise
     */
    public boolean isDead()
    {
        return (getHealth() <= 0);
    }
    
    /**
     * Assign the projectile limit.
     * @param projectileLimit The maximum number of projectiles allowed at once
     */
    protected void setProjectileLimit(final int projectileLimit)
    {
        this.projectileLimit = projectileLimit;
    }
    
    /**
     * Get the projectile limit
     * @return The number of projectiles allowed at once
     */
    private int getProjectileLimit()
    {
        return this.projectileLimit;
    }
    
    /**
     * Add a projectile at this characters current location.<br>
     * If the projectile limit has been reached, nothing will happen and false will be the result
     * @param direction Direction projectile will be facing
     * @return true if a projectile was added, false otherwise
     * @throws Exception 
     */
    protected boolean addProjectile(final Projectile.Facing direction) throws Exception
    {
        //only add the projectile if we are within the limit
        if (projectiles.size() < getProjectileLimit())
        {
            //create a new projectile
            Projectile projectile = new Projectile(direction, getSpeed() * getProjectileSpeedRatio(), getSpriteSheet().getSpriteSheetAnimation(direction).getLocation());
            
            //assign the current location
            projectile.setCol(getCol());
            projectile.setRow(getRow());
            
            //assign the parent id as the creator of this projectile
            projectile.setParentId(getId());
            
            //assign the damage
            projectile.setDamage(getDamage());
            
            //add projectile to list
            projectiles.add(projectile);
            
            //return true as projectile was added
            return true;
        }
        else
        {
            //we are over the limit, projectile not added
            return false;
        }
    }
    
    /**
     * Update the common elements in the game.<br>
     * 1. Update the (x,y) coordinate location<br>
     * 2. Update the entity animation
     * 3. Update the character's projectiles (if they exist)
     * @param engine Object containing common game elements
     * @throws Exception 
     */
    public void updateCommon(final Engine engine) throws Exception
    {
        //assign the new location
        super.updateLocation(engine.getManager().getLevel());
        
        //update animation
        super.updateAnimation(engine.getTime());
        
        //update the projectiles
        this.updateProjectiles(engine);
    }
    
    /**
     * Render the character along with the projectile(s) if they exist
     * @param graphics Object where image is written to
     * @param image The sprite sheet with the image
     * @throws Exception 
     */
    @Override
    public void render(final Graphics graphics, final Image image) throws Exception
    {
        //if the entity is hidden, it will not be rendered
        if (isHidden())
            return;
        
        //draw the projectiles if they exist
        if (!projectiles.isEmpty())
        {
            for (int index = 0; index < projectiles.size(); index++)
            {
                projectiles.get(index).render(graphics, image);
            }
        }
        
        //draw the character
        super.render(graphics, image);
    }
}