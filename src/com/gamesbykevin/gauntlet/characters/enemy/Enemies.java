package com.gamesbykevin.gauntlet.characters.enemy;

import com.gamesbykevin.framework.base.Cell;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.characters.Character.Type;
import com.gamesbykevin.gauntlet.characters.Characters;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.level.Level;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Container for enemies in the game
 * @author GOD
 */
public final class Enemies extends Characters
{
    /**
     * The number of this type of enemy allowed per level
     */
    private static final int ENEMY_LIMIT_IT = 3;
    
    /**
     * The number of this type of enemy allowed per level
     */
    private static final int ENEMY_LIMIT_DEATH = 3;
    
    /**
     * The # of rooms from the start location we can start adding enemies
     */
    private static final int SPAWN_PADDING = 1;
    
    /**
     * Our Random object used to make random decisions
     */
    private Random random;
    
    /**
     * Create a new enemies container
     * @param image Sprite sheet image
     */
    public Enemies(final Image image)
    {
        super(image);
    }
    
    /**
     * Remove all existing objects
     */
    public void reset()
    {
        super.getCharacters().clear();
    }
    
    /**
     * Spawn enemies in the level
     * @param level The current level
     * @param random Object used to make random decisions
     */
    public void spawn(final Level level, final Random random) throws Exception
    {
        if (this.random == null)
            this.random = random;
        
        //options to choose from
        List<Type> options = new ArrayList<>();
        options.add(Type.Blob);
        options.add(Type.Death);
        options.add(Type.Demon);
        options.add(Type.Ghost);
        options.add(Type.Grunt);
        options.add(Type.It);
        options.add(Type.Lobber);
        options.add(Type.Sorcerer);
        options.add(Type.SuperSorcerer);
        
        //available tiles for the specified room
        List<Cell> available = new ArrayList<>();
        
        //the max # of enemies per room, will be determined by the room size
        final int limit = (level.getRoomDimensions() / 3);
        
        //check each room in the maze
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            for (int row = 0; row < level.getMaze().getRows(); row++)
            {
                //is this room a neighbor of the start location
                boolean neighbor = false;
                
                /**
                 * Check all neighbor rooms around the start location.<br>
                 * We want to provide the heroes space when they start
                 */
                for (int tmpCol = -SPAWN_PADDING; tmpCol <= SPAWN_PADDING; tmpCol++)
                {
                    for (int tmpRow = -SPAWN_PADDING; tmpRow <= SPAWN_PADDING; tmpRow++)
                    {
                        if (level.getMaze().getStartCol() + tmpCol == col && level.getMaze().getStartRow() + tmpRow == row)
                            neighbor = true;
                    }
                }
                
                //if the room is close to the start, skip it
                if (neighbor)
                    continue;
                
                //determine the start boundaries of the current room
                int colMin = (level.getRoomDimensions() * col);
                int rowMin = (level.getRoomDimensions() * row);
                
                //remove any existing locations
                available.clear();
                
                for (int tempCol = colMin; tempCol < colMin + level.getRoomDimensions(); tempCol++)
                {
                    for (int tempRow = rowMin; tempRow < rowMin + level.getRoomDimensions(); tempRow++)
                    {
                        if (level.getTile(tempCol, tempRow) == null)
                            continue;
                        if (level.getTile(tempCol, tempRow).isSolid())
                            continue;
                        
                        //add available space
                        available.add(new Cell(tempCol, tempRow));
                    }
                }
                
                //do we spawn an enemy generator in this room
                boolean enemyGenerator = random.nextBoolean();
                
                //if the list is empty, skip to the next room
                if (available.isEmpty())
                    continue;
                
                //count the number of enemies added
                int count = 0;
                
                //pick a random position from our optional enemy list
                final int randomEnemyIndex = random.nextInt(options.size());
                
                //pick random enemy type for this room
                final Type type = options.get(randomEnemyIndex);
                
                //continue while we haven't reached our limit and still have available cells to choose from
                while (count < limit && !available.isEmpty())
                {
                    //if the enemy created was "Death" lets make sure we don't create too many
                    if (type == Type.Death)
                    {
                        //if we exceed the limit, exit loop and remove it from the options
                        if (getEnemyCount(Type.Death) >= ENEMY_LIMIT_DEATH)
                        {
                            options.remove(randomEnemyIndex);
                            break;
                        }
                    }
                    else if (type == Type.It)
                    {
                        //if we exceed the limit, exit loop and remove it from the options
                        if (getEnemyCount(Type.It) >= ENEMY_LIMIT_IT)
                        {
                            options.remove(randomEnemyIndex);
                            break;
                        }
                    }
                    
                    //pick a random index
                    final int index = random.nextInt(available.size());
                    
                    //get the random cell
                    Cell cell = available.get(index);
                    
                    if (enemyGenerator)
                    {
                        //add enemy generator
                        add(cell.getCol() + .5, cell.getRow() + .5, (random.nextBoolean()) ? Type.EnemyGenerator1 : Type.EnemyGenerator2);
                        
                        //we only need 1 enemy generator per room
                        break;
                    }
                    else
                    {
                        //add enemy of random type
                        add(cell.getCol() + .5, cell.getRow() + .5, type);
                    }
                    
                    //we can no longer use this location
                    available.remove(index);
                    
                    //increase count
                    count++;
                }
            }
        }
        
        available.clear();
        available = null;
    }
    
    /**
     * Remove all existing enemies within a certain distance of the entity.<br>
     * This will basically clear all existing enemies on the screen
     * @param The entity which will help identify the range of enemies to remove
     */
    public void removeEnemies(final Entity entity)
    {
        for (int index = 0; index < super.getCharacters().size(); index++)
        {
            //get the current character
            Character character = getCharacters().get(index);
            
            //if the distance is less than the length of a typical screen (maybe a little more)
            if (Cell.getDistance(entity, character) <= Level.SCROLL_RANGE * 2)
            {
                //recycle the character objects
                character.dispose();
                
                //remove the character
                getCharacters().remove(index);
                
                //move the index back
                index--;
            }
        }
    }
    
    /**
     * Get the enemy count
     * @param type The enemy we want to count for
     * @return The total number of enemies that match the specified type
     */
    private int getEnemyCount(final Type type)
    {
        int count = 0;
        
        for (int index = 0; index < super.getCharacters().size(); index++)
        {
            //if the type matches increase our count
            if (getCharacters().get(index).getType() == type)
                count++;
        }
        
        //return the count
        return count;
    }
    
    @Override
    public void add(final double col, final double row, final Type type) throws Exception
    {
        //create enemy of type
        Enemy enemy = new Enemy(type, random);
        
        //assign location
        enemy.setCol(col);
        enemy.setRow(row);
        
        //add character to list
        super.add(enemy);
    }
}