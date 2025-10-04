package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FirstAddTableController implements Initializable {

    @FXML
    private AnchorPane fisrt_pane;
    @FXML
    private StackPane second_stack;
    @FXML
    private AnchorPane second_pane;
    @FXML
    private AnchorPane table_sidepane;
    @FXML
    private ImageView cartoonImage;
    @FXML
    private Label starLabel;
    @FXML
    private Label arrowLabel;
    @FXML
    private AnchorPane Table_pane;
    @FXML
    private Button update_btn;
    @FXML
    private Button clear_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private Button backTo_add;


     public void Updatestyle(){
    
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(500), update_btn);
scaleUp.setToX(1.2);
scaleUp.setToY(1.2);

ScaleTransition scaleDown = new ScaleTransition(Duration.millis(500), update_btn);
scaleDown.setToX(1.0);
scaleDown.setToY(1.0);

update_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
    scaleDown.stop();
    scaleUp.playFromStart();
});

update_btn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
    scaleUp.stop();
    scaleDown.playFromStart();
});

    }
     
        public void Clearstyle(){
    
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(500), clear_btn);
scaleUp.setToX(1.2);
scaleUp.setToY(1.2);

ScaleTransition scaleDown = new ScaleTransition(Duration.millis(500), clear_btn);
scaleDown.setToX(1.0);
scaleDown.setToY(1.0);

clear_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
    scaleDown.stop();
    scaleUp.playFromStart();
});

clear_btn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
    scaleUp.stop();
    scaleDown.playFromStart();
});

    }
        
public void Deletestyle(){
    
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(500), delete_btn);
scaleUp.setToX(1.2);
scaleUp.setToY(1.2);

ScaleTransition scaleDown = new ScaleTransition(Duration.millis(500), delete_btn);
scaleDown.setToX(1.0);
scaleDown.setToY(1.0);

delete_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
    scaleDown.stop();
    scaleUp.playFromStart();
});

delete_btn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
    scaleUp.stop();
    scaleDown.playFromStart();
});

    }

     
    
private void aanimatePaneBorder() {
    Rectangle border = new Rectangle();
    border.setFill(null);
    border.setStroke(Color.web("#354172"));

    border.setStrokeWidth(5);    // Thicker border line
    border.setArcWidth(0);        // No rounded corners
    border.setArcHeight(0);       // No rounded corners

    border.setManaged(false);
    border.setMouseTransparent(true);

    border.widthProperty().bind(Table_pane.widthProperty());
    border.heightProperty().bind(Table_pane.heightProperty());

    if (!Table_pane.getChildren().contains(border)) {
        Table_pane.getChildren().add(0, border);
    }

    FadeTransition fade = new FadeTransition(Duration.seconds(2), border);
    fade.setFromValue(0.0);
    fade.setToValue(1.0);
    fade.setCycleCount(Animation.INDEFINITE);
    fade.setAutoReverse(true);
    fade.play();
}







    
    
    private void loadCartoonImage() {
    cartoonImage.setImage(new javafx.scene.image.Image(
        getClass().getResource("/medicine/images/person.png").toExternalForm()
    ));
}

