package traitement_image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import composant.Ballon;
import personnage.Joueur;

public class TraitementImage {
    
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    
    Mat image;
    Mat hsvImage;
    
    public TraitementImage(File imageFile) throws Exception {
        // Charger l'image
        image = Imgcodecs.imread(imageFile.getAbsolutePath());

        if (image.empty()) {
            throw new Exception("Erreur: Impossible de charger l'image.");
        }

        // Convertir l'image en espace HSV
        this.hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);
    }

    public List<Joueur> detectRedPoints() {
        // Définir les plages de couleur rouge en HSV
        Scalar lowerRed1 = new Scalar(0, 100, 100);    // Premier intervalle rouge
        Scalar upperRed1 = new Scalar(10, 255, 255);
        Scalar lowerRed2 = new Scalar(160, 100, 100);  // Deuxième intervalle rouge
        Scalar upperRed2 = new Scalar(179, 255, 255);
    
        // Masque pour détecter les rouges
        Mat mask1 = new Mat();
        Mat mask2 = new Mat();
        Core.inRange(hsvImage, lowerRed1, upperRed1, mask1);
        Core.inRange(hsvImage, lowerRed2, upperRed2, mask2);
    
        // Combiner les deux masques
        Mat redMask = new Mat();
        Core.add(mask1, mask2, redMask);
    
        // Trouver les contours des zones rouges
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(redMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
    
        // Liste pour stocker les coordonnées des points rouges
        List<Joueur> joueursRed = new ArrayList<>();
    
        for (MatOfPoint contour : contours) {
            if (contour.size().height > 0) { // Vérifier que le contour n'est pas vide
                // Calculer le cercle minimum englobant
                Point center = new Point();
                float[] radius = new float[1];
                Imgproc.minEnclosingCircle(new MatOfPoint2f(contour.toArray()), center, radius);
        
                // Vérifier que le rayon est valide
                if (radius[0] > 0) {
                    // Ajouter le joueur avec sa position et son rayon
                    joueursRed.add(new Joueur(center, 0, (double) radius[0]));
        
                    // Dessiner le cercle pour la visualisation
                    //Imgproc.circle(image, center, (int) radius[0], new Scalar(0, 255, 0), 2);
                } else {
                    System.out.println("Rayon nul détecté, contour ignoré.");
                }
            } else {
                System.out.println("Contour vide détecté et ignoré.");
            }
        }
    
        // Sauvegarder l'image avec les annotations
        System.out.println("Liste des ");
    
        return joueursRed;
    }
    

    public List<Joueur> detectBluePoints() {
        // Définir les plages de couleur bleue en HSV
        Scalar lowerBlue = new Scalar(100, 100, 100);  // Intervalle bleu
        Scalar upperBlue = new Scalar(140, 255, 255);
    
        // Masque pour détecter les bleus
        Mat blueMask = new Mat();
        Core.inRange(hsvImage, lowerBlue, upperBlue, blueMask);
    
        // Trouver les contours des zones bleues
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(blueMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
    
        // Liste pour stocker les coordonnées des points bleus
        List<Joueur> joueursBlue = new ArrayList<>();
    
        for (MatOfPoint contour : contours) {
            if (contour.size().height > 0) { // Vérifier que le contour n'est pas vide
                // Calculer le cercle minimum englobant
                Point center = new Point();
                float[] radius = new float[1];
                Imgproc.minEnclosingCircle(new MatOfPoint2f(contour.toArray()), center, radius);
    
                // Vérifier que le rayon est valide
                if (radius[0] > 0) {
                    // Ajouter le joueur avec sa position et son rayon
                    joueursBlue.add(new Joueur(center, 1, (double) radius[0]));
    
                    // Dessiner le cercle pour la visualisation
                    //Imgproc.circle(image, center, (int) radius[0], new Scalar(255, 0, 0), 2); // Cercle bleu
                } else {
                    System.out.println("Rayon nul détecté, contour ignoré.");
                }
            } else {
                System.out.println("Contour vide détecté et ignoré.");
            }
        }
    
        System.out.println("Azo ny liste des bleus");
    
        return joueursBlue;
    }
    

    public List<Ballon> detectBlackPoints() {
        // Convertir l'image en HSV
        Mat hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);
    
        // Paramètres pour le noir en HSV
        Scalar lowerBlack = new Scalar(0, 0, 0);
        Scalar upperBlack = new Scalar(180, 255, 20);  // Plage pour le noir (plus large pour capturer tout type de noir)
    
        // Créer le masque pour le noir
        Mat blackMask = new Mat();
        Core.inRange(hsvImage, lowerBlack, upperBlack, blackMask);
    
        // Appliquer des opérations morphologiques pour nettoyer le masque
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));
        Imgproc.erode(blackMask, blackMask, kernel);
        Imgproc.dilate(blackMask, blackMask, kernel);
    
        // Trouver les contours dans le masque
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(blackMask, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
    
        // Liste pour stocker les rayons des cercles détectés
        List<Integer> radii = new ArrayList<>();

        List<Ballon> listeBalons = new ArrayList<>();
    
        for (MatOfPoint contour : contours) {
            double contourArea = Imgproc.contourArea(contour);
            
            // Seuil de taille pour éviter les petits points
            if (contourArea > 50) {  // Seuillage pour les points bien définis
                Moments moments = Imgproc.moments(contour);
                if (moments.get_m00() != 0) {
                    int x = (int) (moments.get_m10() / moments.get_m00());
                    int y = (int) (moments.get_m01() / moments.get_m00());
    
                    // Vérification de la couleur du pixel à ce point
                    double[] pixel = image.get(y, x);
                    // Vérifier si le pixel est vraiment noir
                    if (pixel != null && pixel[0] < 20 && pixel[1] < 20 && pixel[2] < 20) {
                        listeBalons.add(new Ballon(new Point(x, y), 5));
                        // Calculer le rayon du cercle détecté
                        int radius = 5; // Défini ici le rayon utilisé dans Imgproc.circle()
                        radii.add(radius); // Stocker le rayon
                        // Marquer le point détecté avec un cercle vert fluorescent
                      //Imgproc.circle(image, new Point(x, y), radius, new Scalar(0, 255, 0), 2);
                    }
                }
            }
        }
    
        // Sauvegarder l'image résultante avec les annotations
        String outputImagePath = "images/result.jpg";
        Imgcodecs.imwrite(outputImagePath, image);
        System.out.println("Image annotée sauvegardée à : " + outputImagePath);
    
        // Retourner les points noirs détectés et les rayons associés
        return listeBalons;
    }
        
    /*----------------------- Apres ------------------------- */
    public void putPlayersNotOff(List<Joueur> joueurs) {
        for (Joueur joueur : joueurs) {
            Point position = joueur.getPosition();
            if (position != null) {
                // Afficher la lettre "H" à côté de la position du joueur
                Imgproc.putText(
                    image,                               // Image sur laquelle écrire
                    "M",                                 // Texte à afficher
                    new Point(position.x + 10, position.y), // Position légèrement décalée à droite du joueur
                    Imgproc.FONT_HERSHEY_SIMPLEX,        // Police du texte
                    1.75,                                 // Taille du texte
                    new Scalar(220, 109, 16),               // Couleur du texte (vert)
                    3                                   // Épaisseur du texte
                );
            }
        }
    
        // Sauvegarder l'image annotée
        String outputImagePath = "images/result.jpg";
        Imgcodecs.imwrite(outputImagePath, image);
        System.out.println("Image annotée sauvegardée à : " + outputImagePath);
    }
    

    public void putPlayersOff( List<Joueur> joueurs){
        for (Joueur joueur : joueurs) {
            Point position = joueur.getPosition();
            if (position != null) {
                // Afficher la lettre "H" à côté de la position du joueur
                Imgproc.putText(
                    image,                               // Image sur laquelle écrire
                    "H",                                 // Texte à afficher
                    new Point(position.x + 10, position.y), // Position légèrement décalée à droite du joueur
                    Imgproc.FONT_HERSHEY_SIMPLEX,        // Police du texte
                    1.75,                                 // Taille du texte
                    new Scalar(214, 78, 210),               // Couleur du texte (vert)
                    3                                   // Épaisseur du texte
                );
            }
        }
    
        // Sauvegarder l'image annotée
        String outputImagePath = "images/result.jpg";
        Imgcodecs.imwrite(outputImagePath, image);
        System.out.println("Image annotée sauvegardée à : " + outputImagePath);
    }

    public void putLinSideOff(Point point,double rayon) {
        if (point != null) {
            // Récupérer la largeur de l'image
            int imageWidth = image.width();
    
            // Déterminer les points de début et de fin de la ligne
            Point startPoint = new Point(0, point.y+rayon);
            Point endPoint = new Point(imageWidth, point.y + rayon);
    
            // Tracer une ligne horizontale jaune
            Imgproc.line(
                image,                     // Image sur laquelle dessiner
                startPoint,                // Point de départ
                endPoint,                  // Point de fin
                new Scalar(0, 255, 255),   // Couleur (jaune en BGR)
                2                          // Épaisseur de la ligne
            );
    
            // Sauvegarder l'image résultante
            String outputImagePath = "images/result.jpg";
            Imgcodecs.imwrite(outputImagePath, image);
            System.out.println("Ligne tracée et image sauvegardée à : " + outputImagePath);
        } else {
            System.out.println("Le point spécifié est nul. Aucune ligne tracée.");
        }
    }

}
