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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
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

    // SUN - Fixed in position
    private void createSunWithLiveRays() {
        if (history_centre_pane == null) return;

        // Wait for pane to have proper size
        Platform.runLater(() -> {
            if (history_centre_pane.getWidth() == 0) return;
            
            double sunX = 40;
            double sunY = 40;

            Circle sunCore = new Circle(20);
            sunCore.setFill(Color.web("#FDB813"));
            sunCore.setEffect(new DropShadow(30, Color.web("#FFD700")));
            sunCore.setLayoutX(sunX);
            sunCore.setLayoutY(sunY);
            sunCore.setManaged(false);

            Circle sunGlow = new Circle(50);
            sunGlow.setFill(Color.web("#FFF9C4", 0.3));
            sunGlow.setLayoutX(sunX);
            sunGlow.setLayoutY(sunY);
            sunGlow.setManaged(false);

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
        });
    }

    private Group createInnerSunRays(double centerX, double centerY) {
        Group rayGroup = new Group();
        rayGroup.setManaged(false);

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

    // ✨ ROTATING COLOR BORDER - Starting from #354172 ✨
    private void applyRainbowBorder() {
        // Starting hue for #354172 (bluish color)
        hue = 230; // This gives us a color close to #354172
        
        rainbowTimeline = new Timeline(
            new KeyFrame(Duration.millis(40), event -> {
                // Hue continuously rotate karta rahega
                hue += 1.5;
                if (hue > 360) {
                    hue = 0;
                }
                
                // HSB color model se rotating color
                Color rotatingColor = Color.hsb(hue, 0.6, 0.45); // Saturation and brightness adjusted for #354172 tone
                
                // Single line border with immediate rounding
                history_centre_pane.setBorder(new Border(
                    new BorderStroke(
                        rotatingColor,                   // rotating color starting from #354172
                        BorderStrokeStyle.SOLID,         // solid single line
                        new CornerRadii(10),             // immediate smooth rounding
                        new BorderWidths(3)              // 3px border
                    )
                ));
            })
        );
        
        rainbowTimeline.setCycleCount(Timeline.INDEFINITE);
        rainbowTimeline.play();
    }

    // Optional: Rainbow animation ko stop/control karne ke liye
    public void stopRainbowAnimation() {
        if (rainbowTimeline != null) {
            rainbowTimeline.stop();
        }
    }
    
    public void setRainbowSpeed(double speed) {
        if (rainbowTimeline != null) {
            rainbowTimeline.setRate(speed);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Stage stage = (Stage) staxkpane.getScene().getWindow();
            if (stage != null) {
                stage.setMaximized(true);
            }
            animateFirstPaneOpen();
            createSunWithLiveRays();
            
            // 🌈 Rainbow border animation start
            applyRainbowBorder();
        });

        startStarAnimation();
        lockHistoryPaneSize();
    }
}