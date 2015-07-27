package com.gamesbykevin.gauntlet.resources;

import com.gamesbykevin.framework.resources.*;

/**
 * All audio for game
 * @author GOD
 */
public final class GameAudio extends AudioManager
{
    //description for progress bar
    private static final String DESCRIPTION = "Loading Audio Resources ";
    
    /**
     * These are the keys used to access the resources and need to match the id in the xml file
     */
    public enum Keys
    {
        Begin, Dead, EnemyDead, EatPoison, Exit, HeroHitByProjectile, HeroHurt, ShootProjectile, 
        
        PickupFood, PickupKey, PickupPotion, PickupTreasure, PickupPowerShot, PickupReflective,
        
        ShotFood, UsePotion, HealthLow, HealthVeryLow, OpenDoor, 
        
        Music_NextLevel, Music_Level_1, Music_Level_2, Music_Level_3, Music_Level_4
    }
    
    public GameAudio() throws Exception
    {
        super(Resources.XML_CONFIG_GAME_AUDIO);
        
        //the description that will be displayed for the progress bar
        super.setProgressDescription(DESCRIPTION);
        
        if (Keys.values().length < 1)
            super.increase();
    }
}