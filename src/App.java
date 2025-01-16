import java.util.List;
import java.io.File;

import org.opencv.core.Point;

import composant.Terrain;
import personnage.Joueur;
import traitement_image.TraitementImage;

public class App {
    public static void main(String[] args) {
        try {
            /* Atao any @ Listener Bouton Upload */
            // File imageFile = new File("images/jeu1.jpg");
            // Terrain terrain = new Terrain(imageFile);
            // List<Joueur> joueursOffSide = terrain.listeJoueurOffSide();
            // List<Joueur> joueursMety = terrain.listeJoueurNoOffSide();
            // System.out.println("La position y du ballon " + terrain.getBalon().getPosition().y);
            // System.out.println("Les joueurs off Side");
            // for (int i = 0; i < joueursOffSide.size(); i++) {
            //     System.out.println("Position : "+joueursOffSide.get(i).getPosition().y);
            // }
            // System.out.println("\n\n");
            // System.out.println("Les joueurs Non Off Side");
            // for (int i = 0; i < joueursMety.size(); i++) {
            //     System.out.println("Position : "+joueursMety.get(i).getPosition().y);
            // }
            // terrain.getTraitement().putPlayersOff(joueursOffSide);
            // terrain.getTraitement().putPlayersNotOff(joueursMety);

            // TraitementImage traitement = new TraitementImage(imageFile);        
            // List<Joueur> listePointsReds = traitement.detectRedPoints();
            // List<Joueur> listeBluePoints = traitement.detectBluePoints();
            // List<Point> balls = traitement.detectBlackPoints();
            // System.out.println("Pour les rouges ");
            // for (int i = 0; i < listePointsReds.size(); i++) {
            //     System.out.println("Coordoonnes x = "+listePointsReds.get(i).getPosition().x + " y = "+listePointsReds.get(i).getPosition().y + " le rayon = "+listePointsReds.get(i).getRayon());
            // }

            // System.out.println("Pour les bleus ");
            // for (int i = 0; i < listeBluePoints.size(); i++) {
            //     System.out.println("Coordoonnes x = "+listeBluePoints.get(i).getPosition().x + " y = "+listeBluePoints.get(i).getPosition().y);
            // }

            // System.out.println("Position des balons");
            // for (int i = 0; i < balls.size(); i++) {
            //     System.out.println("Coordoonnes x = "+balls.get(i).x + " y = "+balls.get(i).y);
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
