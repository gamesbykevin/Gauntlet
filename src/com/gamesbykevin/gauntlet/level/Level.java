package com.gamesbykevin.gauntlet.level;

import com.gamesbykevin.framework.ai.AStar;
import com.gamesbykevin.framework.awt.CustomImage;
import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.framework.maze.Maze;
import com.gamesbykevin.framework.maze.MazeHelper;
import com.gamesbykevin.framework.maze.Room;
import com.gamesbykevin.framework.maze.algorithm.*;

import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.shared.IElement;
import com.gamesbykevin.gauntlet.shared.Shared;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class will represent the level
 * @author GOD
 */
public final class Level extends CustomImage implements IElement
{
    //our maze object
    private Maze maze;
    
    //the size of each room that we create in our maze
    private int roomDimensions;
    
    //the window area where the tiles will be drawn
    private Rectangle window;
    
    /**
     * The smallest dimensions for a single room
     */
    public static final int MIN_ROOM_DIMENSIONS = 4;
    
    /**
     * The number of columns to display on a single screen
     */
    public static final int DISPLAY_COLS = 16;
    
    /**
     * The number of rows to display on a single screen
     */
    public static final int DISPLAY_ROWS = 16;
    
    /**
     * The number of tiles from the edge of the screen before we attempt to scroll
     */
    public static final int SCROLL_RANGE = 8;
    
    //the tiles that make up the level
    private Tile[][] tiles;
    
    /**
     * The A* algorithm used to calculate the shortest path
     */
    private AStar astar;
    
    /**
     * List of locked rooms
     */
    private List<LockedRoom> lockedRooms;
    
    /**
     * The number of locked doors allowed for this level
     */
    private int limit;
    
    /**
     * List of possible wall types
     */
    private List<Tile.Type> wallTypes;
    
    /**
     * Create a new level
     * @param cols The column dimensions for our maze
     * @param rows The row dimensions for our maze
     * @param roomDimensions The size of each individual room in the maze
     * @param image Image containing level graphics
     * @param random Object used to make random decisions
     * @throws Exception 
     */
    public Level(final int cols, final int rows, final int roomDimensions, final Image image, final Random random) throws Exception
    {
        super((roomDimensions * cols) * Tile.DEFAULT_DIMENSION, (roomDimensions * rows) * Tile.DEFAULT_DIMENSION);
        
        //assign the room dimensions
        this.roomDimensions = roomDimensions;
        
        //make sure we meet the requirement
        if (this.roomDimensions < MIN_ROOM_DIMENSIONS)
            throw new Exception("The supplied room dimensions (" + roomDimensions + ") do not meet the minimum (" + MIN_ROOM_DIMENSIONS + ")");
        
        //create a new list of locked rooms
        this.lockedRooms = new ArrayList<>();
        
        //store the sheet containing all the graphics for a level
        super.setImage(image);
        
        //reset the maze object
        reset(cols, rows, random);
        
        //create new list of wall types
        this.wallTypes = new ArrayList<>();
        this.wallTypes.add(Tile.Type.Wall1);
        this.wallTypes.add(Tile.Type.Wall2);
        this.wallTypes.add(Tile.Type.Wall3);
        this.wallTypes.add(Tile.Type.Wall4);
        this.wallTypes.add(Tile.Type.Wall5);
        this.wallTypes.add(Tile.Type.Wall6);
        this.wallTypes.add(Tile.Type.Wall7);
        this.wallTypes.add(Tile.Type.Wall8);
        this.wallTypes.add(Tile.Type.Wall9);
        this.wallTypes.add(Tile.Type.Wall10);
        
        //create a new window where the tiles will be rendered
        this.window = new Rectangle(-Tile.DEFAULT_DIMENSION * 1, -Tile.DEFAULT_DIMENSION * 1, Shared.ORIGINAL_WIDTH + (Tile.DEFAULT_DIMENSION * 2), Shared.ORIGINAL_HEIGHT + (Tile.DEFAULT_DIMENSION * 2));
    }
    
