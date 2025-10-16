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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    private Button close_Btn;
    @FXML
    private Button Add_Btn;
    @FXML
    private AnchorPane Dashboard;
    @FXML
    private Button Delete_Btn;
    @FXML
    private Button Update_Btn;
    @FXML
    private TreeView<String> Tree_View;
    @FXML
    private AnchorPane add_panes;
    @FXML
    private AnchorPane Tree_Dash;
    @FXML
    private AnchorPane add_pane;
    @FXML
    private AnchorPane update_pane;
    @FXML
    private AnchorPane delete_pane;

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
           
           
           
   



        // Root node (invisible)
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);

        // Main nodes
        TreeItem<String> dashboard = new TreeItem<>("Dashboard");
        TreeItem<String> addProductMain = new TreeItem<>("Add Products");

        // Sub-items for Add Product
        TreeItem<String> addProduct = new TreeItem<>("Add Product");
        TreeItem<String> updateProduct = new TreeItem<>("Update Product");
        TreeItem<String> deleteProduct = new TreeItem<>("Delete Product");
        TreeItem<String> viewProduct = new TreeItem<>("View Product");

        // Add sub-items
        addProductMain.getChildren().addAll(addProduct, updateProduct, deleteProduct, viewProduct);

        // Add to root
        rootItem.getChildren().addAll(dashboard, addProductMain);

        // Set root to TreeView
        Tree_View.setRoot(rootItem);
        Tree_View.setShowRoot(false);

        // Initially show dashboard, hide others
        Tree_Dash.setVisible(true);
        add_panes.setVisible(false);

        // Handle selection event
        Tree_View.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                String selected = newValue.getValue();

                // Dashboard selected
                if (selected.equals("Dashboard")) {
                    Tree_Dash.setVisible(true);
                    add_panes.setVisible(false);
                    add_pane.setVisible(false);
                    update_pane.setVisible(false);
                    delete_pane.setVisible(false);
                }
                // Add Products selected
                else if (selected.equals("Add Products")) {
                    Tree_Dash.setVisible(false);
                    add_panes.setVisible(true);
                    add_pane.setVisible(false);
                    update_pane.setVisible(false);
                    delete_pane.setVisible(false);
                }
                // Add Product selected
                else if (selected.equals("Add Product")) {
                    Tree_Dash.setVisible(false);
                    add_panes.setVisible(false);
                    add_pane.setVisible(true);
                    update_pane.setVisible(false);
                    delete_pane.setVisible(false);
                }
                // Update Product
                 else if (selected.equals("Update Product")) {
                    Tree_Dash.setVisible(false);
                    add_panes.setVisible(false);
                    add_pane.setVisible(false);
                    update_pane.setVisible(true);
                    delete_pane.setVisible(false);
                }
                 // Delete Product
                 else if (selected.equals("Delete Product")) {
                    Tree_Dash.setVisible(false);
                    add_panes.setVisible(false);
                    add_pane.setVisible(false);
                    update_pane.setVisible(false);
                    delete_pane.setVisible(true);
                }
                 
                // Hide all others for now
                else {
                    Tree_Dash.setVisible(false);
                    add_panes.setVisible(false);
                }
            }
        });
    }


        
    }    
    

