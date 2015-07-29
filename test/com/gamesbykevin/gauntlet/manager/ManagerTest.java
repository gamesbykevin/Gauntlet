package com.gamesbykevin.gauntlet.manager;

import com.gamesbykevin.gauntlet.menu.CustomMenu;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.main.MainTest;
import com.gamesbykevin.gauntlet.resources.ResourcesTest;
import java.awt.Rectangle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Manager unit test
 * @author GOD
 */
public class ManagerTest 
{
    private Manager manager;
    private Engine engine;
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        Engine engine = new Engine(new Rectangle(0,0,1,1), 1);
        
        //continue loop until resources have been loaded
        while(engine.getResources() == null || engine.getResources().isLoading())
        {
            engine.update(MainTest.MAIN);
            
            if (engine.getMenu() != null)
            {
                engine.getMenu().setLayer(CustomMenu.LayerKey.GameStart);
            }
        }
        
        Manager manager = new Manager(engine);
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        Engine engine = new Engine(new Rectangle(0,0,1,1), 1);
        
        //continue loop until resources have been loaded
        while(engine.getResources() == null || engine.getResources().isLoading())
        {
            engine.update(MainTest.MAIN);
            
            if (engine.getMenu() != null)
            {
                engine.getMenu().setLayer(CustomMenu.LayerKey.GameStart);
            }
        }
        
        Manager manager = new Manager(engine);
        engine.dispose();
        manager.dispose();
    }
    
    @Before
    public void setUp() throws Exception
    {
        engine = new Engine(new Rectangle(0,0,1,1), 1);
        
        //continue loop until resources have been loaded
        while(engine.getResources() == null || engine.getResources().isLoading())
        {
            engine.update(MainTest.MAIN);
            
            if (engine.getMenu() != null)
            {
                engine.getMenu().setLayer(CustomMenu.LayerKey.GameStart);
            }
        }
        
        manager = new Manager(engine);
    }
    
    @After
    public void tearDown() 
    {
        manager.dispose();
    }
    
    @Test
    public void resetTest() throws Exception
    {
        manager.reset(engine);
    }
    
    @Test
    public void updateTest() throws Exception
    {
        manager.update(engine);
    }
    
    @Test
    public void renderTest() throws Exception
    {
        manager.render(ResourcesTest.TEST_IMAGE.createGraphics());
    }
}