    @Override
    public void dispose()
    {
        super.getBufferedImage().flush();
        
        if (maze != null)
        {
            maze.dispose();
            maze = null;
        }
        
        if (tiles != null)
        {
            for (int col = 0; col < tiles[0].length; col++)
            {
                for (int row = 0; row < tiles.length; row++)
                {
                    if (tiles[row][col] != null)
                    {
                        tiles[row][col].dispose();
                        tiles[row][col] = null;
                    }
                }
            }
        
            tiles = null;
        }
        
        if (wallTypes != null)
        {
            wallTypes.clear();
            wallTypes = null;
        }
    }
    
    /**
     * Get the required keys.
     * @return The number of keys needed to unlock all doors
     */
    public int getRequiredKeys()
    {
        return this.lockedRooms.size();
    }
    
    /**
     * Get the list of locked rooms<br>
     * We need to know this so we know where to spawn the keys.
     * @return The list of locked rooms.
     */
    public List<LockedRoom> getLockedRooms()
    {
        return this.lockedRooms;
    }
    
    /**
     * Get the room dimensions
     * @return The size (columns, rows) of each room for this level
     */
    public int getRoomDimensions()
    {
        return this.roomDimensions;
    }
    
    /**
     * Create a new maze instance, which will cause a new level to be generated
     */
    public final void reset(final int cols, final int rows, final Random random) throws Exception
    {
        //create a maze
        switch (random.nextInt(5))
        {
            case 0:
                maze = new Prims(cols, rows);
                break;
                
            case 1:
                maze = new Wilsons(cols, rows);
                break;
                
            case 2:
                maze = new Kruskals(cols, rows);
                break;
                
            case 3:
                maze = new Ellers(cols, rows);
                break;
                
            case 4:
                maze = new Sidewinder(cols, rows);
                break;
                
            default:
                throw new Exception("Random chosen number not setup here.");
        }
        
        maze.getProgress().setScreen(0, 0, Shared.ORIGINAL_WIDTH, Shared.ORIGINAL_HEIGHT);
        maze.getProgress().setDescription("Creating level.. ");
        
        //create array for our tiles
        this.tiles = new Tile[this.roomDimensions * rows][this.roomDimensions * cols];
        
        //start location
        super.setCol(0);
        super.setRow(0);
        
        //reset x,y
        super.setX(0);
        super.setY(0);
    }
    
    /**
     * Get the maze
     * @return The maze containing the rooms for the level
     */
    public Maze getMaze()
    {
        return this.maze;
    }
    
    /**
     * Get the A* Object
     * @return The object capable of calculating the shortest path
     */
    public AStar getAStar()
    {
        return this.astar;
    }
    
    /**
     * Get the tiles
     * @return The tiles that maze up the level
     */
    protected Tile[][] getTiles()
    {
        return this.tiles;
    }
    
    /**
     * Get the window
     * @return The viewable window of the level
     */
    public Rectangle getWindow()
    {
        return this.window;
    }
    
    @Override
    public void update(final Engine engine) throws Exception
    {
        if (!getMaze().isGenerated())
        {
            //generate maze
            getMaze().update(engine.getRandom());
            
            //now that the maze is generated we can assign tiles
            if (getMaze().isGenerated())
            {
                //identify the finish location, and calculate cost
                MazeHelper.locateFinish(getMaze());
                
                //calculate the shortest path
                astar = new AStar(getMaze().getStart(), getMaze().getFinish(), getMaze().getRooms());
                astar.setDiagonal(false);
                astar.generate();
                
                //create some locked doors, that require a key to open
                generateDoors(engine.getRandom());
                
                //create the tiles
                LevelHelper.createTiles(this, wallTypes.get(engine.getRandom().nextInt(wallTypes.size())));
                
                //render a new image
                render();
            }
        }
    }
    
