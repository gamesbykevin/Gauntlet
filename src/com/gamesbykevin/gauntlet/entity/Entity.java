package com.gamesbykevin.gauntlet.entity;

import com.gamesbykevin.framework.base.Animation;
import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.framework.base.Sprite;
import com.gamesbykevin.framework.resources.Disposable;

import com.gamesbykevin.gauntlet.characters.Character;

import java.awt.Graphics;
import java.awt.Image;

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
    protected static final long DELAY_NONE = 0;
    
    protected Entity()
    {
        //create the spritesheet
        super.createSpriteSheet();
    }
    
    /**
     * Do we have a collision?<br>
     * We will check the (column, row) between the entity and character to determine collision.<br>
     * The character has an assigned distance needed to determine collision.
     * @param character Character with an assigned distance to determine collision
     * @return true if the distance between the entity and character is within the assigned character's distance
     */
    public boolean hasCollision(final Character character)
    {
        return (Cell.getDistance(this, character) <= character.getDistance());
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