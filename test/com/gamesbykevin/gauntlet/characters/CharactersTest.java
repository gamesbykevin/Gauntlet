package com.gamesbykevin.gauntlet.characters;

import com.gamesbykevin.gauntlet.resources.ResourcesTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Characters unit test
 * @author GOD
 */
public class CharactersTest extends Characters
{
    public CharactersTest() 
    {
        super(ResourcesTest.TEST_IMAGE);
    }
    
    @Override
    public void add(final double col, final double row, final Character.Type type) throws Exception
    {
        CharacterTest.CharacterChildTest test = new CharacterTest.CharacterChildTest(type);
        test.setCol(col);
        test.setRow(row);
        
        super.add(test);
    }
    
    
    @BeforeClass
    public static void setUpClass() 
    {
        CharactersTest test = new CharactersTest();
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        CharactersTest test = new CharactersTest();
        test.dispose();
        test = null;
    }
    
    @Before
    public void setUp() 
    {
        
    }
    
    @After
    public void tearDown() 
    {
        
    }
    
    @Test
    public void addTest() throws Exception
    {
        assertTrue(super.getCharacters().isEmpty());
        
        this.add(1.5, 1.5, Character.Type.Elf);
        
        assertTrue(super.getCharacters().size() == 1);
                
        CharacterTest.CharacterChildTest test = new CharacterTest.CharacterChildTest(Character.Type.Demon);
        test.setCol(7.5);
        test.setRow(5);
        
        super.add(test);
        
        assertTrue(super.getCharacters().size() == 2);
    }
    
    @Test
    public void disposeTest()
    {
        super.dispose();
    }
    
    @Test
    public void getCharacterTest() throws Exception
    {
        CharacterTest.CharacterChildTest test = new CharacterTest.CharacterChildTest(Character.Type.Demon);
        test.setCol(7.5);
        test.setRow(5);
        
        super.add(test);
        
        assertNull(super.getCharacter(test));
        assertNotNull(super.getCharacter(test.getId()));
        
        CharacterTest.CharacterChildTest tmp = new CharacterTest.CharacterChildTest(Character.Type.SuperSorcerer);
        
        assertNull(super.getCharacter(tmp));
        assertNull(super.getCharacter(tmp.getId()));
    }
    
    @Test
    public void hasCharacterTest() throws Exception
    {
        assertFalse(super.hasCharacter(0, 0));
        
        CharacterTest.CharacterChildTest test = new CharacterTest.CharacterChildTest(Character.Type.Demon);
        test.setCol(7.5);
        test.setRow(5);
        
        super.add(test);
        
        assertTrue(super.hasCharacter(7, 5));
    }
    
    @Test
    public void getCharactersTest() throws Exception
    {
        assertTrue(super.getCharacters().isEmpty());
        this.add(1, 1, Character.Type.Elf);
        assertFalse(super.getCharacters().isEmpty());
    }
    
    @Test
    public void hasCollisionTest() throws Exception
    {
        CharacterTest.CharacterChildTest test = new CharacterTest.CharacterChildTest(Character.Type.Demon);
        test.setCol(10);
        test.setRow(20);
        super.add(test);
        
        CharacterTest.CharacterChildTest tmp = new CharacterTest.CharacterChildTest(Character.Type.Blob);
        tmp.setCol(1);
        tmp.setRow(2);
        super.add(tmp);
        
        assertFalse(super.hasCollision(tmp));
        assertFalse(super.hasCollision(test));
        
        
        super.getCharacters().clear();
        
        test = new CharacterTest.CharacterChildTest(Character.Type.Demon);
        test.setCol(1);
        test.setRow(2);
        test.setHealth(1);
        super.add(test);
        
        tmp = new CharacterTest.CharacterChildTest(Character.Type.Sorcerer);
        tmp.setCol(1);
        tmp.setRow(2);
        tmp.setHealth(1);
        super.add(tmp);
        
        assertTrue(super.hasCollision(tmp));
        assertTrue(super.hasCollision(test));
    }
    
    @Test
    public void renderTest() throws Exception
    {
        this.add(1.5, 1.5, Character.Type.Elf);
        this.add(10.5, 3.5, Character.Type.Demon);
        
        super.render(ResourcesTest.TEST_IMAGE.createGraphics());
    }
}