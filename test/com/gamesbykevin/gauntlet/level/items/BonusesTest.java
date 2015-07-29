package com.gamesbykevin.gauntlet.level.items;

import com.gamesbykevin.framework.base.Cell;

import com.gamesbykevin.gauntlet.characters.enemy.EnemyTest;
import com.gamesbykevin.gauntlet.engine.EngineTest;
import com.gamesbykevin.gauntlet.entity.EntityTest;
import com.gamesbykevin.gauntlet.level.LevelTest;
import com.gamesbykevin.gauntlet.resources.ResourcesTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Bonuses unit test
 * @author GOD
 */
public class BonusesTest 
{
    private Bonuses bonuses;
    
    public BonusesTest() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        Bonuses bonuses = new Bonuses(ResourcesTest.TEST_IMAGE);
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        Bonuses bonuses = new Bonuses(ResourcesTest.TEST_IMAGE);
        bonuses.dispose();
    }
    
    @Before
    public void setUp() 
    {
        bonuses = new Bonuses(ResourcesTest.TEST_IMAGE);
    }
    
    @After
    public void tearDown() 
    {
        bonuses.dispose();
    }
    
    @Test
    public void resetTest() 
    {
        bonuses.reset();
    }
    
    @Test
    public void hasCollisionTest() throws Exception
    {
        EntityTest test = new EntityTest();
        
        assertFalse(bonuses.hasCollision(test));
        
        test.setCol(1.5);
        test.setRow(1.5);
        
        bonuses.add(1, 1, Bonus.Type.Food);
        assertTrue(bonuses.hasCollision(test));
    }
    
    @Test
    public void getBonusTest() throws Exception
    {
        EntityTest test = new EntityTest();
        test.setCol(1.5);
        test.setRow(1.5);
        assertNull(bonuses.getBonus(test));
        
        bonuses.add(1, 1, Bonus.Type.Food);
        assertNotNull(bonuses.getBonus(test));
    }
    
    @Test
    public void spawnTest() throws Exception
    {
        LevelTest level = new LevelTest();
        
        bonuses.reset();
        bonuses.spawn(level.getLevel(), EnemyTest.RANDOM);
        bonuses.reset();
    }
    
    @Test
    public void addTest() throws Exception
    {
        for (Bonus.Type type : Bonus.Type.values())
        {
            final int col = EnemyTest.RANDOM.nextInt(100);
            final int row = EnemyTest.RANDOM.nextInt(100);
            
            Cell cell = new Cell(col, row);
            
            bonuses.add(cell, type);
            bonuses.add(col, row, type);
        }
    }
    
    @Test
    public void removeTest() throws Exception
    {
        LevelTest level = new LevelTest();
        bonuses.spawn(level.getLevel(), EnemyTest.RANDOM);
        
        EntityTest test = new EntityTest();
        
        bonuses.remove(test);
    }
    
    @Test
    public void renderTest() throws Exception
    {
        LevelTest level = new LevelTest();
        bonuses.spawn(level.getLevel(), EnemyTest.RANDOM);
        bonuses.render(ResourcesTest.TEST_IMAGE.createGraphics());
    }
}