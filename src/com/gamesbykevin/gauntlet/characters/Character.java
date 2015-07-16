package com.gamesbykevin.gauntlet.characters;

import com.gamesbykevin.framework.base.Animation;
import com.gamesbykevin.framework.resources.Disposable;
import com.gamesbykevin.framework.util.Timers;

import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.level.Tile;
import com.gamesbykevin.gauntlet.projectile.Projectile;
import com.gamesbykevin.gauntlet.shared.IElement;
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
     * The default dimension (width, height) of each character animation
     */
    public static final int DEFAULT_DIMENSION = 16;
    
    /**
     * Default time delay for the animations
     */
    public static final long DEFAULT_DELAY = Timers.toNanoSeconds(100L);
    
    /**
     * The number of projectiles allowed at once
     */
    public static final int DEFAULT_PROJECTILE_LIMIT = 3;
    
    //the number of projectiles allowed
    private int projectileLimit = DEFAULT_PROJECTILE_LIMIT;
    
    /**
     * The scale ratio
     */
    protected static final double SIZE_RATIO = 1.5;
    
    /**
     * The projectile speed with be a % of the character speed
     */
    protected static final double PROJECTILE_SPEED_RATIO = 2.0;
    
    /**
     * The distance at which to check for collision
     */
    private double distance;
    
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
    
    protected Character()
    {
        super();
        
        //create a new list for the projectiles
        this.projectiles = new ArrayList<>();
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
    protected void updateProjectiles(final Engine engine) throws Exception
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
     * Get the speed of the character
     * @return The rate at which we can move between tiles
     */
    public double getSpeed()
    {
        return this.speed;
    }
    
    /**
     * Assign the speed of the character
     * @param speed The rate at which we can move between tiles
     */
    public void setSpeed(final double speed)
    {
        this.speed = speed;
    }
    
    /**
     * Set the projectile limit.
     * @param projectileLimit The number of projectiles allowed at once
     */
    protected void setProjectileLimit(final int projectileLimit)
    {
        this.projectileLimit = projectileLimit;
    }
    
    /**
     * Get the projectile limit
     * @return The number of projectiles allowed at once
     */
    protected int getProjectileLimit()
    {
        return this.projectileLimit;
    }
    
    /**
     * Add a projectile at the characters current location.<br>
     * If the projectile limit has been reached, nothing will happen
     * @param direction Direction projectile will be facing
     * @throws Exception 
     */
    protected void addProjectile(final Projectile.Facing direction) throws Exception
    {
        //only add the projectile if we are within the limit
        if (projectiles.size() < getProjectileLimit())
            projectiles.add(new Projectile(direction, getSpeed() * PROJECTILE_SPEED_RATIO, getCol(), getRow(), getSpriteSheet().getSpriteSheetAnimation(direction).getLocation()));
    }
    
    /**
     * Assign the collision distance
     */
    protected final void setDistance()
    {
        this.distance = (getWidth() / Tile.DEFAULT_DIMENSION) / 2;
    }
    
    /**
     * Get the collision distance.
     * @return The cell distance at which to check for collision for this hero
     */
    public double getDistance()
    {
        return this.distance;
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