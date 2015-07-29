package com.gamesbykevin.gauntlet.characters.hero;

import com.gamesbykevin.gauntlet.characters.Character.Type;
import com.gamesbykevin.gauntlet.characters.enemy.EnemyTest;
import com.gamesbykevin.gauntlet.resources.ResourcesTest;
import java.awt.Graphics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Heroes unit test
 * @author GOD
 */
public class HeroesTest 
{
    private Heroes heroes;
    
    @BeforeClass
    public static void setUpClass()
    {
        Heroes heroes = new Heroes(ResourcesTest.TEST_IMAGE);
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        Heroes heroes = new Heroes(ResourcesTest.TEST_IMAGE);
        heroes.dispose();
    }
    
    @Before
    public void setUp() 
    {
        heroes = new Heroes(ResourcesTest.TEST_IMAGE);
    }
    
    @After
    public void tearDown() 
    {
        heroes.dispose();
    }
    
    @Test
    public void resetTest() throws Exception
    {
        heroes.reset();
        heroes.add(Type.Elf, 10);
        heroes.add(Type.Valkyrie, 15);
        heroes.add(Type.Warrior, 30);
        heroes.add(Type.Wizard, 10);
        heroes.add(1.5, 1.5, Type.Elf);
        heroes.add(1.5, 1.5, Type.Valkyrie);
        heroes.add(1.5, 1.5, Type.Warrior);
        heroes.add(1.5, 1.5, Type.Wizard);
        heroes.reset();
    }
    
    @Test
    public void getClosestTest() throws Exception
    {
        HeroTest test = new HeroTest();
        test.setCol(1.5);
        test.setRow(1.5);
        
        assertNull(heroes.getClosest(test));
        
        heroes.add(2.0, 2.0, Type.Warrior);
        assertNotNull(heroes.getClosest(test));
        test.setCol(10);
        test.setRow(10);
        assertNotNull(heroes.getClosest(test));
        heroes.reset();
        assertNotNull(heroes.getClosest(test));
    }
    
    @Test
    public void addTest() throws Exception
    {
        heroes.add(Type.Elf, EnemyTest.RANDOM.nextInt(100));
        heroes.add(Type.Valkyrie, EnemyTest.RANDOM.nextInt(100));
        heroes.add(Type.Warrior, EnemyTest.RANDOM.nextInt(100));
        heroes.add(Type.Wizard, EnemyTest.RANDOM.nextInt(100));
        heroes.add(EnemyTest.RANDOM.nextInt(100), EnemyTest.RANDOM.nextInt(100), Type.Elf);
        heroes.add(EnemyTest.RANDOM.nextInt(100), EnemyTest.RANDOM.nextInt(100), Type.Valkyrie);
        heroes.add(EnemyTest.RANDOM.nextInt(100), EnemyTest.RANDOM.nextInt(100), Type.Warrior);
        heroes.add(EnemyTest.RANDOM.nextInt(100), EnemyTest.RANDOM.nextInt(100), Type.Wizard);
    }
    
    @Test
    public void renderTest() throws Exception
    {
        heroes.render(ResourcesTest.TEST_IMAGE.createGraphics());
    }
}