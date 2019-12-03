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
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Main.java
 *	Purpose: This is our programs driver.
 *	Extra Notes: Extends the Application class as required by JavaFX
 */

public class Main extends Application {
	
	
	//Method inherited from Application: creates the main frame for the application
	@Override
	public void start(Stage primaryStage) {
		//Instead of creating our world in the main, we created a GameWorld class that extends a Stage so we may maintain a MVC and linearity
		try{
			primaryStage = new GameWorld();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