    /**
     * Here we will pick random rooms to lock
     * @param random Object used to make random decisions
     */
    private void generateDoors(final Random random)
    {
        //create optional list
        List<Integer> options = new ArrayList<>();
        
        //identify our optional rooms on the unique path to the exit
        for (int index = 0; index < getAStar().getShortestPath().size() - 1; index++)
        {
            //get the current location
            Cell tmp1 = getAStar().getShortestPath().get(index);
            Cell tmp2 = getAStar().getShortestPath().get(index + 1);
            
            //avoid the edges of the maze
            if (tmp1.getCol() < 1 || tmp1.getCol() > tiles[0].length - 2)
                continue;
            if (tmp2.getCol() < 1 || tmp2.getCol() > tiles[0].length - 2)
                continue;
            if (tmp1.getRow() < 1 || tmp1.getRow() > tiles.length - 2)
                continue;
            if (tmp2.getRow() < 1 || tmp2.getRow() > tiles.length - 2)
                continue;
            
            //if they are direct west or south neighbors add to the list
            if (tmp1.getCol() > tmp2.getCol() || tmp1.getRow() < tmp2.getRow())
                options.add(index);
        }
        
        /**
         * Determine the locked door limit by the room size
         */
        limit = (int)((getMaze().getRows() > getMaze().getCols()) ? Math.sqrt(getMaze().getRows()) : Math.sqrt(getMaze().getCols()));
        
        //continue to create doors until we reach our limit, or there are no options left
        while (!options.isEmpty() && lockedRooms.size() < limit)
        {
            //pick random location from our list
            final int index = random.nextInt(options.size());

            //the the location on the shortest path and the neighbor
            Cell tmp1 = getAStar().getShortestPath().get(options.get(index));
            Cell tmp2 = getAStar().getShortestPath().get(options.get(index) + 1);

            //determine where we add the door
            if (tmp1.getCol() > tmp2.getCol())
            {
                //add room to the list of locked rooms, as well as the locked wall
                this.lockedRooms.add(new LockedRoom(tmp1, Room.Wall.West));
            }
            else if (tmp1.getRow() < tmp2.getRow())
            {
                //add room to the list of locked rooms, as well as the locked wall
                this.lockedRooms.add(new LockedRoom(tmp1, Room.Wall.South));
            }

            //remove this from the list of options
            options.remove(index);
        }
    }
    
    /**
     * Is the wall in the specified room locked?
     * @param column Column
     * @param row Row
     * @return true if the room is locked, false otherwise
     */
    protected boolean isLockedWall(final int column, final int row, final Room.Wall wall)
    {
        for (int index = 0; index < lockedRooms.size(); index++)
        {
            if (lockedRooms.get(index).hasLockedWall(column, row, wall))
                return true;
        }
        
        //we didn't find, return false
        return false;
    }
    
    /**
     * Can we scroll horizontally?<br>
     * Here we try to limit the scrolling of the level so scrolling will stop when reaching a wall on any given end
     * @param dx x-velocity
     * @return true as long as the level can scroll or if there is no x-velocity, false otherwise
     */
    private boolean canScrollX(final double dx)
    {
        //if not moving horizontally return true
        if (dx == 0.0)
            return true;
        
        //if we are moving west
        if (dx > 0.0)
        {
            //as long as the initial column stays less than 0 we can scroll
            return (getCol() < 0.0);
        }
        else 
        {
            //if moving east we can scroll as long as the last column is greater that the display cols - 1
            return (getCol() > -this.getTiles()[0].length + DISPLAY_COLS);
        }
    }
    
    /**
     * Can we scroll vertically?<br>
     * Here we try to limit the scrolling of the level so scrolling will stop when reaching a wall on any given end
     * @param dy y-velocity
     * @return true as long as the level can scroll or if there is no y-velocity, false otherwise
     */
    private boolean canScrollY(final double dy)
    {
        //if not moving vertically return true
        if (dy == 0.0)
            return true;
        
        //if we are moving north
        if (dy > 0.0)
        {
            //as long as the initial row stays less than 0 we can scroll
            return (getRow() < 0.0);
        }
        else
        {
            //if moving south we can scroll as long as the last row is greater than the display rows - 1
            return (getRow() > -this.getTiles().length + DISPLAY_ROWS);
        }
    }
    
