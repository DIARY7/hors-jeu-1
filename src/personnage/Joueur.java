package personnage;

import org.opencv.core.Point;

public class Joueur {
    Point position;
    int id_equipe; /* 0 si c'est rouge , 1 si c'est bleu */
    double rayon;

    public Joueur(Point position, int id_equipe,double rayon) {
        this.position = position;
        this.id_equipe = id_equipe;
        this.rayon = rayon;
    }

    public Joueur() {
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }
    
}
