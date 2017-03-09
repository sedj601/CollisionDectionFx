/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisiondection;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author sedj601
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label lblMain;
    @FXML private Polygon polyOne, polyTwo;    
    
    final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        polyOne.setOnMousePressed((MouseEvent event) -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        
        polyOne.setOnMouseDragged((MouseEvent event) -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            polyOne.setLayoutX(polyOne.getLayoutX()+deltaX);
            polyOne.setLayoutY(polyOne.getLayoutY()+deltaY);
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            
            Shape intersect = Shape.intersect(polyOne, polyTwo);
            
            if(intersect.getBoundsInLocal().getWidth() != -1)
            {
                System.out.println("This object can overlap other the other object!");
                lblMain.setText("Collision detected!");
            }
            else
            {
                intersect.getBoundsInLocal().getWidth();
                lblMain.setText("Collision not detected!");
            }            
        });
        
        polyTwo.setOnMousePressed((MouseEvent event) -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        
        polyTwo.setOnMouseDragged((MouseEvent event) -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            polyTwo.setLayoutX(polyTwo.getLayoutX() + deltaX);
            polyTwo.setLayoutY(polyTwo.getLayoutY() + deltaY);
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            
            Shape intersect = Shape.intersect(polyOne, polyTwo);
            {
                if(intersect.getBoundsInLocal().getWidth() != -1)
                {      
                    System.out.println("This object can not overlap other the other object!");
                    polyTwo.setLayoutX(polyTwo.getLayoutX() - deltaX);
                    polyTwo.setLayoutY(polyTwo.getLayoutY() - deltaY);
                    lblMain.setText("Collision detected!");
                }
                else
                {
                    lblMain.setText("Collision not detected!");
                }
            }
        });   
    }
}
