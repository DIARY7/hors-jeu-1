package composant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Point;

import groupe.Equipe;
import personnage.Joueur;
import traitement_image.TraitementImage;
import util.Outil;

public class Terrain {  
    Equipe equipe1;
    Equipe equipe2;
    Ballon balon;

    Joueur possesseur;

    Equipe attaquant;

    Equipe defenseur;

    boolean versBas; /* L'attaque est vers le Bas */


    TraitementImage traitement;

    /* Cage */
    LineSegment traverseHaut;
    LineSegment traverseBas;


    public Terrain() {
           
    }

    public Terrain(File imageFile) throws Exception {
        traitement = new TraitementImage(imageFile);        
        this.equipe1 = new Equipe(1,"Rouge",traitement.detectRedPoints());
        this.equipe2 = new Equipe(2,"Bleu",traitement.detectBluePoints());
        this.balon = traitement.detectBlackPoints().get(0);
        this.init();
    }

    public Terrain(List<Joueur> listeJoueurs1 , List<Joueur> listeJoueurs2, Ballon ballon ){
        this.equipe1 = new Equipe(1,"Rouge",listeJoueurs1);
        this.equipe2 = new Equipe(2,"Bleu",listeJoueurs2);
        this.balon = ballon;
        this.init();
    }

    /* Ho an'ilay apres */
    public Terrain(File imageFile,Terrain tAvant) throws Exception {
        this.traitement = new TraitementImage(imageFile);        
        this.attaquant=tAvant.getAttaquant();
        this.defenseur = tAvant.getDefenseur();
        this.balon = traitement.detectBlackPoints().get(0);
        List<LineSegment> lineSegment = Outil.TrieLineSegmentByY(traitement.detectCages());
        this.traverseHaut = lineSegment.get(0);
        this.traverseBas = lineSegment.get(lineSegment.size()-1);
        this.versBas = tAvant.isVersBas();
        this.possesseur = tAvant.getPossesseur();
        this.setBut();
    }
    /*  */

    public void init(){
        setAttaquantAndDefenseur();
        isVersBasAttaque();
    }

    /* Fonction du Jeu */

    public void setAttaquantAndDefenseur(){
        Joueur[] proche1 = new Joueur[1]; // Tableau mutable
        Joueur[] proche2 = new Joueur[1]; // Tableau mutable
        double distance1 = checkJoueurNearBallon(this.equipe1,proche1);
        double distance2 = checkJoueurNearBallon(this.equipe2,proche2);
        
        if (distance1 < distance2 ) {
            this.attaquant = this.equipe1;
            this.defenseur = this.equipe2;
            this.possesseur = proche1[0];
        }
        else{
            this.attaquant = this.equipe2;
            this.defenseur = this.equipe1;
            this.possesseur = proche2[0];
        }        
    }

    public List<Joueur> listeJoueurOffSide(){ /* Hors Jeu */
        List<Joueur> joueursOffSide = new ArrayList<>();
        Joueur avantDernierDefenseur = new Joueur();
        double rayon;
        if (versBas) {
            avantDernierDefenseur = this.defenseur.getJoueurs().get(this.defenseur.getJoueurs().size() - 2);
            rayon = avantDernierDefenseur.getRayon();
        }
        else{
            avantDernierDefenseur = this.defenseur.getJoueurs().get(1);
            rayon = -1*avantDernierDefenseur.getRayon();
        }
        for (int i = 0; i < this.attaquant.getJoueurs().size() ; i++) {
            Joueur attaquant = this.attaquant.getJoueurs().get(i); 
            if (versBas) {
                if (attaquant.getPosition().y + attaquant.getRayon() > (avantDernierDefenseur.getPosition().y + avantDernierDefenseur.getRayon()) ) {
                    if (attaquant==this.possesseur) {
                        this.possesseur.setHorsJeu(true);
                    }
                    joueursOffSide.add(attaquant);
                }
            }
            else{
                if (attaquant.getPosition().y - attaquant.getRayon() < (avantDernierDefenseur.getPosition().y - avantDernierDefenseur.getRayon()) ) {
                    if (attaquant==this.possesseur) {
                        this.possesseur.setHorsJeu(true);
                    }
                    joueursOffSide.add(attaquant);
                }
                /* Le mbola misy tsy hors jeu */
                // if (attaquant.getPosition().y - attaquant.getRayon() < (avantDernierDefenseur.getPosition().y - avantDernierDefenseur.getRayon()) &&  avantDernierDefenseur.getPosition().y - avantDernierDefenseur.getRayon() < this.balon.getPosition().y ) {
                //     joueursOffSide.add(attaquant);
                // }
            } 
        }
        
        this.traitement.putLinSideOff(avantDernierDefenseur.getPosition(),rayon);
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
                if (attaquant.getPosition().y > this.balon.getPosition().y ) {
                    if ( attaquant.getPosition().y + attaquant.getRayon() < (avantDernierDefenseur.getPosition().y + avantDernierDefenseur.getRayon()) || avantDernierDefenseur.getPosition().y < this.balon.getPosition().y ) {
                        joueursNoOffSide.add(attaquant);
                    }
                }
            }
            else{
                if (attaquant.getPosition().y < this.balon.getPosition().y) {
                    if ( attaquant.getPosition().y - attaquant.getRayon() > (avantDernierDefenseur.getPosition().y - avantDernierDefenseur.getRayon()) || avantDernierDefenseur.getPosition().y > this.balon.getPosition().y   ) {
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
            
        } 
        else{
            this.versBas = false;
            
        }        
    }
    /* pour le deuxieme image */
    
    public void setBut() throws Exception {
        boolean but = false;
        if (this.possesseur.isHorsJeu()) {
            return;
        }
        if (versBas) {
            but = this.traverseBas.isButTraverse(this.balon, versBas);
        }
        else{
            but = this.traverseHaut.isButTraverse(this.balon, versBas);
        }
        if (but) {
            this.attaquant.addPoint(null,1);
            System.out.println("GOALLLLLLL");    
        }
    }

    /* Fonction complementaire */

    public double checkJoueurNearBallon(Equipe equipe,Joueur[] joueurProche){
        double plusProche = Double.POSITIVE_INFINITY;
        for (int i = 0; i < equipe.getJoueurs().size() ; i++) {
            double distance = this.getDistance( equipe.getJoueurs().get(i).getPosition() , this.balon.getPosition());
            if (distance < plusProche ) {
                plusProche = distance;
                joueurProche[0] = equipe.getJoueurs().get(i);
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
    public boolean isVersBas() {
        return versBas;
    }

    public void setVersBas(boolean versBas) {
        this.versBas = versBas;
    }
    public Joueur getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(Joueur possesseur) {
        this.possesseur = possesseur;
    }
}
