/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package medicine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author user
 */
public class HistorytextController implements Initializable {

    @FXML
    private AnchorPane history_text_pane;
    @FXML
    private StackPane history_text_stack;
    @FXML
    private AnchorPane history_text_pane2;
    @FXML
    private AnchorPane daily_pane;
    @FXML
    private Label daily_expensis_label;
    @FXML
    private AnchorPane daily_side_pane;
    @FXML
    private Button daily_add;
    @FXML
    private AnchorPane daly_table_pane;

    /**
     * Initializes the controller class.
     */
    
    
    
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

        double startX = Math.random() * daily_pane.getWidth();
        double startY = Math.random() * daily_pane.getHeight();

        star.setLayoutX(startX);
        star.setLayoutY(startY);
        star.setManaged(false);

        daily_pane.getChildren().add(star);

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
        animation.setOnFinished(e -> daily_pane.getChildren().remove(star));
        animation.play();
    }

     private void animateAddProductLabelBounce() {
        if (daily_expensis_label != null) {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), daily_expensis_label);
            scale.setFromX(0.0);
            scale.setFromY(0.0);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fade = new FadeTransition(Duration.seconds(0.6), daily_expensis_label);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);

            PauseTransition delay = new PauseTransition(Duration.seconds(2.1));

            ParallelTransition labelAnimation = new ParallelTransition(scale, fade);
            SequentialTransition full = new SequentialTransition(delay, labelAnimation);
            full.play();
        }
    }

    private void animateAddProductLabelBounces() {
        if (daily_expensis_label != null) {
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setRadius(30);
            glow.setSpread(0.3);
            daily_expensis_label.setEffect(glow);

            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), daily_expensis_label);
            scale.setFromX(1.0);
            scale.setFromY(1.0);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.setCycleCount(Animation.INDEFINITE);
            scale.setAutoReverse(true);
            scale.play();
        }
    }
    
    
    
private void animateFullBorder() {
    // Gradient border color (blue → white)
    LinearGradient gradient = new LinearGradient(
            0, 0, 1, 0, true, CycleMethod.REPEAT,
            new Stop(0, Color.web("#354172")),
            new Stop(0.5, Color.WHITE),
            new Stop(1, Color.web("#354172"))
    );

    // Rectangle overlay for border
    Rectangle border = new Rectangle();
    border.setFill(null);
    border.setStroke(gradient);
    border.setStrokeWidth(3);
    border.setArcWidth(0);   // rectangular look
    border.setArcHeight(0);
    border.setManaged(false);
    border.setMouseTransparent(true);

    // Bind size with pane
    border.widthProperty().bind(daily_side_pane.widthProperty().subtract(2));
    border.heightProperty().bind(daily_side_pane.heightProperty().subtract(2));
    border.layoutXProperty().set(1);
    border.layoutYProperty().set(1);

    daily_side_pane.getChildren().add(border);

    // Animate gradient offset using strokeDash
    double dash = 40;
    border.getStrokeDashArray().addAll(dash, dash);
    border.setStrokeDashOffset(0);

    Timeline animation = new Timeline(
        new KeyFrame(Duration.ZERO,
            new KeyValue(border.strokeDashOffsetProperty(), 0, Interpolator.LINEAR)
        ),
        new KeyFrame(Duration.seconds(4),
            new KeyValue(border.strokeDashOffsetProperty(), dash * 2, Interpolator.LINEAR)
        )
    );
    animation.setCycleCount(Animation.INDEFINITE);
    animation.play();
}



    
    
//    
//public void Sidepane(){
//    // Rectangle border for the pane
//
//    // Apply initial border
//    daily_side_pane.setBorder(new Border(
//        new BorderStroke(
//            Color.web("#354172"),
//            BorderStrokeStyle.SOLID,
//            CornerRadii.EMPTY,
//            new BorderWidths(3)
//        )
//    ));
//
//    // Create fade transition on the whole pane to simulate border glow
//    FadeTransition glow = new FadeTransition(Duration.seconds(1.5), daily_side_pane);
//    glow.setFromValue(0.6);  // border lighter
//    glow.setToValue(1.0);    // border stronger
//    glow.setCycleCount(Animation.INDEFINITE);
//    glow.setAutoReverse(true);
//
//    glow.play();
//
//
//}

private void buttonAnimation() {
Rectangle border = new Rectangle();
border.setFill(null);


// Gradient border color
LinearGradient gradient = new LinearGradient(
        0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
        new Stop(0, Color.web("#354172")),
        new Stop(1, Color.web("#ffffff"))
);
border.setStroke(gradient);

border.setStrokeWidth(2.5);
border.setArcWidth(12);
border.setArcHeight(12);
border.setManaged(false);
border.setMouseTransparent(true);

double dashLength = 20;
border.getStrokeDashArray().addAll(dashLength, dashLength * 2);

// Bind size to button
border.widthProperty().bind(Bindings.createDoubleBinding(() ->
    daily_add.getLayoutBounds().getWidth() + 12,
    daily_add.layoutBoundsProperty()));
border.heightProperty().bind(Bindings.createDoubleBinding(() ->
    daily_add.getLayoutBounds().getHeight() + 12,
    daily_add.layoutBoundsProperty()));

border.layoutXProperty().bind(daily_add.layoutXProperty().subtract(6));
border.layoutYProperty().bind(daily_add.layoutYProperty().subtract(6));
border.scaleXProperty().bind(daily_add.scaleXProperty());
border.scaleYProperty().bind(daily_add.scaleYProperty());

((Pane) daily_add.getParent()).getChildren().add(border);

// Timeline for dash offset animation
Timeline dashMove = new Timeline(
    new KeyFrame(Duration.ZERO, new KeyValue(border.strokeDashOffsetProperty(), 0, Interpolator.LINEAR)),
    new KeyFrame(Duration.seconds(2), new KeyValue(border.strokeDashOffsetProperty(), 60, Interpolator.LINEAR))
);
dashMove.setCycleCount(Animation.INDEFINITE);

// Fade transition for glowing effect
FadeTransition glow = new FadeTransition(Duration.seconds(1.5), border);
glow.setFromValue(0.6);
glow.setToValue(1.0);
glow.setCycleCount(Animation.INDEFINITE);
glow.setAutoReverse(true);

// Run both animations together
dashMove.play();
glow.play();

}


    public void addstyle() {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(500), daily_add);
        scaleUp.setToX(1.2);
        scaleUp.setToY(1.2);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(500), daily_add);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        daily_add.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            scaleDown.stop();
            scaleUp.playFromStart();
        });

        daily_add.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            scaleUp.stop();
            scaleDown.playFromStart();
        });
    }


    private void startSidePaneStarAnimation() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
        for (int i = 0; i < 2; i++) {
            createSidePaneStar();
        }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
}

