package com.gamesbykevin.gauntlet.characters.enemy;

import com.gamesbykevin.gauntlet.characters.Character.Type;
import static com.gamesbykevin.gauntlet.resources.ResourcesTest.TEST_IMAGE;
import static com.gamesbykevin.gauntlet.characters.enemy.EnemyTest.RANDOM;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Enemies unit test
 * @author GOD
 */
public class EnemiesTest 
{
    private Enemies enemies;
    
    @BeforeClass
    public static void setUpClass() 
    {
        Enemies enemies = new Enemies(TEST_IMAGE);
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        Enemies enemies = new Enemies(TEST_IMAGE);
        enemies.dispose();
    }
    
    @Before
    public void setUp() 
    {
        enemies = new Enemies(TEST_IMAGE);
    }
    
    @After
    public void tearDown() 
    {
        enemies = new Enemies(TEST_IMAGE);
        enemies.dispose();
    }
    
    @Test
    public void resetTest() throws Exception
    {
        enemies = new Enemies(TEST_IMAGE);
        enemies.reset();
        enemies.add(0, 0, Type.Blob);
        enemies.add(0, 0, Type.Death);
        enemies.add(0, 0, Type.Demon);
        enemies.add(0, 0, Type.EnemyGenerator1);
        enemies.add(0, 0, Type.EnemyGenerator2);
        enemies.add(0, 0, Type.Ghost);
        enemies.add(0, 0, Type.Grunt);
        enemies.add(0, 0, Type.It);
        enemies.add(0, 0, Type.Lobber);
        enemies.add(0, 0, Type.Sorcerer);
        enemies.add(0, 0, Type.SuperSorcerer);
        enemies.reset();
    }
    
    @Test
    public void spawnTest() throws Exception
    {
        enemies = new Enemies(TEST_IMAGE);
    }
    
    @Test
    public void removeEnemiesTest() throws Exception
    {
        enemies = new Enemies(TEST_IMAGE);
    }
    
    @Test
    public void addTest() throws Exception
    {
        enemies = new Enemies(TEST_IMAGE);
        //enemies.spawn(LEVEL_OBJECT, RANDOM);
        
        for (int index = 0; index < 1001; index++)
        {
            double col = RANDOM.nextFloat() * 100;
            double row = RANDOM.nextFloat() * 100;
            
            enemies.add(col, row, Type.Blob);
            enemies.add(col, row, Type.Death);
            enemies.add(col, row, Type.Demon);
            enemies.add(col, row, Type.EnemyGenerator1);
            enemies.add(col, row, Type.EnemyGenerator2);
            enemies.add(col, row, Type.Ghost);
            enemies.add(col, row, Type.Grunt);
            enemies.add(col, row, Type.It);
            enemies.add(col, row, Type.Lobber);
            enemies.add(col, row, Type.Sorcerer);
            enemies.add(col, row, Type.SuperSorcerer);
        }
    }
}