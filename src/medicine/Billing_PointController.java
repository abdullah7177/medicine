
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
    @FXML
    private AnchorPane view_product;
    @FXML
    private AnchorPane history_pane;
    private AnchorPane day_history;
    @FXML
    private AnchorPane day_pane;
    @FXML
    private AnchorPane week_pane;
    @FXML
    private AnchorPane monthly_pane;

 
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

        //HISTORY Treeview
         TreeItem<String> HistorytMain = new TreeItem<>("View History");
       
         TreeItem<String> dayhistory = new TreeItem<>("Day History");
         TreeItem<String> weekhistory = new TreeItem<>("Week History");
         TreeItem<String> monthlyhistory = new TreeItem<>("Monthly History");
        
         //Add Product
        addProductMain.getChildren().addAll(addProduct, updateProduct, deleteProduct, viewProduct);
        rootItem.getChildren().addAll(dashboard, addProductMain,HistorytMain);

        //View History
        HistorytMain.getChildren().addAll(dayhistory,weekhistory,monthlyhistory);
        
        Tree_View.setRoot(rootItem);
        Tree_View.setShowRoot(false);

        // Initial pane visibility
        Tree_Dash.setVisible(true);
        add_panes.setVisible(false);
        add_pane.setVisible(false);
        update_pane.setVisible(false);
        delete_pane.setVisible(false);
        view_product.setVisible(false);
        history_pane.setVisible(false);
        day_pane.setVisible(false);
        week_pane.setVisible(false);
        monthly_pane.setVisible(false);
        
        
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
                } else if (selected.equals("View Product")) {
                    showPane(view_product);
                } else if (selected.equals("View History")) {
                    showPane(history_pane);
                } else if (selected.equals("Day History")) {
                    showPane(day_pane);
                } else if (selected.equals("Week History")) {
                    showPane(week_pane);
                } else if (selected.equals("Monthly History")) {
                    showPane(monthly_pane);
                }  
                
            }
        });
    }

    // ===============================
    // SHOW SELECTED PANE WITH ANIMATION
    // ===============================
  private void showPane(AnchorPane targetPane) {
    // Hide all panes first
    Tree_Dash.setVisible(false);
    add_panes.setVisible(false);
    add_pane.setVisible(false);
    update_pane.setVisible(false);
    delete_pane.setVisible(false);
    view_product.setVisible(false);
    history_pane.setVisible(false);
    day_pane.setVisible(false);
    week_pane.setVisible(false);
    monthly_pane.setVisible(false);
    // Show the selected pane
    targetPane.setVisible(true);

    
}




    // ===============================
    // SIDEBAR TOGGLE — moves all visible panes with it
    // ===============================
    @FXML
    private void toggleSidebar() {
        TranslateTransition sidebarSlide = new TranslateTransition(Duration.millis(200), sidebar);

        // Apply same movement to all panes
        TranslateTransition dashSlide = new TranslateTransition(Duration.millis(200), Tree_Dash);
        TranslateTransition addPanesSlide = new TranslateTransition(Duration.millis(200), add_panes);
        TranslateTransition addPaneSlide = new TranslateTransition(Duration.millis(200), add_pane);
        TranslateTransition updatePaneSlide = new TranslateTransition(Duration.millis(200), update_pane);
        TranslateTransition deletePaneSlide = new TranslateTransition(Duration.millis(200), delete_pane);
        TranslateTransition viewPaneSlide = new TranslateTransition(Duration.millis(200), view_product);
        TranslateTransition historyPaneSlide = new TranslateTransition(Duration.millis(200), history_pane);
        TranslateTransition dayPaneSlide = new TranslateTransition(Duration.millis(200), day_pane);
        TranslateTransition weekPaneSlide = new TranslateTransition(Duration.millis(200), week_pane);
        TranslateTransition monthPaneSlide = new TranslateTransition(Duration.millis(200), monthly_pane);
        
        boolean sidebarOpen = sidebar.getTranslateX() == 0;
        double moveX = sidebarOpen ? -sidebar.getWidth() + 50 : 0;

        sidebarSlide.setToX(moveX);
        dashSlide.setToX(moveX);
        addPanesSlide.setToX(moveX);
        addPaneSlide.setToX(moveX);
        updatePaneSlide.setToX(moveX);
        deletePaneSlide.setToX(moveX);
        viewPaneSlide.setToX(moveX);
        historyPaneSlide.setToX(moveX);
        dayPaneSlide.setToX(moveX);
        weekPaneSlide.setToX(moveX);
        monthPaneSlide.setToX(moveX);
                
                
        sidebarSlide.setInterpolator(Interpolator.EASE_BOTH);
        dashSlide.setInterpolator(Interpolator.EASE_BOTH);
        addPanesSlide.setInterpolator(Interpolator.EASE_BOTH);
        addPaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        updatePaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        deletePaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        viewPaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        historyPaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        dayPaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        weekPaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        monthPaneSlide.setInterpolator(Interpolator.EASE_BOTH);
        

        sidebarSlide.play();
        dashSlide.play();
        addPanesSlide.play();
        addPaneSlide.play();
        updatePaneSlide.play();
        deletePaneSlide.play();
        viewPaneSlide.play();
        historyPaneSlide.play();
        dayPaneSlide.play();
        weekPaneSlide.play();
        monthPaneSlide.play();
        
    }
}
