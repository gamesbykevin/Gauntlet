package com.gamesbykevin.gauntlet.characters.hero;

import com.gamesbykevin.framework.base.Animation;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.projectile.Projectile;

/**
 * The Hero class
 * @author GOD
 */
public abstract class Hero extends Character
{
    /**
     * Create a new hero of specified type
     * @param type The type of player we want
     * @throws Exception If the type of player is not setup
     */
    protected Hero(final Character.Type type) throws Exception
    {
        super(type);
        
        super.setHealth(100000);
        
        /**
         * Assign different attributes to make each hero unique such as
         * 1. Character movement speed
         * 2. Projectile limit
         * 3. Damage to enemies
         * 4. Health
         */
        switch (getType())
        {
            case Wizard:
                break;

            case Elf:
                break;

            case Valkyrie:
                break;
                
            case Warrior:
                break;
                
            default:
                throw new Exception("Type not setup here " + getType().toString());
        }
    }
}