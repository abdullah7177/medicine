
package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

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
    @FXML
    private Button mene_button;

    private boolean sidebarVisible = true;

    // ===============================
    // NAVIGATION BUTTONS (open new scenes)
    // ===============================
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
            stage.setTitle("Update Products");
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
            stage.setTitle("Delete Products");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    // ===============================
    // INITIALIZATION
    // ===============================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Full screen
        Platform.runLater(() -> {
            Stage stage = (Stage) Billing_StackPane.getScene().getWindow();
            if (stage != null) {
                stage.setFullScreen(true);
            }
        });

        sidebar.setTranslateX(0);

        // TreeView setup
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);

        TreeItem<String> dashboard = new TreeItem<>("Dashboard");
        TreeItem<String> addProductMain = new TreeItem<>("Add Products");

        TreeItem<String> addProduct = new TreeItem<>("Add Product");
        TreeItem<String> updateProduct = new TreeItem<>("Update Product");
        TreeItem<String> deleteProduct = new TreeItem<>("Delete Product");
        TreeItem<String> viewProduct = new TreeItem<>("View Product");

        addProductMain.getChildren().addAll(addProduct, updateProduct, deleteProduct, viewProduct);
        rootItem.getChildren().addAll(dashboard, addProductMain);

        Tree_View.setRoot(rootItem);
        Tree_View.setShowRoot(false);

        // Initial pane visibility
        Tree_Dash.setVisible(true);
        add_panes.setVisible(false);
        add_pane.setVisible(false);
        update_pane.setVisible(false);
        delete_pane.setVisible(false);

        // Tree selection listener
        Tree_View.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                String selected = newValue.getValue();

                if (selected.equals("Dashboard")) {
                    showPane(Tree_Dash);
                } else if (selected.equals("Add Products")) {
                    showPane(add_panes);
                } else if (selected.equals("Add Product")) {
                    showPane(add_pane);
                } else if (selected.equals("Update Product")) {
                    showPane(update_pane);
                } else if (selected.equals("Delete Product")) {
                    showPane(delete_pane);
                }
            }
        });
    }

    // ===============================
    // SHOW SELECTED PANE WITH ANIMATION
    // ===============================
    private void showPane(AnchorPane targetPane) {
        Tree_Dash.setVisible(false);
        add_panes.setVisible(false);
        add_pane.setVisible(false);
        update_pane.setVisible(false);
        delete_pane.setVisible(false);

        targetPane.setVisible(true);
        slideInFromRight(targetPane);
    }

    // ===============================
    // SLIDE-IN ANIMATION (RIGHT)
    // ===============================
    private void slideInFromRight(AnchorPane pane) {
        pane.setVisible(true);
        pane.setOpacity(0);
        pane.setTranslateX(150);

        TranslateTransition slide = new TranslateTransition(Duration.millis(300), pane);
        slide.setFromX(100);
        slide.setToX(0);

        FadeTransition fade = new FadeTransition(Duration.millis(300), pane);
        fade.setFromValue(0);
        fade.setToValue(1);

        ParallelTransition smooth = new ParallelTransition(slide, fade);
        smooth.setInterpolator(Interpolator.EASE_BOTH);
        smooth.play();
    }

    // ===============================
    // SIDEBAR TOGGLE — moves all visible panes with it
    // ===============================
    @FXML
    private void toggleSidebar() {
        TranslateTransition sidebarSlide = new TranslateTransition(Duration.millis(400), sidebar);

        // Apply same movement to all panes
        TranslateTransition dashSlide = new TranslateTransition(Duration.millis(400), Tree_Dash);
        TranslateTransition addPanesSlide = new TranslateTransition(Duration.millis(400), add_panes);
        TranslateTransition addPaneSlide = new TranslateTransition(Duration.millis(400), add_pane);
        TranslateTransition updatePaneSlide = new TranslateTransition(Duration.millis(400), update_pane);
        TranslateTransition deletePaneSlide = new TranslateTransition(Duration.millis(400), delete_pane);

        boolean sidebarOpen = sidebar.getTranslateX() == 0;
        double moveX = sidebarOpen ? -sidebar.getWidth() + 50 : 0;

        sidebarSlide.setToX(moveX);
        dashSlide.setToX(moveX);
        addPanesSlide.setToX(moveX);
        addPaneSlide.setToX(moveX);
        updatePaneSlide.setToX(moveX);
        deletePaneSlide.setToX(moveX);

        sidebarSlide.setInterpolator(Interpolator.EASE_BOTH);
        dashSlide.setInterpolator(Interpolator.EASE_BOTH);
        addPanesSlide.setInterpolator(Interpolator.EASE_BOTH);
        addPaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        updatePaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        deletePaneSlide.setInterpolator(Interpolator.EASE_BOTH);

        sidebarSlide.play();
        dashSlide.play();
        addPanesSlide.play();
        addPaneSlide.play();
        updatePaneSlide.play();
        deletePaneSlide.play();
    }
}

























