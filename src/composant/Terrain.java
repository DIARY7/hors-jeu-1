package composant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Point;

import groupe.Equipe;
import personnage.Joueur;
import traitement_image.TraitementImage;

public class Terrain {  
    Equipe equipe1;
    Equipe equipe2;
    Ballon balon;

    int posseur;

    Equipe attaquant;

    Equipe defenseur;

    boolean versBas; /* L'attaque est vers le Bas */

    TraitementImage traitement;

    public Terrain() {
           
    }

    public Terrain(File imageFile) throws Exception {
        traitement = new TraitementImage(imageFile);        
        this.equipe1 = new Equipe(0,"Rouge",traitement.detectRedPoints());
        this.equipe2 = new Equipe(1,"Bleu",traitement.detectBluePoints());
        this.balon = traitement.detectBlackPoints().get(0);
        this.init();
    }

    public Terrain(List<Joueur> listeJoueurs1 , List<Joueur> listeJoueurs2, Ballon ballon ){
        this.equipe1 = new Equipe(0,"Rouge",listeJoueurs1);
        this.equipe2 = new Equipe(1,"Bleu",listeJoueurs2);
        this.balon = ballon;
        this.init();
    }

    public void init(){
        setAttaquantAndDefenseur();
        isVersBasAttaque();
    }

    /* Fonction du Jeu */
    public void setAttaquantAndDefenseur(){
        double distance1 = checkJoueurNearBallon( this.equipe1);
        double distance2 = checkJoueurNearBallon(this.equipe2);
        if (distance1 < distance2 ) {
            this.attaquant = this.equipe1;
            this.defenseur = this.equipe2;
        }
        else{
            this.attaquant = this.equipe2;
            this.defenseur = this.equipe1;
        }        
    }

    public List<Joueur> listeJoueurOffSide(){ /* Hors Jeu */
        List<Joueur> joueursOffSide = new ArrayList<>();
        Joueur avantDernierDefenseur = new Joueur();
        if (versBas) {
            avantDernierDefenseur = this.defenseur.getJoueurs().get(this.defenseur.getJoueurs().size() - 2);
        }
        else{
            avantDernierDefenseur = this.defenseur.getJoueurs().get(1);
        }
        for (int i = 0; i < this.attaquant.getJoueurs().size() ; i++) {
            Joueur attaquant = this.attaquant.getJoueurs().get(i); 
            if (versBas) {
                if ( attaquant.getPosition().y + attaquant.getRayon() > (avantDernierDefenseur.getPosition().y + avantDernierDefenseur.getRayon())) {
                    joueursOffSide.add(attaquant);
                }
            }
            else{
                if (attaquant.getPosition().y - attaquant.getRayon() < (avantDernierDefenseur.getPosition().y - avantDernierDefenseur.getRayon())) {
                    joueursOffSide.add(attaquant);
                }
            } 
        }
        return joueursOffSide;
    }

    public List<Joueur> listeJoueurNoOffSide(){ /* Sady apres an'ilay ballon */
        List<Joueur> joueursNoOffSide = new ArrayList<>();
        Joueur avantDernierDefenseur = new Joueur();
        if (versBas) {
            avantDernierDefenseur = this.defenseur.getJoueurs().get(this.defenseur.getJoueurs().size() - 2);
        }
        else{
            avantDernierDefenseur = this.defenseur.getJoueurs().get(1);
        }
        for (int i = 0; i < this.attaquant.getJoueurs().size() ; i++) {
            Joueur attaquant = this.attaquant.getJoueurs().get(i); 
            if (versBas) {
                if (attaquant.getPosition().y > this.balon.getPosition().y) {
                    if ( attaquant.getPosition().y + attaquant.getRayon() < (avantDernierDefenseur.getPosition().y + avantDernierDefenseur.getRayon())) {
                        joueursNoOffSide.add(attaquant);
                    }
                }
            }
            else{
                if (attaquant.getPosition().y < this.balon.getPosition().y) {
                    if ( attaquant.getPosition().y - attaquant.getRayon() > (avantDernierDefenseur.getPosition().y - avantDernierDefenseur.getRayon())) {
                        joueursNoOffSide.add(attaquant);
                    }
                }
            } 
        }
        return joueursNoOffSide;
    }

    public void isVersBasAttaque(){
        double yMinAttaquant = this.attaquant.getMinYPlayers();
        double yMinDefenseur = this.defenseur.getMinYPlayers();
        if (yMinAttaquant < yMinDefenseur ) {
            this.versBas = true;
            System.out.println("Vers le bas");
        } 
        else{
            this.versBas = false;
            System.out.println("Vers le haut");
        }        
    }

    /* Fonction complementaire */

    public double checkJoueurNearBallon(Equipe equipe){
        double plusProche = Double.POSITIVE_INFINITY;
        for (int i = 0; i < equipe.getJoueurs().size() ; i++) {
            double distance = this.getDistance( equipe.getJoueurs().get(i).getPosition() , this.balon.getPosition());
            if (distance < plusProche ) {
                plusProche = distance;
            }
        }
        return plusProche;
    }

    public double getDistance(Point p1 , Point p2){
        double distance = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        return distance;
    }

    public Ballon getBalon() {
        return balon;
    }

    public void setBalon(Ballon balon) {
        this.balon = balon;
    }

    public TraitementImage getTraitement() {
        return traitement;
    }

    public void setTraitement(TraitementImage traitement) {
        this.traitement = traitement;
    }

    public Equipe getAttaquant() {
        return attaquant;
    }

    public void setAttaquant(Equipe attaquant) {
        this.attaquant = attaquant;
    }

    
    public Equipe getDefenseur() {
        return defenseur;
    }

    public void setDefenseur(Equipe defenseur) {
        this.defenseur = defenseur;
    }
    
}
