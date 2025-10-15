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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Billing_PointController implements Initializable {

    @FXML
    private StackPane Billing_StackPane;
    @FXML
    private VBox sidebar;
    @FXML
    private Button dashboard_Btn;
    @FXML
    private Button close_Btn;
    @FXML
    private Button Add_Btn;
    @FXML
    private AnchorPane Dashboard;
    @FXML
    private Button Delete_Btn;
    @FXML
    private Button Update_Btn;

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private void OpenAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Product.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Add_Btn.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setTitle("Add Products");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void OpenUpdate() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_pane.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Add_Btn.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setTitle("Add Products");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
     private void OpenDelete() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete_Pane.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Add_Btn.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setTitle("Add Products");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    public void close(){
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           Platform.runLater(() -> {
        Stage stage = (Stage) Billing_StackPane.getScene().getWindow();
        if (stage != null) {
            stage.setFullScreen(true);
        }
    });
           
           
       
        
    }    
    
}