private void createSidePaneStar() {
    Circle star = new Circle(1 + Math.random() * 2);
    star.setFill(Color.WHITE);
    star.setOpacity(0.0);

    // Random position inside daily_side_pane
    double startX = Math.random() * daily_side_pane.getWidth();
    double startY = Math.random() * daily_side_pane.getHeight();

    star.setLayoutX(startX);
    star.setLayoutY(startY);
    star.setManaged(false);

    daily_side_pane.getChildren().add(star);

    // Fade in
    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.8), star);
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);

    // Small movement
    TranslateTransition move = new TranslateTransition(Duration.seconds(2.5), star);
    move.setByY(-10 + Math.random() * 20);
    move.setByX(-10 + Math.random() * 20);

    // Fade out
    FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), star);
    fadeOut.setFromValue(1);
    fadeOut.setToValue(0);
    fadeOut.setDelay(Duration.seconds(1));

    // Combine animation
    ParallelTransition animation = new ParallelTransition(fadeIn, move, fadeOut);
    animation.setOnFinished(e -> daily_side_pane.getChildren().remove(star));
    animation.play();
}

    
    
    
    
    
    
    
//    private void startStarAnimations() {
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
//            for (int i = 0; i < 3; i++) {
//                createAndAnimateStar();
//            }
//        }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//    }
//
//    private void createAndAnimateStars() {
//        Circle star = new Circle(1 + Math.random() * 2);
//        star.setFill(Color.WHITE);
//        star.setOpacity(0.0);
//
//        double startX = Math.random() * daily_side_pane.getWidth();
//        double startY = Math.random() * daily_side_pane.getHeight();
//
//        star.setLayoutX(startX);
//        star.setLayoutY(startY);
//        star.setManaged(false);
//
//        daily_side_pane.getChildren().add(star);
//
//        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), star);
//        fadeIn.setFromValue(0);
//        fadeIn.setToValue(1);
//
//        TranslateTransition move = new TranslateTransition(Duration.seconds(3), star);
//        move.setByY(20 + Math.random() * 30);
//        move.setByX(-10 + Math.random() * 20);
//
//        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), star);
//        fadeOut.setFromValue(1);
//        fadeOut.setToValue(0);
//        fadeOut.setDelay(Duration.seconds(1));
//
//        ParallelTransition animation = new ParallelTransition(fadeIn, move, fadeOut);
//        animation.setOnFinished(e -> daily_side_pane.getChildren().remove(star));
//        animation.play();
//    }

private void animatePaneBorder(Pane targetPane) {
    Rectangle border = new Rectangle();
    border.setFill(null);
    border.setStrokeWidth(4);
    border.setArcWidth(20);  // smooth rounding
    border.setArcHeight(20);
    border.setManaged(false);
    border.setMouseTransparent(true);

    // Bind border size to pane size
    border.widthProperty().bind(targetPane.widthProperty().subtract(4));
    border.heightProperty().bind(targetPane.heightProperty().subtract(4));
    border.layoutXProperty().bind(targetPane.layoutXProperty().add(2));
    border.layoutYProperty().bind(targetPane.layoutYProperty().add(2));

    // Gradient stroke
    LinearGradient gradient = new LinearGradient(
            0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.web("#fff")),
            new Stop(0.5, Color.web("#354172")),
            new Stop(1, Color.web("#fff"))
    );
    border.setStroke(gradient);

    // Stroke dash array - a single visible line
    border.getStrokeDashArray().addAll(50.0, 10.0);

    ((Pane) targetPane.getParent()).getChildren().add(border);

    // Animate dash offset to make it rotate around the border
    Timeline dashAnimation = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(border.strokeDashOffsetProperty(), 0, Interpolator.LINEAR)),
            new KeyFrame(Duration.seconds(2), new KeyValue(border.strokeDashOffsetProperty(), 60, Interpolator.LINEAR))
    );
    dashAnimation.setCycleCount(Animation.INDEFINITE);
    dashAnimation.play();
}




    
    @FXML
    public void close(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Platform.runLater(() -> {
            Stage stage = (Stage) history_text_pane.getScene().getWindow();
            if (stage != null) {
                stage.setMaximized(true);
            }
        startStarAnimation();            
           
        });
    animateAddProductLabelBounce();
    animateAddProductLabelBounces();    
     addstyle();
buttonAnimation();    
 animateFullBorder();
 startSidePaneStarAnimation();
animatePaneBorder(daly_table_pane);
    }    
    
}
