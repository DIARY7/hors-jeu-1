package connection;
import java.sql.*;


public class ConnectionPost{
    Connection con ;
    String user="postgres";
    String mdp ="root";
    String url = "jdbc:postgresql://localhost:5432/hors_jeu";
    String dbDriver = "org.postgresql.Driver";
    Statement stmt;
    //Constructor
    public ConnectionPost() {
        set_user(user);
        set_mdp(this.mdp);
        createConnection();
        creerStatement();
    }
    public void createConnection(){
        try{
            Class.forName(this.dbDriver);
            set_connection(DriverManager.getConnection(this.url, this.user, this.mdp));
            
        }catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }

        
    }
    // Fonction supplementaire
    public void creerStatement(){
        try{
            this.stmt=this.getConnection().createStatement();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public void closeConnection(){
        try{
            this.stmt.close();
            this.con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    //Fonction setters
    public void set_connection(Connection con){
        this.con = con;
    }
    public void set_user(String nomUser ){
        this.user = nomUser;
    }
    public void set_mdp(String mdp){
        this.mdp = mdp;
    }

    //Fonction getters
    public Connection getConnection(){ return this.con; }
    public String getUser(){ return this.user; }
    public String getMdp(){ return this.mdp; }
    public Statement getStatement(){ return this.stmt; }
   
}