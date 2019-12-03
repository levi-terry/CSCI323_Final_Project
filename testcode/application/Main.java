package application;
/*
 *	AUTHORS: John Boyle, Colton Gerth, Levi Terry, Brendan Hagan
 *	CLASS: Main.java
 *	Purpose: Application driver.
 *	Extra Notes: Extends the Application class as required by JavaFX.
 */
import javafx.application.Application;
import javafx.stage.Stage;

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
