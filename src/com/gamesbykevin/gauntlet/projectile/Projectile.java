package com.gamesbykevin.gauntlet.projectile;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.characters.hero.Hero;
import static com.gamesbykevin.gauntlet.characters.Character.Type.EnemyGenerator1;
import static com.gamesbykevin.gauntlet.characters.Character.Type.EnemyGenerator2;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.level.Level;
import com.gamesbykevin.gauntlet.level.items.Bonus;
import com.gamesbykevin.gauntlet.resources.GameAudio;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Projectile
 * @author GOD
 */
public final class Projectile extends Entity
{
    //the life of the projectile
    private boolean dead = false;
    
    /**
     * Does the projectile bounce off walls?<br>
     * If so we kill the projectile after 8 bounces or it goes off screen or until it hits an enemy
     */
    private boolean reflective = false;
    
    /**
     * The number of walls this projectile has bounced off of
     */
    private int countReflective;
    
    /**
     * The limited number of times the projectile can bounce off the wall
     */
    private static final int REFLECTIVE_LIMIT = 8;
    
    /**
     * If this is a power projectile it will instantly kill any enemy it comes in contact with.
     * This will be valid until the projectile hits a wall or kills 10 enemies
     */
    private boolean power = false;
    
    /**
     * The number of enemies this projectile has destroyed
     */
    private int countPower;
    
    /**
     * The limited number of enemies that can be destroyed with this projectile
     */
    private static final int POWER_LIMIT = 8;
    
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
        
        //store the location
        final double col = getCol();
        final double row = getRow();
        
        //update the location of the projectile
        setCol(getCol() + getVelocityX());
        setRow(getRow() + getVelocityY());
        
        //check for level collision, and if not reflective we will flag dead
        if (level.hasSolidCollision(this, getVelocityX(), getVelocityY()))
        {
            /**
             * If the projectile is reflective lets see if we can bounce it off the walls
             */
            if (isReflective() && countReflective < REFLECTIVE_LIMIT)
            {
                //move back to the previous location
                setCol(col);
                setRow(row);
                
                //flip the velocity
                setVelocityX(-getVelocityX());
                setVelocityY(-getVelocityY());
                
                //flip the animation as well
                setHorizontalFlip(true);
                setVerticalFlip(true);
                
                //increase the count
                countReflective++;
            }
            else
            {
                //mark it as dead
                setDead(true);
            }
        }
        
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
                
                //if this is a power projectile and we haven't reached the limit, keep it alive
                if (hasPower() && countPower < POWER_LIMIT)
                {
                    //increase the count
                    countPower++;
                
                    //we won't remove yet
                    setDead(false);
                }

                switch (character.getType())
                {
                    /**
                     * If this is an enemy generator, we will perform special logic
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
                            
                            //if the character is now dead, add score to hero
                            if (character.isDead())
                            {
                                Hero hero = (Hero)engine.getManager().getHeroes().getCharacter(getParentId());
                                hero.addScore(Bonus.SCORE_ENEMY_GENERATOR);
                                
                                //play sound effect
                                engine.getResources().playGameAudio(GameAudio.Keys.EnemyDead);
                            }
                        }
                        break;

                    /**
                     * These enemies can't be killed
                     */
                    case Blob:
                    case It:
                    case Death:
                        
                        /**
                         * Modify the enemies health, so we know it was hit and can be killed
                         */
                        character.setHealth(character.getHealth() + 1);
                        
                        //mark the projectile as dead
                        setDead(true);
                        break;
                        
                    default:

                        //apply damage to the character
                        character.setHealth(character.getHealth() - getDamage());
                        
                        //if the character is now dead, add score to hero
                        if (character.isDead())
                        {
                            Hero hero = (Hero)engine.getManager().getHeroes().getCharacter(getParentId());
                            hero.addScore(Bonus.SCORE_ENEMY);
                            
                            //play sound effect
                            engine.getResources().playGameAudio(GameAudio.Keys.EnemyDead);
                        }
                        break;
                }
            }
            else
            {
                //get the bonus that we may have collision with
                Bonus bonus = engine.getManager().getBonuses().getBonus(this);
                
                /**
                 * If the hero projectile hits a bonus, handle accordingly
                 */
                if (bonus != null)
                {
                    //mark the projectile as dead
                    setDead(true);
                    
                    //for some bonus types we will remove
                    switch (bonus.getType())
                    {
                        /**
                         * Keep these
                         */
                        case Key:
                        case TreasureBag:
                        case TreasureChest:
                            break;
                            
                            
                        case Food:
                            //play sound effect
                            engine.getResources().playGameAudio(GameAudio.Keys.ShotFood);
                            
                            //remove the bonus as well
                            engine.getManager().getBonuses().remove(bonus);
                            break;
                            
                        /**
                         * All others we will remove
                         */
                        default:
                            //remove the bonus as well
                            engine.getManager().getBonuses().remove(bonus);
                            break;
                    }
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
                
                //play sound effect
                engine.getResources().playGameAudio(GameAudio.Keys.HeroHitByProjectile);
            }
            else
            {
                //if the enemy projectile hits a bonus, we will remove the projectile
                if (engine.getManager().getBonuses().getBonus(this) != null)
                {
                    //mark the projectile as dead
                    setDead(true);
                }
            }
        }
    }
    
    /**
     * Set this projectile as reflective.<br>
     * If this is reflective we will bounce the projectile off the wall 8 times or until it hits an enemy
     * @param reflective true if we want the projectile to be reflective, otherwise false
     */
    public void setReflective(final boolean reflective)
    {
        this.reflective = reflective;
    }
    
    /**
     * Is this projectile reflective?<br>
     * Reflective will cause the projectile to bounce off walls for a short time until it hits an enemy
     * @return true = yes, false = no
     */
    public boolean isReflective()
    {
        return this.reflective;
    }
    
    /**
     * Set this projectile as a power projectile.<br>
     * If this is a power projectile we will immediately kill enemies upon contact and continue up to 10 enemies.<br>
     * Or until we hit a wall
     * @param power true if we want the projectile to go directly through enemies, otherwise false
     */
    public void setPower(final boolean power)
    {
        this.power = power;
    }
    
    /**
     * Is this a power projectile?
     * @return true = yes, false = no
     */
    public boolean hasPower()
    {
        return this.power;
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