package com.gamesbykevin.gauntlet.entity;

import com.gamesbykevin.gauntlet.level.Tile;
import com.gamesbykevin.gauntlet.resources.ResourcesTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Entity unit test
 * @author GOD
 */
public class EntityTest extends Entity
{
    EntityTest test;
    
    public EntityTest() throws Exception
    {
        super();
        
        addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 16, 16);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        EntityTest test = new EntityTest();
        
        assertEquals(DEFAULT_ANIMATION_KEY, "Default");
        assertTrue(DELAY_NONE == 0);
        assertTrue(HEALTH_MINIMUM == 0);
        assertTrue(DAMAGE_DEFAULT == 1);
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        EntityTest test = new EntityTest();
        test.dispose();
    }
    
    @Before
    public void setUp() throws Exception
    {
        test = new EntityTest();
    }
    
    @After
    public void tearDown() 
    {
        test.dispose();
    }
    
    @Test
    public void setHealthTest() 
    {
        for (int i = -10; i < 20; i++)
        {
            test.setHealth(i);
        }
    }
    
    @Test
    public void getHealthTest() 
    {
        for (int i = -10; i < 20; i++)
        {
            test.setHealth(i);
            
            if (i < HEALTH_MINIMUM)
            {
                assertTrue(test.getHealth() == HEALTH_MINIMUM);
            }
            else
            {
                assertTrue(test.getHealth() == i);
            }
        }
    }
    
    @Test
    public void setDamageTest()
    {
        for (int i = 0; i < 2000; i++)
        {
            test.setDamage(i);
        }
    }
    
    @Test
    public void getDamageTest()
    {
        for (int i = 0; i < 2000; i++)
        {
            test.setDamage(i);
            assertTrue(test.getDamage() == i);
        }
    }
    
    @Test
    public void setHiddenTest()
    {
        test.setHidden(true);
        test.setHidden(false);
    }
    
    @Test
    public void isHiddenTest()
    {
        assertFalse(test.isHidden());
        test.setHidden(true);
        assertTrue(test.isHidden());
        test.setHidden(false);
        assertFalse(test.isHidden());
    }
    
    @Test
    public void resetAnimationTest() throws Exception
    {
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        test.resetAnimation();
    }
    
    @Test
    public void hasAnimationTest() throws Exception
    {
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        assertTrue(test.hasAnimation(DEFAULT_ANIMATION_KEY));
    }
    
    @Test
    public void isAnimationTest() throws Exception
    {
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        assertTrue(test.isAnimation(DEFAULT_ANIMATION_KEY));
        
        final String DEFAULT_OTHER_KEY = "DEFAULT_OTHER_KEY";
        test.addAnimation(DEFAULT_OTHER_KEY, 0, 0, 1, 1);
        assertFalse(test.isAnimation(DEFAULT_OTHER_KEY));
        test.setAnimation(DEFAULT_OTHER_KEY);
        assertTrue(test.isAnimation(DEFAULT_OTHER_KEY));
    }
    
    @Test
    public void setAnimationTest() throws Exception
    {
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        final String DEFAULT_OTHER_KEY = "DEFAULT_OTHER_KEY";
        test.addAnimation(DEFAULT_OTHER_KEY, 0, 0, 1, 1);
        test.setAnimation(DEFAULT_ANIMATION_KEY);
        test.setAnimation(DEFAULT_OTHER_KEY);
    }
    
    @Test
    public void addAnimationTest() throws Exception
    {
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        final String DEFAULT_OTHER_KEY = "DEFAULT_OTHER_KEY";
        test.addAnimation(DEFAULT_OTHER_KEY, 0, 0, 1, 1);
        test.setAnimation(DEFAULT_ANIMATION_KEY);
        test.setAnimation(DEFAULT_OTHER_KEY);
    }
    
    @Test
    public void updateAnimationTest() throws Exception
    {
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        test.updateAnimation(DELAY_NONE);
        test.updateAnimation(1);
    }
    
    @Test
    public void hasCollisionTest() throws Exception
    {
        test.setCol(0);
        test.setRow(0);
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        assertFalse(test.hasCollision(test));
        
        EntityTest test1 = new EntityTest();
        test1.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        test1.setCol(10);
        test1.setRow(10);
        assertFalse(test.hasCollision(test1));
        test1.setCol(0.5);
        test1.setRow(0.5);
        assertTrue(test.hasCollision(test1));
    }
    
    @Test
    public void getCollisionDistanceTest() throws Exception
    {
        test.setCol(0);
        test.setRow(0);
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        
        assertTrue(test.getCollisionDistance() == (test.getSpriteSheet().getLocation().getWidth() / Tile.DEFAULT_DIMENSION));
    }
    
    @Test
    public void renderTest() throws Exception
    {
        test.addAnimation(DEFAULT_ANIMATION_KEY, 0, 0, 100, 100);
        test.setDimensions();
        
        test.setImage(ResourcesTest.TEST_IMAGE);
        test.render(ResourcesTest.TEST_IMAGE.createGraphics());
        
        test.setImage(null);
        test.render(ResourcesTest.TEST_IMAGE.createGraphics(), ResourcesTest.TEST_IMAGE);
    }
}