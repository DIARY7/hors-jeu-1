import java.util.List;
import java.io.File;

import org.opencv.core.Point;
import org.opencv.core.Rect;

import composant.LineSegment;
import composant.Terrain;
import personnage.Joueur;
import traitement_image.TraitementImage;
import util.Outil;

public class App {
    public static void main(String[] args) {
        /* Atambatra ho ligne ray izay même y */
        /* Atambatra ho ligne iray izay même x ( ka longueur width mitovy @ width de ligne même y) */
        try {
            
            Terrain terrain = new Terrain(new File("images/Match/1-avant-tir.jpg"));
            List<LineSegment> lineSegment = terrain.getTraitement().detectCages();
            lineSegment =  Outil.TrieLineSegmentByY(lineSegment);
            // System.out.println("La liste des lineSegment sont au nombre de "+lineSegment.size());
            terrain.getTraitement().drawLine(lineSegment.get(0));
            terrain.getTraitement().drawLine(lineSegment.get(lineSegment.size()-1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
