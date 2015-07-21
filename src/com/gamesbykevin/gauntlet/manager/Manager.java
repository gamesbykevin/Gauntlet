package com.gamesbykevin.gauntlet.manager;

import com.gamesbykevin.gauntlet.characters.hero.Heroes;
import com.gamesbykevin.gauntlet.characters.enemy.Enemies;
import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.characters.CharacterHelper;
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
    //the current level in play
    private Level level;
    
    //our hero container
    private Heroes heroes;
    
    //enemy container
    private Enemies enemies;
    
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
    
    public Heroes getHeroes()
    {
        return this.heroes;
    }
    
    public Enemies getEnemies()
    {
        return this.enemies;
    }
    
    @Override
    public void reset(final Engine engine) throws Exception
    {
        if (getLevel() == null)
        {
            int roomDimensions = 3;
            
            if (roomDimensions < Level.MIN_ROOM_DIMENSIONS)
                roomDimensions = Level.MIN_ROOM_DIMENSIONS;
            
            this.level = new Level(9, 9, roomDimensions, engine.getResources().getGameImage(GameImages.Keys.Level));
            this.level.setX(0);
            this.level.setY(0);
        }
        
        if (getHeroes() == null)
        {
            this.heroes = new Heroes(engine.getResources().getGameImage(GameImages.Keys.Heroes));
            getHeroes().add(1.5, 1.5, Character.Type.Elf);
        }
        
        if (getEnemies() == null)
        {
            this.enemies = new Enemies(engine.getResources().getGameImage(GameImages.Keys.Enemies));
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
            if (level != null)
            {
                level.dispose();
                level = null;
            }
            
            if (enemies != null)
            {
                enemies.dispose();
                enemies = null;
            }
            
            if (heroes != null)
            {
                heroes.dispose();
                heroes = null;
            }
            
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
        if (getLevel() != null)
        {
            //has the maze been generated
            boolean generated = getLevel().getMaze().isGenerated();
            
            //update the level
            getLevel().update(engine);
            
            //the level must be created before we update anything else
            if (getLevel().getMaze().isGenerated())
            {
                //if the level previously wasn't created
                if (!generated)
                    getEnemies().spawn(getLevel(), engine.getRandom());
                
                if (getHeroes() != null)
                    getHeroes().update(engine);
                if (getEnemies() != null)
                    getEnemies().update(engine);
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
        if (getLevel() != null)
        {
            //render the level
            getLevel().render(graphics);
            
            //only these if the maze has been generated
            if (getLevel().getMaze().isGenerated())
            {
                if (getEnemies() != null)
                    getEnemies().render(graphics);
                if (getHeroes() != null)
                    getHeroes().render(graphics);
            }
        }
    }
}