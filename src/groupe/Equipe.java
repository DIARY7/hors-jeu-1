package groupe;

import java.util.Comparator;
import java.util.List;

import personnage.Joueur;

public class Equipe {
    int idEquipe;
    String nomEquipe;
    List<Joueur> joueurs;
    int camp; /* 0 izy raha le gardien d'equipe ambony , 1 raha gardien d'equipe ambany  */
    
    public Equipe(int idEquipe, String nomEquipe, List<Joueur> joueurs) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.joueurs = joueurs;
        this.TrieJoueursY();
    }

    public Equipe() {
        this.TrieJoueursY();
    }
    
    public int getIdEquipe() {
        return idEquipe;
    }
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }
    public String getNomEquipe() {
        return nomEquipe;
    }


    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }
    public List<Joueur> getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public double getMinYPlayers(){
        return this.getJoueurs().get(0).getPosition().y;
    }

    public void TrieJoueursY(){ /* Du min Y vers max Y */
        this.getJoueurs().sort(Comparator.comparingDouble(j -> j.getPosition().y));
    }
    
}
