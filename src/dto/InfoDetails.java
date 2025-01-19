package dto;

public class InfoDetails {
    String nomAttaquant;
    int nbOffSide;
    int nbNoOffSide;
    boolean tireurHjeu;

    public InfoDetails(String nomAttaquant, int nbOffSide, int nbNoOffSide,boolean tireurHjeu) {
        this.nomAttaquant = nomAttaquant;
        this.nbOffSide = nbOffSide;
        this.nbNoOffSide = nbNoOffSide;
        this.tireurHjeu = tireurHjeu;
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
    public String isTireurHjeu() {
        if (tireurHjeu) {
            return "Hors jeu ❌";
        }
        return "Pas hors jeu ✅ ";
    }
    public boolean isHorsJeu(){
        return tireurHjeu;
    }

    public void setTireurHjeu(boolean tireurHjeu) {
        this.tireurHjeu = tireurHjeu;
    }
        
}
