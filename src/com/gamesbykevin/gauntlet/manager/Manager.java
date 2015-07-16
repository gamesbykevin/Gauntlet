package com.gamesbykevin.gauntlet.manager;

import com.gamesbykevin.gauntlet.characters.hero.*;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.level.Level;
import com.gamesbykevin.gauntlet.menu.CustomMenu;
import com.gamesbykevin.gauntlet.menu.CustomMenu.*;
import com.gamesbykevin.gauntlet.resources.GameAudio;
import com.gamesbykevin.gauntlet.resources.GameFont;
import com.gamesbykevin.gauntlet.resources.GameImages;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that contains all of the game elements
 * @author GOD
 */
public final class Manager implements IManager
{
    private Level level;
    
    //our hero
    private Hero hero;
    
    /**
     * Constructor for Manager, this is the point where we load any menu option configurations
     * @param engine Engine for our game that contains all objects needed
     * @throws Exception 
     */
    public Manager(final Engine engine) throws Exception
    {
        //set the audio depending on menu setting
        engine.getResources().setAudioEnabled(engine.getMenu().getOptionSelectionIndex(LayerKey.Options, OptionKey.Sound) == CustomMenu.SOUND_ENABLED);
    }
    
    public Level getLevel()
    {
        return this.level;
    }
    
    public Hero getHero()
    {
        return this.hero;
    }
    
    @Override
    public void reset(final Engine engine) throws Exception
    {
        if (this.level == null)
        {
            this.level = new Level(15, 5, 8, engine.getResources().getGameImage(GameImages.Keys.Level));
            this.level.setX(0);
            this.level.setY(0);
        }
        
        if (hero == null)
        {
            this.hero = new Human(Hero.Type.Elf);
            this.hero.setImage(engine.getResources().getGameImage(GameImages.Keys.Heroes));
            this.hero.setCol(1.5);
            this.hero.setRow(1.5);
            
            this.hero.setX(level.getCoordinateX(this.hero.getCol()));
            this.hero.setY(level.getCoordinateY(this.hero.getRow()));
        }
        
        //see what game we selected
        //final int gameSelection = engine.getMenu().getOptionSelectionIndex(LayerKey.Options, OptionKey.Game);
        
        //get the difficulty
        //final int difficultyIndex = engine.getMenu().getOptionSelectionIndex(LayerKey.Options, OptionKey.Difficulty);
        
        //get the game mode
        //final int modeIndex = engine.getMenu().getOptionSelectionIndex(LayerKey.Options, OptionKey.Mode);
        
        //the background image will be determined by multi-player
        //this.background = engine.getResources().getGameImage(options.get(engine.getRandom().nextInt(options.size())));
        //this.background = engine.getResources().getGameImage(GameImages.Keys.Cards);
    }
    
    /**
     * Free up resources
     */
    @Override
    public void dispose()
    {
        try
        {
            //recycle objects
            super.finalize();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Update all elements
     * @param engine Our game engine
     * @throws Exception 
     */
    @Override
    public void update(final Engine engine) throws Exception
    {
        if (level != null)
        {
            level.update(engine);
            
            if (level.getMaze().isGenerated())
            {
                if (hero != null)
                    hero.update(engine);
            }
        }
    }
    
    /**
     * Draw all of our application elements
     * @param graphics Graphics object used for drawing
     */
    @Override
    public void render(final Graphics graphics) throws Exception
    {
        if (level != null)
        {
            level.render(graphics);
            
            //only draw the hero if the maze has been generated
            if (level.getMaze().isGenerated())
            {
                if (hero != null)
                    hero.render(graphics);
            }
        }
        
    }
}