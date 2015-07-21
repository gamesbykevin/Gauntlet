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
    public Heroes(final Image image)
    {
        super(image);
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
