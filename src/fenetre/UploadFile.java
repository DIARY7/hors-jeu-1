package fenetre;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import personnage.Joueur;

import java.io.File;
import java.util.List;

import composant.Terrain;
import dto.InfoDetails;

public class UploadFile extends Application {
    private TextField filePathField;
    private Button uploadButton;
    private Button analyseButton;
    private Label statusLabel;
    private File selectedFile;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Analyseur de Fichiers");

        // Titre de bienvenue
        Label welcomeLabel = new Label("VAR");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        // Message d'instruction
        Label instructionLabel = new Label("Veuillez uploader votre fichier");
        instructionLabel.setFont(Font.font("Arial", 16));

        // Champ de fichier et bouton d'upload
        filePathField = new TextField();
        filePathField.setEditable(false);
        filePathField.setPrefWidth(300);
        filePathField.setPromptText("Aucun fichier sélectionné");

        uploadButton = new Button("Uploader");
        uploadButton.getStyleClass().add("action-button");

        // Bouton d'analyse
        analyseButton = new Button("Analyser");
        analyseButton.getStyleClass().add("action-button");
        analyseButton.setDisable(true);

        // Label de status
        statusLabel = new Label("");
        statusLabel.getStyleClass().add("status-label");

        // Layout pour la sélection de fichier
        HBox fileBox = new HBox(10);
        fileBox.setAlignment(Pos.CENTER);
        fileBox.getChildren().addAll(filePathField, uploadButton);

        // Layout principal
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(
            welcomeLabel,
            instructionLabel,
            fileBox,
            analyseButton,
            statusLabel
        );

        // Gestionnaires d'événements
        uploadButton.setOnAction(e -> uploadFile(primaryStage));
        analyseButton.setOnAction(e -> analyseFile(primaryStage));

        // Création de la scène
        Scene scene = new Scene(mainLayout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void uploadFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier");
        
        // Définir un répertoire initial
        File initialDirectory = new File("D:\\Etudes\\S5\\ArchiLog\\foot\\hors-jeu-1\\images"); // Remplacez par le répertoire souhaité
        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        // Filtres pour les types de fichiers
        FileChooser.ExtensionFilter imageFilter = 
            new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            filePathField.setText(selectedFile.getName());
            analyseButton.setDisable(false);
            statusLabel.setText("Fichier sélectionné avec succès");
        }
    }

    private void analyseFile(Stage primaryStage) {
        try {
            if (selectedFile != null) {
                /* Ato miantso an'ilay IA */
                Terrain terrain = new Terrain(selectedFile);
                List<Joueur> joueursOffSide = terrain.listeJoueurOffSide();
                List<Joueur> joueursMety = terrain.listeJoueurNoOffSide();
                
                terrain.getTraitement().putPlayersNotOff(joueursMety);
                terrain.getTraitement().putPlayersOff(joueursOffSide);

                InfoDetails infoDetails = new InfoDetails( terrain.getAttaquant().getNomEquipe() , joueursOffSide.size(), joueursMety.size());
                new DetailHorsJeu(selectedFile.getName(),infoDetails);
            }    
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);

        // Définir le titre de l'alerte
            alert.setTitle("Erreur");

        // Définir l'en-tête (facultatif)
            alert.setHeaderText("Une erreur s'est produite");

        // Définir le contenu du message
            alert.setContentText(e.getMessage());

        // Afficher l'alerte et attendre la réponse de l'utilisateur
            alert.showAndWait();
        }
        
    }

    /*  */

    public static void main(String[] args) {
        launch(args);
    }
}
