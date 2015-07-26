package com.gamesbykevin.gauntlet.level.items;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.entity.Entity;

/**
 * Bonus item in the game
 * @author GOD
 */
public final class Bonus extends Entity
{
    /**
     * The types of bonuses in the game
     */
    public enum Type
    {
        Food, FoodPoison, 
        Poison, Potion, 
        PowerShot, ReflectiveShot, 
        TreasureBag, TreasureChest, 
        
        //keep the key at the end
        Key
    }
    
    /**
     * The amount of health to restore
     */
    protected static final int FOOD_BONUS = 250;
    
    /**
     * The amount of damage poison will cause
     */
    protected static final int POISON_DAMAGE = -100;
    
    /**
     * The score for the treasure bag
     */
    protected static final int SCORE_TREASURE_BAG = 100;
    
    /**
     * The score for the treasure chest
     */
    protected static final int SCORE_TREASURE_CHEST = 500;
    
    //assign the bonus type
    private Type type;
    
    protected Bonus(final Type type) throws Exception
    {
        setType(type);
    }
    
    /**
     * Assign the type of bonus<br>
     * Will also update the animation
     * @param type The type of bonus we want.
     * @throws Exception 
     */
    public final void setType(final Type type) throws Exception
    {
        //assign the type
        this.type = type;
        
        //update animation
        assignAnimation();
    }
    
    /**
     * Assign the animation
     * @throws Exception 
     */
    public final void assignAnimation() throws Exception
    {
        final int x, y, w, h;
        
        //setup animation
        switch (getType())
        {
            case PowerShot:
                x = 36;
                y = 0;
                w = 12;
                h = 16;
                break;
            
            case Poison:
                x = 0;
                y = 0;
                w = 12;
                h = 16;
                break;
            
            case Potion:
                x = 12;
                y = 0;
                w = 12;
                h = 16;
                break;
            
            case ReflectiveShot:
                x = 24;
                y = 0;
                w = 12;
                h = 16;
                break;
            
                
            case Food:
                x = 0;
                y = 16;
                w = 12;
                h = 16;
                break;
            
            case FoodPoison:
                x = 12;
                y = 16;
                w = 12;
                h = 16;
                break;
            
            case Key:
                x = 0;
                y = 32;
                w = 16;
                h = 9;
                break;
            
            case TreasureBag:
                x = 0;
                y = 41;
                w = 16;
                h = 15;
                break;
            
            case TreasureChest:
                x = 16;
                y = 41;
                w = 16;
                h = 15;
                break;
                
            default:
                throw new Exception("Type not setup here " + getType());
        }
        
        //add the single animation as the projectile
        super.addAnimation(DEFAULT_ANIMATION_KEY, x, y, w, h);
        
        //assign the dimensions
        setDimensions(w * Character.SIZE_RATIO, h * Character.SIZE_RATIO);
    }
    
    /**
     * Get the type
     * @return The type of bonus item
     */
    public final Type getType()
    {
        return this.type;
    }
}