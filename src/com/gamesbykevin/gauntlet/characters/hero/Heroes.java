package com.gamesbykevin.gauntlet.characters.hero;

import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.characters.Characters;
import com.gamesbykevin.gauntlet.entity.Entity;

import java.awt.Image;

/**
 * Container for all heroes in the game
 * @author GOD
 */
public final class Heroes extends Characters
{
    /**
     * The (column, row) to spawn the first hero
     */
    private static final double START_LOCATION = 1.5;
    
    public Heroes(final Image image)
    {
        super(image);
    }
    
    /**
     * Reset all existing objects
     */
    public void reset()
    {
        double col = START_LOCATION;
        double row = START_LOCATION;
        
        for (int index = 0; index < super.getCharacters().size(); index++)
        {
            //get the current character
            Character character = super.getCharacters().get(index);
            
            //reset position
            character.setCol(col);
            character.setRow(row);
            
            //remove any existing projectiles
            character.removeProjectiles();
            
            //move the column over
            col += 1.0d;
        }
    }
    
    /**
     * Get the character closest to the supplied entity
     * @param entity The entity we want to check
     * @return The character found, if none found null is returned
     */
    public Character getClosest(final Entity entity)
    {
        double distance = -1;
        
        Character hero = null;
        
        for (int index = 0; index < getCharacters().size(); index++)
        {
            //get the current character
            final Character character = getCharacters().get(index);
            
            //don't check ourselves
            if (entity.hasId(character))
                continue;
            
            final double tmp = Cell.getDistance(entity, character);
            
            //if this is a closer distance
            if (tmp < distance || distance < 0)
            {
                //store the winner
                hero = character;
                
                //also store the winning distance
                distance = tmp;
            }
        }
        
        //return the character found
        return hero;
    }
    
    /**
     * Add hero at the default start point
     * @param type Type of hero to add
     * @throws Exception 
     */
    public void add(final Character.Type type) throws Exception
    {
        add(START_LOCATION, START_LOCATION, type);
    }
    
    @Override
    public void add(final double col, final double row, final Character.Type type) throws Exception
    {
        //create new character
        Character character = new Human(type);
        
        //set the position
        character.setCol(col);
        character.setRow(row);
        
        //add to list
        super.add(character);
    }
}