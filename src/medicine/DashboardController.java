package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
    private StackPane dash_stack;
    @FXML
    private Button dash_add_btnn;
    @FXML
    private ImageView addProductImage;
    @FXML
    private Button historyBtn;
    @FXML
    private ImageView hostoryimage;
    @FXML
    private Button log_out;

    @FXML
    public void close() {
        System.exit(0);
    }

    
    //kmcnvndfvjndf/
    //njnvjxfvnj
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Stage stage = (Stage) dash_pane.getScene().getWindow();
            if (stage != null) {
                stage.setFullScreen(true);
            }
    // Start smooth color transition for dashboard_pane
            startSmoothPaneColorTransition(dashboard_pane, dashboard_label);

            // Start stars animation inside dashboard_pane
            startStarAnimation();
        });
        
     animateAddProductButtonBounces();
     animateAddProductButtonBounce();

        
        animateAddProductLabelBounce();
        animateAddProductLabelBounces();
        
        animateButtonColor(dash_add_btnn);
        
        startImagePulseAnimation(addProductImage);
        
        animateHistoryBtn();
        animateHistoryBtns();
        AnimateHISTORY(historyBtn);
        
        histtyBtnImage();

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
    //.........///////////////////
    //////AADD bUTTON
    
    
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
    
 private void animateButtonColor(Button button) {
    Color startColor = Color.web("#378b38");
    Color endColor = Color.web("#d4c146");

    ObjectProperty<Color> colorProperty = new SimpleObjectProperty<>(startColor);

    colorProperty.addListener((obs, oldColor, newColor) -> {
        String rgb = String.format("#%02X%02X%02X",
                (int) (newColor.getRed() * 255),
                (int) (newColor.getGreen() * 255),
                (int) (newColor.getBlue() * 255));
        button.setStyle("-fx-background-color: " + rgb + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
    });

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(colorProperty, startColor, Interpolator.EASE_IN)),
            new KeyFrame(Duration.seconds(4), new KeyValue(colorProperty, endColor, Interpolator.EASE_IN))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.setAutoReverse(true);
    timeline.play();
}
 
 
 
  private void animateAddProductButtonBounce() {
        if (dash_add_btnn != null) {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), dash_add_btnn);
            scale.setFromX(0.0);
            scale.setFromY(0.0);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fade = new FadeTransition(Duration.seconds(0.6), dash_add_btnn);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);

            PauseTransition delay = new PauseTransition(Duration.seconds(2.1));

            ParallelTransition labelAnimation = new ParallelTransition(scale, fade);
            SequentialTransition full = new SequentialTransition(delay, labelAnimation);
            full.play();
        }
    }

    private void animateAddProductButtonBounces() {
        if (dash_add_btnn != null) {
//            DropShadow glow = new DropShadow();
//            glow.setColor(Color.CORNFLOWERBLUE);
//            glow.setRadius(30);
//            glow.setSpread(0.3);
//            dash_add_btnn.setEffect(glow);

            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), dash_add_btnn);
            scale.setFromX(1.0);
            scale.setFromY(1.0);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.setCycleCount(Animation.INDEFINITE);
            scale.setAutoReverse(true);
            scale.play();
        }
    }
    
    
    @FXML
private void handleDashAddButton(ActionEvent event) {
    try {
        // Load the target FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
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
private void handleDashHIstoryButton(ActionEvent event) {
    try {
        // Load the target FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("historyMonthly.fxml"));
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

    
    
    
    
    
    
    
    //////////////////////////////////
    //HISTORY BUTTONS

    private void startImagePulseAnimation(ImageView imageView) {
    ScaleTransition pulse = new ScaleTransition(Duration.seconds(1), imageView);
    pulse.setFromX(1.0);
    pulse.setFromY(1.0);
    pulse.setToX(1.2);
    pulse.setToY(1.2);
    pulse.setCycleCount(Animation.INDEFINITE);
    pulse.setAutoReverse(true);
    pulse.play();
}

    
      private void animateHistoryBtn() {
        if (historyBtn != null) {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), historyBtn);
            scale.setFromX(0.0);
            scale.setFromY(0.0);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fade = new FadeTransition(Duration.seconds(0.6), historyBtn);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);

            PauseTransition delay = new PauseTransition(Duration.seconds(2.1));

            ParallelTransition labelAnimation = new ParallelTransition(scale, fade);
            SequentialTransition full = new SequentialTransition(delay, labelAnimation);
            full.play();
        }
    }

    private void animateHistoryBtns() {
        if (historyBtn != null) {

            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), historyBtn);
            scale.setFromX(1.0);
            scale.setFromY(1.0);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.setCycleCount(Animation.INDEFINITE);
            scale.setAutoReverse(true);
            scale.play();
        }
    }
     
    
     private void AnimateHISTORY(Button button) {
    Color startColor = Color.web("#8d8484");
    Color endColor = Color.web("#a36868");

    ObjectProperty<Color> colorProperty = new SimpleObjectProperty<>(startColor);

    colorProperty.addListener((obs, oldColor, newColor) -> {
        String rgb = String.format("#%02X%02X%02X",
                (int) (newColor.getRed() * 255),
                (int) (newColor.getGreen() * 255),
                (int) (newColor.getBlue() * 255));
        button.setStyle("-fx-background-color: " + rgb + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");
    });

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(colorProperty, startColor, Interpolator.EASE_IN)),
            new KeyFrame(Duration.seconds(4), new KeyValue(colorProperty, endColor, Interpolator.EASE_IN))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.setAutoReverse(true);
    timeline.play();
}

    
      private void histtyBtnImage() {
        RotateTransition rotate = new RotateTransition(Duration.seconds(2), hostoryimage);
        rotate.setByAngle(-360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();
    }
     
      
      
      
     
}
