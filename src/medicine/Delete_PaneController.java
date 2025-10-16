/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */



public class Delete_PaneController implements Initializable {

    @FXML
    private AnchorPane Delete_pane;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
      private void OpenDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Billing_Point.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) close.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setTitle("Add Products");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
     

    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       }    

    @FXML
    private void close(ActionEvent event) {
    }
    
}
