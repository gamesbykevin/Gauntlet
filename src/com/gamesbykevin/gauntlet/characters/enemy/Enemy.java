package com.gamesbykevin.gauntlet.characters.enemy;

import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.framework.util.Timer;
import com.gamesbykevin.framework.util.Timers;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.level.Level;
import com.gamesbykevin.gauntlet.level.Tile;
import com.gamesbykevin.gauntlet.projectile.Projectile.Facing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Random;

/**
 * Enemy in the game
 * @author GOD
 */
public class Enemy extends Character
{
    //determine how often the enemy can attack via collision
    private Timer timerCollision;
    
    //determine how long the enemy is hurt
    private Timer timerHurt;
    
    /**
     * The speed at which the character can move
     */
    protected static final double DEFAULT_ENEMY_SPEED = 0.015;
    
    /**
     * The speed at which the character can move
     */
    protected static final double ENEMY_SPEED_SLOW = 0.010;
    
    /**
     * The speed at which the character can move
     */
    protected static final double ENEMY_SPEED_SLOWER = 0.0075;
    
    /**
     * Default time to pause the enemy when hurt
     */
    protected static final long DELAY_HURT = Timers.toNanoSeconds(750L);
    
    /**
     * Default time to wait until the next attack
     */
    protected static final long DELAY_COLLISION = Timers.toNanoSeconds(750L);
    
    /**
     * Default time to wait until we spawn a new enemy
     */
    protected static final long SPAWN_DELAY = Timers.toNanoSeconds(3000L);
    
    //list of optional cell to spawn an enemy, used by the enemy generator
    private List<Cell> options;
    
    //the previous health to determine if the enemy was hurt 
    private int previousHealth;
    
    //health
    private static final int HEALTH_1 = 1;
    private static final int HEALTH_2 = 2;
    private static final int HEALTH_3 = 3;
    
    //damage
    private static final int DAMAGE_1 = 10;
    private static final int DAMAGE_2 = 25;
    private static final int DAMAGE_3 = 100;
    private static final int DAMAGE_4 = 150;
    
    public Enemy(final Type type, final Random random) throws Exception
    {
        super(type);

        /**
         * Assign different attributes to make each enemy unique such as
         * 1. Character movement speed
         * 2. Projectile limit
         * 3. Projectile speed ratio
         * 4. Time delay between each projectile fired
         * 4. Damage
         * 6. Health
         */
        
        super.setSpeed(DEFAULT_ENEMY_SPEED);
        
        //set the time to determine when to shoot a projectile
        setTimerProjectile(DELAY_PROJECTILE);
        
        //set the time to determine when the enemy can move
        setTimerHurt(DELAY_HURT);
        
        //setup the rate of collision attack
        setTimerCollision(DELAY_COLLISION);
        
        //determine the timer by Character type
        switch (getType())
        {
            case Ghost:
                super.setSpeed(DEFAULT_ENEMY_SPEED);
                super.setProjectileLimit(0);
                super.setProjectileSpeedRatio(0);
                super.getTimerProjectile().setReset(0);
                super.setDamage(DAMAGE_1);
                super.setHealth(HEALTH_2);
                break;
                
            case Demon:
                super.setSpeed(ENEMY_SPEED_SLOW);
                super.setProjectileLimit(1);
                super.setProjectileSpeedRatio(PROJECTILE_SPEED_RATIO_FASTEST);
                super.getTimerProjectile().setReset(DELAY_PROJECTILE_SLOW);
                super.setDamage(DAMAGE_2);
                super.setHealth(HEALTH_1);
                break;
                
            case Blob:
                super.setSpeed(ENEMY_SPEED_SLOWER);
                super.setProjectileLimit(0);
                super.setProjectileSpeedRatio(0);
                super.getTimerProjectile().setReset(0);
                super.setDamage(DAMAGE_3);
                super.setHealth(HEALTH_1);
                break;
                
            case Grunt:
                super.setSpeed(DEFAULT_ENEMY_SPEED);
                super.setProjectileLimit(0);
                super.setProjectileSpeedRatio(0);
                super.getTimerProjectile().setReset(0);
                super.setDamage(DAMAGE_2);
                super.setHealth(HEALTH_3);
                break;
                
            case Sorcerer:
                super.setSpeed(ENEMY_SPEED_SLOW);
                super.setProjectileLimit(0);
                super.setProjectileSpeedRatio(0);
                super.getTimerProjectile().setReset(0);
                super.setDamage(DAMAGE_1);
                super.setHealth(HEALTH_1);
                break;
                
            case SuperSorcerer:
                super.setSpeed(ENEMY_SPEED_SLOWER);
                super.setProjectileLimit(1);
                super.setProjectileSpeedRatio(PROJECTILE_SPEED_RATIO_FASTEST);
                super.getTimerProjectile().setReset(DELAY_PROJECTILE);
                super.setDamage(DAMAGE_1);
                super.setHealth(HEALTH_3);
                break;
                
            case Lobber:
                super.setSpeed(DEFAULT_ENEMY_SPEED);
                super.setProjectileLimit(1);
                super.setProjectileSpeedRatio(PROJECTILE_SPEED_RATIO_FASTEST);
                super.getTimerProjectile().setReset(DELAY_PROJECTILE);
                super.setDamage(DAMAGE_2);
                super.setHealth(HEALTH_1);
                break;
                
            case It:
                super.setSpeed(ENEMY_SPEED_SLOW);
                super.setProjectileLimit(0);
                super.setProjectileSpeedRatio(0);
                super.getTimerProjectile().setReset(0);
                super.setDamage(DAMAGE_3);
                super.setHealth(HEALTH_1);
                break;
                
            case Death:
                super.setSpeed(ENEMY_SPEED_SLOW);
                super.setProjectileLimit(0);
                super.setProjectileSpeedRatio(0);
                super.getTimerProjectile().setReset(0);
                super.setDamage(DAMAGE_4);
                super.setHealth(HEALTH_1);
                break;
            
            case EnemyGenerator1:
            case EnemyGenerator2:
                
                //default health
                super.setHealth(HEALTH_1);
                
                //make the spawn rate a little random (between 3 - 9 seconds)
                setTimerCollision(SPAWN_DELAY + (long)(random.nextFloat() * (SPAWN_DELAY * 2)));
                break;
                
            default:
                throw new Exception("Character type not setup here = " + getType().toString());
        }
        
        //create a new container list
        this.options = new ArrayList<>();
        
        //assign the previous health
        this.setPreviousHealth(super.getHealth());
    }
    
