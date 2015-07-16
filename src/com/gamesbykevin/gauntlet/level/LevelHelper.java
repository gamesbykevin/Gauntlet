package com.gamesbykevin.gauntlet.level;

import com.gamesbykevin.framework.maze.Room;

/**
 * Level Helper methods
 * @author GOD
 */
public class LevelHelper 
{
    /**
     * Assign the correct animations for the walls and doors
     * @param level The level we are creating
     */
    private static void correctAnimations(final Level level)
    {
        //make sure each tile has the correct animation
        for (int col = 0; col < level.getTiles()[0].length; col++)
        {
            for (int row = 0; row < level.getTiles().length; row++)
            {
                //the current tile
                Tile tile = level.getTile(col, row);
                
                //skip if the tile does not exist
                if (tile == null)
                    continue;
                
                //the tiles in each direction
                Tile west = level.getTile(col - 1, row);
                Tile east = level.getTile(col + 1, row);
                Tile north = level.getTile(col, row - 1);
                Tile south = level.getTile(col, row + 1);
                
                if (west != null && east != null && north != null && south != null)
                {
                    if (west.isWall() && east.isWall() && north.isWall() && south.isWall())
                        tile.setDirection(Tile.Direction.NorthSouthWestEast);
                }
                else if (west != null && east != null && south != null)
                {
                    if (west.isWall() && east.isWall() && south.isWall())
                        tile.setDirection(Tile.Direction.SouthWestEast);
                }
                else if (west != null && east != null && north != null)
                {
                    if (west.isWall() && east.isWall() && north.isWall())
                        tile.setDirection(Tile.Direction.NorthWestEast);
                }
                else if (north != null && south != null && east != null)
                {
                    if (north.isWall() && south.isWall() && east.isWall())
                        tile.setDirection(Tile.Direction.NorthSouthEast);
                }
                else if (north != null && south != null && west != null)
                {
                    if (north.isWall() && south.isWall() && west.isWall())
                        tile.setDirection(Tile.Direction.NorthSouthWest);
                }
                else if (south != null && north != null)
                {
                    if (south.isWall() && north.isWall())
                        tile.setDirection(Tile.Direction.NorthSouth);
                }
                else if (south != null && east != null)
                {
                    if (south.isWall() && east.isWall())
                        tile.setDirection(Tile.Direction.SouthEast);
                }
                else if (south != null && west != null)
                {
                    if (south.isWall() && west.isWall())
                        tile.setDirection(Tile.Direction.SouthWest);
                }
                else if (north != null && east != null)
                {
                    if (north.isWall() && east.isWall())
                        tile.setDirection(Tile.Direction.NorthEast);
                }
                else if (north != null && west != null)
                {
                    if (north.isWall() && west.isWall())
                        tile.setDirection(Tile.Direction.NorthWest);
                }
                else if (east != null && west != null)
                {
                    if (east.isWall() && west.isWall())
                        tile.setDirection(Tile.Direction.WestEast);
                }
                else if (east != null)
                {
                    if (east.isWall())
                        tile.setDirection(Tile.Direction.East);
                }
                else if (west != null)
                {
                    if (west.isWall())
                        tile.setDirection(Tile.Direction.West);
                }
                else if (north != null)
                {
                    if (north.isWall())
                        tile.setDirection(Tile.Direction.North);
                }
                else if (south != null)
                {
                    if (south.isWall())
                        tile.setDirection(Tile.Direction.South);
                }
                else
                {
                    tile.setDirection(Tile.Direction.Single);
                }
            }
        }
    }
    
    /**
     * Create the borders around the entire level
     * @param level The level we are creating
     * @param wallType The type of wall to add everywhere
     * @throws Exception 
     */
    private static void createBorders(final Level level, final Tile.Type wallType) throws Exception
    {
        /**
         * Create the borders for the general maze
         */
        createBorderWest(level,  0, level.getTiles().length,    0,                              wallType);
        createBorderEast(level,  0, level.getTiles().length,    level.getTiles()[0].length - 1, wallType);
        createBorderNorth(level, 0, level.getTiles()[0].length, 0,                              wallType);
        createBorderSouth(level, 0, level.getTiles()[0].length, level.getTiles().length - 1,    wallType);
        
        //create the walls for each room in the maze
        for (int row = 0; row < level.getMaze().getRows(); row++)
        {
            final int startRow = row * level.getRoomDimensions();
                
            for (int col = 0; col < level.getMaze().getCols(); col++)
            {
                final int startCol = col * level.getRoomDimensions();
                
                //get the current room
                Room room = level.getMaze().getRoom(col, row);
                
                //now determine where to add the walls
                /*
                if (room.hasWall(Room.Wall.North))
                    createBorderNorth(level, startCol, startCol + level.getRoomDimensions(), startRow, wallType);
                if (room.hasWall(Room.Wall.East))
                    createBorderWest(level, startRow, startRow + level.getRoomDimensions(), startCol + level.getRoomDimensions() - 1, wallType);
                */
                if (room.hasWall(Room.Wall.South))
                    createBorderSouth(level, startCol, startCol + level.getRoomDimensions() + 1, startRow + level.getRoomDimensions() - 1, wallType);
                if (room.hasWall(Room.Wall.West))
                    createBorderWest(level, startRow, startRow + level.getRoomDimensions(), startCol, wallType);
            }
        }
    }
    
