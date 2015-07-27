package com.gamesbykevin.gauntlet.characters.enemy;

import com.gamesbykevin.framework.util.Timers;
import com.gamesbykevin.gauntlet.characters.Character.Type;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

/**
 * Enemy unit test
 * @author GOD
 */
public class EnemyTest 
{
    public static final Random RANDOM = new Random();
    
    private Enemy enemy;
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        assertTrue(Enemy.DEFAULT_ENEMY_SPEED == 0.015);
        assertTrue(Enemy.ENEMY_SPEED_SLOW == 0.010);
        assertTrue(Enemy.ENEMY_SPEED_SLOWER == 0.0075);
        assertTrue(Enemy.DELAY_HURT == Timers.toNanoSeconds(750L));
        assertTrue(Enemy.DELAY_COLLISION == Timers.toNanoSeconds(750L));
        assertTrue(Enemy.SPAWN_DELAY == Timers.toNanoSeconds(3000L));
        
        Enemy enemy;
        
        enemy = new Enemy(Type.Blob, RANDOM);
        enemy = new Enemy(Type.Death, RANDOM);
        enemy = new Enemy(Type.Demon, RANDOM);
        enemy = new Enemy(Type.EnemyGenerator1, RANDOM);
        enemy = new Enemy(Type.EnemyGenerator2, RANDOM);
        enemy = new Enemy(Type.Ghost, RANDOM);
        enemy = new Enemy(Type.Grunt, RANDOM);
        enemy = new Enemy(Type.It, RANDOM);
        enemy = new Enemy(Type.Lobber, RANDOM);
        enemy = new Enemy(Type.Sorcerer, RANDOM);
        enemy = new Enemy(Type.SuperSorcerer, RANDOM);
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        Enemy enemy = new Enemy(Type.Blob, RANDOM);
        enemy.dispose();
    }
    
    @Before
    public void setUp() throws Exception
    {
        enemy = new Enemy(Type.Blob, RANDOM);
    }
    
    @After
    public void tearDown() 
    {
        enemy.dispose();
    }
    
    @Test
    public void disposeTest() 
    {
        enemy.dispose();
    }
    
    @Test
    public void setTimerHurtTest() 
    {
        enemy.setTimerHurt(Enemy.DELAY_HURT);
    }
    
    @Test
    public void getTimerHurtTest() 
    {
        assertNotNull(enemy.getTimerHurt());
        enemy.setTimerHurt(Enemy.DELAY_HURT);
        assertTrue(enemy.getTimerHurt().getReset() == Enemy.DELAY_HURT);
        enemy.setTimerHurt(Enemy.DELAY_NONE);
        assertTrue(enemy.getTimerHurt().getReset() == Enemy.DELAY_NONE);
    }
    
    @Test
    public void setTimerCollisionTest() 
    {
        enemy.setTimerCollision(Enemy.DELAY_HURT);
    }
    
    @Test
    public void getTimerCollisionTest() 
    {
        assertNotNull(enemy.getTimerCollision());
        enemy.setTimerCollision(Enemy.DELAY_HURT);
        assertTrue(enemy.getTimerCollision().getReset() == Enemy.DELAY_HURT);
        enemy.setTimerCollision(Enemy.DELAY_NONE);
        assertTrue(enemy.getTimerCollision().getReset() == Enemy.DELAY_NONE);
    }
    
    @Test
    public void hasTargetTest() throws Exception
    {
        Enemy tmp = new Enemy(Type.EnemyGenerator2, RANDOM);
        assertFalse(enemy.hasTarget(tmp.getId()));
        enemy.assignTarget(tmp.getId());
        assertTrue(enemy.hasTarget(tmp.getId()));
    }
    
    @Test
    public void assignTargetTest() throws Exception
    {
        Enemy tmp = new Enemy(Type.EnemyGenerator2, RANDOM);
        
        tmp.assignTarget(enemy.getId());
        enemy.assignTarget(tmp.getId());
    }
    
    @Test
    public void getTargetTest() throws Exception
    {
        Enemy tmp = new Enemy(Type.EnemyGenerator2, RANDOM);
        assertNull(enemy.getTarget());
        assertEquals(enemy.getTarget(), null);
        enemy.assignTarget(tmp.getId());
        assertEquals(enemy.getTarget(), tmp.getId());
    }
}