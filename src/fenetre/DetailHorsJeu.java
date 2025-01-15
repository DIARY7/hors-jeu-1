package fenetre;

import java.nio.file.Path;
import java.nio.file.Paths;

import dto.InfoDetails;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DetailHorsJeu {

    public DetailHorsJeu(String nomFile,InfoDetails infoDetails) {
        Stage primaryStage = new Stage();
        // Titre
        Label titleLabel = new Label("Analyse d'Hors Jeu");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Police en gras, taille 24
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-alignment: center; -fx-padding: 20px; -fx-font-size:20px ; -fx-font-weight:bold ; -fx-margin-top:5px;");
        titleLabel.setMaxWidth(Double.MAX_VALUE);  // Pour centrer horizontalement

        // Image Avant
        Path path = Paths.get("images/"+nomFile);
        Image avantImage = new Image("file:" + path.toAbsolutePath().toString());
        ImageView avantImageView = new ImageView(avantImage);
        avantImageView.setFitWidth(300);
        avantImageView.setPreserveRatio(true);

        // Image Après
        Path pathResult = Paths.get("images/result.jpg");
        Image apresImage = new Image("file:" + pathResult.toAbsolutePath().toString());
        ImageView apresImageView = new ImageView(apresImage);
        apresImageView.setFitWidth(300);
        apresImageView.setPreserveRatio(true);

        // Sous-titres pour les images
        Label avantLabel = new Label("Avant");
        avantLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        avantLabel.setTextFill(Color.WHITE);

        Label apresLabel = new Label("Après");
        apresLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        apresLabel.setTextFill(Color.WHITE);

        // Conteneur des images avec sous-titres
        GridPane imagesBox = new GridPane();
        imagesBox.setHgap(20);
        imagesBox.setVgap(10);
        imagesBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
        
        // Ajouter les sous-titres et les images dans le GridPane
        imagesBox.add(avantLabel, 0, 0); // Titre avant
        imagesBox.add(avantImageView, 0, 1); // Image avant
        imagesBox.add(apresLabel, 1, 0); // Titre après
        imagesBox.add(apresImageView, 1, 1); // Image après

        // Tableau des statistiques
        VBox statsBox = new VBox(10);
        statsBox.setStyle("-fx-background-color: #2E3B4E; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label teamLabel = new Label("L'équipe attaquante : "+infoDetails.getNomAttaquant());
        teamLabel.setFont(Font.font("Arial", 16));
        teamLabel.setTextFill(Color.WHITE);

        Label offsideLabel = new Label("Nombre de joueurs hors-jeu (H) : "+infoDetails.getNbOffSide());
        offsideLabel.setFont(Font.font("Arial", 16));
        offsideLabel.setTextFill(Color.WHITE);

        Label onsideLabel = new Label("Nombre de joueurs pas H.jeu (M) : "+infoDetails.getNbNoOffSide());
        onsideLabel.setFont(Font.font("Arial", 16));
        onsideLabel.setTextFill(Color.WHITE);

        statsBox.getChildren().addAll(teamLabel, offsideLabel, onsideLabel);

        // Disposition principale avec GridPane et VBox
        StackPane root = new StackPane();
        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(titleLabel, imagesBox, statsBox);

        root.getChildren().add(mainLayout);

        // Style CSS
        root.setStyle("-fx-background-color: #1A1A1A;");
        root.setPadding(new javafx.geometry.Insets(20));

        // Scene
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("style/style-analyse.css").toExternalForm());

        // Configuration de la fenêtre
        primaryStage.setTitle("Match Stats");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
