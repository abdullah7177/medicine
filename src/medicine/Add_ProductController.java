/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package medicine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Add_ProductController implements Initializable {

    @FXML
    private AnchorPane Add_Pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
             Platform.runLater(() -> {
        Stage stage = (Stage) Add_Pane.getScene().getWindow();
        if (stage != null) {
            stage.setFullScreen(true);
        }
    });
           
        
    
    }    
    
    
    
    
}