    @Override
    public void dispose()
    {
        super.dispose();
        
        this.timerCollision = null;
        this.timerHurt = null;
    }
    
    /**
     * Set the previous health, so we can determine if the enemy was hurt
     * @param previousHealth The previous health.
     */
    private void setPreviousHealth(final int previousHealth)
    {
        this.previousHealth = previousHealth;
    }
    
    /**
     * Get the previous health for comparison
     * @return The previous health
     */
    private int getPreviousHealth()
    {
        return this.previousHealth;
    }
    
    /**
     * Setup the timer at which the enemy can move when not hurt
     * @param delay Time delay in nanoseconds
     */
    protected final void setTimerHurt(final long delay)
    {
        if (this.timerHurt == null)
            this.timerHurt = new Timer(delay);
        
        //update the timer delay
        this.timerHurt.setReset(delay);
        
        //expire the delay
        this.timerHurt.setRemaining(DELAY_NONE);
    }
    
    /**
     * Setup the timer at which the enemy can attack via collision
     * @param delay Time delay in nanoseconds
     */
    protected final void setTimerCollision(final long delay)
    {
        if (this.timerCollision == null)
            this.timerCollision = new Timer(delay);
        
        //update the timer delay
        this.timerCollision.setReset(delay);
        this.timerCollision.reset();
    }
    
    /**
     * Get the hurt timer.
     * @return The timer to determine when the enemy can move
     */
    protected Timer getTimerHurt()
    {
        return this.timerHurt;
    }
    
    /**
     * Get the collision timer.
     * @return The timer to determine when to attack via collision
     */
    protected Timer getTimerCollision()
    {
        return this.timerCollision;
    }
    
    /**
     * Does this enemy have the specified target
     * @param uuid Unique key object of the target
     * @return true if we have, false otherwise
     */
    public boolean hasTarget(final UUID uuid)
    {
        //if no target is assigned, we don't have the target
        if (getTarget() == null)
            return false;
        
        //check if the target matches
        return getTarget().equals(uuid);
    }
    
    /**
     * Assign the hero the enemy is targeting
     * @param uuid The unique object of the hero we want to target
     */
    public void assignTarget(final UUID uuid)
    {
        super.setParentId(uuid);
    }
    
    /**
     * Get the target
     * @return The target of the hero this enemy will attack
     */
    public UUID getTarget()
    {
        return super.getParentId();
    }
    
