package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HistoryMonthlyController implements Initializable {

    @FXML
    private AnchorPane fisrt_pane;
    @FXML
    private StackPane staxkpane;
    @FXML
    private AnchorPane sec_pane;
    @FXML
    private Button back_btn;
    @FXML
    private AnchorPane history_pane;
    @FXML
    private ImageView imageicon;
    @FXML
    private AnchorPane history_centre_pane;

    // Rainbow border animation
    private Timeline rainbowTimeline;
    private double hue = 0;
    @FXML
    private Label monthly_label;
    @FXML
    private Button daily_btn;

    private void handleUpdateClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) back_btn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setTitle("FXMLDocument View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    private void animateHistoryIconRotation() {
        RotateTransition rotate = new RotateTransition(Duration.seconds(2), imageicon);
        rotate.setByAngle(-360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();
    }

    private void lockHistoryPaneSize() {
        double originalScaleX = history_pane.getScaleX();
        double originalScaleY = history_pane.getScaleY();

        history_pane.setOnMouseClicked(event -> {
            history_pane.setScaleX(originalScaleX);
            history_pane.setScaleY(originalScaleY);
        });

        animateHistoryIconRotation();
    }

    private void animateFirstPaneOpen() {
        fisrt_pane.setScaleX(0.0);
        fisrt_pane.setScaleY(0.0);
        fisrt_pane.setOpacity(0.0);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.6), fisrt_pane);
        scaleTransition.setFromX(0.0);
        scaleTransition.setFromY(0.0);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.6), fisrt_pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setInterpolator(Interpolator.EASE_IN);

        ParallelTransition openAnimation = new ParallelTransition(scaleTransition, fadeTransition);
        openAnimation.play();
    }

    // STAR FIELD ANIMATION
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
        Circle star = new Circle(1 + Math.random() * 2);
        star.setFill(Color.WHITE);
        star.setOpacity(0.0);

        double startX = Math.random() * history_pane.getWidth();
        double startY = Math.random() * history_pane.getHeight();

        star.setLayoutX(startX);
        star.setLayoutY(startY);
        star.setManaged(false);

        history_pane.getChildren().add(star);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), star);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition move = new TranslateTransition(Duration.seconds(3), star);
        move.setByY(20 + Math.random() * 30);
        move.setByX(-10 + Math.random() * 20);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), star);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(1));

        ParallelTransition animation = new ParallelTransition(fadeIn, move, fadeOut);
        animation.setOnFinished(e -> history_pane.getChildren().remove(star));
        animation.play();
    }

   
    
        private void animateAddProductLabelBounce() {
        if (monthly_label != null) {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), monthly_label);
            scale.setFromX(0.0);
            scale.setFromY(0.0);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fade = new FadeTransition(Duration.seconds(0.6), monthly_label);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);

            PauseTransition delay = new PauseTransition(Duration.seconds(2.1));

            ParallelTransition labelAnimation = new ParallelTransition(scale, fade);
            SequentialTransition full = new SequentialTransition(delay, labelAnimation);
            full.play();
        }
    }

    private void animateAddProductLabelBounces() {
        if (monthly_label != null) {
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setRadius(30);
            glow.setSpread(0.3);
            monthly_label.setEffect(glow);

            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), monthly_label);
            scale.setFromX(1.0);
            scale.setFromY(1.0);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.setCycleCount(Animation.INDEFINITE);
            scale.setAutoReverse(true);
            scale.play();
        }
    }
    
    
    
/////Back to dashboard
    ///////////BACKKKK
    
    
    
      @FXML
private void handleDashHIstoryButton(ActionEvent event) {
    try {
        // Load the target FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = loader.load();

        // Create a new scene and stage
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        // Apply initial black overlay
        Rectangle fadeOverlay = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
        fadeOverlay.widthProperty().bind(scene.widthProperty());
        fadeOverlay.heightProperty().bind(scene.heightProperty());
        ((Pane) root).getChildren().add(fadeOverlay);

        stage.setScene(scene);
        stage.show();

        // Start fade-out animation
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
        fadeOut.setFromValue(1.0);  // Fully black
        fadeOut.setToValue(0.0);    // Fully visible content
        fadeOut.setInterpolator(Interpolator.EASE_BOTH);
        fadeOut.setOnFinished(e -> ((Pane) root).getChildren().remove(fadeOverlay));
        fadeOut.play();

    } catch (IOException e) {
        e.printStackTrace();
    }
}


  @FXML
private void handleDailyButton(ActionEvent event) {
    try {
        // Load the target FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("historytext.fxml"));
        Parent root = loader.load();

        // Create a new scene and stage
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        // Apply initial black overlay
        Rectangle fadeOverlay = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
        fadeOverlay.widthProperty().bind(scene.widthProperty());
        fadeOverlay.heightProperty().bind(scene.heightProperty());
        ((Pane) root).getChildren().add(fadeOverlay);

        stage.setScene(scene);
        stage.show();

        // Start fade-out animation
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), fadeOverlay);
        fadeOut.setFromValue(1.0);  // Fully black
        fadeOut.setToValue(0.0);    // Fully visible content
        fadeOut.setInterpolator(Interpolator.EASE_BOTH);
        fadeOut.setOnFinished(e -> ((Pane) root).getChildren().remove(fadeOverlay));
        fadeOut.play();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Stage stage = (Stage) staxkpane.getScene().getWindow();
            if (stage != null) {
                stage.setFullScreen(true);
            }
            animateFirstPaneOpen();
            
           
        });

        startStarAnimation();
        lockHistoryPaneSize();
        
        

    // Rectangle as the border path
    Rectangle borderLine = new Rectangle();
    borderLine.setFill(null);

    // Bind size with slight margin
    borderLine.widthProperty().bind(history_centre_pane.widthProperty().subtract(4));
    borderLine.heightProperty().bind(history_centre_pane.heightProperty().subtract(4));

    // No radius (perfect rectangular line)
    borderLine.setArcWidth(0);
    borderLine.setArcHeight(0);

    // Gradient stroke: #354172 → #ffffff
    LinearGradient gradient = new LinearGradient(
            0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#354172")),
            new Stop(1, Color.web("#ffffff"))
    );
    borderLine.setStroke(gradient);
    borderLine.setStrokeWidth(3);

    // Dash effect → sirf ek line move hogi
    borderLine.getStrokeDashArray().addAll(80.0, 300.0); // adjust 80/300 for length-gap

    // Position inside pane
    history_centre_pane.getChildren().add(borderLine);

    // Offset animation for continuous movement
    DoubleProperty offset = new SimpleDoubleProperty(0);
    borderLine.strokeDashOffsetProperty().bind(offset);

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(offset, 0)),
            new KeyFrame(Duration.seconds(3), new KeyValue(offset, 380)) // speed control
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();


        
    }
}