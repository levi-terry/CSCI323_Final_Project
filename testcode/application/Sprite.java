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

public class Sprite
{
    private Image image;
    private double posX;
    private double posY;    
    private double dx;
    private double dy;
    private double width;
    private double height;
    
    //CONSTRUCTOR - Instantiates the Sprite Image, and sets the width, height, and possible position of the sprite
    public Sprite(String imageURL)
    {
    	image = new Image(imageURL);
    	width = image.getWidth();
    	height = image.getHeight();
    	
    	//dx and dy is our user velocity. i.e. 10 would be 10 pixels per frame rate
    	//for now, dx and dy will be set to 1
    	dx = 1;
    	dy = 1;
    }
    
    //Method to update position of the sprite
    public void updatePosition(double x, double y)
    {
    	posX = x;
    	posY = y;
    }
    //Method to update position of the sprite relative to time.
    public void update(double time)
    {
        posX = dx * time;
        posY = dy * time;
    }
 
    //RENDERS our sprite to the screen by calling GraphicsContext
    public void render(GraphicsContext gc)
    {
        gc.drawImage(image, posX, posY);
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
}