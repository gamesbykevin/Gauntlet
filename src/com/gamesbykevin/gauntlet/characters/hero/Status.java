package com.gamesbykevin.gauntlet.characters.hero;

import com.gamesbykevin.framework.awt.CustomImage;
import java.awt.Color;
import java.awt.Font;

/**
 * Hero status
 * @author GOD
 */
public final class Status extends CustomImage
{
    //the player status
    private int health = 0, keys = 0, potions = 0, score = 0;
    
    
    public Status(final int width, final int height)
    {
        super(width, height);
    }
    
    public void setHealth(final int health)
    {
        this.health = health;
    }
    
    public void setKeys(final int keys)
    {
        this.keys = keys;
    }
    
    public void setPotions(final int potions)
    {
        this.potions = potions;
    }
    
    public void setScore(final int score)
    {
        this.score = score;
    }
    
    @Override
    public void render()
    {
        //clear image
        super.clear();
        super.getGraphics2D().setFont(super.getGraphics2D().getFont().deriveFont(Font.BOLD, 12f));
        super.getGraphics2D().setColor(Color.WHITE);
        super.getGraphics2D().drawString("Health: " + health,   10,  15);
        super.getGraphics2D().drawString("Keys: " + keys,       100, 15);
        super.getGraphics2D().drawString("Potions: " + potions, 170, 15);
        super.getGraphics2D().drawString("Score: " + score,     250, 15);
    }
}
