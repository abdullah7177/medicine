/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Total_expensesController implements Initializable {

    @FXML
    private AnchorPane total_pane;

    /**
     * Initializes the controller class.
     */
    
    
      @FXML
    public void close(){
    System.exit(0);
    
    }
    
    
    @FXML
private void handleBACKButton(ActionEvent event) throws IOException {
//    try {
        // Load the target FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("historyMonthly.fxml"));
        Parent root = loader.load();

        // Create a new scene and stage
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        // Apply initial black overlay
//        Rectangle fadeOverlay = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
//        fadeOverlay.widthProperty().bind(scene.widthProperty());
//        fadeOverlay.heightProperty().bind(scene.heightProperty());
//        ((Pane) root).getChildren().add(fadeOverlay);

        stage.setScene(scene);
        stage.show();

//        // Start fade-out animation
//        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
//        fadeOut.setFromValue(1.0);  // Fully black
//        fadeOut.setToValue(0.0);    // Fully visible content
//        fadeOut.setInterpolator(Interpolator.EASE_BOTH);
//        fadeOut.setOnFinished(e -> ((Pane) root).getChildren().remove(fadeOverlay));
//        fadeOut.play();
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        Platform.runLater(() -> {
            Stage stage = (Stage) total_pane.getScene().getWindow();
            if (stage != null) {
                stage.setFullScreen(true);
            }
   
        });
    }    
    
}
