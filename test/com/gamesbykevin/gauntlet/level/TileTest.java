/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamesbykevin.gauntlet.level;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tile unit test
 * @author GOD
 */
public class TileTest 
{
    private Tile tile;
    
    public TileTest() 
    {
        tile = new Tile(0, 0, Tile.Direction.East, Tile.Type.Wall1);
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        for (Tile.Direction dir : Tile.Direction.values())
        {
            for (Tile.Type type : Tile.Type.values())
            {
                Tile tile = new Tile(0, 0, dir, type);
            }
        }
        
        assertTrue(Tile.DEFAULT_DIMENSION == 32);
        assertEquals(Tile.KEY, "Default");
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        for (Tile.Direction dir : Tile.Direction.values())
        {
            for (Tile.Type type : Tile.Type.values())
            {
                Tile tile = new Tile(0, 0, dir, type);
                tile.dispose();
            }
        }
    }
    
    @Before
    public void setUp() 
    {
        tile = new Tile(0, 0, Tile.Direction.East, Tile.Type.Wall1);
    }
    
    @After
    public void tearDown() 
    {
        tile.dispose();
    }
    
    @Test
    public void setTypeTest() 
    {
        for (Tile.Type type : Tile.Type.values())
        {
            tile.setType(type);
        }
    }
    
    @Test
    public void getTypeTest() 
    {
        for (Tile.Type type : Tile.Type.values())
        {
            tile.setType(type);
            assertTrue(tile.getType() == type);
            assertEquals(tile.getType(), type);
        }
    }
    
    @Test
    public void setDirectionTest() 
    {
        for (Tile.Direction dir : Tile.Direction.values())
        {
            tile.setDirection(dir);
        }
    }
    
    @Test
    public void getDirectionTest() 
    {
        for (Tile.Direction dir : Tile.Direction.values())
        {
            tile.setDirection(dir);
            assertTrue(tile.getDirection() == dir);
            assertEquals(tile.getDirection(), dir);
        }
    }
    
    @Test
    public void assignAnimationTest() throws Exception
    {
        for (Tile.Direction dir : Tile.Direction.values())
        {
            for (Tile.Type type : Tile.Type.values())
            {
                tile = new Tile(0, 0, dir, type);
                tile.assignAnimation();
            }
        }
    }
    
    @Test
    public void isWallTest() throws Exception
    {
        for (Tile.Type type : Tile.Type.values())
        {
            tile.setType(type);
            
            switch (type)
            {
                case Wall1:
                case Wall2:
                case Wall3:
                case Wall4:
                case Wall5:
                case Wall6:
                case Wall7:
                case Wall8:
                case Wall9:
                case Wall10:
                    assertTrue(tile.isWall());
                    break;
                    
                default:
                    assertFalse(tile.isWall());
                    break;
            }
        }
    }
    
    @Test
    public void isDoorTest() throws Exception
    {
        for (Tile.Type type : Tile.Type.values())
        {
            tile.setType(type);
            
            switch (type)
            {
                case Door1:
                case Door2:
                    assertTrue(tile.isDoor());
                    break;
                    
                default:
                    assertFalse(tile.isDoor());
                    break;
            }
        }
    }
    
    @Test
    public void isFloorTest() throws Exception
    {
        for (Tile.Type type : Tile.Type.values())
        {
            tile.setType(type);
            
            switch (type)
            {
                case FloorWall2:
                case FloorDoor2:
                    assertTrue(tile.isFloor());
                    break;
                    
                default:
                    assertFalse(tile.isFloor());
                    break;
            }
        }
    }
    
    @Test
    public void isExitTest() throws Exception
    {
        for (Tile.Type type : Tile.Type.values())
        {
            tile.setType(type);
            
            switch (type)
            {
                case Exit:
                    assertTrue(tile.isExit());
                    break;
                    
                default:
                    assertFalse(tile.isExit());
                    break;
            }
        }
    }
    
    @Test
    public void setSolidTest() throws Exception
    {
        tile.setSolid(true);
        tile.setSolid(false);
    }
    
    @Test
    public void isSolidTest() throws Exception
    {
        tile.setSolid(true);
        assertTrue(tile.isSolid());
        tile.setSolid(false);
        assertFalse(tile.isSolid());
        
        for (Tile.Type type : Tile.Type.values())
        {
            tile.setType(type);
            
            if (tile.isDoor() || tile.isWall())
            {
                assertTrue(tile.isSolid());
            }
            else
            {
                assertFalse(tile.isSolid());
            }
        }
    }
}