package com.gamesbykevin.gauntlet.projectile;

import com.gamesbykevin.gauntlet.characters.Character;
import static com.gamesbykevin.gauntlet.characters.Character.Type.EnemyGenerator1;
import static com.gamesbykevin.gauntlet.characters.Character.Type.EnemyGenerator2;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.level.Level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.UUID;

/**
 * Projectile
 * @author GOD
 */
public final class Projectile extends Entity
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
     * @param r Rectangle containing animation coordinates
     * @throws Exception 
     */
    public Projectile(final Facing direction, final double speed, final Rectangle r) throws Exception
    {
        this(direction, speed, r.x, r.y, r.width, r.height);
    }
    
    /**
     * Create a new projectile.
     * @param direction Direction facing
     * @param speed Speed of projectile
     * @param x x-coordinate of single animation
     * @param y y-coordinate of single animation
     * @param w width of single animation
     * @param h height of single animation
     * @throws Exception 
     */
    public Projectile(final Facing direction, final double speed, final int x, final int y, final int w, final int h) throws Exception
    {
        super();
        
        //add the single animation as the projectile
        super.addAnimation(DEFAULT_ANIMATION_KEY, x, y, w, h);
        
        //assign the dimensions
        setDimensions(w * Character.SIZE_RATIO, h * Character.SIZE_RATIO);
        
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
    
    /**
     * Update the projectile
     * @param engine
     * @throws Exception 
     */
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
        super.updateLocation(level);
        
        //our tmp character object
        Character character;

        //was this projectile created from a hero, if so we will check the enemies
        if (engine.getManager().getHeroes().getCharacter(getParentId()) != null)
        {
            //check for collision with the enemies
            character = engine.getManager().getEnemies().getCharacter(this);

            //if a character was found and the character was not the creator of the projectile
            if (character != null && !character.hasId(getParentId()))
            {
                //mark the projectile as dead
                setDead(true);

                switch (character.getType())
                {
                    /**
                     * If this is an enemy generator, we will perform special logic to update the animation
                     */
                    case EnemyGenerator1:
                    case EnemyGenerator2:

                        //apply damage to the found character
                        character.setHealth(1);

                        if (character.isAnimation(Character.Directions.S))
                        {
                            character.setAnimation(Character.Directions.W);
                        }
                        else if (character.isAnimation(Character.Directions.W))
                        {
                            character.setAnimation(Character.Directions.E);
                        }
                        else if (character.isAnimation(Character.Directions.E))
                        {
                            character.setHealth(0);
                        }
                        break;

                    default:

                        //apply damage to the found character
                        character.setHealth(character.getHealth() - getDamage());
                        break;
                }
            }
        }
        else if (engine.getManager().getEnemies().getCharacter(getParentId()) != null)
        {
            /**
             * If this projectile was created from an enemy, check the heroes
             */
            character = engine.getManager().getHeroes().getCharacter(this);

            //if a character was found and the character was not the creator of the projectile
            if (character != null && !character.hasId(getParentId()))
            {
                //mark the projectile as dead
                setDead(true);

                //apply damage to the found character
                character.setHealth(character.getHealth() - getDamage());
            }
        }
    }
    
    /**
     * Set if the projectile is dead
     * @param dead true = yes, false = no
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