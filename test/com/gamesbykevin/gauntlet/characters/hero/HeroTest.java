package com.gamesbykevin.gauntlet.characters.hero;

import com.gamesbykevin.framework.util.Timers;
import com.gamesbykevin.gauntlet.engine.Engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Hero unit test
 * @author GOD
 */
public class HeroTest extends Hero
{
    public HeroTest() throws Exception
    {
        super(Type.Elf);
    }
    
    @Override
    public void update(final Engine engine) throws Exception
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        assertTrue(Hero.EXIT_DISTANCE_CHECK == .35);
        assertTrue(Hero.DAMAGE_NORMAL == 2);
        assertTrue(Hero.DAMAGE_EXTRA == 3);
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
        super.dispose();
    }
    
    @Test
    public void getStatusTest() 
    {
        assertNotNull(super.getStatus());
    }
    
    @Test
    public void setHealthTest()
    {
        assertTrue(super.getHealth() > 0);
        super.setHealth(100);
        assertTrue(super.getHealth() > 0);
        assertTrue(super.getHealth() == 100);
        super.setHealth(0);
        assertFalse(super.getHealth() > 0);
        assertFalse(super.getHealth() == 100);
    }
    
    @Test
    public void getTimerHealthTest()
    {
        assertNotNull(super.getTimerHealth());
        assertTrue(super.getTimerHealth().getReset() > 0);
    }
    
    @Test
    public void hasReflectiveShotTest()
    {
        assertFalse(super.hasReflectiveShot());
        super.getTimerReflectiveShot().reset();
        assertTrue(super.hasReflectiveShot());
    }
    
    @Test
    public void hasPowerShotTest()
    {
        assertFalse(super.hasPowerShot());
        super.getTimerPowerShot().reset();
        assertTrue(super.hasPowerShot());
    }
    
    @Test
    public void getTimerReflectiveShotTest()
    {
        assertTrue(super.getTimerReflectiveShot().hasTimePassed());
        super.getTimerReflectiveShot().reset();
        assertFalse(super.getTimerReflectiveShot().hasTimePassed());
    }
    
    @Test
    public void getTimerPowerShotTest()
    {
        assertTrue(super.getTimerPowerShot().hasTimePassed());
        super.getTimerPowerShot().reset();
        assertFalse(super.getTimerPowerShot().hasTimePassed());
    }
    
    @Test
    public void addScoreTest()
    {
        assertTrue(super.getScore() == 0);
        super.addScore(100);
        assertTrue(super.getScore() == 100);
        super.addScore(100);
        assertTrue(super.getScore() == 200);
    }
    
    @Test
    public void getScoreTest()
    {
        assertTrue(super.getScore() == 0);
        
        int count = 0;
        
        for (int i = 0; i < 100; i++)
        {
            count += i;
            
            super.addScore(i);
            assertTrue(super.getScore() == count);
        }
    }
    
    @Test
    public void addPotionTest()
    {
        assertFalse(super.hasPotion());
        super.addPotion();
        assertTrue(super.hasPotion());
    }
    
    @Test
    public void hasPotionTest()
    {
        assertFalse(super.hasPotion());
        super.addPotion();
        assertTrue(super.hasPotion());
    }
    
    @Test
    public void removePotionTest()
    {
        assertFalse(super.hasPotion());
        super.addPotion();
        assertTrue(super.hasPotion());
        super.removePotion();
        assertFalse(super.hasPotion());
        super.addPotion();
        super.addPotion();
        super.removePotion();
        assertTrue(super.hasPotion());
    }
    
    @Test
    public void addKeyTest()
    {
        assertFalse(super.hasKey());
        super.addKey();
        assertTrue(super.hasKey());
    }
    
    @Test
    public void hasKeyTest()
    {
        assertFalse(super.hasKey());
        super.addKey();
        assertTrue(super.hasKey());
    }
    
    @Test
    public void removeKeyTest()
    {
        assertFalse(super.hasKey());
        super.addKey();
        assertTrue(super.hasKey());
        super.removeKey();
        assertFalse(super.hasKey());
        super.addKey();
        super.addKey();
        super.removeKey();
        assertTrue(super.hasKey());
    }
}