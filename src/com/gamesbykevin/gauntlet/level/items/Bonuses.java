package com.gamesbykevin.gauntlet.level.items;

import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.framework.maze.Room;

import com.gamesbykevin.gauntlet.characters.hero.Hero;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.level.Level;
import com.gamesbykevin.gauntlet.level.Level.LockedRoom;
import com.gamesbykevin.gauntlet.resources.GameAudio;
import com.gamesbykevin.gauntlet.shared.IElement;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Container of bonuses
 * @author GOD
 */
public class Bonuses implements IElement
{
    /**
     * List of bonus items in the game
     */
    private List<Bonus> bonuses;
    
    /**
     * Our bonus image
     */
    private Image image;
    
    public Bonuses(final Image image)
    {
        //assign the sprite sheet image
        this.image = image;
        
        //create a new list of bonuses
        this.bonuses = new ArrayList<>();
    }
    
    /**
     * Remove all existing objects
     */
    public void reset()
    {
        this.bonuses.clear();
    }
    
    /**
     * Does this entity have a collision
     * @param entity Entity we are checking for
     * @return true if the objects collide, otherwise false
     * @throws Exception 
     */
    public boolean hasCollision(final Entity entity) throws Exception
    {
        return (getBonus(entity) != null);
    }
    
    /**
     * Get the bonus item we are in collision with
     * @param entity Entity we are checking for
     * @return The bonus object in collision with the entity, if not found return null
     * @throws Exception 
     */
    public Bonus getBonus(final Entity entity) throws Exception
    {
        for (int index = 0; index < bonuses.size(); index++)
        {
            //get the current bonus
            Bonus bonus = bonuses.get(index);
            
            //if we have collision, return true
            if (bonus.hasCollision(entity))
                return bonus;
        }
        
        //we do not have collision
        return null;
    }
    
    /**
     * Spawn Bonus items in the level
     * @param level The current level
     * @param random Object used to make random decisions
     * @throws Exception 
     */
    public void spawn(final Level level, final Random random) throws Exception
    {
        //clear any existing
        bonuses.clear();
        
        //available tiles for the specified room
        List<Cell> available = new ArrayList<>();
        
        //list of rooms to choose from
        List<Room> rooms = new ArrayList<>();
        
        //our list of locked rooms
        List<LockedRoom> tmp = level.getLockedRooms();
        
        /**
         * First lets spawn our keys and continue until we have reached the required amount
         */
        while (getTypeCount(Bonus.Type.Key) < level.getRequiredKeys())
        {
            for (int index = 0; index < tmp.size(); index++)
            {
                //get first locked door's room location
                final int tmpCol = (int)level.getLockedRooms().get(index).getCol();
                final int tmpRow = (int)level.getLockedRooms().get(index).getRow();

                //the cost of the current room
                final int cost = level.getMaze().getRoom(tmpCol, tmpRow).getCost();

                //clear list in case
                rooms.clear();

                /**
                 * Now lets build a list of rooms with a lower cost than the current
                 */
                for (int col = 0; col < level.getMaze().getCols(); col++)
                {
                    for (int row = 0; row < level.getMaze().getRows(); row++)
                    {
                        //skip the start room
                        if (level.getMaze().getStart().equals(col, row))
                            continue;
                        
                        //if the cost is lower add to the list
                        if (level.getMaze().getRoom(col, row).getCost() < cost)
                            rooms.add(level.getMaze().getRoom(col, row));
                    }
                }

                //pick a random room
                final Room room = rooms.get(random.nextInt(rooms.size()));

                //determine the start boundaries of the current room
                int colMin = (level.getRoomDimensions() * room.getCol());
                int rowMin = (level.getRoomDimensions() * room.getRow());

                //remove any existing locations
                available.clear();

                //check to see what positions in this room are open
                for (int tempCol = colMin; tempCol < colMin + level.getRoomDimensions(); tempCol++)
                {
                    for (int tempRow = rowMin; tempRow < rowMin + level.getRoomDimensions(); tempRow++)
                    {
                        if (level.getTile(tempCol, tempRow) == null)
                            continue;
                        if (level.getTile(tempCol, tempRow).isSolid())
                            continue;
                        if (hasCollision(tempCol, tempRow))
                            continue;
                        
                        //add available space
                        available.add(new Cell(tempCol, tempRow));
                    }
                }

                //make sure there are places to add a key
                if (!available.isEmpty())
                {
                    //add key at a random location
                    add(available.get(random.nextInt(available.size())), Bonus.Type.Key);
                    
                    //since we were able to spawn, remove from our list
                    tmp.remove(index);
                    
                    //move index back
                    index--;
                }
            }
        }
        
        //clear list in case
        rooms.clear();

        /**
         * Now lets build a list of rooms
         */
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            for (int row = 0; row < level.getMaze().getRows(); row++)
            {
                //skip start location
                if (level.getMaze().getStart().equals(col, row))
                    continue;

                //add room to the list
                rooms.add(level.getMaze().getRoom(col, row));
            }
        }
        
        //the limit will be the # of required keys + The square root of the level dimensions
        final double limit = level.getRequiredKeys() + Math.sqrt(level.getMaze().getCols() * level.getMaze().getRows());
        