private void startJumpingAnimation() {
    // 🧍 Cartoon jumps
    TranslateTransition jumpCartoon = new TranslateTransition();
    jumpCartoon.setNode(cartoonImage);
    jumpCartoon.setByY(-100);
    jumpCartoon.setDuration(Duration.millis(500));
    jumpCartoon.setAutoReverse(true);
    jumpCartoon.setCycleCount(TranslateTransition.INDEFINITE);
    jumpCartoon.play();

    // ✨ Label jumps
    TranslateTransition jumpLabel = new TranslateTransition();
    jumpLabel.setNode(starLabel);
    jumpLabel.setByY(-100);
    jumpLabel.setDuration(Duration.millis(500));
    jumpLabel.setAutoReverse(true);
    jumpLabel.setCycleCount(TranslateTransition.INDEFINITE);
    jumpLabel.play();

    // 🔍 Label grows when up, shrinks when down
    ScaleTransition scaleLabel = new ScaleTransition();
    scaleLabel.setNode(starLabel);
    scaleLabel.setFromX(1.0);
    scaleLabel.setFromY(1.0);
    scaleLabel.setToX(1.3); // grow
    scaleLabel.setToY(1.3);
    scaleLabel.setDuration(Duration.millis(500));
    scaleLabel.setAutoReverse(true);
    scaleLabel.setCycleCount(ScaleTransition.INDEFINITE);
    scaleLabel.play();
    
    TranslateTransition arrowFloat = new TranslateTransition();
arrowFloat.setNode(arrowLabel);
arrowFloat.setByY(-100);
arrowFloat.setDuration(Duration.millis(500));
arrowFloat.setAutoReverse(true);
arrowFloat.setCycleCount(TranslateTransition.INDEFINITE);
arrowFloat.play();

}

    
private void animatePaneBorder() {
    // Avoid adding multiple times
    Rectangle border = new Rectangle();
    border.setFill(null);
    border.setStroke(Color.web("#1a238b"));
    border.setStrokeWidth(3);
    border.setArcWidth(20);
    border.setArcHeight(20);
    border.setManaged(false);               // ✅ Prevent layout sizing
    border.setMouseTransparent(true);       // ✅ Ignore mouse clicks

    // Bind to pane size
    border.widthProperty().bind(table_sidepane.widthProperty());
    border.heightProperty().bind(table_sidepane.heightProperty());

    // Stroke dash effect
    double dashLength = 200;
    border.getStrokeDashArray().addAll(dashLength, dashLength * 3);
    border.setStrokeDashOffset(0);

    // ✅ Only add if not already added
    if (!table_sidepane.getChildren().contains(border)) {
        table_sidepane.getChildren().add(0, border); // Behind all children
    }

    // Animate
    Timeline animateDash = new Timeline(
        new KeyFrame(Duration.ZERO,
            new KeyValue(border.strokeDashOffsetProperty(), 0, Interpolator.LINEAR)
        ),
        new KeyFrame(Duration.seconds(2),
            new KeyValue(border.strokeDashOffsetProperty(), dashLength * 4, Interpolator.LINEAR)
        )
    );
    animateDash.setCycleCount(Animation.INDEFINITE);
    animateDash.setAutoReverse(false);
    animateDash.play();
}
    @FXML
    public void close(){
    
    System.exit(0);
    }
    
    @FXML
private void handleUpdateClick() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = (Stage) backTo_add.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

 private void startStarAnimation() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
        for (int i = 0; i < 3; i++) {
            createAndAnimateStar();
        }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
}

private void createAndAnimateStar() {
    // Create a new small star with random size
    Circle star = new Circle(1 + Math.random() * 2); // size: 1–3px
    star.setFill(Color.GRAY);
    star.setOpacity(0.0);

    // Random start position inside the pane
    double startX = Math.random() * table_sidepane.getWidth();
    double startY = Math.random() * table_sidepane.getHeight();

    // Set position and make sure it's unmanaged (so it doesn't get resized or repositioned)
    star.setLayoutX(startX);
    star.setLayoutY(startY);
    star.setManaged(false);

    table_sidepane.getChildren().add(star);

    // Fade in
    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), star);
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);

    // Move star slightly for twinkling effect
    TranslateTransition move = new TranslateTransition(Duration.seconds(3), star);
    move.setByY(20 + Math.random() * 30); // gentle vertical drift
    move.setByX(-10 + Math.random() * 20); // slight horizontal drift

    // Fade out
    FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), star);
    fadeOut.setFromValue(1);
    fadeOut.setToValue(0);
    fadeOut.setDelay(Duration.seconds(1));

    // Combine animations
    ParallelTransition animation = new ParallelTransition(fadeIn, move, fadeOut);
    animation.setOnFinished(e -> table_sidepane.getChildren().remove(star));
    animation.play();
}


//
//@FXML
//private void openFirstAddTable(MouseEvent event) {
//    try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstAddTable.fxml"));
//        AnchorPane newPane = loader.load();
//
//        // Get the stage and current scene
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = stage.getScene();
//
//        // Set initial position off-screen for slide-in effect
//        newPane.setTranslateX(scene.getWidth());
//
//        // Add new pane to root (or switch scene root if preferred)
//        StackPane root = (StackPane) scene.getRoot();  // Make sure your main root is a StackPane
//        root.getChildren().add(newPane);
//
//        // Animate sliding in from right
//        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), newPane);
//        slideIn.setToX(0);
//        slideIn.setInterpolator(Interpolator.EASE_BOTH);
//
//        // Optional: remove previous pane after animation
//        slideIn.setOnFinished(e -> {
//            if (root.getChildren().size() > 1) {
//                root.getChildren().remove(0); // remove old pane
//            }
//        });
//
//        slideIn.play();
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}

       @FXML
    private void hhandleUpdateClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backTo_add.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setTitle("FXMLDocument View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Run after scene is loaded
        
   Platform.runLater(() -> {
        Stage stage = (Stage) second_stack.getScene().getWindow();
        if (stage != null) {
            stage.setMaximized(true);
        }
//        animateFirstPaneOpen();
    });
        animatePaneBorder();
        loadCartoonImage();
        startJumpingAnimation();
        aanimatePaneBorder();
        Updatestyle();
        Clearstyle();
        Deletestyle();
        
        startStarAnimation();
        createAndAnimateStar();
    }
}
