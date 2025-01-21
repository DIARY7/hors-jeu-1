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
    int arretRouge;
    int arretBleu;

    public DetailHorsJeu(File fileAvant, File fileApres, InfoDetails infoDetails) throws Exception {
        this.setScore();

        Stage primaryStage = new Stage();

        // Titre
        Label titleLabel = new Label("🕵️‍♀️ Analyse d'Hors Jeu ⚽");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-alignment: center; -fx-padding: 20px; -fx-font-size:20px ; -fx-font-weight:bold ; -fx-margin-top:5px;");
        titleLabel.setMaxWidth(Double.MAX_VALUE);

        // Section des scores et arrêts
        VBox scoreBoxWrapper = new VBox(10);
        scoreBoxWrapper.setAlignment(Pos.CENTER);
        scoreBoxWrapper.setPadding(new Insets(15));
        scoreBoxWrapper.setStyle("-fx-background-color: #2E3B4E; -fx-padding: 15; -fx-border-radius: 8; -fx-background-radius: 8;");

        HBox scoreBox = new HBox(20);
        scoreBox.setAlignment(Pos.CENTER);

        Label equipeRouge = new Label("Rouge");
        equipeRouge.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        equipeRouge.setTextFill(Color.RED);
        equipeRouge.setStyle("-fx-text-fill:rgb(238, 58, 58);-fx-font-size:22px;-fx-font-weight: bold; -fx-font-style: italic; -fx-letter-spacing: 5px;");

        Label scoreRouge = new Label(this.pointRouge + "");
        scoreRouge.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreRouge.setTextFill(Color.WHITE);

        Label separator = new Label("-");
        separator.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        separator.setTextFill(Color.WHITE);

        Label scoreBleu = new Label(this.pointBleu + "");
        scoreBleu.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreBleu.setTextFill(Color.WHITE);

        Label equipeBleu = new Label("Bleu");
        equipeBleu.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        equipeBleu.setTextFill(Color.BLUE);
        equipeBleu.setStyle("-fx-text-fill:rgb(39, 75, 233);-fx-font-size:22px;-fx-font-weight: bold; -fx-font-style: italic; -fx-letter-spacing: 5px;");

        scoreBox.getChildren().addAll(equipeRouge, scoreRouge, separator, scoreBleu, equipeBleu);

        // Section des arrêts
        HBox stopsBox = new HBox(20);
        stopsBox.setAlignment(Pos.CENTER);

        Label arretRougeLabel = new Label("Arrêt.A");
        arretRougeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        arretRougeLabel.setTextFill(Color.RED);
        arretRougeLabel.setStyle("-fx-text-fill:rgb(238, 58, 58);-fx-font-size:22px;-fx-font-weight: bold; -fx-font-style: italic; -fx-letter-spacing: 5px;");

        Label arretRouge = new Label(this.arretRouge + "");
        arretRouge.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        arretRouge.setTextFill(Color.WHITE);

        Label separatorStops = new Label("-");
        separatorStops.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        separatorStops.setTextFill(Color.WHITE);

        Label arretBleu = new Label(this.arretBleu + "");
        arretBleu.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        arretBleu.setTextFill(Color.WHITE);

        Label arretBleuLabel = new Label("Arrêt.B");
        arretBleuLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        arretBleuLabel.setTextFill(Color.BLUE);
        arretBleuLabel.setStyle("-fx-text-fill:rgb(39, 75, 233);-fx-font-size:22px;-fx-font-weight: bold; -fx-font-style: italic; -fx-letter-spacing: 5px;");

        stopsBox.getChildren().addAll(arretRougeLabel, arretRouge, separatorStops, arretBleu, arretBleuLabel);

        scoreBoxWrapper.getChildren().addAll(scoreBox, stopsBox);

        // Section des images
        Path pathAvant = Paths.get("images/result-" + fileAvant.getName() + "-.jpg");
        Image avantImage = new Image("file:" + pathAvant.toAbsolutePath().toString());
        ImageView avantImageView = new ImageView(avantImage);
        avantImageView.setFitWidth(350);
        avantImageView.setPreserveRatio(true);

        Path pathApres = Paths.get("images/result-" + fileApres.getName() + "-.jpg");
        Image apresImage = new Image("file:" + pathApres.toAbsolutePath().toString());
        ImageView apresImageView = new ImageView(apresImage);
        apresImageView.setFitWidth(350);
        apresImageView.setPreserveRatio(true);

        Label avantLabel = new Label("Avant");
        avantLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        avantLabel.setTextFill(Color.WHITE);

        Label apresLabel = new Label("Après");
        apresLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        apresLabel.setTextFill(Color.WHITE);

        GridPane imagesBox = new GridPane();
        imagesBox.setHgap(20);
        imagesBox.setVgap(10);
        imagesBox.setAlignment(Pos.CENTER);
        imagesBox.add(avantLabel, 0, 0);
        imagesBox.add(avantImageView, 0, 1);
        imagesBox.add(apresLabel, 1, 0);
        imagesBox.add(apresImageView, 1, 1);

        // Section des statistiques
        VBox statsBox = new VBox(10);
        statsBox.setStyle("-fx-background-color: #2E3B4E; -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label teamLabel = new Label("L'équipe attaquante : " + infoDetails.getNomAttaquant());
        teamLabel.setFont(Font.font("Arial", 16));
        teamLabel.setTextFill(Color.WHITE);

        Label offsideLabel = new Label("Nombre de joueurs hors-jeu (H) : " + infoDetails.getNbOffSide());
        offsideLabel.setFont(Font.font("Arial", 16));
        offsideLabel.setTextFill(Color.WHITE);

        Label onsideLabel = new Label("Nombre de joueurs pas H.jeu (M) : " + infoDetails.getNbNoOffSide());
        onsideLabel.setFont(Font.font("Arial", 16));
        onsideLabel.setTextFill(Color.WHITE);

                // Réintégration de votre section originale
        Label hjLabel = new Label("TIREUR : ");
        hjLabel.setFont(Font.font("Arial", 16));

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

        boxHorsJeu.getChildren().addAll(hjLabel, l1, l2);

        // Ajout des éléments au conteneur statsBox
        statsBox.getChildren().addAll(teamLabel, offsideLabel, onsideLabel, boxHorsJeu);
        // Layout principal
        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(titleLabel, scoreBoxWrapper, imagesBox, statsBox);

        StackPane root = new StackPane(mainLayout);
        root.setStyle("-fx-background-color: #1A1A1A;");

        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("style/style-analyse.css").toExternalForm());

        primaryStage.setTitle("Match Stats");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setScore() throws Exception {
        this.pointRouge = Equipe.getPoint(null, 1 , 1);
        this.pointBleu = Equipe.getPoint(null, 2, 1);
        this.arretRouge = Equipe.getArret(null, 1, 1);
        this.arretBleu = Equipe.getArret(null, 2, 1);
        System.out.println("Arret rouge ="+this.arretRouge);
        System.out.println("Arret bleu = "+this.arretBleu);
    }

}
