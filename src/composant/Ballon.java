package composant;

import org.opencv.core.Point;

public class Ballon {
    Point position;
    double rayon;
    
    public Ballon(Point position, double rayon) {
        this.position = position;
        this.rayon = rayon;
    }
    
    public Ballon() {
    }
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }
    public double getRayon() {
        return rayon;
    }
    public void setRayon(double rayon) {
        this.rayon = rayon;
    }
}
