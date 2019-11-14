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
import javafx.scene.canvas.GraphicsContext;\
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
