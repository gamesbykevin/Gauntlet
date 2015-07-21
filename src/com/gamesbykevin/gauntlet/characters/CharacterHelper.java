package com.gamesbykevin.gauntlet.characters;

import com.gamesbykevin.framework.base.Animation;
import com.gamesbykevin.gauntlet.characters.Character.Directions;
import static com.gamesbykevin.gauntlet.characters.Character.DEFAULT_DELAY;
import static com.gamesbykevin.gauntlet.characters.Character.DEFAULT_DIMENSION;
import static com.gamesbykevin.gauntlet.characters.Character.SIZE_RATIO;
import static com.gamesbykevin.gauntlet.entity.Entity.DELAY_NONE;
import com.gamesbykevin.gauntlet.projectile.Projectile;

/**
 * Character Helper class
 * @author GOD
 */
public final class CharacterHelper
{
    /**
     * Setup the character animations
     * @param character Object we are adding the animations to.
     * @throws Exception 
     */
    protected static void setupAnimations(final Character character) throws Exception
    {
        //animation object
        Animation animation;
        
        switch (character.getType())
        {
            case Wizard:
                animation = new Animation(281, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(281, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                animation = new Animation(301, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(301, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                animation = new Animation(322, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(322, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                animation = new Animation(343, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(343, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                animation = new Animation(284, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(284, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SW);
                
                animation = new Animation(305, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(305, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SE);
                
                animation = new Animation(325, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(325, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NW);
                
                animation = new Animation(344, 42, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(344, 62, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NE);
                
                animation = new Animation(309, 84, 8, 6, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(290, 84, 8, 6, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(346, 82, 6, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(329, 82, 6, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(344, 106, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(327, 106, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(307, 106, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(291, 106, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
            
            case Elf:
                animation = new Animation(190, 6, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(190, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                animation = new Animation(209, 4, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(210, 23, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                animation = new Animation(231, 4, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(231, 23, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                animation = new Animation(248, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(250, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                animation = new Animation(191, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(191, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SW);
                
                animation = new Animation(211, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(211, 63, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SE);
                
                animation = new Animation(230, 46, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(231, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NW);
                
                animation = new Animation(248, 46, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(248, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NE);
                
                animation = new Animation(195, 94, 8, 3, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(216, 94, 8, 3, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(238, 89, 3, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(256, 89, 3, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(195, 110, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(214, 112, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(235, 111, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(254, 111, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
                
            case Valkyrie:
                animation = new Animation(99, 6, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(101, 27, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                animation = new Animation(120, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(122, 26, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                animation = new Animation(142, 6, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(144, 26, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                animation = new Animation(162, 5, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(163, 25, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                animation = new Animation(100, 47, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(98, 68, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SW);
                
                animation = new Animation(121, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(120, 67, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SE);
                
                animation = new Animation(142, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(143, 65, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NW);
                
                animation = new Animation(163, 45, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(164, 64, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NE);
                
                animation = new Animation(102, 98, 8, 4, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(121, 98, 8, 4, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(148, 92, 4, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(169, 93, 4, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(102, 113, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(124, 113, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(145, 113, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(164, 114, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
                
            case Warrior:
                animation = new Animation(4, 4, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(4, 27, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                animation = new Animation(28, 3, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(29, 26, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                animation = new Animation(50, 3, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(50, 25, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                animation = new Animation(73, 3, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(74, 24, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                animation = new Animation(5, 52, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(7, 74, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SW);
                
                animation = new Animation(27, 51, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(26, 73, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SE);
                
                animation = new Animation(75, 46, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(74, 71, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NW);
                
                animation = new Animation(52, 48, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(50, 72, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NE);
                
                animation = new Animation(9, 96, 8, 7, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(31, 97, 8, 7, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(56, 98, 7, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(75, 94, 7, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(10, 113, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NW);
                
                animation = new Animation(32, 114, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(55, 114, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(75, 113, 8, 8, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SE);
                break;
                
            case Ghost:
                animation = new Animation(6, 16, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 16, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(43, 16, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(61, 16, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                animation = new Animation(6, 35, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(23, 35, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 35, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(61, 35, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NW);
                
                animation = new Animation(6, 53, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 52, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(43, 53, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(62, 53, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                animation = new Animation(6, 70, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 70, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 70, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(61, 70, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SW);
                
                animation = new Animation(6, 87, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 87, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(43, 87, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(61, 87, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                animation = new Animation(6, 107, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(25, 107, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(43, 107, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(61, 107, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SE);
                
                animation = new Animation(6, 125, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 125, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(42, 125, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(61, 124, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                animation = new Animation(6, 143, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(26, 143, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(45, 143, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(64, 142, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NE);
                break;
                
            case Demon:
                int x = 7;
                int y = 166;
                
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NE);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SE);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SW);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NW);
                
                animation = new Animation(82, 188, 7, 9, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.N);
                
                animation = new Animation(82, 200, 8, 9, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NE);
                
                animation = new Animation(82, 213, 8, 7, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.E);
                
                animation = new Animation(82, 223, 8, 9, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SE);
                
                animation = new Animation(82, 235, 7, 9, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.S);
                
                animation = new Animation(81, 247, 8, 9, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.SW);
                
                animation = new Animation(81, 259, 8, 7, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.W);
                
                animation = new Animation(81, 269, 8, 9, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.NW);
                break;
                
            case Blob:
                animation = new Animation(8, 315, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 315, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(42, 315, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(57, 314, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                animation = new Animation(8, 332, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 332, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 332, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(58, 332, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NE);
                
                animation = new Animation(8, 349, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 349, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 349, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(58, 350, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                animation = new Animation(8, 366, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 366, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 366, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(58, 366, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SE);
                
                animation = new Animation(7, 383, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 382, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 383, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(57, 383, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                animation = new Animation(8, 401, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 401, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 401, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(58, 401, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.SW);
                
                animation = new Animation(8, 418, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 418, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 418, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(58, 419, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                animation = new Animation(8, 435, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(24, 435, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(41, 435, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(58, 435, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.NW);
                break;
                
            case Grunt:
                x = 100;
                y = 6;
                
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                y = 24;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                y = 41;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                y = 59;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                break;
                
            case Sorcerer:
                x = 100;
                y = 81;
                
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18) - 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18) - 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                y = 99;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18) - 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                y = 118;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                y = 136;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18) - 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                break;
                
            case Lobber:
                x = 101;
                y = 158;
                
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                y = 175;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                y = 192;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18) + 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18) + 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                y = 208;
                animation = new Animation(x + (0 * 18) - 2, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18) - 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18) - 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                x = 172;
                y = 187;
                int w = 7;
                int h = 7;
                
                animation = new Animation(x, y, w, h, DELAY_NONE);
                character.getSpriteSheet().add(animation, Projectile.Facing.N);
                character.getSpriteSheet().add(animation, Projectile.Facing.NE);
                character.getSpriteSheet().add(animation, Projectile.Facing.E);
                character.getSpriteSheet().add(animation, Projectile.Facing.SE);
                character.getSpriteSheet().add(animation, Projectile.Facing.S);
                character.getSpriteSheet().add(animation, Projectile.Facing.SW);
                character.getSpriteSheet().add(animation, Projectile.Facing.W);
                character.getSpriteSheet().add(animation, Projectile.Facing.NW);
                break;
                
            case It:
                x = 100;
                y = 231;
                
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                y = 250;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                y = 269;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                y = 288;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                break;
                
            case SuperSorcerer:
                x = 101;
                y = 310;
                
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                y = 328;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                y = 346;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18) + 1, y + 1, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18) + 1, y + 1, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18) + 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                y = 365;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18) - 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18) + 1, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                
                y = 505;
                w = 9;
                h = 9;
                
                character.getSpriteSheet().add(new Animation(52, y, w, h, DELAY_NONE), Projectile.Facing.N);
                character.getSpriteSheet().add(new Animation(64, y, w, h, DELAY_NONE), Projectile.Facing.NE);
                character.getSpriteSheet().add(new Animation(75, y, w, h, DELAY_NONE), Projectile.Facing.E);
                character.getSpriteSheet().add(new Animation(86, y, w, h, DELAY_NONE), Projectile.Facing.SE);
                character.getSpriteSheet().add(new Animation(96, y, w, h, DELAY_NONE), Projectile.Facing.S);
                character.getSpriteSheet().add(new Animation(107, y, w, h, DELAY_NONE), Projectile.Facing.SW);
                character.getSpriteSheet().add(new Animation(118, y, w, h, DELAY_NONE), Projectile.Facing.W);
                character.getSpriteSheet().add(new Animation(129, y, w, h, DELAY_NONE), Projectile.Facing.NW);
                break;
                
            case Death:
                x = 101;
                y = 385;
                
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.N);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                
                y += 18;
                animation = new Animation(x + (0 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (1 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (2 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.add(x + (3 * 18), y, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DEFAULT_DELAY);
                animation.setLoop(true);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                break;
                
            case EnemyGenerator1:
                animation = new Animation(215, 0, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DELAY_NONE);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                animation = new Animation(200, 0, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DELAY_NONE);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                animation = new Animation(185, 0, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DELAY_NONE);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                break;
                
            case EnemyGenerator2:
                animation = new Animation(215, 15, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DELAY_NONE);
                character.getSpriteSheet().add(animation, Character.Directions.S);
                animation = new Animation(200, 15, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DELAY_NONE);
                character.getSpriteSheet().add(animation, Character.Directions.W);
                animation = new Animation(185, 15, DEFAULT_DIMENSION, DEFAULT_DIMENSION, DELAY_NONE);
                character.getSpriteSheet().add(animation, Character.Directions.E);
                break;
                
            default:
                throw new Exception("Type not setup here " + character.getType().toString());
        }
        
        //assign the start animation
        character.setAnimation(Directions.S);
        
        //assign the dimensions
        character.setDimensions(Character.DEFAULT_DIMENSION * SIZE_RATIO, Character.DEFAULT_DIMENSION * SIZE_RATIO);
    }
}