    /**
     * Create the border
     * @param level The level we are creating
     * @param firstRow Row to start at
     * @param lastRow The last row
     * @param col Current column
     * @param wallType The wall animation
     * @throws Exception 
     */
    private static void createBorderWest(final Level level, final int firstRow, final int lastRow, final int col, Tile.Type wallType) throws Exception
    {
        Tile.Direction direction;
        
        //create west border
        for (int row = firstRow; row < lastRow; row++)
        {
            if (row == 0)
            {
                direction = Tile.Direction.SouthEast;
            }
            else if (row < level.getTiles().length - 1)
            {
                direction = Tile.Direction.NorthSouth;
            }
            else
            {
                direction = Tile.Direction.NorthEast;
            }
            
            //create tile
            level.createTile(col, row, direction, wallType);
        }
    }
    
    /**
     * Create the border
     * @param level The level we are creating
     * @param firstRow Row to start at
     * @param lastRow The last row
     * @param col Current column
     * @param wallType The wall animation
     * @throws Exception 
     */
    private static void createBorderEast(final Level level, final int firstRow, final int lastRow, final int col, final Tile.Type wallType) throws Exception
    {
        Tile.Direction direction;
        
        //create east border
        for (int row = firstRow; row < lastRow; row++)
        {
            if (row == 0)
            {
                direction = Tile.Direction.SouthWest;
            }
            else if (row < level.getTiles().length - 1)
            {
                direction = Tile.Direction.NorthSouth;
            }
            else
            {
                direction = Tile.Direction.NorthWest;
            }
            
            //create tile
            level.createTile(col, row, direction, wallType);
        }
    }
    
    /**
     * Create the border
     * @param level The level we are creating
     * @param firstCol Column to start at
     * @param lastCol The last column
     * @param row Current row
     * @param wallType The wall animation
     * @throws Exception 
     */
    private static void createBorderNorth(final Level level, final int firstCol, final int lastCol, final int row, final Tile.Type wallType) throws Exception
    {
        Tile.Direction direction;
        
        //create north border
        for (int col = firstCol; col < lastCol; col++)
        {
            if (col == 0)
            {
                direction = Tile.Direction.SouthEast;
            }
            else if (col < level.getTiles()[0].length - 1)
            {
                direction = Tile.Direction.WestEast;
            }
            else
            {
                direction = Tile.Direction.SouthWest;
            }
            
            //create tile
            level.createTile(col, row, direction, wallType);
        }
    }
    
    /**
     * Create the border
     * @param level The level we are creating
     * @param firstCol Column to start at
     * @param lastCol The last column
     * @param row Current row
     * @param wallType The wall animation
     * @throws Exception 
     */
    private static void createBorderSouth(final Level level, final int firstCol, final int lastCol, final int row, final Tile.Type wallType) throws Exception
    {
        Tile.Direction direction;
        
        //create south border
        for (int col = firstCol; col < lastCol; col++)
        {
            if (col == 0)
            {
                direction = Tile.Direction.NorthEast;
            }
            else if (col < level.getTiles()[0].length - 1)
            {
                direction = Tile.Direction.WestEast;
            }
            else if (col == level.getTiles()[0].length - 1)
            {
                direction = Tile.Direction.NorthWest;
            }
            else
            {
                //skip if out of bounds
                continue;
            }
            
            //create tile
            level.createTile(col, row, direction, wallType);
        }
    }
    
    /**
     * Apply shadows next to the walls and doors
     * @param level The level we are creating
     * @throws Exception 
     */
    private static void createShadows(final Level level) throws Exception
    {
        for (int row = 0; row < level.getTiles().length; row++)
        {
            for (int col = 0; col < level.getTiles()[0].length; col++)
            {
                //get the current tile
                Tile tile = level.getTile(col, row);
                
                //we only want places with no tile
                if (tile != null)
                    continue;
                
                //the tiles in each direction
                Tile west = level.getTile(col - 1, row);
                Tile south = level.getTile(col, row + 1);

                if (south != null && west != null && south.isWall() && west.isWall())
                {
                    level.createTile(col, row, Tile.Direction.SouthEast, Tile.Type.FloorWall2);
                }
                else if (west != null && west.isWall())
                {
                    level.createTile(col, row, Tile.Direction.NorthSouth, Tile.Type.FloorWall2);
                }
                else if (south != null && south.isWall())
                {
                    level.createTile(col, row, Tile.Direction.North, Tile.Type.FloorWall2);
                }
            }
        }
    }
    
    /**
     * Create the tiles for our level
     * @throws Exception 
     */
    protected static void createTiles(final Level level) throws Exception
    {
        //create border
        createBorders(level, Tile.Type.Wall3);
        
        //assign the correct animations for the walls
        correctAnimations(level);
        
        //create shadows
        createShadows(level);
        
        //fill in the remaining with floors
        for (int row = 0; row < level.getTiles().length; row++)
        {
            for (int col = 0; col < level.getTiles()[0].length; col++)
            {
                if (level.getTile(col, row) == null)
                    level.createTile(col, row, Tile.Direction.Single, Tile.Type.FloorWall2);
            }
        }
        
        //now finally add the animations
        for (int row = 0; row < level.getTiles().length; row++)
        {
            for (int col = 0; col < level.getTiles()[0].length; col++)
            {
                level.getTile(col, row).assignAnimation();
            }
        }
    }
}