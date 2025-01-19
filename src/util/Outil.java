package util;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import composant.LineSegment;

public class Outil {
    
    public static List<LineSegment> TrieLineSegmentByY(List<LineSegment> lineSegments) {
        Collections.sort(lineSegments, new Comparator<LineSegment>() {
            @Override
            public int compare(LineSegment l1, LineSegment l2) {
                double y1 = Math.min(l1.getStart().y, l1.getEnd().y); // Plus petit Y du premier segment
                double y2 = Math.min(l2.getStart().y, l2.getEnd().y); // Plus petit Y du second segment
                return Double.compare(y1, y2); // Comparaison croissante
            }
        });

        return lineSegments; // Retourne la liste triée
    }
}
