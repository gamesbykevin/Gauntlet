package com.gamesbykevin.gauntlet.characters;

import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.shared.IElement;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class that will contain a collection of characters
 * @author GOD
 */
public abstract class Characters implements IElement
{
    /**
     * Image containing all animations for the characters
     */
    private Image image;
    
    /**
     * List of characters
     */
    private List<Character> characters;
    
    /**
     * Create the characters container
     * @param image Image containing the specified characters animations
     */
    protected Characters(final Image image)
    {
        //assign the animation image
        this.image = image;
        
        //create a new list for our characters
        this.characters = new ArrayList<>();
    }
    
    /**
     * Add character to list
     * @param character Character we want to add
     */
    protected void add(final Character character) throws Exception
    {
        //setup the animations
        CharacterHelper.setupAnimations(character);
        
        //add to list
        getCharacters().add(character);
    }
    
    /**
     * This method will add the appropriate character to the 
     * @param col Column location
     * @param row Row location
     * @param type Type of character to add
     */
    public abstract void add(final double col, final double row, final Character.Type type) throws Exception;
    
    @Override
    public void dispose()
    {
        if (characters != null)
        {
            for (int index = 0; index < characters.size(); index++)
            {
                characters.get(index).dispose();
                characters.set(index, null);
            }
            
            characters.clear();
            characters = null;
        }
    }
    
    /**
     * Get the character with the matching unique id
     * @param id The unique id
     * @return The character with the matching unique id, if not found null is returned
     */
    public Character getCharacter(final UUID id)
    {
        for (int index = 0; index < characters.size(); index++)
        {
            if (characters.get(index).hasId(id))
                return characters.get(index);
        }
        
        return null;
    }
    
    /**
     * Check if we have a character at this location.<br>
     * Here we are only checking if the same (Column, Row) without the accuracy.<br>
     * This means (1.4, 3) will be the same as (1, 3)
     * @param col Column
     * @param row Row
     * @return true if there is a character located here, otherwise false
     */
    public boolean hasCharacter(final int col, final int row)
    {
        for (int index = 0; index < characters.size(); index++)
        {
            //get the current character
            final Character character = characters.get(index);
            
            //if the integer values match, we have a character
            if ((int)character.getCol() == col && (int)character.getRow() == row)
                return true;
        }
        
        //no characters found, return false
        return false;
    }
    
    /**
     * Get the characters
     * @return The list of characters
     */
    protected List<Character> getCharacters()
    {
        return this.characters;
    }
    
    @Override
    public void update(final Engine engine) throws Exception
    {
        for (int index = 0; index < characters.size(); index++)
        {
            //get the current character
            final Character character = characters.get(index);
            
            //if the character is dead, we will remove it
            if (character.isDead())
            {
                //remove character
                characters.remove(index);
                
                //alter index
                index--;
            }
            else
            {
                //update basic common elements for this character
                character.updateCommon(engine);

                //update character specified logic
                character.update(engine);
            }
        }
    }
    
    /**
     * Does the entity have collision with any of the characters?
     * @param entity The entity we want to check
     * @return true if close enough, false otherwise
     */
    public boolean hasCollision(final Entity entity) throws Exception
    {
        return (getCharacter(entity) != null);
    }
    
    /**
     * Get the character that has collision with the supplied entity
     * @param entity The entity we want to check
     * @return The character in collision with the supplied entity, if there is no collision null is returned
     */
    public Character getCharacter(final Entity entity) throws Exception
    {
        //check each character
        for (int index = 0; index < getCharacters().size(); index++)
        {
            //get the current character
            final Character character = getCharacters().get(index);
            
            //don't check if the character is dead
            if (character.isDead())
                continue;
            
            //if the distance is close enough, we have collision with this character
            if (character.hasCollision(entity))
                return character;
        }
        
        //we didn't find any enemy close enough, return null
        return null;
    }
    
    /**
     * Get the image
     * @return The image containing all enemy animations
     */
    private Image getImage()
    {
        return this.image;
    }
    
    @Override
    public void render(final Graphics graphics) throws Exception
    {
        //render each character in the list
        for (int index = 0; index < characters.size(); index++)
        {
            //get the current character
            Character character = characters.get(index);
            
            //only render if the character is not dead
            if (!character.isDead())
                character.render(graphics, getImage());
        }
    }
}