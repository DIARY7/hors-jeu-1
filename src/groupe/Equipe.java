package groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import connection.ConnectionPost;
import personnage.Joueur;

public class Equipe {
    int idEquipe;
    String nomEquipe;
    List<Joueur> joueurs;
    int nbBut;

    public Equipe(int idEquipe, String nomEquipe, List<Joueur> joueurs) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.joueurs = joueurs;
        this.TrieJoueursY();
    }
    public Equipe(int idEquipe, String nomEquipe) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
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

    public double getMinYPlayers(){
        return this.getJoueurs().get(0).getPosition().y;
    }
    
    public int getNbBut() {
        return nbBut;
    }

    public void setNbBut(int nbBut) {
        this.nbBut = nbBut;
    }


    public void TrieJoueursY(){ /* Du min Y vers max Y */
        this.getJoueurs().sort(Comparator.comparingDouble(j -> j.getPosition().y));
    }

    /* Fonction Base de donnnees */
    public void addPoint(Connection con,int idMatch) throws Exception{
        if (con==null) {
            ConnectionPost conPost = new ConnectionPost();
            con = conPost.getConnection();
        }
        String sql = "INSERT INTO score (id_equipe, points, id_match) VALUES (?, 1, ?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, this.idEquipe); // Remplacez le premier `?`
            preparedStatement.setInt(2, idMatch); // Remplacez le deuxième `?`
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'insertion dans la table score.");
        }
    }

    public void addArret(Connection con,int idMatch) throws Exception{
        if (con==null) {
            ConnectionPost conPost = new ConnectionPost();
            con = conPost.getConnection();
        }
        String sql = "INSERT INTO arret (id_equipe, nb_arret , id_match) VALUES (?, 1, ?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, this.idEquipe); // Remplacez le premier `?`
            preparedStatement.setInt(2, idMatch); // Remplacez le deuxième `?`
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'insertion dans la table score.");
        }
    }


    public int getPoint(Connection con,int id_match) throws Exception {
        if (con==null) {
            ConnectionPost conPost = new ConnectionPost();
            con = conPost.getConnection();
        }

        String sql ="SELECT id_equipe , SUM(points) as score FROM score WHERE id_match = ? "+ 
        " GROUP BY id_equipe having id_equipe = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id_match);
        pstmt.setInt( 2 , this.idEquipe);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) { // Vérifie s'il y a un résultat
                this.nbBut = rs.getInt("score"); 
                return rs.getInt("score"); // Retourne la colonne "score"

            } else {
                this.nbBut = 0;
                return 0;
            }
        }

    }

    public static int getPoint(Connection con,int idEquipe,int id_match) throws Exception {
        if (con==null) {
            ConnectionPost conPost = new ConnectionPost();
            con = conPost.getConnection();
        }

        String sql ="SELECT id_equipe , SUM(points) as score FROM score WHERE id_match = ? "+ 
        " GROUP BY id_equipe having id_equipe = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id_match);
        pstmt.setInt( 2 ,idEquipe);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) { // Vérifie s'il y a un résultat
                return rs.getInt("score"); // Retourne la colonne "score"

            } else {
                return 0;
            }
        }

    }

    public static int getArret(Connection con,int idEquipe,int id_match ) throws Exception {
        if (con==null) {
            ConnectionPost conPost = new ConnectionPost();
            con = conPost.getConnection();
        }

        String sql ="SELECT id_equipe , SUM(nb_arret) as arret FROM arret WHERE id_match = ? "+ 
        " GROUP BY id_equipe having id_equipe = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id_match);
        pstmt.setInt( 2 ,idEquipe);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) { // Vérifie s'il y a un résultat
                return rs.getInt("arret"); // Retourne la colonne "score"

            } else {
                return 0;
            }
        }

    }

    
}
