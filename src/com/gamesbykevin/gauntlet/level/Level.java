package com.gamesbykevin.gauntlet.level;

import com.gamesbykevin.framework.awt.CustomImage;
import com.gamesbykevin.framework.base.Cell;
import com.gamesbykevin.framework.maze.Maze;
import com.gamesbykevin.framework.maze.algorithm.*;

import com.gamesbykevin.gauntlet.characters.Character;
import com.gamesbykevin.gauntlet.engine.Engine;
import com.gamesbykevin.gauntlet.entity.Entity;
import com.gamesbykevin.gauntlet.shared.IElement;
import com.gamesbykevin.gauntlet.shared.Shared;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

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
    public static final int MIN_ROOM_DIMENSIONS = 3;
    
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
    
    public Level(final int cols, final int rows, final int roomDimensions, final Image image) throws Exception
    {
        super((roomDimensions * cols) * Tile.DEFAULT_DIMENSION, (roomDimensions * rows) * Tile.DEFAULT_DIMENSION);
        
        //store the sheet containing all the graphics for a level
        super.setImage(image);
        
        //assign the room dimensions
        this.roomDimensions = roomDimensions;
        
        //make sure we meet the requirement
        if (this.roomDimensions < MIN_ROOM_DIMENSIONS)
            this.roomDimensions = MIN_ROOM_DIMENSIONS;
        
        //create a maze
        maze = new RecursiveBacktracking(cols, rows);
        maze.getProgress().setScreen(0, 0, Shared.ORIGINAL_WIDTH, Shared.ORIGINAL_HEIGHT);
        maze.getProgress().setDescription("Creating level.. ");
        
        //create array for our tiles
        this.tiles = new Tile[roomDimensions * rows][roomDimensions * cols];
        
        //create a new window where the tiles will be rendered
        this.window = new Rectangle(-Tile.DEFAULT_DIMENSION * 1, -Tile.DEFAULT_DIMENSION * 1, Shared.ORIGINAL_WIDTH + (Tile.DEFAULT_DIMENSION * 2), Shared.ORIGINAL_HEIGHT + (Tile.DEFAULT_DIMENSION * 2));
        
        //start location
        super.setCol(0);
        super.setRow(0);
    }
    
    @Override
    public void dispose()
    {
        super.dispose();
        
        maze.dispose();
        maze = null;
        
        for (int col = 0; col < tiles[0].length; col++)
        {
            for (int row = 0; row < tiles.length; row++)
            {
                tiles[row][col].dispose();
                tiles[row][col] = null;
            }
        }
        
        tiles = null;
    }
    
    /**
     * Get the room dimensions
     * @return The size (columns, rows) of each room for this level
     */
    protected int getRoomDimensions()
    {
        return this.roomDimensions;
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
                //create the tiles
                LevelHelper.createTiles(this);
                
                //render a new image
                render();
            }
        }
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
     * Is the character object too close to a wall
     * @param Character The character we want to check
     * @param dx x-velocity
     * @param dy y-velocity
     * @return true if we are too close to a wall, false otherwise
     */
    public boolean hasWallCollision(final Character character, final double dx, final double dy)
    {
        int colMin = (int)(character.getCol() - character.getDistance());
        int colMax = (int)(character.getCol() + character.getDistance());
        int rowMin = (int)(character.getRow() - character.getDistance());
        int rowMax = (int)(character.getRow() + character.getDistance());
        
        if (dx < 0 && (getTile(colMin, rowMin).isWall() || getTile(colMin, rowMax).isWall()))
        {
            return true;
        }
        else if (dx > 0 && (getTile(colMax, rowMin).isWall() || getTile(colMax, rowMax).isWall()))
        {
            return true;
        }
        else if (dy < 0 && (getTile(colMin, rowMin).isWall() || getTile(colMax, rowMin).isWall()))
        {
            return true;
        }
        else if (dy > 0 && (getTile(colMin, rowMax).isWall() || getTile(colMax, rowMax).isWall()))
        {
            return true;
        }
        
        //we did not find a wall, return false
        return false;
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
            maze.render(graphics);
        }
        else
        {
            //draw our maze image
            super.draw(graphics, getBufferedImage());
        }
    }
}