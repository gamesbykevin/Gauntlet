package com.gamesbykevin.gauntlet.characters;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Character Helper unit test
 * @author GOD
 */
public class CharacterHelperTest 
{
    
    public CharacterHelperTest() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
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
        
    }
    
    @Test
    public void setupAnimationsTest() throws Exception
    {
        for (Character.Type type : Character.Type.values())
        {
            CharacterTest.CharacterChildTest tmp = new CharacterTest.CharacterChildTest(type);
            
            CharacterHelper.setupAnimations(tmp);
        }
    }
}