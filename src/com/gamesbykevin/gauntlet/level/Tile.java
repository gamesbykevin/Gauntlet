package com.gamesbykevin.gauntlet.level;

import com.gamesbykevin.gauntlet.entity.Entity;

/**
 * A Tile is 1 cell that makes up the level
 * @author GOD
 */
public final class Tile extends Entity
{
    /**
     * The default dimensions (width/height) of every tile
     */
    public static final int DEFAULT_DIMENSION = 32;
    
    /**
     * Every tile animation will be 1 frame
     */
    protected static final String KEY = "Default";

    
    /**
     * Is this tile solid, meaning should we apply collision detection
     */
    private boolean solid = false;
    
    public enum Type
    {
        //there are many different walls 
        Wall1, Wall2, Wall3, Wall4, Wall5, Wall6, Wall7, Wall8, Wall9, Wall10, 
        
        //only two locked doors 
        Door1, Door2, 
        
        //2 floors 
        //FloorWall1, FloorDoor1, 
        FloorWall2, FloorDoor2,
        
        //the exit tile
        Exit
    }
    
    /**
     * Here lies the different types of tiles
     */
    public enum Direction
    {
        Single, East, South, West, North, WestEast, NorthSouth, SouthEast, 
        SouthWest, NorthWest, NorthEast, SouthWestEast, 
        NorthSouthWest, NorthWestEast, NorthSouthEast, NorthSouthWestEast
    }
    
    //the type of tile this is
    private Type type;
    
    //the animation of this tile
    private Direction direction;
    
    protected Tile(final double col, final double row, final Direction direction, final Type type)
    {
        //store the tile direction
        setDirection(direction);
        
        //store the tile type
        setType(type);
        
        super.setCol(col);
        super.setRow(row);
    }
    
    /**
     * Assign the type.<br>
     * Depending on the type, will also determine if this tile is solid
     * @param type Type of animation to show
     */
    protected final void setType(final Type type)
    {
        this.type = type;
        
        /**
         * If this is a door or a wall<br>
         * Then the tile is solid
         */
        setSolid(isDoor() || isWall());
    }
    
    /**
     * Assign the direction
     * @param direction Direction of animation
     */
    protected final void setDirection(final Direction direction)
    {
        this.direction = direction;
    }
    
    /**
     * Assign the animation based on the current type and direction
     * @throws Exception 
     */
    protected void assignAnimation() throws Exception
    {
        int x;
        int y;
        
        switch (direction)
        {
            case Single:
                x = 0;
                y = 0;
                break;
                
            case East:
                x = 34;
                y = 0;
                break;
                
            case South:
                x = 68;
                y = 0;
                break;
                
            case West:
                x = 102;
                y = 0;
                break;
                
            case North:
                x = 0;
                y = 34;
                break;
                
            case WestEast:
                x = 34;
                y = 34;
                break;
                
            case NorthSouth:
                x = 68;
                y = 34;
                break;
                
            case SouthEast:
                x = 102;
                y = 34;
                break;
                
            case SouthWest:
                x = 0;
                y = 68;
                break;
                
            case NorthWest:
                x = 34;
                y = 68;
                break;
                
            case NorthEast:
                x = 68;
                y = 68;
                break;
                
            case SouthWestEast:
                x = 102;
                y = 68;
                break;
                
            case NorthSouthWest:
                x = 0;
                y = 102;
                break;
                
            case NorthWestEast:
                x = 34;
                y = 102;
                break;
                
            case NorthSouthEast:
                x = 68;
                y = 102;
                break;
                
            case NorthSouthWestEast:
                x = 102;
                y = 102;
                break;
                
            default:
                throw new Exception("Type is not setup here: " + type.toString());
        }
        
        switch (type)
        {
            case Wall1:
                x += 484;
                y += 4;
                break;
                
            case Wall2:
                x += 622;
                y += 4;
                break;
                
            case Wall3:
                x += 760;
                y += 4;
                break;
                
            case Wall4:
                x += 484;
                y += 142;
                break;
                
            case Wall5:
                x += 622;
                y += 142;
                break;
                
            case Wall6:
                x += 760;
                y += 142;
                break;
                
            case Wall7:
                x += 484;
                y += 280;
                break;
                
            case Wall8:
                x += 622;
                y += 280;
                break;
                
            case Wall9:
                x += 760;
                y += 280;
                break;
                
            case Wall10:
                x += 898;
                y += 280;
                break;
                
            case Door1:    
                x += 484;
                y += 418;
                break;
                
            case Door2:
                x += 622;
                y += 418;
                break;
                
            /*
            case FloorWall1:
                x += 484;
                y += 556;
                break;
                
            case FloorDoor1:
                x += 760;
                y += 556;
                break;
            */
                
            case FloorWall2:
                x += 622;
                y += 556;
                break;
                
            case FloorDoor2:
                x += 898;
                y += 556;
                break;
                
            case Exit:
                x = 794;
                y = 520;
                break;
                
            default:
                throw new Exception("Type is not setup here: " + type.toString());
        }
        
        //add the animation
        super.addAnimation(KEY, x, y, DEFAULT_DIMENSION, DEFAULT_DIMENSION);
            
        //set the dimensions
        super.setDimensions();
    }
    
    /**
     * Get the type of tile
     * @return The type of tile
     */
    public Type getType()
    {
        return this.type;
    }
    
    /**
     * Get the direction of the tile
     * @return The direction of the tile
     */
    public Direction getDirection()
    {
        return this.direction;
    }
    
    /**
     * Is this tile a wall?
     * @return true if the type matches any of the wall types, false otherwise
     */
    public boolean isWall()
    {
        switch (getType())
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
                return true;
                
            default:
                return false;
        }
    }
    
    /**
     * Is this tile a door?
     * @return true if the type matches any of the door types, false otherwise
     */
    public boolean isDoor()
    {
        switch (getType())
        {
            case Door1:
            case Door2:
                return true;
                
            default:
                return false;
        }
    }
    
    /**
     * Is this tile part of the floor?
     * @return true if the type matches any of the floor types, false otherwise
     */
    public boolean isFloor()
    {
        switch (getType())
        {
            //case FloorWall1:
            //case FloorDoor1:
            case FloorWall2:
            case FloorDoor2:
                return true;
                
            default:
                return false;
        }
    }
    
    /**
     * Is this tile part of the exit?
     * @return true if the type is exit, false otherwise
     */
    public boolean isExit()
    {
        switch (getType())
        {
            case Exit:
                return true;
                
            default:
                return false;
        }
    }
    
    /**
     * Mark the tile as solid or not
     * @param solid true if we want to prevent the characters from walking through, false otherwise
     */
    public void setSolid(final boolean solid)
    {
        this.solid = solid;
    }
    
    /**
     * Is this tile solid?
     * @return true if we want to check for collision, false otherwise
     */
    public boolean isSolid()
    {
        return this.solid;
    }
}