    /**
     * Update the location of the tiles
     * @param dx x-velocity
     */
    public void updateTilesCol(final double dx)
    {
        //update the location
        super.setCol(getCol() + dx);
        
        //if we can't scroll reset the location accordingly
        if (!canScrollX(dx))
        {
            //reset location
            super.setCol(getCol() - dx);
        }
        else 
        {
            //update x-coordinate based on the column
            super.setX(getCol() * Tile.DEFAULT_DIMENSION);

            //update the location of all the tiles
            for (int row = 0; row < getTiles().length; row++)
            {
                for (int col = 0; col < getTiles()[0].length; col++)
                {
                    //get the current tile
                    Tile tile = getTile(col, row);

                    //update the column location
                    tile.setCol(tile.getCol() + dx);
                }
            }
        }
    }
    
    /**
     * Update the location of the tiles
     * @param dy y-velocity
     */
    public void updateTilesRow(final double dy)
    {
        //update the location
        super.setRow(getRow() + dy);
        
        //if we can't scroll reset the location accordingly
        if (!canScrollY(dy))
        {
            //reset location
            super.setRow(getRow() - dy);
        }
        else 
        {
            //update y-coordinate based on the row
            super.setY(getRow() * Tile.DEFAULT_DIMENSION);

            //update the location of all the tiles
            for (int row = 0; row < getTiles().length; row++)
            {
                for (int col = 0; col < getTiles()[0].length; col++)
                {
                    //get the current tile
                    Tile tile = getTile(col, row);

                    //update the row location
                    tile.setRow(tile.getRow() + dy);
                }
            }
        }
    }
    
    /**
     * Create a tile at the specified location.<br>
     * If a tile already exists, it will be updated
     * @param col Column
     * @param row Row
     * @param direction Direction of tile
     * @param type Type of tile
     */
    protected void createTile(final int col, final int row, Tile.Direction direction, Tile.Type type) throws Exception
    {
        //if it already exists update it
        if (getTile(col, row) != null)
        {
            getTile(col, row).setDirection(direction);
            getTile(col, row).setType(type);
        }
        else
        {
            this.tiles[row][col] = new Tile(col + .5, row + .5, direction, type);
            getTile(col, row).setX(getCoordinateX(col + .5));
            getTile(col, row).setY(getCoordinateY(row + .5));
        }
    }
    
    /**
     * Unlock the door
     * @param tile One of the door tiles we made collision with
     * @throws Exception 
     */
    public void unlockDoor(final Tile tile) throws Exception
    {
        LevelHelper.unlockDoor(this, tile);
    }
    
    /**
     * Get the array index column where the tile is located
     * @param tile The tile we are searching for
     * @return The index of the column in the tiles array
     */
    protected int getTileArrayIndexColumn(final Tile tile)
    {
        for (int row = 0; row < getTiles().length; row++)
        {
            for (int col = 0; col < getTiles()[0].length; col++)
            {
                if (getTile(col, row).hasId(tile))
                    return col;
            }
        }
        
        //this should not happen
        return -1;
    }
    
    /**
     * Get the array index row where the tile is located
     * @param tile The tile we are searching for
     * @return The index of the row in the tiles array
     */
    protected int getTileArrayIndexRow(final Tile tile)
    {
        for (int row = 0; row < getTiles().length; row++)
        {
            for (int col = 0; col < getTiles()[0].length; col++)
            {
                if (getTile(col, row).hasId(tile))
                    return row;
            }
        }
        
        //this should not happen
        return -1;
    }
    
    /**
     * Get the tile at the specified location in the array.<br>
     * Note: The tile may have a different (column, row) position in the game
     * @param col Column
     * @param row Row
     * @return The tile at the current location, if the location is out of bounds null is returned
     */
    public Tile getTile(final int col, final int row)
    {
        if (col < 0)
            return null;
        if (row < 0)
            return null;
        if (col > this.tiles[0].length - 1)
            return null;
        if (row > this.tiles.length - 1)
            return null;
        
        return this.tiles[row][col];
    }
    
