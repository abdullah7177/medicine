/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Update_paneController implements Initializable {

    @FXML
    private StackPane Update_Stackepane;
    @FXML
    private AnchorPane Update_Pane;
    private Button back_to_dashboard;
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
                Platform.runLater(() -> {
        Stage stage = (Stage) Update_Pane.getScene().getWindow();
        if (stage != null) {
            stage.setFullScreen(true);
        }
    });

    }    
    
}
