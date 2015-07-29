package com.gamesbykevin.gauntlet.projectile;

import com.gamesbykevin.gauntlet.resources.ResourcesTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Rectangle;

/**
 * Projectile unit test
 * @author GOD
 */
public class ProjectileTest 
{
    private Projectile projectile;
    
    public ProjectileTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        for (Projectile.Facing tmp : Projectile.Facing.values())
        {
            Projectile projectile = new Projectile(tmp, .3, new Rectangle(0,0,8,8));
            projectile = new Projectile(tmp, .3, 0, 0, 8, 8);
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        for (Projectile.Facing tmp : Projectile.Facing.values())
        {
            Projectile projectile = new Projectile(tmp, .3, new Rectangle(0,0,8,8));
            projectile.dispose();
            
            projectile = new Projectile(tmp, .3, 0, 0, 8, 8);
            projectile.dispose();
        }
    }
    
    @Before
    public void setUp() throws Exception
    {
        projectile = new Projectile(Projectile.Facing.N, .3, new Rectangle(0,0,8,8));
    }
    
    @After
    public void tearDown() 
    {
        projectile.dispose();
    }
    
    @Test
    public void setReflectiveTest() 
    {
        projectile.setReflective(true);
        projectile.setReflective(false);
    }
    
    @Test
    public void isReflectiveTest() 
    {
        projectile.setReflective(true);
        assertTrue(projectile.isReflective());
        projectile.setReflective(false);
        assertFalse(projectile.isReflective());
    }
    
    @Test
    public void setPowerTest() 
    {
        projectile.setPower(true);
        projectile.setPower(false);
    }
    
    @Test
    public void hasPowerTest() 
    {
        projectile.setPower(true);
        assertTrue(projectile.hasPower());
        projectile.setPower(false);
        assertFalse(projectile.hasPower());
    }
    
    @Test
    public void setDeadTest() 
    {
        projectile.setDead(true);
        projectile.setDead(false);
    }
    
    @Test
    public void isDeadTest() 
    {
        projectile.setDead(true);
        assertTrue(projectile.isDead());
        projectile.setDead(false);
        assertFalse(projectile.isDead());
    }
    
    @Test
    public void renderTest() throws Exception
    {
        projectile.render(ResourcesTest.TEST_IMAGE.createGraphics(), ResourcesTest.TEST_IMAGE);
    }
}