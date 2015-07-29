package com.gamesbykevin.gauntlet.level;

import com.gamesbykevin.gauntlet.level.Tile.Type;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Level Helper unit test
 * @author GOD
 */
public class LevelHellperTest 
{
    @BeforeClass
    public static void setUpClass() 
    {
        
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        
    }
    
    @Before
    public void setUp() 
    {
        
    }
    
    @After
    public void tearDown() 
    {
        
    }
    
    @Test
    public void createTilesTest() throws Exception
    {
        LevelTest level = new LevelTest();
        
        LevelHelper.createTiles(level.getLevel(), Type.Wall1);
        LevelHelper.createTiles(level.getLevel(), Type.Wall2);
        LevelHelper.createTiles(level.getLevel(), Type.Wall3);
        LevelHelper.createTiles(level.getLevel(), Type.Wall4);
        LevelHelper.createTiles(level.getLevel(), Type.Wall5);
        LevelHelper.createTiles(level.getLevel(), Type.Wall6);
        LevelHelper.createTiles(level.getLevel(), Type.Wall7);
        LevelHelper.createTiles(level.getLevel(), Type.Wall8);
        LevelHelper.createTiles(level.getLevel(), Type.Wall9);
        LevelHelper.createTiles(level.getLevel(), Type.Wall10);
    }
    
    @Test
    public void unlockDoorTest() throws Exception
    {
        LevelTest level = new LevelTest();
        
        for (int col = 0; col < level.getLevel().getMaze().getCols(); col++)
        {
            for (int row = 0; row < level.getLevel().getMaze().getRows(); row++)
            {
                LevelHelper.unlockDoor(level.getLevel(), level.getLevel().getTile(col, row));
            }
        }
    }
}