package medicine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dash_pane;
    @FXML
    private AnchorPane dash_pane2;
    @FXML
    private AnchorPane dashboard_pane;
    @FXML
    private Label dashboard_label;

    // Star color property (dynamic based on background)
    private final ObjectProperty<Color> starColorProp = new SimpleObjectProperty<>(Color.WHITE);

    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Stage stage = (Stage) dash_pane.getScene().getWindow();
            if (stage != null) {
                stage.setMaximized(true);
            }

            // Start smooth color transition for dashboard_pane
            startSmoothPaneColorTransition(dashboard_pane, dashboard_label);

            // Start stars animation inside dashboard_pane
            startStarAnimation();
        });
        
        animateAddProductLabelBounce();
        animateAddProductLabelBounces();
        
    }

    private void startSmoothPaneColorTransition(AnchorPane pane, Label label) {
        Color darkColor = Color.web("#354172");
        Color lightColor = Color.web("#ffffff");

        // Property to animate pane color
        ObjectProperty<Color> paneColorProp = new SimpleObjectProperty<>(darkColor);
        paneColorProp.addListener((obs, oldColor, newColor) -> {
            String rgb = String.format("#%02X%02X%02X",
                    (int) (newColor.getRed() * 255),
                    (int) (newColor.getGreen() * 255),
                    (int) (newColor.getBlue() * 255));
            pane.setStyle("-fx-background-color: " + rgb + ";");

            // Agar background dark hai (#354172 ke close) -> stars white
            // Agar background light hai (#fff ke close) -> stars dark
            if (newColor.getBrightness() < 0.5) {
                starColorProp.set(Color.WHITE);
            } else {
                starColorProp.set(darkColor);
            }
        });

        // Property to animate label color
        ObjectProperty<Color> labelColorProp = new SimpleObjectProperty<>(Color.WHITE);
        labelColorProp.addListener((obs, oldColor, newColor) -> label.setTextFill(newColor));

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO,
                    new KeyValue(paneColorProp, darkColor, Interpolator.EASE_BOTH),
                    new KeyValue(labelColorProp, Color.WHITE, Interpolator.EASE_BOTH)
            ),
            new KeyFrame(Duration.seconds(5), // slow motion (5 sec)
                    new KeyValue(paneColorProp, lightColor, Interpolator.EASE_BOTH),
                    new KeyValue(labelColorProp, darkColor, Interpolator.EASE_BOTH)
            )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }
  private void startStarAnimation() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
        // Ek sath 3 stars create karenge
        for (int i = 0; i < 3; i++) {
            createStar();
        }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
}


    private void createStar() {
        Circle star = new Circle(1 + Math.random() * 2);
        star.setFill(starColorProp.get());
        star.setOpacity(0.0);

        // Random position inside dashboard_pane
        double startX = Math.random() * dashboard_pane.getWidth();
        double startY = Math.random() * dashboard_pane.getHeight();

        star.setLayoutX(startX);
        star.setLayoutY(startY);
        star.setManaged(false);

        dashboard_pane.getChildren().add(star);

        // Jab star colorProp change ho, star ka color bhi change ho jaye
        starColorProp.addListener((obs, oldColor, newColor) -> star.setFill(newColor));

        // Fade in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), star);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Small movement
        TranslateTransition move = new TranslateTransition(Duration.seconds(3), star);
        move.setByY(-10 + Math.random() * 20);
        move.setByX(-10 + Math.random() * 20);

        // Fade out
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), star);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.seconds(1));

        // Combine animation
        ParallelTransition animation = new ParallelTransition(fadeIn, move, fadeOut);
        animation.setOnFinished(e -> dashboard_pane.getChildren().remove(star));
        animation.play();
    }
    
    
    private void animateAddProductLabelBounce() {
        if (dashboard_label != null) {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), dashboard_label);
            scale.setFromX(0.0);
            scale.setFromY(0.0);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fade = new FadeTransition(Duration.seconds(0.6), dashboard_label);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);

            PauseTransition delay = new PauseTransition(Duration.seconds(2.1));

            ParallelTransition labelAnimation = new ParallelTransition(scale, fade);
            SequentialTransition full = new SequentialTransition(delay, labelAnimation);
            full.play();
        }
    }

    private void animateAddProductLabelBounces() {
        if (dashboard_label != null) {
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setRadius(30);
            glow.setSpread(0.3);
            dashboard_label.setEffect(glow);

            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), dashboard_label);
            scale.setFromX(1.0);
            scale.setFromY(1.0);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.setCycleCount(Animation.INDEFINITE);
            scale.setAutoReverse(true);
            scale.play();
        }
    }


    
    
    
}
