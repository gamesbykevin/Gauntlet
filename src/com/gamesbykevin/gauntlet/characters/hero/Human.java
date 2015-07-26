package com.gamesbykevin.gauntlet.characters.hero;

import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.level.Level;
import com.gamesbykevin.gauntlet.level.Tile;
import com.gamesbykevin.gauntlet.projectile.Projectile;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;

/**
 * Class representing the human
 * @author GOD
 */
public final class Human extends Hero
{
    protected Human(final Hero.Type type) throws Exception
    {
        super(type);
    }
    
    @Override
    public void update(final Engine engine) throws Exception
    {
        //update the timer
        if (hasPowerShot())
            getTimerPowerShot().update(engine.getTime());
        if (hasReflectiveShot())
            getTimerReflectiveShot().update(engine.getTime());
        
        //update health timer
        getTimerHealth().update(engine.getTime());
        
        //if time passed, deduct health
        if (getTimerHealth().hasTimePassed())
        {
            //update health
            setHealth(getHealth() - 1);
            getTimerHealth().reset();
        }
        
        double dx = 0, dy = 0;

        //did keyboard input occur
        boolean input = true;
        
        //determine if we are to pause the animation
        super.getSpriteSheet().setPause(engine.getKeyboard().isKeyPressed() ? false : true);
        
        //set the velocity based on keyboard input
        if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_LEFT) && engine.getKeyboard().hasKeyPressed(KeyEvent.VK_UP))
        {
            dx = -getSpeed();
            dy = -getSpeed();
            setAnimation(Character.Directions.NW);
        }
        else if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_LEFT) && engine.getKeyboard().hasKeyPressed(KeyEvent.VK_DOWN))
        {
            dx = -getSpeed();
            dy = getSpeed();
            setAnimation(Character.Directions.SW);
        }
        else if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_RIGHT) && engine.getKeyboard().hasKeyPressed(KeyEvent.VK_UP))
        {
            dx = getSpeed();
            dy = -getSpeed();
            setAnimation(Character.Directions.NE);
        }
        else if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_RIGHT) && engine.getKeyboard().hasKeyPressed(KeyEvent.VK_DOWN))
        {
            dx = getSpeed();
            dy = getSpeed();
            setAnimation(Character.Directions.SE);
        }
        else if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_LEFT))
        {
            dx = -getSpeed();
            setAnimation(Character.Directions.W);
        }
        else if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_RIGHT))
        {
            dx = getSpeed();
            setAnimation(Character.Directions.E);
        }
        else if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_UP))
        {
            dy = -getSpeed();
            setAnimation(Character.Directions.N);
        }
        else if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_DOWN))
        {
            dy = getSpeed();
            setAnimation(Character.Directions.S);
        }
        else
        {
            //no keys were pressed
            input = false;
        }
        
        /**
         * Check if we want to shoot a projectile
         */
        if (engine.getKeyboard().hasKeyPressed(KeyEvent.VK_A) && getTimerProjectile().hasTimePassed())
        {
            //reset timer
            getTimerProjectile().reset();
            
            //the projectile animation
            final Projectile.Facing projectileAnimation;
            
            //determine which projectile animation to add
            switch ((Character.Directions)getSpriteSheet().getCurrent())
            {
                case N:
                    projectileAnimation = Projectile.Facing.N;
                    break;
                    
                case S:
                    projectileAnimation = Projectile.Facing.S;
                    break;
                    
                case W:
                    projectileAnimation = Projectile.Facing.W;
                    break;
                    
                case E:
                    projectileAnimation = Projectile.Facing.E;
                    break;
                    
                case NW:
                    projectileAnimation = Projectile.Facing.NW;
                    break;
                    
                case NE:
                    projectileAnimation = Projectile.Facing.NE;
                    break;
                    
                case SW:
                    projectileAnimation = Projectile.Facing.SW;
                    break;
                    
                case SE:
                    projectileAnimation = Projectile.Facing.SE;
                    break;
                    
                default:
                    throw new Exception("Unexpected animation here = " + getSpriteSheet().getCurrent().toString());
            }
            
            //add the projectile of the specified animation
            addProjectile(projectileAnimation, hasReflectiveShot(), hasPowerShot());
        }
        else if (engine.getKeyboard().hasKeyReleased(KeyEvent.VK_S))
        {
            //stop the key released event
            engine.getKeyboard().removeKeyReleased(KeyEvent.VK_S);
            
            //if we have a potion use it
            if (hasPotion())
            {
                //remove it
                removePotion();
                
                //and remove all enemies on screen
                engine.getManager().getEnemies().removeEnemies(this);
            }
        }
        
        //if there was no keyboard input, no need to continue
        if (!input)
            return;
        
        //store the original col, row
        final double col = getCol();
        final double row = getRow();
        
        //update the location
        super.setCol(getCol() + dx);
        super.setRow(getRow() + dy);
        
        //get the level
        final Level level = engine.getManager().getLevel();
        
        //get the tile we have collided with
        Tile tile = level.getSolidCollision(this, dx, dy);
        
        /**
         * Check for collision, and if so move back to previous position
         */
        if (tile != null || engine.getManager().getEnemies().hasCollision(this))
        {
            super.setCol(col);
            super.setRow(row);
            
            //if we collided with a tile and it is a door, lets see if we can open
            if (tile != null && tile.isDoor())
            {
                //if we have at least 1 key
                if (hasKey())
                {
                    //remove the key
                    removeKey();

                    //unlock the door
                    level.unlockDoor(tile);
                }
            }
        }
        else
        {
            Tile tmp = level.getTile((int)getCol(), (int)getRow());
            
            //if we hit the exit, reset
            if (tmp != null && tmp.isExit())
            {
                //reset and no need to continue
                engine.getManager().reset(engine);
                return;
            }
            
            //the updated (x,y) coordinates
            double x = level.getCoordinateX(getCol());
            double y = level.getCoordinateY(getRow());

            //assign the new location
            super.updateLocation(level);
            
            //get the viewable window of the level
            final Rectangle window = level.getWindow();
            
            //the boundary coordinates for scrolling
            double xMin = x - (Tile.DEFAULT_DIMENSION * Level.SCROLL_RANGE);
            double xMax = x + (Tile.DEFAULT_DIMENSION * Level.SCROLL_RANGE);
            double yMin = y - (Tile.DEFAULT_DIMENSION * Level.SCROLL_RANGE);
            double yMax = y + (Tile.DEFAULT_DIMENSION * Level.SCROLL_RANGE);
            
            //the limits of the window to determine if there is scrolling
            double xWindowMin = window.x;
            double xWindowMax = window.x + window.width;
            double yWindowMin = window.y;
            double yWindowMax = window.y + window.height;
            
            //determine how to scroll based on location and direction
            if (xMax >= xWindowMax && dx > 0 && yMax >= yWindowMax && dy > 0)
            {
                level.updateTilesCol(-dx);
                level.updateTilesRow(-dy);
            }
            else if (xMax >= xWindowMax && dx > 0 && yMin <= yWindowMin && dy < 0)
            {
                level.updateTilesCol(-dx);
                level.updateTilesRow(-dy);
            }
            else if (xMin <= xWindowMin && dx < 0 && yMax >= yWindowMax && dy > 0)
            {
                level.updateTilesCol(-dx);
                level.updateTilesRow(-dy);
            }
            else if (xMin <= xWindowMin && dx < 0 && yMin <= yWindowMin && dy < 0)
            {
                level.updateTilesCol(-dx);
                level.updateTilesRow(-dy);
            }
            else if (xMax >= xWindowMax && dx > 0)
            {
                level.updateTilesCol(-dx);
            }
            else if (xMin <= xWindowMin && dx < 0)
            {
                level.updateTilesCol(-dx);
            }
            else if (yMax >= yWindowMax && dy > 0)
            {
                level.updateTilesRow(-dy);
            }
            else if (yMin <= yWindowMin && dy < 0)
            {
                level.updateTilesRow(-dy);
            }
        }
    }
}