package dto;

public class InfoDetails {
    String nomAttaquant;
    int nbOffSide;
    int nbNoOffSide;
    
    public InfoDetails(String nomAttaquant, int nbOffSide, int nbNoOffSide) {
        this.nomAttaquant = nomAttaquant;
        this.nbOffSide = nbOffSide;
        this.nbNoOffSide = nbNoOffSide;
    }

    public String getNomAttaquant() {
        return nomAttaquant;
    }
    public void setNomAttaquant(String nomAttaquant) {
        this.nomAttaquant = nomAttaquant;
    }
    public int getNbOffSide() {
        return nbOffSide;
    }
    public void setNbOffSide(int nbOffSide) {
        this.nbOffSide = nbOffSide;
    }
    public int getNbNoOffSide() {
        return nbNoOffSide;
    }
    public void setNbNoOffSide(int nbNoOffSide) {
        this.nbNoOffSide = nbNoOffSide;
    }
        
}
