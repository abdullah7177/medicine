package medicine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Point3D;

public class FXMLDocumentController implements Initializable {

    @FXML private StackPane stacpane;
    @FXML private AnchorPane anchor_one;
    @FXML private AnchorPane anchor_two;
    @FXML private Button close;
    @FXML private AnchorPane add_product_anchorpane;
    @FXML private Label add_product_label;
    @FXML private AnchorPane text_centre_pane;
    @FXML private Button add_product_btn;
    @FXML private Button change_btn;

    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Stage stage = (Stage) stacpane.getScene().getWindow();
            if (stage != null) {
                stage.setMaximized(true);
            }
        });

        if (add_product_anchorpane != null) {
            add_product_anchorpane.setOpacity(0.0);
            add_product_anchorpane.setTranslateY(-60);
        }
        if (add_product_label != null) {
            add_product_label.setScaleX(0.0);
            add_product_label.setScaleY(0.0);
            add_product_label.setOpacity(0.0);
        }

        animateFullPageSwing();
        animateAddProductPane();
        animateAddProductLabelBounce();
        animateAddProductLabelBounces();

        addClickRippleEffect(close);
        addClickRippleEffect(stacpane);

        animatePaneBorder();
        addstyle();
        animateAddProductButtonBorder();
        
        
        startStarAnimation();
        createAndAnimateStar();
    }

    //STARS ANIMATION 
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
    star.setFill(Color.WHITE);
    star.setOpacity(0.0);

    // Random start position inside the pane
    double startX = Math.random() * add_product_anchorpane.getWidth();
    double startY = Math.random() * add_product_anchorpane.getHeight();

    // Set position and make sure it's unmanaged (so it doesn't get resized or repositioned)
    star.setLayoutX(startX);
    star.setLayoutY(startY);
    star.setManaged(false);

    add_product_anchorpane.getChildren().add(star);

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
    animation.setOnFinished(e -> add_product_anchorpane.getChildren().remove(star));
    animation.play();
}
    
    
    
    // ✅ OPEN FXMLDocument.fxml AGAIN ON BUTTON CLICK
    @FXML
    private void handleUpdateClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("firstAddTable.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) change_btn.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setTitle("FXMLDocument View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void animateAddProductButtonBorder() {
        Rectangle border = new Rectangle();
        border.setFill(null);
        border.setStroke(Color.web("#1a238b"));
        border.setStrokeWidth(2);
        border.setArcWidth(10);
        border.setArcHeight(10);
        border.setManaged(false);
        border.setMouseTransparent(true);

        double dashLength = 15;
        border.getStrokeDashArray().addAll(dashLength, dashLength * 2);
        border.setStrokeDashOffset(0);

        border.widthProperty().bind(Bindings.createDoubleBinding(() ->
            add_product_btn.getLayoutBounds().getWidth() + 10,
            add_product_btn.layoutBoundsProperty()));
        border.heightProperty().bind(Bindings.createDoubleBinding(() ->
            add_product_btn.getLayoutBounds().getHeight() + 10,
            add_product_btn.layoutBoundsProperty()));

        border.layoutXProperty().bind(add_product_btn.layoutXProperty().subtract(5));
        border.layoutYProperty().bind(add_product_btn.layoutYProperty().subtract(5));
        border.scaleXProperty().bind(add_product_btn.scaleXProperty());
        border.scaleYProperty().bind(add_product_btn.scaleYProperty());

        ((Pane)add_product_btn.getParent()).getChildren().add(border);

        Timeline borderAnimation = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(border.strokeDashOffsetProperty(), 0, Interpolator.LINEAR)),
            new KeyFrame(Duration.seconds(2), new KeyValue(border.strokeDashOffsetProperty(), 60, Interpolator.LINEAR))
        );
        borderAnimation.setCycleCount(Animation.INDEFINITE);
        borderAnimation.play();
    }

    public void addstyle() {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(500), add_product_btn);
        scaleUp.setToX(1.2);
        scaleUp.setToY(1.2);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(500), add_product_btn);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        add_product_btn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            scaleDown.stop();
            scaleUp.playFromStart();
        });

        add_product_btn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            scaleUp.stop();
            scaleDown.playFromStart();
        });
    }

    private void animatePaneBorder() {
        Rectangle border = new Rectangle();
        border.setFill(null);
        border.setStroke(Color.web("#1a238b"));
        border.setStrokeWidth(3);
        border.setArcWidth(20);
        border.setArcHeight(20);
        border.setManaged(false);
        border.setMouseTransparent(true);

        border.widthProperty().bind(text_centre_pane.widthProperty());
        border.heightProperty().bind(text_centre_pane.heightProperty());

        double dashLength = 200;
        border.getStrokeDashArray().addAll(dashLength, dashLength * 3);
        border.setStrokeDashOffset(0);

        if (!text_centre_pane.getChildren().contains(border)) {
            text_centre_pane.getChildren().add(0, border);
        }

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

    public void addClickRippleEffect(final Node node) {
        final Pane parentPane;
        if (node instanceof Pane) {
            parentPane = (Pane) node;
        } else if (node.getParent() instanceof Pane) {
            parentPane = (Pane) node.getParent();
        } else {
            return;
        }

        node.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            double x = e.getX();
            double y = e.getY();

            Circle ripple = new Circle(0, Color.web("#3399FF", 0.5));
            ripple.setCenterX(x);
            ripple.setCenterY(y);

            parentPane.getChildren().add(ripple);

            Timeline expand = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(ripple.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(ripple.radiusProperty(), 50))
            );

            FadeTransition fade = new FadeTransition(Duration.seconds(0.5), ripple);
            fade.setFromValue(0.5);
            fade.setToValue(0);

            ParallelTransition animation = new ParallelTransition(expand, fade);
            animation.setOnFinished(ev -> parentPane.getChildren().remove(ripple));
            animation.play();
        });
    }

    private void animateFullPageSwing() {
        if (stacpane != null) {
            stacpane.setTranslateY(-100);
            stacpane.setRotationAxis(new Point3D(0, 0, 1));
            stacpane.setRotate(-10);

            TranslateTransition drop = new TranslateTransition(Duration.seconds(1.5), stacpane);
            drop.setFromY(-100);
            drop.setToY(0);
            drop.setInterpolator(Interpolator.EASE_OUT);

            RotateTransition rotate = new RotateTransition(Duration.seconds(0.6), stacpane);
            rotate.setFromAngle(-10);
            rotate.setToAngle(0);
            rotate.setInterpolator(Interpolator.EASE_BOTH);

            ScaleTransition bounce = new ScaleTransition(Duration.seconds(0.2), stacpane);
            bounce.setFromX(1.0);
            bounce.setFromY(1.0);
            bounce.setToX(1.03);
            bounce.setToY(1.03);
            bounce.setCycleCount(2);
            bounce.setAutoReverse(true);
            bounce.setInterpolator(Interpolator.EASE_OUT);

            SequentialTransition entrance = new SequentialTransition(drop, rotate, bounce);
            entrance.play();
        }
    }
    
    ////

    private void animateAddProductPane() {
        if (add_product_anchorpane != null) {
            TranslateTransition slideDown = new TranslateTransition(Duration.seconds(2.0), add_product_anchorpane);
            slideDown.setFromY(-60);
            slideDown.setToY(0);
            slideDown.setInterpolator(Interpolator.EASE_BOTH);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2.0), add_product_anchorpane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.setInterpolator(Interpolator.EASE_IN);

            ScaleTransition bounce = new ScaleTransition(Duration.seconds(0.3), add_product_anchorpane);
            bounce.setFromX(1.0);
            bounce.setFromY(1.0);
            bounce.setToX(1.03);
            bounce.setToY(1.03);
            bounce.setCycleCount(2);
            bounce.setAutoReverse(true);
            bounce.setInterpolator(Interpolator.EASE_OUT);

            ParallelTransition appear = new ParallelTransition(slideDown, fadeIn);
            SequentialTransition fullAnimation = new SequentialTransition(appear, bounce);
            fullAnimation.play();
        }
    }

    private void animateAddProductLabelBounce() {
        if (add_product_label != null) {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), add_product_label);
            scale.setFromX(0.0);
            scale.setFromY(0.0);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fade = new FadeTransition(Duration.seconds(0.6), add_product_label);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);

            PauseTransition delay = new PauseTransition(Duration.seconds(2.1));

            ParallelTransition labelAnimation = new ParallelTransition(scale, fade);
            SequentialTransition full = new SequentialTransition(delay, labelAnimation);
            full.play();
        }
    }

    private void animateAddProductLabelBounces() {
        if (add_product_label != null) {
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setRadius(30);
            glow.setSpread(0.3);
            add_product_label.setEffect(glow);

            ScaleTransition scale = new ScaleTransition(Duration.seconds(1), add_product_label);
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
