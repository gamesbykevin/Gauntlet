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
     * Types of heroes available
     */
    public enum Type
    {
        Wizard, Elf, Valkyrie, Warrior
    }
    
    /**
     * The type of hero
     */
    private final Type type;
    
    /**
     * Create a new hero of specified type
     * @param type The type of player we want
     * @throws Exception If the type of player is not setup
     */
    public Hero(final Type type) throws Exception
    {
        this.type = type;
        
        //animation object
        Animation animation;
        
        switch (getType())
        {
            case Wizard:
                animation = new Animation(281, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(281, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.S);
                
                animation = new Animation(301, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(301, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.E);
                
                animation = new Animation(322, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(322, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.W);
                
                animation = new Animation(343, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(343, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.N);
                
                animation = new Animation(284, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(284, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SW);
                
                animation = new Animation(305, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(305, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SE);
                
                animation = new Animation(325, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(325, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NW);
                
                animation = new Animation(344, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(344, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NE);
                
                animation = new Animation(309, 84, 8, 6, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(290, 84, 8, 6, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(346, 82, 6, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(329, 82, 6, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(344, 106, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(327, 106, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(307, 106, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(291, 106, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
            
            case Elf:
                animation = new Animation(190, 6, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(190, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.S);
                
                animation = new Animation(209, 4, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(210, 23, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.E);
                
                animation = new Animation(231, 4, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(231, 23, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.W);
                
                animation = new Animation(248, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(250, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.N);
                
                animation = new Animation(191, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(191, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SW);
                
                animation = new Animation(211, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(211, 63, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SE);
                
                animation = new Animation(230, 46, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(231, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NW);
                
                animation = new Animation(248, 46, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(248, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NE);
                
                animation = new Animation(195, 94, 8, 3, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(216, 94, 8, 3, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(238, 89, 3, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(256, 89, 3, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(195, 110, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(214, 112, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(235, 111, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(254, 111, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
                
            case Valkyrie:
                animation = new Animation(99, 6, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(101, 27, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.S);
                
                animation = new Animation(120, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(122, 26, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.E);
                
                animation = new Animation(142, 6, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(144, 26, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.W);
                
                animation = new Animation(162, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(163, 25, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.N);
                
                animation = new Animation(100, 47, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(98, 68, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SW);
                
                animation = new Animation(121, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(120, 67, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SE);
                
                animation = new Animation(142, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(143, 65, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NW);
                
                animation = new Animation(163, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(164, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NE);
                
                animation = new Animation(102, 98, 8, 4, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(121, 98, 8, 4, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(148, 92, 4, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(169, 93, 4, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(102, 113, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(124, 113, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(145, 113, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(164, 114, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
                
            case Warrior:
                animation = new Animation(4, 4, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(4, 27, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.S);
                
                animation = new Animation(28, 3, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(29, 26, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.E);
                
                animation = new Animation(50, 3, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(50, 25, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.W);
                
                animation = new Animation(73, 3, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(74, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.N);
                
                animation = new Animation(5, 52, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(7, 74, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SW);
                
                animation = new Animation(27, 51, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(26, 73, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.SE);
                
                animation = new Animation(75, 46, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(74, 71, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NW);
                
                animation = new Animation(52, 48, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(50, 72, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                getSpriteSheet().add(animation, Hero.Directions.NE);
                
                animation = new Animation(9, 96, 8, 7, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(31, 97, 8, 7, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(56, 98, 7, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(75, 94, 7, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(10, 113, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(32, 114, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(55, 114, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(75, 113, 8, 8, DELAY_NONE);
                getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
                
            default:
                throw new Exception("Type not setup here " + getType().toString());
        }
        
        //assign the animation
        setAnimation(Hero.Directions.S);
        
        //assign the dimensions
        setDimensions(Character.DEFAULT_DIMENSION * SIZE_RATIO, Character.DEFAULT_DIMENSION * SIZE_RATIO);
        
        //assign the collision distance
        setDistance();
    }
    
    public final Type getType()
    {
        return this.type;
    }
}