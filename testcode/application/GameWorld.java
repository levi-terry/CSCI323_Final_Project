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
import javafx.stage.Stage;

public class GameWorld extends Stage
{
	Player player;
	Tile tiles[];
	ArrayList<String> keyinput = new ArrayList<>();;
	String keycode;
	public GameWorld()
	{
		renderGame();
	}
	public void instantiateSprite()
	{
		//Instantiate player
	    player = new Player();
	    player.setVelocity(0, 0);
	    //Instantiate player
	  	Player player1 = new Player();
	  	tiles = new Tile[5];
	  	tiles[0] = new Tile("Images/TileSprites/tile27.png");
	}
	
	public void updateSprites(GraphicsContext gc)
	{
		drawBackground(gc);
		updatePlayer(gc);
		//for(Tile t : tiles)
		//{
			//t.render(gc);
		//}
		//checkCollisions(gc);
	}
	public void updatePlayer(GraphicsContext gc)
	{
		player.move();
		player.render(gc);
	}
	
	public void drawBackground(GraphicsContext gc)
	{
		Image map = new Image("Images/TileSprites/Background.png");
		gc.drawImage(map, 0, 0);
	}
	public void renderGame()
	{
		instantiateSprite();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,1240,960);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		setScene(scene);
		Canvas canvas = new Canvas(1240, 960);
	    root.getChildren().add(canvas);
	    
	    
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    
	    
	    //render the player
	    drawBackground(gc);
	    player.render(gc);
		
		
		//KEY LISTENER
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
	  //KEYBOARD LISTENER
	    new AnimationTimer() {
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(keyinput.contains("DOWN"))
				{
					player.setVelocity(0, 5);
					updateSprites(gc);
				}
				if(keyinput.contains("UP"))
				{
					player.setVelocity(0, -5);
					updateSprites(gc);
				}
				if(keyinput.contains("LEFT"))
				{
					player.setVelocity(-5, 0);
					updateSprites(gc);
				}
				if(keyinput.contains("RIGHT"))
				{
					player.setVelocity(5, 0);
					updateSprites(gc);
				}
				player.setVelocity(0,0);
				
				//checkCollisions(gc);
				gc.fillText("Player Coords: (" + player.getX() + " : " + player.getY() + ")" , 100, 50);
			}
		}.start();
	    //gameLoop(gc);
		show();
	}
	
	public void checkCollisions(GraphicsContext gc)
	{
		for(Tile t : tiles)
		{
			if(player.intersects(t))
			{
				System.out.println("PLAYER COLLIDING WITH TILE");
				player.setY(t.getY() - 10);
			}
		}
		gc.fillText("Player Coords: (" + player.getX() + " : " + player.getY() + ")" , 100, 50);
	}
} 
	

