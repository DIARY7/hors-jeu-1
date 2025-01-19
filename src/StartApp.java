import fenetre.UploadFile;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Lancer l'interface graphique ici
        new UploadFile().start(primaryStage);  // Lancer votre classe UploadFile
    }

    public static void main(String[] args) {
        launch(args);  // Lancer l'application JavaFX
    }
}