package application;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Tile.java
 *	Purpose: Creates a tile image
 *	Extra Notes: Extends the Sprite class to make our tile a sprite image
 */
public class Tile extends Sprite
{
	//PRIVATE Global Variables
	private boolean visible;
	
	//Tile Super Constructor: Creates our tile into a sprite
	public Tile(String imageURL) 
	{
		super(imageURL);
		visible = true;
	}
	//checks if tile is visible
	public boolean isVisible()
	{
		return visible;
	}
	//sets tile visibility
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

}
