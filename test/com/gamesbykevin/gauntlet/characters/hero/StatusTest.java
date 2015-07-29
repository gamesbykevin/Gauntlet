package com.gamesbykevin.gauntlet.characters.hero;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Status unit test
 * @author GOD
 */
public class StatusTest 
{
    private Status status;
    
    @BeforeClass
    public static void setUpClass() 
    {
        Status status = new Status(512, 512);
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        Status status = new Status(512, 512);
        status.dispose();
    }
    
    @Before
    public void setUp() 
    {
        status = new Status(512, 512);
    }
    
    @After
    public void tearDown() 
    {
        status.dispose();
    }
    
    @Test
    public void setHealthTest() 
    {
        for (int health = 0; health <= 100; health++)
        {
            status.setHealth(health);
            status.render();
        }
    }
    
    @Test
    public void setKeysTest() 
    {
        for (int keys = 0; keys <= 100; keys++)
        {
            status.setKeys(keys);
            status.render();
        }
    }
    
    @Test
    public void setPotionsTest() 
    {
        for (int potions = 0; potions <= 100; potions++)
        {
            status.setPotions(potions);
            status.render();
        }
    }
    
    @Test
    public void setScoreTest() 
    {
        for (int score = 0; score <= 100; score++)
        {
            status.setScore(score);
            status.render();
        }
    }
    
    @Test
    public void renderTest() 
    {
        for (int i = 0; i < 1000; i++)
        {
            status.render();
        }
    }
}