package fenetre;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import composant.Terrain;
import dto.InfoDetails;
import groupe.Equipe;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    int pointRouge;
    int pointBleu;

    public DetailHorsJeu(File fileAvant, File fileApres, InfoDetails infoDetails) throws Exception {
        /*  */
        this.setScore();
        Stage primaryStage = new Stage();
        // Titre
        Label titleLabel = new Label("🕵️‍♀️ Analyse d'Hors Jeu ⚽");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-alignment: center; -fx-padding: 20px; -fx-font-size:20px ; -fx-font-weight:bold ; -fx-margin-top:5px;");
        titleLabel.setMaxWidth(Double.MAX_VALUE);

        // Score Display
        HBox scoreBox = new HBox(20); // 20 pixels spacing between elements
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setPadding(new Insets(15));
        scoreBox.setStyle("-fx-background-color: #2E3B4E; -fx-padding: 15; -fx-border-radius: 8; -fx-background-radius: 8;");

        Label equipeRouge = new Label("Rouge");
        equipeRouge.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        equipeRouge.setTextFill(Color.RED);
        equipeRouge.setStyle("-fx-text-fill:rgb(238, 58, 58);-fx-font-size:22px;-fx-font-weight: bold; -fx-font-style: italic; -fx-letter-spacing: 5px;");

        Label scoreRouge = new Label(this.pointRouge+"");
        scoreRouge.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreRouge.setTextFill(Color.WHITE);

        Label separator = new Label("-");
        separator.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        separator.setTextFill(Color.WHITE);

        Label scoreBleu = new Label(" "+this.pointBleu);
        scoreBleu.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreBleu.setTextFill(Color.WHITE);

        Label equipeBleu = new Label("Bleu");
        equipeBleu.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        equipeBleu.setTextFill(Color.BLUE);
        equipeBleu.setStyle("-fx-text-fill: rgb(15, 105, 222); -fx-font-size: 22px; -fx-font-weight: bold; -fx-font-style: italic; -fx-letter-spacing: 5px;");

        // Add some padding between elements
        HBox.setMargin(equipeRouge, new Insets(0, 10, 0, 0));
        HBox.setMargin(scoreRouge, new Insets(0, 10, 0, 0));
        HBox.setMargin(scoreBleu, new Insets(0, 10, 0, 0));

        scoreBox.getChildren().addAll(equipeRouge, scoreRouge, separator, scoreBleu, equipeBleu);

        // Rest of your existing code...
        // Image Avants
        Path pathAvant = Paths.get("images/result-"+ fileAvant.getName() +"-.jpg");
        Image avantImage = new Image("file:" + pathAvant.toAbsolutePath().toString());
        ImageView avantImageView = new ImageView(avantImage);
        avantImageView.setFitWidth(350);
        avantImageView.setPreserveRatio(true);

        // Image Après
        Path pathApres = Paths.get("images/result-"+ fileApres.getName() +"-.jpg");
        Image apresImage = new Image("file:" + pathApres.toAbsolutePath().toString());
        ImageView apresImageView = new ImageView(apresImage);
        apresImageView.setFitWidth(350);
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
        imagesBox.add(avantLabel, 0, 0);
        imagesBox.add(avantImageView, 0, 1);
        imagesBox.add(apresLabel, 1, 0);
        imagesBox.add(apresImageView, 1, 1);

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

        Label hjLabel = new Label("TIREUR : ");
        hjLabel.setFont(Font.font("Arial", 16)); 
        //hjLabel.setTextFill(Color.WHITE);
        HBox boxHorsJeu = new HBox(2);
        Label l1 = new Label("Pas Hors jeu ");
        l1.setFont(Font.font("Arial", 16));
        Label l2 = new Label("✅");
        l2.setStyle("-fx-text-fill: rgb(0, 255, 0);");
        l2.setFont(Font.font("Arial", 20));
        if (infoDetails.isHorsJeu()) {
            l1.setText("Hors jeu ");
            l2.setText("❌");
            l2.setStyle("-fx-text-fill: rgb(255, 0, 0);");
        }
        boxHorsJeu.getChildren().addAll( hjLabel , l1,l2);

        statsBox.getChildren().addAll(teamLabel, offsideLabel, onsideLabel,boxHorsJeu);

        // Disposition principale avec GridPane et VBox
        StackPane root = new StackPane();
        VBox mainLayout = new VBox(20);
        // Add scoreBox before imagesBox
        mainLayout.getChildren().addAll(titleLabel, scoreBox, imagesBox, statsBox);

        root.getChildren().add(mainLayout);

        // Style CSS
        root.setStyle("-fx-background-color: #1A1A1A;");
        root.setPadding(new javafx.geometry.Insets(20));

        // Scene
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("style/style-analyse.css").toExternalForm());

        // Configuration de la fenêtre
        primaryStage.setTitle("Match Stats");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setScore() throws Exception {
        this.pointRouge = Equipe.getPoint(null, 1 , 1);
        this.pointBleu = Equipe.getPoint(null, 2, 1);
    }

}
