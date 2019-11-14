package application;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Main.java
 *	Purpose:
 *	Extra Notes:
 */
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{	
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1000,588);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			Canvas canvas = new Canvas(1000, 588);
		    root.getChildren().add(canvas);
		 
		    ArrayList<String> input = new ArrayList<>();
		    
		    GraphicsContext gc = canvas.getGraphicsContext2D();
		    Image map = new Image("Images/Dungeon.jpg");
		    //Instantiate player
		    Player player = new Player();
		    //render the player
		    gc.drawImage(map, 0, 0);
		    player.render(gc);
			//Instantiate player
			Player player1 = new Player();
			//render the player
			player1.render(gc);
			
			
			//KEY LISTENER
			/*
			scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
					String code = event.getCode().toString();
					System.out.println(code + " Pressed");
                    if(!input.contains(code))
                    {
                    	input.add(code);
                    }
				}
			});
			
		    scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
					String code = event.getCode().toString();
					System.out.println(code + " Released");
					input.remove(code);
				}
			});
		    
		    
		    
		    new AnimationTimer() {
				
				@Override
				public void handle(long now) {
					// TODO Auto-generated method stub
					if(input.contains("DOWN"))
					{
						player.setVelocity(0, 5);
						player.move();
						gc.clearRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
						drawBackground(gc);
						player.render(gc);
					}
					else
					{
						player.setVelocity(0, 0);
					}
					
					if(input.contains("UP"))
					{
						player.setVelocity(0, -5);
						player.move();
						gc.clearRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
						drawBackground(gc);
						player.render(gc);
					}
					else
					{
						player.setVelocity(0, 0);
					}
					if(input.contains("LEFT"))
					{
						player.setVelocity(-5, 0);
						player.move();
						gc.clearRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
						drawBackground(gc);
						player.render(gc);
					}
					else
					{
						player.setVelocity(0, 0);
					}
					if(input.contains("RIGHT"))
					{
						player.setVelocity(5, 0);
						player.move();
						gc.clearRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
						drawBackground(gc);
						player.render(gc);
					}
					else
					{
						player.setVelocity(0, 0);
					}
					gc.setFill(null);
					gc.fillText("Player Coords: (" + player.getX() + " : " + player.getY() + ")" , 100, 50);
				}
			}.start();
			Canvas canvas = new Canvas( 512, 512 );
			root.getChildren().add( canvas );

			GraphicsContext gc = canvas.getGraphicsContext2D();*/
			primaryStage.show();
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
