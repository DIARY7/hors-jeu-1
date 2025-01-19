package composant;

import org.opencv.core.Point;

public class LineSegment {
    /* Mila triena par rapport a x ary y ny lineSegment  */
    private Point start; // Point de départ de la ligne
    private Point end;   // Point de fin de la ligne
    double width;
    double height;

    public LineSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.width = Math.abs(end.x - start.x);
        this.height = Math.abs(end.y - start.y);
    }

    /* Fonction du jeu */
    public boolean isButTraverse(Ballon ballon,boolean isVersBas){ /* Mijery traverse fotsiny ilay but */
        Point point = ballon.getPosition();
        if (this.start.x < point.x && point.x < this.end.x) {
            if (isVersBas) {
                if ( point.y > this.start.y ) {
                    return true;
                }
                return false;
            }
            else{
                if ( point.y < this.start.y ) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /* Getters and setters */
    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "LineSegment{" +
               "start=" + start +
               ", end=" + end +
               '}';
    }
    
}