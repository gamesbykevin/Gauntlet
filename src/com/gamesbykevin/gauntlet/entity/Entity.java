package com.gamesbykevin.gauntlet.entity;

import com.gamesbykevin.framework.base.Animation;
import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.framework.base.Sprite;
import com.gamesbykevin.framework.resources.Disposable;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.level.Level;
import com.gamesbykevin.gauntlet.level.Tile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * The heroes, enemies, projectiles, tiles are all entities
 * @author GOD
 */
public abstract class Entity extends Sprite implements Disposable
{
    /**
     * Default animation key to use
     */
    public static final String DEFAULT_ANIMATION_KEY = "Default";
    
    /**
     * The 0 time delay
     */
    public static final long DELAY_NONE = 0;
    
    /**
     * Do we render the object
     */
    private boolean hide = false;
    
    /**
     * Health of the character
     */
    private int health = 0;
    
    /**
     * The lowest amount of health possible, 0
     */
    public static final int HEALTH_MINIMUM = 0;
    
    /**
     * The damage the character can cause
     */
    private int damage;
    
    /**
     * Default damage
     */
    protected static final int DAMAGE_DEFAULT = 1;
    
    protected Entity()
    {
        //create the spritesheet
        super.createSpriteSheet();
        
        //set the default
        setDamage(DAMAGE_DEFAULT);
    }
    
    /**
     * Assign the character health.<br>
     * The character health can't go below 0.<br>
     * If a number less than 0 is assigned 0 will be the health
     * @param health The desired health
     */
    public void setHealth(final int health)
    {
        this.health = health;
        
        if (getHealth() < HEALTH_MINIMUM)
            setHealth(HEALTH_MINIMUM);
    }
    
    /**
     * Get the health of the entity
     * @return The remaining health
     */
    public int getHealth()
    {
        return this.health;
    }
    
    /**
     * Assign the damage the character is capable of
     * @param damage The desired damage
     */
    public final void setDamage(final int damage)
    {
        this.damage = damage;
    }
    
    /**
     * Get the damage
     * @return The damage the character is capable of
     */
    public int getDamage()
    {
        return this.damage;
    }
    
    /**
     * Do we hide this entity from rendering
     * @param hide true=yes, false=no
     */
    public void setHidden(final boolean hide)
    {
        this.hide = hide;
    }
    
    /**
     * Is this entity hidden?
     * @return true if we are to not render this entity, otherwise false
     */
    public boolean isHidden()
    {
        return this.hide;
    }
    
    /**
     * Reset the current animation
     */
    public void resetAnimation() throws Exception
    {
        super.getSpriteSheet().reset();
    }
    
    /**
     * Does this animation exist in the sprite sheet?
     * @param key The key we are looking for
     * @return True if the animation already exists in the sprite sheet, false otherwise
     */
    public boolean hasAnimation(final Object key) throws Exception
    {
        return (getSpriteSheet().getSpriteSheetAnimation(key) != null);
    }
    
    /**
     * Is the supplied animation the current one
     * @param key The key of the object we are looking for
     * @return true if this is the current animation, false otherwise
     * @throws Exception 
     */
    public boolean isAnimation(final Object key) throws Exception
    {
        return (super.getSpriteSheet().getCurrent().equals(key));
    }
    
    /**
     * Assign the current animation.<br>
     * @param key The key of the animation
     */
    public void setAnimation(final Object key) throws Exception
    {
        super.getSpriteSheet().setCurrent(key);
    }
    
    /**
     * Add animation to sprite sheet (single frame)
     * @param key Object used to identify the animation
     * @param x starting x-coordinate of animation
     * @param y starting y-coordinate of animation
     * @param w width of animation
     * @param h height of animation
     */
    protected void addAnimation(final Object key, final int x, final int y, final int w, final int h) throws Exception
    {
        //add animation to spritesheet
        super.getSpriteSheet().add(new Animation(x, y, w, h, DELAY_NONE), key);
        
        //if no current animation set, set this as a default
        if (getSpriteSheet().getCurrent() == null)
            setAnimation(key);
    }
    
    /**
     * Update the location based on the entity's (column, row)<br>
     * Then once we update the location we can determine
     * @param level Level object that will assist with (x,y) placement
     */
    public void updateLocation(final Level level)
    {
        //assign the new location
        super.setX(level.getCoordinateX(getCol()));
        super.setY(level.getCoordinateY(getRow()));
        
        //get the window
        Rectangle window = level.getWindow();
        
        //determine if the entity is offscreen
        if (getX() < window.x || getX() > window.x + window.width || getY() < window.y || getY() > window.y + window.height)
        {
            setHidden(true);
        }
        else
        {
            setHidden(false);
        }
    }
    
    /**
     * Update the entity animation
     * @param time Time to deduct in (nanoseconds)
     * @throws Exception 
     */
    public void updateAnimation(final long time) throws Exception
    {
        super.getSpriteSheet().update(time);
    }
    
    /**
     * Do we have collision?<br>
     * Here we will compare the (column, row) location of the two entities to determine collision.<br>
     * If the entities are the same object, we will no check and return false
     * @param entity Entity we want to check
     * @return true=yes, false=no
     */
    public boolean hasCollision(final Entity entity) throws Exception
    {
        //if we are checking the same entity, return false
        if (hasId(entity))
            return false;
        
        //calculate the distance
        final double distance = Cell.getDistance(this, entity);
        
        //if we are close enough, we have collision
        if (distance < getCollisionDistance())
            return true;
        if (distance < entity.getCollisionDistance())
            return true;
        
        //we don't have collision
        return false;
    }
    
    /**
     * Get the collision distance.
     * @return The cell distance at which to check for collision for this entity
     */
    public double getCollisionDistance() throws Exception
    {
        return ((getSpriteSheet().getLocation().getWidth() * 1.000) / Tile.DEFAULT_DIMENSION);
    }
    
    /**
     * Render the entity.<br>
     * Here we assume an image was assigned to an entity
     * @param graphics Object where image is written to
     * @throws Exception 
     */
    public void render(final Graphics graphics) throws Exception
    {
        this.render(graphics, getImage());
    }
    
    /**
     * Render the entity
     * @param graphics Object where image is written to
     * @param image The sprite sheet with the image
     * @throws Exception 
     */
    public void render(final Graphics graphics, final Image image) throws Exception
    {
        //get original location
        final double x = getX();
        final double y = getY();
        
        //offset location
        super.setX(x - (getWidth() / 2));
        super.setY(y - (getHeight() / 2));
        
        //render card
        super.draw(graphics, image);
        
        //reset location
        super.setX(x);
        super.setY(y);
    }
}