    /**
     * Get the solid tile this entity is too close to
     * @param entity The entity we want to check
     * @param dx x-velocity
     * @param dy y-velocity
     * @return The solid level tile we are too close to, if none found null will be returned
     * @throws Exception 
     */
    public Tile getSolidCollision(final Entity entity, final double dx, final double dy) throws Exception
    {
        int colMin = (int)(entity.getCol() - entity.getCollisionDistance());
        int colMax = (int)(entity.getCol() + entity.getCollisionDistance());
        int rowMin = (int)(entity.getRow() - entity.getCollisionDistance());
        int rowMax = (int)(entity.getRow() + entity.getCollisionDistance());
        
        if (dx < 0 && (getTile(colMin, rowMin).isSolid() || getTile(colMin, rowMax).isSolid()))
        {
            if (getTile(colMin, rowMin).isSolid())
            {
                return getTile(colMin, rowMin);
            }
            else
            {
                return getTile(colMin, rowMax);
            }
        }
        else if (dx > 0 && (getTile(colMax, rowMin).isSolid() || getTile(colMax, rowMax).isSolid()))
        {
            if (getTile(colMax, rowMin).isSolid())
            {
                return getTile(colMax, rowMin);
            }
            else
            {
                return getTile(colMax, rowMax);
            }
        }
        else if (dy < 0 && (getTile(colMin, rowMin).isSolid() || getTile(colMax, rowMin).isSolid()))
        {
            if (getTile(colMin, rowMin).isSolid())
            {
                return getTile(colMin, rowMin);
            }
            else
            {
                return getTile(colMax, rowMin);
            }
        }
        else if (dy > 0 && (getTile(colMin, rowMax).isSolid() || getTile(colMax, rowMax).isSolid()))
        {
            if (getTile(colMin, rowMax).isSolid())
            {
                return getTile(colMin, rowMax);
            }
            else
            {
                return getTile(colMax, rowMax);
            }
        }
        
        //we did not find a solid tile too close, return null
        return null;
    }
    
    /**
     * Is the character object too close to a solid object
     * @param Character The character we want to check
     * @param dx x-velocity
     * @param dy y-velocity
     * @return true if we are too close to a solid object, false otherwise
     */
    public boolean hasSolidCollision(final Entity entity, final double dx, final double dy) throws Exception
    {
        return (getSolidCollision(entity, dx, dy) != null);
    }
    
    /**
     * Get the x-coordinate
     * @param col Column
     * @return The x coordinate
     */
    public double getCoordinateX (final double col)
    {
        return (getX() + (col * Tile.DEFAULT_DIMENSION));
    }
    
    /**
     * Get the y-coordinate
     * @param row Row
     * @return The y coordinate
     */
    public double getCoordinateY (final double row)
    {
        return (getY() + (row * Tile.DEFAULT_DIMENSION));
    }
    
    /**
     * Render a new image
     * @throws Exception 
     */
    @Override
    public void render() throws Exception
    {
        //clear the existing image
        super.clear();
        
        //render the tiles as one image
        for (int row = 0; row < getTiles().length; row++)
        {
            for (int col = 0; col < getTiles()[0].length; col++)
            {
                //render this tile
                getTile(col, row).render(getGraphics2D(), getImage());
            }
        }
    }
    
    @Override
    public void render(final Graphics graphics) throws Exception
    {
        //if the maze hasn't generated yet
        if (!maze.isGenerated())
        {
            //render progress
            maze.render(graphics);
        }
        else
        {
            //draw our maze image
            super.draw(graphics, getBufferedImage());
        }
    }

    /**
     * This class will represent a locked room and which wall is locked
     */
    public class LockedRoom extends Cell
    {
        private final Room.Wall lockedWall;
        
        private LockedRoom(final Cell cell, final Room.Wall lockedWall)
        {
            super(cell);
            
            //assign the locked wall
            this.lockedWall = lockedWall;
        }
        
        /**
         * Do we have this locked wall?
         * @param col Column
         * @param row Row
         * @param lockedWall The targeted wall
         * @return true if the (column, row) matches as well as the specified wall, otherwise false
         */
        private boolean hasLockedWall(final int col, final int row, final Room.Wall lockedWall)
        {
            if (getCol() != col)
                return false;
            if (getRow() != row)
                return false;
            
            return (this.lockedWall == lockedWall);
        }
    }
}