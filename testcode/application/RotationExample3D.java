package application;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.shape.Box; 
import javafx.scene.transform.Rotate; 
import javafx.scene.transform.Translate; 
import javafx.stage.Stage; 
         
public class RotationExample3D extends Application { 
   @Override 
   public void start(Stage stage) { 
      //Drawing a Box 
      Box box = new Box();  
      
      //Setting the properties of the Box 
      box.setWidth(150.0); 
      box.setHeight(150.0);   
      box.setDepth(150.0);       
       
      //Creating the translation transformation 
      Translate translate = new Translate();       
      translate.setX(400); 
      translate.setY(150); 
      translate.setZ(25);  
       
      Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS); 
      Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS); 
      Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS); 
      rxBox.setAngle(30); 
      ryBox.setAngle(50); 
      rzBox.setAngle(30); 
      box.getTransforms().addAll(translate,rxBox, ryBox, rzBox); 
        
      //Creating a Group object  
      Group root = new Group(box); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Drawing a cylinder"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}