        //continue until we reach our limit
        while (bonuses.size() < limit)
        {
            //pick a random room
            final Room room = rooms.get(random.nextInt(rooms.size()));

            //determine the start boundaries of the current room
            int colMin = (level.getRoomDimensions() * room.getCol());
            int rowMin = (level.getRoomDimensions() * room.getRow());

            //remove any existing locations
            available.clear();

            //check to see what positions in this room are open
            for (int tempCol = colMin; tempCol < colMin + level.getRoomDimensions(); tempCol++)
            {
                for (int tempRow = rowMin; tempRow < rowMin + level.getRoomDimensions(); tempRow++)
                {
                    if (level.getTile(tempCol, tempRow) == null)
                        continue;
                    if (level.getTile(tempCol, tempRow).isSolid())
                        continue;
                    if (hasCollision(tempCol, tempRow))
                        continue;

                    //add available space
                    available.add(new Cell(tempCol, tempRow));
                }
            }
            
            //now pick something at random from this list, and add at random location
            add(available.get(random.nextInt(available.size())), Bonus.Type.values()[random.nextInt(Bonus.Type.values().length - 1)]);
        }
        
        available.clear();
        available = null;
        
        rooms.clear();
        rooms = null;
        
        tmp.clear();
        tmp = null;
    }
    
    /**
     * Do we have collision? 
     * @param column Column
     * @param row Row
     * @return true if we have a bonus with collision at the specified location, otherwise false
     */
    private boolean hasCollision(final double column, final double row)
    {
        for (int index = 0; index < bonuses.size(); index++)
        {
            //if close enough, we have collision
            if (Cell.getDistance(column, row, bonuses.get(index)) < 1.0)
                return true;
        }
        
        //no collision was found
        return false;
    }
    
    /**
     * Get the count of objects with the specified type
     * @param type The type we want to count
     * @return The total # of objects with the matching type
     */
    private int getTypeCount(final Bonus.Type type)
    {
        int count = 0;
        
        for (int index = 0; index < bonuses.size(); index++)
        {
            //if they match, add to count
            if (bonuses.get(index).getType() == type)
                count++;
        }
        
        //return the final count
        return count;
    }
    
    /**
     * Add the bonus item to the list
     * @param cell The desired location
     * @param type Bonus Type
     * @throws Exception 
     */
    public void add(final Cell cell, final Bonus.Type type) throws Exception
    {
        add(cell.getCol(), cell.getRow(), type);
    }
    
    /**
     * Add the bonus item to the list
     * @param column Column position
     * @param row Row position
     * @param type Bonus Type
     * @throws Exception 
     */
    public void add(final double column, final double row, final Bonus.Type type) throws Exception
    {
        //create new bonus of type
        Bonus bonus = new Bonus(type);
        
        //update the location
        bonus.setCol(column + .5);
        bonus.setRow(row + .5);
        
        //add to the list
        bonuses.add(bonus);
    }
    
    @Override
    public void dispose()
    {
        if (bonuses != null)
        {
            for (int index = 0; index < bonuses.size(); index++)
            {
                bonuses.get(index).dispose();
                bonuses.set(index, null);
            }
            
            bonuses.clear();
            bonuses = null;
        }
    }
    
    /**
     * Remove the entity from the list
     * @param entity The entity we want to remove
     */
    public void remove(final Entity entity)
    {
        for (int index = 0; index < bonuses.size(); index++)
        {
            //if the id matches, we will remove it
            if (bonuses.get(index).hasId(entity))
            {
                bonuses.remove(index);
                break;
            }
        }
    }

    @Override
    public void update(final Engine engine) throws Exception
    {
        for (int index = 0; index < bonuses.size(); index++)
        {
            //get the current bonus
            Bonus bonus = bonuses.get(index);
            
            //update the location
            bonus.updateLocation(engine.getManager().getLevel());
            
            //did the hero touch the bonus
            if (engine.getManager().getHeroes().hasCollision(bonus))
            {
                //get the hero that collected the bonus
                final Hero hero = (Hero)engine.getManager().getHeroes().getCharacter(bonus);
                
                //here we will handle the bonus
                switch (bonus.getType())
                {
                    case PowerShot:
                        hero.getTimerPowerShot().reset();
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.PickupPowerShot);
                        break;
                        
                    case ReflectiveShot:
                        hero.getTimerReflectiveShot().reset();
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.PickupReflective);
                        break;
                        
                    case Food:
                        hero.addScore(Bonus.SCORE_FOOD);
                        hero.setHealth(hero.getHealth() + Bonus.FOOD_BONUS);
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.PickupFood);
                        break;
                        
                    case Key:
                        hero.addScore(Bonus.SCORE_KEY);
                        hero.addKey();
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.PickupKey);
                        break;
                        
                    case Poison:
                    case FoodPoison:
                        hero.setHealth(hero.getHealth() + Bonus.POISON_DAMAGE);
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.EatPoison);
                        break;
                        
                    case Potion:
                        hero.addPotion();
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.PickupPotion);
                        break;
                        
                    case TreasureBag:
                        hero.addScore(Bonus.SCORE_TREASURE_BAG);
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.PickupTreasure);
                        break;
                        
                    case TreasureChest:
                        hero.addScore(Bonus.SCORE_TREASURE_CHEST);
                        
                        //play sound effect
                        engine.getResources().playGameAudio(GameAudio.Keys.PickupTreasure);
                        break;
                }
                
                //remove from the list
                remove(bonus);
                
                //move back
                index--;
            }
        }
    }
    
    @Override
    public void render(final Graphics graphics) throws Exception
    {
        for (int index = 0; index < bonuses.size(); index++)
        {
            //render each bonus
            bonuses.get(index).render(graphics, image);
        }
    }
}