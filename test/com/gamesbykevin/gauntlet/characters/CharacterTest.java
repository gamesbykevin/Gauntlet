package com.gamesbykevin.gauntlet.characters;

import com.gamesbykevin.framework.util.Timers;

import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.engine.EngineTest;
import com.gamesbykevin.gauntlet.projectile.Projectile;
import com.gamesbykevin.gauntlet.resources.ResourcesTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Character unit test
 * @author GOD
 */
public class CharacterTest extends Character
{
    private EngineTest engineTest;
    
    public CharacterTest() throws Exception
    {
        super(Type.Blob);
        
        //create new engine
        engineTest = new EngineTest();
        
        super.setWidth(1);
        super.setHeight(1);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        assertTrue(Character.DEFAULT_DIMENSION == 16);
        assertTrue(Character.DEFAULT_DELAY == Timers.toNanoSeconds(250L));
        
        assertTrue(Character.DELAY_PROJECTILE == Timers.toNanoSeconds(250L));
        assertTrue(Character.DELAY_PROJECTILE_SLOW == Timers.toNanoSeconds(350L));
        assertTrue(Character.DELAY_PROJECTILE_SLOWEST == Timers.toNanoSeconds(500L));
        
        
        assertTrue(Character.DEFAULT_PROJECTILE_LIMIT == 2);
        assertTrue(Character.SIZE_RATIO == 1.5);
        
        assertTrue(Character.PROJECTILE_SPEED_RATIO == 2.0);
        assertTrue(Character.PROJECTILE_SPEED_RATIO_FASTER == 2.5);
        assertTrue(Character.PROJECTILE_SPEED_RATIO_FASTEST == 3.0);
        
        assertTrue(Character.DEFAULT_SPEED == 0.10000);
        
        assertTrue(Character.FASTEST_SPEED == 0.115000);
        assertTrue(Character.FASTER_SPEED == 0.105000);
        assertTrue(Character.SLOW_SPEED == 0.07500);
        
        for (Character.Type type : Character.Type.values())
        {
            CharacterTest test = new CharacterTest();
            CharacterChildTest tmp = new CharacterChildTest(type);
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        for (Character.Type type : Character.Type.values())
        {
            CharacterTest test = new CharacterTest();
            test.dispose();
            
            CharacterChildTest tmp = new CharacterChildTest(type);
            tmp.dispose();
        }
    }
    
    @Before
    public void setUp() throws Exception
    {
        for (Character.Type type : Character.Type.values())
        {
            CharacterTest character = new CharacterTest();
            CharacterChildTest tmp = new CharacterChildTest(type);
        }
    }
    
    @After
    public void tearDown() throws Exception
    {
        super.dispose();
        
        for (Character.Type type : Character.Type.values())
        {
            CharacterTest character = null;
            assertNull(character);
            character = new CharacterTest();
            assertNotNull(character);
            character.dispose();
            character = null;
            assertNull(character);
            
            CharacterChildTest tmp = null;
            assertNull(tmp);
            tmp = new CharacterChildTest(type);
            assertNotNull(tmp);
            tmp.dispose();
            tmp = null;
            assertNull(tmp);
        }
    }
    
    @Test
    public void setTimerProjectileTest() 
    {
        setTimerProjectile(Character.DELAY_PROJECTILE);
        setTimerProjectile(Character.DELAY_PROJECTILE);
    }
    
    @Test
    public void getTimerProjectileTest() 
    {
        assertNotNull(super.getTimerProjectile());
        setTimerProjectile(Character.DELAY_PROJECTILE);
        assertNotNull(super.getTimerProjectile());
        assertTrue(super.getTimerProjectile().getReset() == Character.DELAY_PROJECTILE);
        setTimerProjectile(Character.DELAY_NONE);
        assertTrue(super.getTimerProjectile().getReset() == Character.DELAY_NONE);
    }
    
    @Test
    public void getTypeTest() throws Exception
    {
        for (Character.Type type : Character.Type.values())
        {
            assertEquals(new CharacterChildTest(type).getType(), type);
        }
    }
    
    @Test
    public void disposeTest()
    {
        super.dispose();
    }
    
    @Test
    public void setProjectileSpeedRatioTest()
    {
        super.setProjectileSpeedRatio(SIZE_RATIO);
        super.setProjectileSpeedRatio(0);
        super.setProjectileSpeedRatio(Character.PROJECTILE_SPEED_RATIO);
        super.setProjectileSpeedRatio(Character.PROJECTILE_SPEED_RATIO_FASTER);
        super.setProjectileSpeedRatio(Character.PROJECTILE_SPEED_RATIO_FASTEST);
    }
    
    @Test
    public void getSpeedTest()
    {
        assertTrue(getSpeed() == DEFAULT_SPEED);
    }
    
    @Test
    public void setSpeedTest()
    {
        assertTrue(getSpeed() == DEFAULT_SPEED);
        super.setSpeed(0);
        assertFalse(getSpeed() == DEFAULT_SPEED);
        assertTrue(getSpeed() == 0);
    }
    
    @Test
    public void isDeadTest()
    {
        assertTrue(super.isDead());
        super.setHealth(1);
        assertFalse(super.isDead());
        super.setHealth(0);
        assertTrue(super.isDead());
    }
    
    @Test
    public void setProjectileLimitTest()
    {
        for (int i = 0; i < 11; i++)
        {
            super.setProjectileLimit(i);
        }
    }
    
    @Test
    public void removeProjectilesTest()
    {
        super.removeProjectiles();
    }
    
    @Test
    public void addProjectileTest() throws Exception
    {
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            super.getSpriteSheet().remove(value);
        }
        
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            super.getSpriteSheet().add(0, 0, 1, 1, DELAY_NONE, value);
        }
        
        //set the limit
        super.setProjectileLimit(Projectile.Facing.values().length);
        
        super.removeProjectiles();
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertTrue(super.addProjectile(value));
        }
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertFalse(super.addProjectile(value));
        }
        
        super.removeProjectiles();
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertTrue(super.addProjectile(value, true, false));
        }
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertFalse(super.addProjectile(value, true, false));
        }
        
        super.removeProjectiles();
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertTrue(super.addProjectile(value, false, true));
        }
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertFalse(super.addProjectile(value, false, true));
        }
        
        super.removeProjectiles();
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertTrue(super.addProjectile(value, false, false));
        }
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertFalse(super.addProjectile(value, false, false));
        }
        
        super.removeProjectiles();
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertTrue(super.addProjectile(value, true, true));
        }
        for (Projectile.Facing value : Projectile.Facing.values())
        {
            assertFalse(super.addProjectile(value, true, true));
        }
    }
    
    @Test
    public void renderTest() throws Exception
    {
        super.render(ResourcesTest.TEST_IMAGE.createGraphics(), ResourcesTest.TEST_IMAGE);
    }
    
    @Override
    public void update(final Engine engine) throws Exception
    {
        super.updateCommon(engine);
    }
    
    public static class CharacterChildTest extends Character
    {
        protected CharacterChildTest(final Type type) throws Exception
        {
            super(type);
        }
        
        @Override
        public void update(final Engine engine) throws Exception
        {
            super.updateCommon(engine);
        }
    }
}