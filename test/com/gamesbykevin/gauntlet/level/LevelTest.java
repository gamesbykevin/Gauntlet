package com.gamesbykevin.gauntlet.level;

import com.gamesbykevin.framework.maze.Room;
import com.gamesbykevin.gauntlet.characters.enemy.EnemyTest;
import com.gamesbykevin.gauntlet.engine.EngineTest;
import static com.gamesbykevin.gauntlet.entity.Entity.DEFAULT_ANIMATION_KEY;
import com.gamesbykevin.gauntlet.entity.EntityTest;
import com.gamesbykevin.gauntlet.level.Level.LockedRoom;
import com.gamesbykevin.gauntlet.resources.ResourcesTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Level unit test
 * @author GOD
 */
public class LevelTest 
{
    private Level level;
    
    public LevelTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        level = new Level(10, 10, 4, ResourcesTest.TEST_IMAGE, EnemyTest.RANDOM);
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
    }
    
    public Level getLevel()
    {
        return this.level;
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception
    {
        Level level = new Level(10, 10, 4, ResourcesTest.TEST_IMAGE, EnemyTest.RANDOM);
        assertTrue(Level.MIN_ROOM_DIMENSIONS == 4);
        assertTrue(Level.DISPLAY_COLS == 16);
        assertTrue(Level.DISPLAY_ROWS == 16);
        assertTrue(Level.SCROLL_RANGE == 8);
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception
    {
        Level level = new Level(10, 10, 4, ResourcesTest.TEST_IMAGE, EnemyTest.RANDOM);
        level.dispose();
    }
    
    @Before
    public void setUp() throws Exception
    {
        level = new Level(10, 10, 4, ResourcesTest.TEST_IMAGE, EnemyTest.RANDOM);
    }
    
    @After
    public void tearDown() 
    {
        level.dispose();
    }
    
    @Test
    public void getRequiredKeysTest() 
    {
        assertTrue(level.getRequiredKeys() == 0);
    }
    
    @Test
    public void getLockedRoomsTest()
    {
        assertTrue(level.getLockedRooms().isEmpty());
    }
    
    @Test
    public void getRoomDimensionsTest()
    {
        assertTrue(level.getRoomDimensions() == 4);
    }
    
    @Test
    public void resetTest() throws Exception
    {
        level.reset(10, 8, EnemyTest.RANDOM);
    }
    
    @Test
    public void getMazeTest() throws Exception
    {
        assertNotNull(level.getMaze());
        
        final int cols = 11, rows = 14;
        
        level.reset(cols, rows, EnemyTest.RANDOM);
        
        assertTrue(level.getMaze().getCols() == cols);
        assertTrue(level.getMaze().getRows() == rows);
    }
    
    @Test
    public void getAStarTest() throws Exception
    {
        assertNull(level.getAStar());
        
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        assertNotNull(level.getAStar());
    }
    
    @Test
    public void isLockedWallTest() throws Exception
    {
        assertFalse(level.isLockedWall(0, 0, Room.Wall.East));
        
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        assertFalse(level.isLockedWall(0, 0, Room.Wall.East));
        
        for (int i = 0; i < level.getLockedRooms().size(); i++)
        {
            LockedRoom lr = level.getLockedRooms().get(i);
            
            assertTrue(level.isLockedWall((int)lr.getCol(), (int)lr.getRow(), lr.getLockedWall()));
        }
    }
    
    @Test
    public void updateTilesColTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        level.updateTilesCol(.1);
        level.updateTilesCol(0);
        level.updateTilesCol(100);
    }
    
    @Test
    public void updateTilesRowTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        level.updateTilesRow(.1);
        level.updateTilesRow(0);
        level.updateTilesRow(100);
    }
    
    @Test
    public void createTileTest() throws Exception
    {
        for (Tile.Direction direction : Tile.Direction.values())
        {
            for (Tile.Type type : Tile.Type.values())
            {
                for (int col = 0; col < level.getMaze().getCols(); col++)
                {
                    for (int row = 0; row < level.getMaze().getRows(); row++)
                    {
                        level.createTile(col, row, direction, type);
                        level.createTile(col, row, direction, type);
                    }
                }
            }
        }
    }
    
    @Test
    public void unlockDoorTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        level.unlockDoor(level.getTile(0, 0));
    }
    
    @Test
    public void getTileArrayIndexColumnTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        for (int i = 0; i < 100; i++)
        {
            final int col = engine.getEngine().getRandom().nextInt(level.getMaze().getCols());
            final int row = engine.getEngine().getRandom().nextInt(level.getMaze().getRows());

            Tile tile = level.getTile(col, row);

            assertTrue(level.getTileArrayIndexColumn(tile) == col);
        }
    }
    
    @Test
    public void getTileArrayIndexRowTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        for (int i = 0; i < 100; i++)
        {
            final int col = engine.getEngine().getRandom().nextInt(level.getMaze().getCols());
            final int row = engine.getEngine().getRandom().nextInt(level.getMaze().getRows());

            Tile tile = level.getTile(col, row);

            assertTrue(level.getTileArrayIndexRow(tile) == row);
        }
    }
    
    @Test
    public void getTileTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            for (int row = 0; row < level.getMaze().getRows(); row++)
            {
                assertNotNull(level.getTile(col, row));
            }
        }
        
        for (int row = 0; row < level.getMaze().getRows(); row++)
        {
            assertNull(level.getTile(-1, row));
        }
        
        for (int row = 0; row < level.getMaze().getRows(); row++)
        {
            assertNull(level.getTile(level.getMaze().getCols() * level.getRoomDimensions(), row));
        }
        
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            assertNull(level.getTile(col, -1));
        }
        
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            assertNull(level.getTile(col, level.getMaze().getRows() * level.getRoomDimensions()));
        }
    }
    

    @Test
    public void getSolidCollisionTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        EntityTest test = new EntityTest();
        test.getSpriteSheet().add(0, 0, 1, 1, 0, "Default");
        test.setAnimation("Default");
        
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            for (int row = 0; row < level.getMaze().getRows(); row++)
            {
                test.setCol(col);
                test.setRow(row);
                
                Tile tile = level.getTile(col, row);
                
                if (tile.isSolid())
                {
                    assertNotNull(level.getSolidCollision(test, .1, .1));
                }
                
                assertNull(level.getSolidCollision(test, 0, 0));
            }
        }
    }
    
    @Test
    public void hasSolidCollisionTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        EntityTest test = new EntityTest();
        test.getSpriteSheet().add(0, 0, 1, 1, 0, "Default");
        test.setAnimation("Default");
        
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            for (int row = 0; row < level.getMaze().getRows(); row++)
            {
                test.setCol(col);
                test.setRow(row);
                
                Tile tile = level.getTile(col, row);
                
                if (tile.isSolid())
                {
                    assertTrue(level.hasSolidCollision(test, .1, .1));
                }
                
                assertFalse(level.hasSolidCollision(test, 0, 0));
            }
        }
    }
    
    @Test
    public void getCoordinateXTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        final int x = 10;
        
        level.setX(x);
        
        for (int col = 0; col < level.getMaze().getCols(); col++)
        {
            assertTrue(level.getCoordinateX(col) == (x + (col * Tile.DEFAULT_DIMENSION)));
        }
    }
    
    @Test
    public void getCoordinateYTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        final int y = 10;
        
        level.setY(y);
        
        for (int row = 0; row < level.getMaze().getRows(); row++)
        {
            assertTrue(level.getCoordinateY(row) == (y + (row * Tile.DEFAULT_DIMENSION)));
        }
    }
    
    @Test
    public void renderTest() throws Exception
    {
        EngineTest engine = new EngineTest();
        
        while(!level.getMaze().isGenerated())
        {
            level.update(engine.getEngine());
        }
        
        level.setImage(ResourcesTest.TEST_IMAGE);
        level.render();
        level.render(ResourcesTest.TEST_IMAGE.createGraphics());
    }
}