///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
// */
//package medicine;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.animation.FadeTransition;
//import javafx.animation.Interpolator;
//import javafx.animation.ParallelTransition;
//import javafx.animation.TranslateTransition;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TreeItem;
//import javafx.scene.control.TreeView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
///**
// * FXML Controller class
// *
// * @author user
// */
//public class Billing_PointController implements Initializable {
//
//    @FXML
//    private StackPane Billing_StackPane;
//    @FXML
//    private VBox sidebar;
//    @FXML
//    private Button close_Btn;
//    @FXML
//    private Button Add_Btn;
//    @FXML
//    private AnchorPane Dashboard;
//    @FXML
//    private Button Delete_Btn;
//    @FXML
//    private Button Update_Btn;
//    @FXML
//    private TreeView<String> Tree_View;
//    @FXML
//    private AnchorPane add_panes;
//    @FXML
//    private AnchorPane Tree_Dash;
//    @FXML
//    private AnchorPane add_pane;
//    @FXML
//    private AnchorPane update_pane;
//    @FXML
//    private AnchorPane delete_pane;
//    @FXML
//    private Button mene_button;
//
//    /**
//     * Initializes the controller class.
//     */
//    
//    private boolean sidebarVisible = true;
//     @FXML
//    private void OpenAdd() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Product.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) Add_Btn.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            
//            stage.setMaximized(true);
//            stage.setFullScreen(true);
//            stage.setTitle("Add Products");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    private void OpenUpdate() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_pane.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) Add_Btn.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            
//            stage.setMaximized(true);
//            stage.setFullScreen(true);
//            stage.setTitle("Add Products");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @FXML
//     private void OpenDelete() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete_Pane.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) Add_Btn.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            
//            stage.setMaximized(true);
//            stage.setFullScreen(true);
//            stage.setTitle("Add Products");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//     
//      @FXML
//    private void toggleSidebar() {
//        TranslateTransition slide = new TranslateTransition(Duration.millis(400), sidebar);
//
//        if (sidebarVisible) {
//            // move sidebar left (hide)
//            slide.setToX(-sidebar.getWidth() + 50); // leaves just 50px (for button area)
//            sidebarVisible = false;
//        } else {
//            // move sidebar back to original position (show)
//            slide.setToX(0);
//            sidebarVisible = true;
//        }
//
//        slide.setCycleCount(1);
//        slide.setAutoReverse(false);
//        slide.play();
//    }
//    
//    @FXML
//    public void close(){
//        System.exit(0);
//    }
//    //ANIMATION
//   
// @Override
//public void initialize(URL url, ResourceBundle rb) {
//    // Full screen on start
//    Platform.runLater(() -> {
//        Stage stage = (Stage) Billing_StackPane.getScene().getWindow();
//        if (stage != null) {
//            stage.setFullScreen(true);
//        }
//    });
//
//    sidebar.setTranslateX(0);
//
//    // Root node (invisible)
//    TreeItem<String> rootItem = new TreeItem<>("Root");
//    rootItem.setExpanded(true);
//
//    // Main nodes
//    TreeItem<String> dashboard = new TreeItem<>("Dashboard");
//    TreeItem<String> addProductMain = new TreeItem<>("Add Products");
//
//    // Sub-items for Add Product
//    TreeItem<String> addProduct = new TreeItem<>("Add Product");
//    TreeItem<String> updateProduct = new TreeItem<>("Update Product");
//    TreeItem<String> deleteProduct = new TreeItem<>("Delete Product");
//    TreeItem<String> viewProduct = new TreeItem<>("View Product");
//
//    // Add sub-items
//    addProductMain.getChildren().addAll(addProduct, updateProduct, deleteProduct, viewProduct);
//
//    // Add to root
//    rootItem.getChildren().addAll(dashboard, addProductMain);
//
//    // Set root to TreeView
//    Tree_View.setRoot(rootItem);
//    Tree_View.setShowRoot(false);
//
//    // Initially show dashboard, hide others
//    Tree_Dash.setVisible(true);
//    add_panes.setVisible(false);
//    add_pane.setVisible(false);
//    update_pane.setVisible(false);
//    delete_pane.setVisible(false);
//
//    // Handle selection event
//    Tree_View.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
//        if (newValue != null) {
//            String selected = newValue.getValue();
//
//            // Dashboard selected
//            if (selected.equals("Dashboard")) {
//                Tree_Dash.setVisible(true);
//                add_panes.setVisible(false);
//                add_pane.setVisible(false);
//                update_pane.setVisible(false);
//                delete_pane.setVisible(false);
//                slideInFromRight(Tree_Dash);
//            }
//
//            // Add Products selected
//            else if (selected.equals("Add Products")) {
//                Tree_Dash.setVisible(false);
//                add_panes.setVisible(true);
//                add_pane.setVisible(false);
//                update_pane.setVisible(false);
//                delete_pane.setVisible(false);
//                slideInFromRight(add_panes);
//            }
//
//            // Add Product selected
//            else if (selected.equals("Add Product")) {
//                Tree_Dash.setVisible(false);
//                add_panes.setVisible(false);
//                add_pane.setVisible(true);
//                update_pane.setVisible(false);
//                delete_pane.setVisible(false);
//                slideInFromRight(add_pane);
//            }
//
//            // Update Product
//            else if (selected.equals("Update Product")) {
//                Tree_Dash.setVisible(false);
//                add_panes.setVisible(false);
//                add_pane.setVisible(false);
//                update_pane.setVisible(true);
//                delete_pane.setVisible(false);
//                slideInFromRight(update_pane);
//            }
//
//            // Delete Product
//            else if (selected.equals("Delete Product")) {
//                Tree_Dash.setVisible(false);
//                add_panes.setVisible(false);
//                add_pane.setVisible(false);
//                update_pane.setVisible(false);
//                delete_pane.setVisible(true);
//                slideInFromRight(delete_pane);
//            }
//
//            // Hide all others for now
//            else {
//                Tree_Dash.setVisible(false);
//                add_panes.setVisible(false);
//                add_pane.setVisible(false);
//                update_pane.setVisible(false);
//                delete_pane.setVisible(false);
//            }
//        }
//    });
//}
//
//// 🔹 Smooth Slide-In Animation Method (Right Side)
//private void slideInFromRight(AnchorPane pane) {
//    pane.setVisible(true);
//    pane.setOpacity(0);
//    pane.setTranslateX(150); // start slightly right (off-screen)
//
//    TranslateTransition slide = new TranslateTransition(Duration.millis(400), pane);
//    slide.setFromX(100);
//    slide.setToX(0);
//
//    FadeTransition fade = new FadeTransition(Duration.millis(400), pane);
//    fade.setFromValue(0);
//    fade.setToValue(1);
//
//    ParallelTransition smooth = new ParallelTransition(slide, fade);
//    smooth.setInterpolator(Interpolator.EASE_BOTH);
//    smooth.play();
//}
//
//// 🔹 Sidebar Toggle (moves sidebar + Tree_Dash together)
//@FXML
//private void toggleSideebar() {
//    TranslateTransition sidebarSlide = new TranslateTransition(Duration.millis(400), sidebar);
//    TranslateTransition dashSlide = new TranslateTransition(Duration.millis(400), Tree_Dash);
//
//    if (sidebar.getTranslateX() == 0) {
//        // Hide sidebar and move Tree_Dash left
//        sidebarSlide.setToX(-sidebar.getWidth() + 50);
//        dashSlide.setToX(-sidebar.getWidth() + 50);
//    } else {
//        // Show sidebar and move Tree_Dash back
//        sidebarSlide.setToX(0);
//        dashSlide.setToX(0);
//    }
//
//    sidebarSlide.setInterpolator(Interpolator.EASE_BOTH);
//    dashSlide.setInterpolator(Interpolator.EASE_BOTH);
//    sidebarSlide.play();
//    dashSlide.play();
//}
//
//}
//
//        
//          
//    
//
