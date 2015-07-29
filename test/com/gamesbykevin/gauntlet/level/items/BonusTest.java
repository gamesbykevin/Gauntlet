package com.gamesbykevin.gauntlet.level.items;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Bonus unit test
 * @author GOD
 */
public class BonusTest 
{
    private Bonus bonus;
    
    public BonusTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        assertTrue(Bonus.POISON_DAMAGE == -100);
        
        assertTrue(Bonus.SCORE_TREASURE_BAG == 100);
        assertTrue(Bonus.SCORE_FOOD == 100);
        assertTrue(Bonus.SCORE_KEY == 200);
        assertTrue(Bonus.SCORE_ENEMY == 20);
        assertTrue(Bonus.SCORE_ENEMY_GENERATOR == 75);
        assertTrue(Bonus.SCORE_TREASURE_CHEST == 500);
        
        for (Bonus.Type type : Bonus.Type.values())
        {
            Bonus bonus = new Bonus(type);
        }
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        
    }
    
    @Before
    public void setUp() throws Exception
    {
        bonus = new Bonus(Bonus.Type.Food);
    }
    
    @After
    public void tearDown() 
    {
        bonus.dispose();
    }
    
    @Test
    public void setTypeTest() throws Exception
    {
        for (Bonus.Type type : Bonus.Type.values())
        {
            bonus.setType(type);
        }
    }
    
    @Test
    public void getTypeTest() throws Exception
    {
        for (Bonus.Type type : Bonus.Type.values())
        {
            bonus.setType(type);
            
            assertNotNull(bonus.getType());
            assertEquals(bonus.getType(), type);
        }
    }
    
    @Test
    public void assignAnimationTest() throws Exception
    {
        bonus.assignAnimation();
    }
}