    @Override
    public void update(final Engine engine) throws Exception
    {
        //don't contine if dead
        if (isDead())
            return;
        
        //if the enemy does not have a target, lets find one
        if (getTarget() == null)
        {
            //get the hero character that is closest to this enemy
            Character tmp = engine.getManager().getHeroes().getClosest(this);
            
            //make sure a hero character was found before assigning a target
            if (tmp != null)
            {
                //assign the target
                assignTarget(tmp.getId());
            }
            else
            {
                //we can't continue if there is no target for the enemy
                return;
            }
        }
        
        //get the assigned target
        final Character target = engine.getManager().getHeroes().getCharacter(getTarget());

        //we can't continue if there is no target for the enemy
        if (target == null)
        {
            //remove the previous target, so we can look for a new one
            assignTarget(null);
            
            //can't continue for the moment
            return;
        }
        
        //check the distance
        final double distance = Cell.getDistance(this, target);
        
        //get the current level
        final Level level = engine.getManager().getLevel();
        
        //the enemy generator will be handled differently
        if (getType() == Type.EnemyGenerator1 || getType() == Type.EnemyGenerator2)
        {
            //if this enemy isn't close enough to the target we won't update
            if (distance > level.getRoomDimensions() * 2)
                return;
            
            //if the timer has passed, lets see if we have an available space to spawn an enemy
            if (getTimerCollision().hasTimePassed())
            {
                //clear our list
                options.clear();
                
                //check the area around the generator
                for (int col = -1; col < 2; col++)
                {
                    for (int row = -1; row < 2; row++)
                    {
                        //don't check the same location
                        if (col == 0 && row == 0)
                            continue;
                        
                        //check the level tile at this location
                        Tile tile = level.getTile(col + (int)getCol(), row + (int)getRow());
                        
                        //if the tile exists and is not solid, so far so good
                        if (tile != null && !tile.isSolid())
                        {
                            //also make sure there are no enemies here
                            if (engine.getManager().getEnemies().hasCharacter(col + (int)getCol(), row + (int)getRow()))
                                continue;
                            
                            //and make sure the hero isn't here
                            if (engine.getManager().getHeroes().hasCharacter(col + (int)getCol(), row + (int)getRow()))
                                continue;
                            
                            //add this valid option
                            options.add(new Cell(col + getCol(), row + getRow()));
                        }
                    }
                }
                
                //if options exist
                if (!options.isEmpty())
                {
                    //get the current option
                    Cell option = options.get(engine.getRandom().nextInt(options.size()));
                    
                    //spawn an enemy here
                    engine.getManager().getEnemies().add(option.getCol(), option.getRow(), (getType() == Type.EnemyGenerator2) ? Type.Ghost : Type.Demon);
                    
                    //reset timer
                    getTimerCollision().reset();
                }
            }
        }
        else
        {
            //if this enemy isn't close enough to the target we won't update
            if (distance > level.getRoomDimensions() * 3 || distance > level.getRoomDimensions() * 3)
            {
                //pause the animation
                getSpriteSheet().setPause(true);
                return;
            }
            else
            {
                //un-pause the animation
                getSpriteSheet().setPause(false);
            }
            
            /**
             * If the previous health does not match the current we know the enemy was hit
             */
            if (getPreviousHealth() != getHealth())
            {
                //set the new previous health
                setPreviousHealth(getHealth());
                
                //reset the hurt timer
                getTimerHurt().reset();
            }
            
            //only allow the enemy to move when no longer hurt
            if (getTimerHurt().hasTimePassed())
            {
                //store the original location
                final double col = getCol();
                final double row = getRow();

                //enemy velocity
                double dx = 0;
                double dy = 0;

                if (getCol() + getSpeed() < target.getCol() && getRow() + getSpeed() < target.getRow())
                {
                    //south east
                    dx = getSpeed();
                    dy = getSpeed();

                    //set the appropriate animation
                    setAnimation(hasAnimation(Directions.SE) ? Directions.SE : Directions.S);
                }
                else if (getCol() + getSpeed() < target.getCol() && getRow() - getSpeed() > target.getRow())
                {
                    //north east
                    dx = getSpeed();
                    dy = -getSpeed();

                    //set the appropriate animation
                    setAnimation(hasAnimation(Directions.NE) ? Directions.NE : Directions.N);
                }
                else if (getCol() - getSpeed() > target.getCol() && getRow() + getSpeed() < target.getRow())
                {
                    //south west
                    dx = -getSpeed();
                    dy = getSpeed();

                    //set the appropriate animation
                    setAnimation(hasAnimation(Directions.SW) ? Directions.SW : Directions.S);
                }
                else if (getCol() - getSpeed() > target.getCol() && getRow() - getSpeed() > target.getRow())
                {
                    //north west
                    dx = -getSpeed();
                    dy = -getSpeed();

                    //set the appropriate animation
                    setAnimation(hasAnimation(Directions.NW) ? Directions.NW : Directions.N);
                }
                else if (getCol() + getSpeed() < target.getCol())
                {
                    //east
                    dx = getSpeed();

                    //set the appropriate animation
                    setAnimation(Directions.E);
                }
                else if (getCol() - getSpeed() > target.getCol())
                {
                    //west
                    dx = -getSpeed();

                    //set the appropriate animation
                    setAnimation(Directions.W);
                }
                else if (getRow() + getSpeed() < target.getRow())
                {
                    //south
                    dy = getSpeed();

                    //set the appropriate animation
                    setAnimation(Directions.S);
                }
                else if (getRow() - getSpeed() > target.getRow())
                {
                    //north
                    dy = -getSpeed();

                    //set the appropriate animation
                    setAnimation(Directions.N);
                }

                //update location
                setCol(getCol() + dx);
                setRow(getRow() + dy);

                //check for wall collision
                if (level.hasSolidCollision(this, dx, dy))
                {
                    //restore previous location
                    setCol(col);
                    setRow(row);

                    //try to only update the column and see if there is collision
                    setCol(getCol() + dx);

                    //if there is collision 
                    if (level.hasSolidCollision(this, dx, 0))
                    {
                        //restore previous location
                        setCol(col);
                        setRow(row);

                        //try to only update the row and see if there is collision
                        setRow(getRow() + dy);

                        //if we still have collision reset all back
                        if (level.hasSolidCollision(this, 0, dy))
                        {
                            //restore previous location
                            setCol(col);
                            setRow(row);
                        }
                    }
                }

                //check for other enemy collision
                if (engine.getManager().getEnemies().hasCollision(this))
                {
                    //restore previous location
                    setCol(col);
                    setRow(row);

                    //try to only update the column and see if there is collision
                    setCol(getCol() + dx);

                    //if there is collision 
                    if (engine.getManager().getEnemies().hasCollision(this))
                    {
                        //restore previous location
                        setCol(col);
                        setRow(row);

                        //try to only update the row and see if there is collision
                        setRow(getRow() + dy);

                        //if we still have collision reset all back
                        if (engine.getManager().getEnemies().hasCollision(this))
                        {
                            //restore previous location
                            setCol(col);
                            setRow(row);
                        }
                    }
                }

                //if time has passed and the target isn't already dead, we can attack the target
                if (getTimerCollision().hasTimePassed() && !target.isDead())
                {
                    //check for collision
                    if (hasCollision(target))
                    {
                        switch (getType())
                        {
                            /**
                             * Once these enemies attack their target, remove them
                             */
                            case Ghost:
                            case Blob:
                            case It:
                                setHealth(0);
                                break;
                        }
                        
                        //deduct health from target
                        target.setHealth(target.getHealth() - getDamage());

                        //reset timer
                        getTimerCollision().reset();
                    }
                }

                if (getTimerProjectile().hasTimePassed())
                {
                    //check if a projectile can be thrown
                    checkProjectile(dx, dy);

                    //reset the timer
                    getTimerProjectile().reset();
                }

                //if collision we will move back
                if (hasCollision(target))
                {
                    //move back to previous location
                    setCol(col);
                    setRow(row);
                }

                //update (x, y) coordinates
                setX(level.getCoordinateX(getCol()));
                setY(level.getCoordinateY(getRow()));
            }
        }
        
        //update the timers
        getTimerCollision().update(engine.getTime());
        getTimerHurt().update(engine.getTime());
    }
    
