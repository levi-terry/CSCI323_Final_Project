package application;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: GameWorld.java
 *	Purpose: Creates our world by creating a frame with a canvas inside and drawing images to the canvas0.
 *	Extra Notes: Extends the Stage class and essentially makes it a JavaFX Stage (Which is a Window/JFrame/Whatever_U_Call_It)
 */
public class GameWorld extends Stage
{
	//GLOBAL VARIABLES
	private Player player;
	private Tile tiles[];
	private Tile coins[];
	private ArrayList<String> keyinput = new ArrayList<>();
	
	//DEFAULT CONSTRUCTOR: Point of Execution
	public GameWorld()
	{
		renderGame();
	}
	
	//Method to instantiate our sprites & world tiles
	private void instantiateSprite()
	{
		//Instantiate player
	    player = new Player();
	    player.setVelocity(0, 0);
	  	
	    //Instantiates tiles
	  	instantiateTiles();
	}
	//Creates our tiles (Not calling this method would result in a NullPointerException)
	private void instantiateTiles()
	{
		//Generates an int array of X and Y coords of where our tiles should be drawn
		int[] tileXPos = {125,300,250,625,550,420,690,920,720,800};
		int[] tileYPos = {125,300,250,625,550,420,690,920,720,900};
		
		int[] coinPos = {900,700,500,400,600};
		//Instantiates the tiles with our tile images (AS OF NOW)
		tiles = new Tile[10];
	  	for(int i = 0; i < tiles.length/2; i++)
	  	{
	  		tiles[i] = new Tile("Images/TileSprites/LevelObjects/CavePlatform.png");
	  		tiles[i].updatePosition(tileXPos[i], tileYPos[i]);
	  	}
	  	for(int i = tiles.length/2; i < tiles.length; i++)
	  	{
	  		tiles[i] = new Tile("Images/TileSprites/LevelObjects/DungeonPlatform.png");
	  		tiles[i].updatePosition(tileXPos[i], tileYPos[i]);
	  	}
	  	
	  	//instantiates coins
	  	coins = new Tile[5];
	  	for(int i = 0; i < coins.length; i++)
	  	{
	  		coins[i] = new Tile("Images/Coin.png");
	  		coins[i].updatePosition(coinPos[i], coinPos[i]);
	  	}
	}
	//Renders our Tiles
	private void renderTiles(GraphicsContext gc)
	{
		//iterate through tiles in the array and render
		for(Tile t : tiles)
		{
			//We have this conditional for now for safety but it's not required
			if(t.isVisible())
			{
				t.render(gc);
			}
		}
		//Do the same for coins
		for(Tile c : coins)
		{
			if(c.isVisible())
			{
				c.render(gc);
			}
		}
	}
	//This method is called continuously throughout our game loop to continuously render our objects to the screen
	private void updateSprites(GraphicsContext gc)
	{
		drawBackground(gc);
		
		//Check is player is alive, then update player
		if(player.alive)
		{
			player.move();
			player.render(gc);
		}
		
		//checkCollisions is called when we are updating our sprites
		checkCollisions(gc);
	}
	//METHOD drawBackground() is called to draw our background image before drawing our sprite and tile objects on top
	private void drawBackground(GraphicsContext gc)
	{
		Image background = new Image("Images/TileSprites/Background.png");
		gc.drawImage(background, 0, 0);
		
		//method renderTiles() is appended to this method because this method is called continuously in the game loop
		//WITHOUT renderTiles() our background would be drawn over our tiles.
		renderTiles(gc);	
		
		//This method sets our text font and font size
		gc.setFont(new Font(Font.getDefault().getName(), 24.0));
		
	}
	
	//This is GameWorlds main execution point. Our game loop is handled in this method and our sprites are instantiates and updated in this method as well
	private void renderGame()
	{
		instantiateSprite();
		
		//These 6 lines of code instantiate our games frame
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,1240,960);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		setScene(scene);
		setTitle("Dungeons Deep - version 1.06");
		Canvas canvas = new Canvas(1240, 960);
	    root.getChildren().add(canvas);
	    
	    //Class to handle our 2D Graphics
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    
	    
	    //render the player, background, and tiles
	    drawBackground(gc);
	    player.render(gc);
		
		
		//INSTANTIATES KEY LISTENER: Key Listener events are called and handled in the game loop
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				String code = event.getCode().toString();
				System.out.println(code + " Pressed");
                if(!keyinput.contains(code))
                {
                	keyinput.add(code);
                }
			}
		});
	    scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				String code = event.getCode().toString();
				System.out.println(code + " Released");
				keyinput.remove(code);
			}
		});
	   
	    
	    //**GAME LOOP**: AnimationTimer() is our main game loop, it updates our objects in the game real time
	    new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				//These 3 Method calls are to be called continuously while the game is running
				player.implementGravity(gc);
				updateSprites(gc);
				keyListener(keyinput, gc, now);
				
				//OPTIONAL: Added Player Coordinates to the screen 
				gc.fillText("Player Coords: (" + player.getX() + " : " + player.getY() + ")" , 100, 500);
				
			}
		}.start();
		show();
	}
	//This method handles our KeyListener by taking in Keyboard input as a string and the current game time to move the player with the movePlayer() method
	private void keyListener(ArrayList<String> keyinput, GraphicsContext gc, long now)
	{
		if(keyinput.contains("DOWN"))
		{
			player.movePlayer(gc, "DOWN", now);
		}
		if(keyinput.contains("UP"))
		{
			player.movePlayer(gc, "UP", now);
		}
		if(keyinput.contains("LEFT"))
		{
			player.movePlayer(gc, "LEFT", now);
		}
		if(keyinput.contains("RIGHT"))
		{
			player.movePlayer(gc, "RIGHT", now);
		}
		player.setVelocity(0,0);
		if(keyinput.isEmpty())
		{
			player.movePlayer(gc, "STOP", now);
		}
	}
	
	//This is our check collision method. We iterate through the tiles
	private void checkCollisions(GraphicsContext gc)
	{
		//check for platform collision
		for(Tile t : tiles)
		{
			if(player.intersects(t))
			{
				System.out.println("PLAYER COLLIDING WITH TILE");
				player.setY(t.getY() - 50);
			}
		}
		
		//check for coin collision
		for(Tile c : coins)
		{
			if(player.intersects(c))
			{
				System.out.println("You found a coin and this is a test... coin now going to sleep");
				c.setVisible(false);
			}
		}
	}
	//Draws the Game Start Screen has only two options for now; start & quit
	private void drawSplashScreen(GraphicsContext gc)
	{
		
	}
	//Draws the Game Over Screen
	private void drawGameOver(GraphicsContext gc)
	{
		//DRAW GAME OVER SCREEN HERE gc.drawImage or gc.fillText
	}
} 
	

