package medicine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Medicine extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("historyMonthly.fxml"));
        Parent root = loader.load();

        //firstAddTable
        //historyMonthly  FXMLDocument
        
        
        
        
        
        // Create the scene
        Scene scene = new Scene(root);

        // Set window properties
        stage.setTitle("Medicine Management System");

//         ✅ Keep window buttons (minimize, maximize, close)
//        stage.setResizable(true);
//        stage.setMaximized(true); // Full-screen effect but keeps window controls
        stage.initStyle(StageStyle.UNDECORATED);
        // ❌ DO NOT use fullScreen mode
//         stage.setFullScreen(true);

        // Show the stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
