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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    @FXML
    private Label dashboardClock;

    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sidebar.setTranslateX(0);
startClock();
        // TreeView setup
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);

        TreeItem<String> dashboard = new TreeItem<>("Dashboard");
        TreeItem<String> addProductMain = new TreeItem<>("Add Products");

        TreeItem<String> addProduct = new TreeItem<>("Add Product");
        TreeItem<String> updateProduct = new TreeItem<>("Update Product");
        TreeItem<String> deleteProduct = new TreeItem<>("Delete Product");
        TreeItem<String> viewProduct = new TreeItem<>("View Product");

        TreeItem<String> HistorytMain = new TreeItem<>("View History");
        TreeItem<String> dayhistory = new TreeItem<>("Day History");
        TreeItem<String> weekhistory = new TreeItem<>("Week History");
        TreeItem<String> monthlyhistory = new TreeItem<>("Monthly History");

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


private void startClock() {
    Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        dashboardClock.setText(time.format(formatter));
    }));
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
}


}
