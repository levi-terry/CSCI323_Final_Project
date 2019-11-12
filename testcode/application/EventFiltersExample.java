package application;

import javafx.application.Application; 
import static javafx.application.Application.launch;

import java.util.Random;

import javafx.event.EventHandler;
 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 

import javafx.scene.text.Font; 
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text; 
import javafx.stage.Stage; 
         
public class EventFiltersExample extends Application { 
   @Override 
   public void start(Stage stage) {      
      //Drawing a Circle 
      Circle circle = new Circle(); 
      //Setting the position of the circle 
      circle.setCenterX(300.0f); 
      circle.setCenterY(135.0f); 
      
      //Setting the radius of the circle 
      circle.setRadius(25.0f); 
      
      //Setting the color of the circle 
      circle.setFill(Color.BROWN); 
      
      //Setting the stroke width of the circle 
      circle.setStrokeWidth(20);      
       
      //Setting the text 
      Text text = new Text("Click on the circle to change its color"); 
      
    //Setting the font of the text 
      text.setFont(Font.font(null, FontWeight.BOLD, 15));     
      
      //Setting the color of the text 
      text.setFill(Color.CRIMSON); 
  
      //setting the position of the text 
      text.setX(150); 
      text.setY(50); 
      Text text1 = new Text("tHIS IS A TEST");
      //Creating the mouse event handler 
      
      //Registering the event filter 
      Image back = new Image("Users\\Tizzle\\eclipse-workspace\\CSCI323_Exam\\application\\Battleground1.png"); 
      //Creating a Group object
      ImageView b = new ImageView(back);
      Group root = new Group(circle, text); 
      Group branch = new Group(b, text);   
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
      Scene scene1 = new Scene(branch, 600,300);
      Random r = new Random();
      //Setting the fill color to the scene 
      scene.setFill(Color.rgb(r.nextInt(255) + 1, r.nextInt(255) + 1, r.nextInt(255) + 1));  
      //Setting title to the Stage 
      stage.setTitle("Event Filters Example");       
         
      //Adding scene to the stage 
      stage.setScene(scene); 
      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
          @Override 
          public void handle(MouseEvent e) { 
             System.out.println("Hello World"); 
             Random r = new Random();
             stage.setScene(scene1);
             circle.setFill(Color.rgb(r.nextInt(255) + 1, r.nextInt(255) + 1, r.nextInt(255) + 1));
          } 
       };  
       circle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);   

      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
