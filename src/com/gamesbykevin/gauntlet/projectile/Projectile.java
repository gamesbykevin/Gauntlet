package com.gamesbykevin.gauntlet.projectile;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.level.Level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Projectile
 * @author GOD
 */
public final class Projectile extends Character
{
    //the life of the projectile
    private boolean dead = false;
    
    /**
     * The direction the projectile is facing and heading towards
     */
    public enum Facing
    {
        N, S, W, E, NW, NE, SW, SE
    }
    
    /**
     * Create a new projectile.
     * @param direction Direction facing
     * @param speed Speed of projectile
     * @param col Current column location
     * @param row Current row location
     * @param r Rectangle containing animation coordinates
     * @throws Exception 
     */
    public Projectile(final Facing direction, final double speed, final double col, final double row, final Rectangle r) throws Exception
    {
        this(direction, speed, col, row, r.x, r.y, r.width, r.height);
    }
    
    /**
     * Create a new projectile.
     * @param direction Direction facing
     * @param speed Speed of projectile
     * @param col Current column location
     * @param row Current row location
     * @param x x-coordinate of single animation
     * @param y y-coordinate of single animation
     * @param w width of single animation
     * @param h height of single animation
     * @throws Exception 
     */
    public Projectile(final Facing direction, final double speed, final double col, final double row, final int x, final int y, final int w, final int h) throws Exception
    {
        //call parent contstructor
        super();
        
        //assign the current location
        super.setCol(col);
        super.setRow(row);
        
        //add the single animation as the projectile
        super.addAnimation(DEFAULT_ANIMATION_KEY, x, y, w, h);
        
        //assign the dimensions
        setDimensions(w * SIZE_RATIO, h * SIZE_RATIO);
        
        //assign collision distance
        super.setDistance();
        
        //determine the direction by the specified parameter
        switch (direction)
        {
            case N:
                super.setVelocityY(-speed);
                break;
                
            case S:
                super.setVelocityY(speed);
                break;
                
            case W:
                super.setVelocityX(-speed);
                break;
                
            case E:
                super.setVelocityX(speed);
                break;
                
            case NE:
                super.setVelocityX(speed);
                super.setVelocityY(-speed);
                break;
                
            case NW:
                super.setVelocityX(-speed);
                super.setVelocityY(-speed);
                break;
                
            case SE:
                super.setVelocityX(speed);
                super.setVelocityY(speed);
                break;
                
            case SW:
                super.setVelocityX(-speed);
                super.setVelocityY(speed);
                break;
                
            default:
                throw new Exception("Direction not handled here " + direction);
        }
    }
    
    @Override
    public void update(final Engine engine) throws Exception
    {
        //do not continue if already dead
        if (isDead())
            return;
        
        //get the level
        final Level level = engine.getManager().getLevel();
        
        //check for level collision
        if (level.hasWallCollision(this, getVelocityX(), getVelocityY()))
            setDead(true);
        
        //update the location of the projectile
        setCol(getCol() + getVelocityX());
        setRow(getRow() + getVelocityY());
        
        //update where this is rendered on screen
        setX(level.getCoordinateX(getCol()));
        setY(level.getCoordinateY(getRow()));
    }
    
    /**
     * Set the life of the projectile
     * @param dead true if dead, false otherwise
     */
    public void setDead(final boolean dead)
    {
        this.dead = dead;
    }
    
    /**
     * Is the projectile dead?
     * @return true = yes, false = no
     */
    public boolean isDead()
    {
        return this.dead;
    }
    
    /**
     * Render the entity
     * @param graphics Object where image is written to
     * @param image The sprite sheet with the image
     * @throws Exception 
     */
    @Override
    public void render(final Graphics graphics, final Image image) throws Exception
    {
        //no need to render if dead
        if (isDead())
            return;
        
        super.render(graphics, image);
    }
}