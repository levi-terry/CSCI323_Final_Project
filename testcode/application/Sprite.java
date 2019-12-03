package application;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Sprite.java
 *	Purpose:
 *	Extra Notes:
 */
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	//PRIVATE Global Variables
    private Image image;
    private double posX;
    private double posY;    
    private double dx;
    private double dy;
    private double width;
    private double height;
    
    //CONSTRUCTOR - Instantiates the Sprite Image, and sets the width, height, and possible position of the sprite
    public Sprite(String imageURL) {
    	image = new Image(imageURL);
    	width = image.getWidth();
    	height = image.getHeight();
    	
    	//dx and dy is our user velocity. i.e. 10 would be 10 pixels per frame rate
    	//for now, dx and dy will be set to 1
    	dx = 1;
    	dy = 1;
    }
    
    //Method to update position of the sprite
    public void updatePosition(double x, double y) {
    	posX = x;
    	posY = y;
    }

    //Method to update position of the sprite relative to time.
    public void update(double time) {
        posX = dx * time;
        posY = dy * time;
    }
 
    //RENDERS our sprite to the screen by calling GraphicsContext
    public void render(GraphicsContext gc)
    {
        gc.drawImage(image, posX, posY);
    }
    //returns x coordinate
    public double getX() {
		return posX;
	}
    //returns y coordinate
	public double getY() {
		return posY;
	}
	//returns sprite width (used for collision detection)
	public double getWidth() {
		return width;
	}
	//returns sprite height (used for collision detection)
	public double getHeight() {
		return height;
	}
	//returns sprite image
	public Image getImage()
	{
		return image;
	}
	//sets the sprite image (used for animation)
	public void setFrame(Image img)
	{
		image = img;
	}
	//Gets a 2D boundary for our image used for collision detection
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(posX, posY, width, height);
    }
 
    //Returns a boolean if our sprite intersects another sprite
    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects(this.getBoundary());
    }
    //return dx velocity
    public double getDX()
    {
    	return dx;
    }
    //returns dy velocity
    public double getDY()
    {
    	return dy;
    }
    //sets the Y coordinate
    public void setY(double y)
    {
    	posY = y;
    }
    //sets the X coordinate
    public void setX(double x)
    {
    	posX = x;
    }
    //sets the velocity of the sprite
    public void setVelocity(double dx, double dy) {
    	this.dx = dx;
    	this.dy = dy;
    }
    //moves the sprite by adding velocity to x and y coordinates
    public void move() {
    	posX += dx;
    	posY += dy;
    }
}