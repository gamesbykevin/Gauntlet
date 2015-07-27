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
    private static void correctAnimations(final Level level) throws Exception
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
                    if (west.isSolid()&& east.isSolid() && north.isSolid() && south.isSolid())
                        tile.setDirection(Tile.Direction.NorthSouthWestEast);
                }
                else if (west != null && east != null && south != null)
                {
                    if (west.isSolid() && east.isSolid() && south.isSolid())
                        tile.setDirection(Tile.Direction.SouthWestEast);
                }
                else if (west != null && east != null && north != null)
                {
                    if (west.isSolid() && east.isSolid() && north.isSolid())
                        tile.setDirection(Tile.Direction.NorthWestEast);
                }
                else if (north != null && south != null && east != null)
                {
                    if (north.isSolid() && south.isSolid() && east.isSolid())
                        tile.setDirection(Tile.Direction.NorthSouthEast);
                }
                else if (north != null && south != null && west != null)
                {
                    if (north.isSolid() && south.isSolid() && west.isSolid())
                        tile.setDirection(Tile.Direction.NorthSouthWest);
                }
                else if (south != null && north != null)
                {
                    if (south.isSolid() && north.isSolid())
                        tile.setDirection(Tile.Direction.NorthSouth);
                }
                else if (south != null && east != null)
                {
                    if (south.isSolid() && east.isSolid())
                        tile.setDirection(Tile.Direction.SouthEast);
                }
                else if (south != null && west != null)
                {
                    if (south.isSolid() && west.isSolid())
                        tile.setDirection(Tile.Direction.SouthWest);
                }
                else if (north != null && east != null)
                {
                    if (north.isSolid() && east.isSolid())
                        tile.setDirection(Tile.Direction.NorthEast);
                }
                else if (north != null && west != null)
                {
                    if (north.isSolid() && west.isSolid())
                        tile.setDirection(Tile.Direction.NorthWest);
                }
                else if (east != null && west != null)
                {
                    if (east.isSolid() && west.isSolid())
                        tile.setDirection(Tile.Direction.WestEast);
                }
                else if (east != null)
                {
                    if (east.isSolid())
                        tile.setDirection(Tile.Direction.East);
                }
                else if (west != null)
                {
                    if (west.isSolid())
                        tile.setDirection(Tile.Direction.West);
                }
                else if (north != null)
                {
                    if (north.isSolid())
                        tile.setDirection(Tile.Direction.North);
                }
                else if (south != null)
                {
                    if (south.isSolid())
                        tile.setDirection(Tile.Direction.South);
                }
                else
                {
                    tile.setDirection(Tile.Direction.Single);
                }
                
                //assign the animation
                tile.assignAnimation();
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
     * Create the doors
     * @param level The level containing the rooms that have the walls marked as doors
     */
    private static void createDoors(final Level level) throws Exception
    {
        //check each room on the maze to see where we need doors, skipping the edges
        for (int row = 0; row < level.getMaze().getRows(); row++)
        {
            final int startRow = row * level.getRoomDimensions();
                
            for (int col = 0; col < level.getMaze().getCols(); col++)
            {
                final int startCol = col * level.getRoomDimensions();
                
                if (level.isLockedWall(col, row, Room.Wall.West))
                {
                    createBorderWest(level, startRow, startRow + level.getRoomDimensions(), startCol, Tile.Type.Door2);
                }
                else if (level.isLockedWall(col, row, Room.Wall.South))
                {
                    createBorderSouth(level, startCol, startCol + level.getRoomDimensions(), startRow + level.getRoomDimensions() - 1, Tile.Type.Door2);
                }
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

                if (south != null && west != null && south.isWall()&& west.isWall())
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
    protected static void createTiles(final Level level, final Tile.Type wallType) throws Exception
    {
        //create doors
        createDoors(level);
        
        //create border
        createBorders(level, wallType);
        
        //create the exit tile
        createExit(level);
        
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
    
    /**
     * Unlock the door.<br>
     * Also will unlock neighbor tiles
     * @param level The level we need to update
     * @param tile The door tile we need to unlock
     * @throws Exception 
     */
    protected static void unlockDoor(final Level level, final Tile tile) throws Exception
    {
        Tile tmp;
        
        boolean scrollFinished1 = false;
        boolean scrollFinished2 = false;
        
        final int tmpCol = level.getTileArrayIndexColumn(tile);
        final int tmpRow = level.getTileArrayIndexRow(tile);
        
        switch (tile.getDirection())
        {
            case NorthSouth:
                for (int row = 1; row < level.getRoomDimensions(); row++)
                {
                    if (!scrollFinished1)
                    {
                        tmp = level.getTile(tmpCol, tmpRow + row);
                    
                        if (tmp != null && tmp.isDoor())
                        {
                            if (tmp.getDirection() != Tile.Direction.NorthSouth)
                                scrollFinished1 = true;
                            
                            level.createTile(tmpCol, tmpRow + row, Tile.Direction.Single, Tile.Type.FloorWall2);
                            tmp.assignAnimation();
                        }
                        else
                        {
                            scrollFinished1 = true;
                        }
                    }
                    
                    if (!scrollFinished2)
                    {
                        tmp = level.getTile(tmpCol, tmpRow - row);
                    
                        if (tmp != null && tmp.isDoor())
                        {
                            if (tmp.getDirection() != Tile.Direction.NorthSouth)
                                scrollFinished2 = true;
                            
                            level.createTile(tmpCol, tmpRow - row, Tile.Direction.Single, Tile.Type.FloorWall2);
                            tmp.assignAnimation();
                        }
                        else
                        {
                            scrollFinished2 = true;
                        }
                    }
                }
                break;
                
            case WestEast:
                for (int col = 1; col < level.getRoomDimensions(); col++)
                {
                    if (!scrollFinished1)
                    {
                        tmp = level.getTile(tmpCol + col, tmpRow);

                        if (tmp != null && tmp.isDoor())
                        {
                            if (tmp.getDirection() != Tile.Direction.WestEast)
                                scrollFinished1 = true;
                            
                            level.createTile(tmpCol + col, tmpRow, Tile.Direction.Single, Tile.Type.FloorWall2);
                            tmp.assignAnimation();
                        }
                        else
                        {
                            scrollFinished1 = true;
                        }
                    }
                    
                    if (!scrollFinished2)
                    {
                        tmp = level.getTile(tmpCol - col, tmpRow);

                        if (tmp != null && tmp.isDoor())
                        {
                            if (tmp.getDirection() != Tile.Direction.WestEast)
                                scrollFinished2 = true;
                            
                            level.createTile(tmpCol - col, tmpRow, Tile.Direction.Single, Tile.Type.FloorWall2);
                            tmp.assignAnimation();
                        }
                        else
                        {
                            scrollFinished2 = true;
                        }
                    }
                }
                break;
        }
        
        level.createTile(tmpCol, tmpRow, Tile.Direction.Single, Tile.Type.FloorWall2);
        tile.assignAnimation();
        
        //render a new image
        level.render();
    }
    
    /**
     * Create the exit.
     * @param level The level where we want to add an exit
     * @throws Exception 
     */
    private static void createExit(final Level level) throws Exception
    {
        //the location of the exit
        final int col = level.getMaze().getFinishCol() * level.getRoomDimensions() + 2;
        final int row = level.getMaze().getFinishRow() * level.getRoomDimensions() + 2;
        
        //create the exit tile
        level.createTile(col, row, Tile.Direction.Single, Tile.Type.Exit);
    }
}