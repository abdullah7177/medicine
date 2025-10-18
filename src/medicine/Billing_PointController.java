package medicine;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Billing_PointController implements Initializable {

    @FXML
    private StackPane Billing_StackPane;
    @FXML
    private VBox sidebar;
    @FXML
    private Button close_Btn;
    @FXML
    private AnchorPane Dashboard;
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

    @FXML
    private AnchorPane day_pane;
    @FXML
    private AnchorPane week_pane;
    @FXML
    private AnchorPane monthly_pane;
    private Label dashboardClock;
    @FXML
    private Label Clock;
    @FXML
    private Label clock1;
    @FXML
    private Label clock2;
    @FXML
    private ImageView View_expenses_image;
    @FXML
    private ImageView daily_image;
    @FXML
    private ImageView add_image;
    @FXML
    private ImageView update_image;
    @FXML
    private ImageView delete_image;
    @FXML
    private Label clock3;
    @FXML
    private ImageView view_products_image;
    @FXML
    private ImageView dashboard_image;
    @FXML
    private Label dashboard_label;
 
    @FXML
    private ImageView customer_image;
    @FXML
    private ImageView sale_image;
    @FXML
    private ImageView total_images;
    @FXML
    private ImageView addProducts_image;
    @FXML
    private AnchorPane total_image;
    @FXML
    private ImageView prooducts_image;
    @FXML
    private ImageView Dele_products_image;
    @FXML
    private ImageView monthly_image;
    @FXML
    private ImageView monthly_ex;
   

    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sidebar.setTranslateX(0);
        
        
        startClock(clock1);
        startClock(clock2);
        startClock(Clock);
        startClock(clock3);
        
        imageAnimation(View_expenses_image);
        imageAnimation(daily_image);
        imageAnimation(add_image);
        imageAnimation(update_image);
        imageAnimation(delete_image);
        imageAnimation(view_products_image);
        imageAnimation(daily_image);
        imageAnimation(dashboard_image);
        imageAnimation(total_images);
        imageAnimation(sale_image);
        imageAnimation(customer_image);
        imageAnimation(addProducts_image);
        imageAnimation(prooducts_image);
        imageAnimation(Dele_products_image);
        imageAnimation(monthly_image);
        imageAnimation(monthly_ex);
         
        // TreeView setup
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);

        TreeItem<String> dashboard = new TreeItem<>("Dashboard");
        TreeItem<String> addProductMain = new TreeItem<>("Add Products");

        TreeItem<String> addProduct = new TreeItem<>("Add Product");
        TreeItem<String> updateProduct = new TreeItem<>("Update Product");
        TreeItem<String> deleteProduct = new TreeItem<>("Delete Product");
        TreeItem<String> viewProduct = new TreeItem<>("View Product");

        TreeItem<String> HistorytMain = new TreeItem<>("Expenses");
        TreeItem<String> dayhistory = new TreeItem<>("Daily Expenses");
        TreeItem<String> weekhistory = new TreeItem<>("View Expenses");
        TreeItem<String> monthlyhistory = new TreeItem<>("Monthly Expenses");

        addProductMain.getChildren().addAll(addProduct, updateProduct, deleteProduct, viewProduct);
        rootItem.getChildren().addAll(dashboard, addProductMain, HistorytMain);
        HistorytMain.getChildren().addAll(dayhistory, weekhistory, monthlyhistory);

        Tree_View.setRoot(rootItem);
        Tree_View.setShowRoot(false);

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
                } else if (selected.equals("Expenses")) {
                    showPane(history_pane);
                } else if (selected.equals("Daily Expenses")) {
                    showPane(day_pane);
                } else if (selected.equals("View Expenses")) {
                    showPane(week_pane);
                } else if (selected.equals("Monthly Expenses")) {
                    showPane(monthly_pane);
                }
            }
        });
    }


    
    
    
    //image
    
    public static void imageAnimation(ImageView imageView) {
        if (imageView == null) return;

        ScaleTransition scale = new ScaleTransition(Duration.seconds(1), imageView);
        scale.setFromX(1.2);
        scale.setFromY(1.2);
        scale.setToX(1.1);
        scale.setToY(1.1);
        scale.setAutoReverse(true);
        scale.setCycleCount(ScaleTransition.INDEFINITE);
        scale.play();
    }
    
    public static void startClock(Label... labels) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            String timeNow = LocalTime.now().format(formatter);
            for (Label label : labels) {
                label.setText(timeNow);
            }
        }));

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    private void showPane(AnchorPane targetPane) {
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

        targetPane.setVisible(true);
    }

    // ===============================
    // SUPER SMOOTH SIDEBAR TOGGLE (iPhone style)
    // ===============================
@FXML
private void toggleSidebar() {
    double durationMillis = 200; // slightly smoother
    Duration duration = Duration.millis(durationMillis);

    boolean sidebarOpen = sidebar.getTranslateX() == 0;
    double moveX = sidebarOpen ? -sidebar.getWidth() + 50 : 0;

    // Use our new smooth function for all
    TranslateTransition sidebarSlide = createSmoothSlide(sidebar, moveX, duration);
    TranslateTransition dashSlide = createSmoothSlide(Tree_Dash, moveX, duration);
    TranslateTransition addPanesSlide = createSmoothSlide(add_panes, moveX, duration);
    TranslateTransition addPaneSlide = createSmoothSlide(add_pane, moveX, duration);
    TranslateTransition updatePaneSlide = createSmoothSlide(update_pane, moveX, duration);
    TranslateTransition deletePaneSlide = createSmoothSlide(delete_pane, moveX, duration);
    TranslateTransition viewPaneSlide = createSmoothSlide(view_product, moveX, duration);
    TranslateTransition historyPaneSlide = createSmoothSlide(history_pane, moveX, duration);
    TranslateTransition dayPaneSlide = createSmoothSlide(day_pane, moveX, duration);
    TranslateTransition weekPaneSlide = createSmoothSlide(week_pane, moveX, duration);
    TranslateTransition monthPaneSlide = createSmoothSlide(monthly_pane, moveX, duration);

    // Play all animations together
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

    // Helper method — creates smooth animation
// Smooth slide animation for any Node (AnchorPane, VBox, etc.)
private TranslateTransition createSmoothSlide(javafx.scene.Node node, double toX, Duration duration) {
    TranslateTransition slide = new TranslateTransition(duration, node);
    slide.setToX(toX);
    slide.setInterpolator(Interpolator.EASE_BOTH);
    return slide;
}





}