    /**
     * Check if the enemy can shoot a projectile.<br>
     * If the projectile timer has passed the projectile will
     * @param dx x-velocity
     * @param dy y-velocity
     */
    private void checkProjectile(final double dx, final double dy) throws Exception
    {
        //if time has passed
        if (getTimerProjectile().hasTimePassed())
        {
            //if moving diagnolly
            if (dx != 0 && dy != 0)
            {
                //if we can shoot a projectile in any of the 8 directions
                if (canShootProjectiles8())
                {
                    if (dx < 0)
                    {
                        addProjectile(dy < 0 ? Facing.NW : Facing.SW);
                    }
                    else
                    {
                        addProjectile(dy < 0 ? Facing.NE : Facing.SE);
                    }
                }
            }
            else
            {
                //if we can shoot a projectile in any of the 4 main directions
                if (canShootProjectiles4())
                {
                    if (dx != 0)
                    {
                        addProjectile(dx < 0 ? Facing.W : Facing.E);
                    }
                    else
                    {
                        addProjectile(dy < 0 ? Facing.N : Facing.S);
                    }
                }
            }
        }
    }
    
    /**
     * Can this enemy shoot a projectile in 8 directions?<br>
     * North, South, East, West<br>
     * North West, North East, South West, South East<br>
     * @return true if we the NE animation, otherwise false
     */
    private boolean canShootProjectiles8() throws Exception
    {
        return super.hasAnimation(Facing.NE);
    }
    
    /**
     * Can this enemy shoot a projectile in 4 directions?<br>
     * North, South, East, West<br>
     * @return true if we have the N animation, otherwise false
     */
    private boolean canShootProjectiles4() throws Exception
    {
        return super.hasAnimation(Facing.N);
    }
}