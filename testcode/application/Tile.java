package application;

/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Tile.java
 *	Purpose: Creates a tile image
 *	Extra Notes: Extends the Sprite class to make our tile a sprite image
 */

import javafx.geometry.Rectangle2D;

public class Tile extends Sprite {
	//PRIVATE Global Variables
	private boolean visible;
	private int damage, reward;
	//Tile Super Constructor: Creates our tile into a sprite
	public Tile(String imageURL) {
		super(imageURL);
		visible = true;
		damage = 0;
		reward = 0;
	}
	//checks if tile is visible
	public boolean isVisible() {
		return visible;
	}
	//sets tile visibility
	public void setVisible(boolean visible)	{
		this.visible = visible;
	}
	//returns the tiles reward if the tile is visible
	public int getReward() {
		if(isVisible())
			return reward;
		else
			return 0;
	}
	//Gets a more accurate 2D boundary for our top of our image used for collision detection
    public Rectangle2D getEdgeBoundary() {
        return new Rectangle2D(getX(), getY(), getWidth(), getHeight() - (getHeight() - 20));
    }
    //returns the tiles damage it may inflict
	public int getDamage() {
		return damage;
	}
	//straightforward from here boys
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
}