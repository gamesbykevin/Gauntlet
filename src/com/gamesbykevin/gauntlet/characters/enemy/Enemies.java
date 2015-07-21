package com.gamesbykevin.gauntlet.characters.enemy;

import com.gamesbykevin.framework.base.Cell;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.characters.Character.Type;
import com.gamesbykevin.gauntlet.characters.Characters;
import com.gamesbykevin.gauntlet.engine.Engine;
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
    public Enemies(final Image image)
    {
        super(image);
    }
    
    /**
     * Spawn enemies in the level
     * @param level The current level
     * @param random Object used to make random decisions
     */
    public void spawn(final Level level, final Random random) throws Exception
    {
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
        
        //the limit  of enemies per room
        final int limit = (level.getRoomDimensions() / 3);
        
        //check each room in the maze
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            for (int row = 0; row < level.getMaze().getRows(); row++)
            {
                //is this room a neighbor of the start location
                boolean neighbor = false;
                
                //check all neighbor rooms around the start location, because we want to provide space for the player
                for (int tmpCol = -1; tmpCol < 2; tmpCol++)
                {
                    for (int tmpRow = -1; tmpRow < 2; tmpRow++)
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
                
                int count = 0;
                
                //pick random enemy type for this room
                final Type type = options.get(random.nextInt(options.size()));
                
                //continue while we haven't reached our limit and still have available cells to choose from
                while (count < limit && !available.isEmpty())
                {
                    //pick a random index
                    final int index = random.nextInt(available.size());
                    
                    //get the random cell
                    Cell cell = available.get(index);
                    
                    if (enemyGenerator)
                    {
                        //add enemy generator
                        add(cell.getCol() + .5, cell.getRow() + .5, (random.nextBoolean()) ? Type.EnemyGenerator1 : Type.EnemyGenerator2);
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
    
    @Override
    public void add(final double col, final double row, final Type type) throws Exception
    {
        //create enemy of type
        Enemy enemy = new Enemy(type);
        
        //assign location
        enemy.setCol(col);
        enemy.setRow(row);
        
        //add character to list
        super.add(enemy);
    }
}