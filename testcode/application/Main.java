package application;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Main.java
 *	Purpose: Application driver.
 *	Extra Notes: Extends the Application class as required by JavaFX.
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	//Method inherited from Application: creates the main frame for the application
	@Override
	public void start(Stage primaryStage) {
		//Instead of creating our world in the main, we created a GameWorld class that extends a Stage so we may maintain a MVC and linearity
		try{
			//These 6 lines of code instantiate our games frame
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1240,960);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Dungeons Deep - version 1.06");
			Canvas canvas = new Canvas(1240, 960);
			root.getChildren().add(canvas);

			//Class to handle our 2D Graphics
			GraphicsContext gc = canvas.getGraphicsContext2D();
			Image mainmenu = new Image("Images/mainmenu.png");
			Image play = new Image("Images/playbtn.png");
			
			gc.drawImage(mainmenu, 0, 0);
			gc.drawImage(play, 400, 600);
			EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
				@Override 
				public void handle(MouseEvent e) { 
					if(e.getX() > 390 && e.getX() < (390 + play.getWidth()))
					{
						new GameWorld();	
						primaryStage.close();
					}

				} 
			};   
			//Adding event Filter 
			primaryStage.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
