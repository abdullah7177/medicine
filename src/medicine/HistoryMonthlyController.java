package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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

    @FXML
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
        rotate.setByAngle(-360); // Counter-clockwise rotation
        rotate.setCycleCount(Animation.INDEFINITE); // Loop forever
        rotate.setInterpolator(Interpolator.LINEAR); // Smooth speed
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
        Circle star = new Circle(1 + Math.random() * 2); // size: 1–3px
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

  //   SUN///////////////
    
private void createSunWithLiveRays() {
    if (history_centre_pane == null || history_centre_pane.getWidth() == 0) return;

 
    double sunX = 40;  // thoda bahar left
    double sunY = 40;  // thoda bahar top

    Circle sunCore = new Circle(20);
    sunCore.setFill(Color.web("#FDB813"));
    sunCore.setEffect(new DropShadow(30, Color.web("#FFD700")));
    sunCore.setLayoutX(sunX);
    sunCore.setLayoutY(sunY);

    Circle sunGlow = new Circle(50);
    sunGlow.setFill(Color.web("#FFF9C4", 0.3));
    sunGlow.setLayoutX(sunX);
    sunGlow.setLayoutY(sunY);

    ScaleTransition pulse = new ScaleTransition(Duration.seconds(2), sunGlow);
    pulse.setFromX(1);
    pulse.setFromY(1);
    pulse.setToX(1.15);
    pulse.setToY(1.15);
    pulse.setCycleCount(Animation.INDEFINITE);
    pulse.setAutoReverse(true);
    pulse.play();

    Group rays = createInnerSunRays(sunX, sunY);

    history_centre_pane.getChildren().addAll(rays, sunGlow, sunCore);
}

private Group createInnerSunRays(double centerX, double centerY) {
    Group rayGroup = new Group();

    int rayCount = 8;
    double rayLength = 30;
    double rayWidth = 6;

    for (int i = 0; i < rayCount; i++) {
        double angle = i * (360.0 / rayCount);

        Rectangle ray = new Rectangle(rayLength, rayWidth);
        ray.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#FFF176", 0.6)),
                new Stop(1, Color.TRANSPARENT)
        ));
        ray.setArcWidth(4);
        ray.setArcHeight(4);

        // position relative to centerX, centerY
        ray.setTranslateX(centerX - rayLength / 2);
        ray.setTranslateY(centerY - rayWidth / 2);

        ray.setRotate(angle);

        rayGroup.getChildren().add(ray);
    }

    FadeTransition shimmer = new FadeTransition(Duration.seconds(2), rayGroup);
    shimmer.setFromValue(0.4);
    shimmer.setToValue(0.8);
    shimmer.setCycleCount(Animation.INDEFINITE);
    shimmer.setAutoReverse(true);
    shimmer.play();

    return rayGroup;
}




    
    
//private void createGradientMeteor() {
//    if (history_centre_pane == null || history_centre_pane.getWidth() == 0) return;
//
//    double startX = Math.random() * history_centre_pane.getWidth() * 0.7;
//    double startY = Math.random() * history_centre_pane.getHeight() * 0.2;
//
//    // 🌟 Head: Shiny circle
//    Circle head = new Circle(5 + Math.random() * 3); // Bigger head
//    head.setFill(Color.web("#FFFFFF"));
//    head.setEffect(new DropShadow(20, Color.web("#FFFFFF"))); // glowing
//    head.setOpacity(0.0);
//    head.setLayoutX(startX);
//    head.setLayoutY(startY);
//    head.setManaged(false);
//
//    // 🌠 Tail: Gradient rectangle
//    Rectangle tail = new Rectangle(120, 2); // Long thin trail
//    tail.setFill(new LinearGradient(
//            1, 0, 0, 0, true, CycleMethod.NO_CYCLE,
//            new Stop(0, Color.web("#354172")), // Start of tail (near head)
//            new Stop(1, Color.web("#FFFFFF", 0)) // Fades out
//    ));
//    tail.setArcWidth(2);
//    tail.setArcHeight(2);
//    tail.setOpacity(0.0);
//    tail.setLayoutX(startX);
//    tail.setLayoutY(startY);
//    tail.setRotate(45 + Math.random() * 10); // diagonal
//    tail.setManaged(false);
//
//    history_centre_pane.getChildren().addAll(tail, head);
//
//    // ✨ Fade in quickly
//    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.1), head);
//    fadeIn.setFromValue(0);
//    fadeIn.setToValue(1);
//
//    FadeTransition tailFadeIn = new FadeTransition(Duration.seconds(0.2), tail);
//    tailFadeIn.setFromValue(0);
//    tailFadeIn.setToValue(1);
//
//    // 🚀 Move both together
//    double moveX = 500 + Math.random() * 200;
//    double moveY = 400 + Math.random() * 200;
//
//    TranslateTransition moveHead = new TranslateTransition(Duration.seconds(1.2), head);
//    moveHead.setByX(moveX);
//    moveHead.setByY(moveY);
//    moveHead.setInterpolator(Interpolator.EASE_OUT);
//
//    TranslateTransition moveTail = new TranslateTransition(Duration.seconds(1.2), tail);
//    moveTail.setByX(moveX);
//    moveTail.setByY(moveY);
//    moveTail.setInterpolator(Interpolator.EASE_OUT);
//
//    // 🧊 Fade out at the end
//    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.4), head);
//    fadeOut.setFromValue(1);
//    fadeOut.setToValue(0);
//    fadeOut.setDelay(Duration.seconds(0.9));
//
//    FadeTransition tailFadeOut = new FadeTransition(Duration.seconds(0.4), tail);
//    tailFadeOut.setFromValue(1);
//    tailFadeOut.setToValue(0);
//    tailFadeOut.setDelay(Duration.seconds(0.9));
//
//    ParallelTransition all = new ParallelTransition(
//            fadeIn, tailFadeIn,
//            moveHead, moveTail,
//            fadeOut, tailFadeOut
//    );
//
//    all.setOnFinished(e -> {
//        history_centre_pane.getChildren().removeAll(head, tail);
//    });
//
//    all.play();
//}
//
//
//private void startGradientMeteorShower() {
//    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
//        int count = 4 + (int) (Math.random() * 3); // 4–6 meteors
//        for (int i = 0; i < count; i++) {
//            PauseTransition delay = new PauseTransition(Duration.millis(i * 300 + Math.random() * 200));
//            delay.setOnFinished(ev -> createGradientMeteor());
//            delay.play();
//        }
//    }));
//    timeline.setCycleCount(Animation.INDEFINITE);
//    timeline.play();
//}




@Override
public void initialize(URL url, ResourceBundle rb) {
    Platform.runLater(() -> {
        Stage stage = (Stage) staxkpane.getScene().getWindow();
        if (stage != null) {
            stage.setMaximized(true);
        }
        animateFirstPaneOpen();
           createSunWithLiveRays();

//        createGradientMeteor(); // moved inside runLater
    });

    startStarAnimation();
    lockHistoryPaneSize();
//    createSunInHistoryPane();
//    startGradientMeteorShower();
}




}
