import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Request {
    //Attributs paramètres BDD
    static final String DB_URL = "jdbc:mysql://localhost/Javacours?serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    //Connexion à la BDD
    private static Connection connexion;
    static{
        try{
            connexion = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static User addUser(User user){
        //instancier un Objet User null
        User userAdd = null;
        try {
            //1 Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //2 Requête SQL
            String sql = "INSERT INTO users (nom, prenom, email,pwd )VALUES (?, ?, ?, ?)";
            //3 préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //4 Bind des paramètres
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPwd());
            //5 Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //6 Test si la requête à été effectué
            if(addedRows>0){
                //assigner un user à userAdd
                userAdd = new User(user.getNom(), user.getPrenom(), user.getEmail(), user.getPwd());
            }
            //7 Fermer la connexion à la BDD
            //stmt.close();
            //connexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAdd;
    }
    public static User getUserByMail(User user){
        //instancier un objet User null
        User userGet = null;
        try {
            //1 Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //2 Requête SQL
            String sql = "SELECT id,nom, prenom, email, pwd FROM users WHERE email =?";
            //3 préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //4 Bind des paramètres
            preparedStatement.setString(1, user.getEmail());
            //5 Exécution de la requête
            ResultSet rs = preparedStatement.executeQuery();
            //6 Test si la requête à été effectué
            //Parcours du résultat
            //Si la réponse est différente de null
            if (rs.next()){
                if (rs.getString(1)!= null){
                    userGet = new User();
                    userGet.setId(Integer.parseInt(rs.getString("id")));
                    //userGet.setId(rs.getInt("id"));
                    userGet.setNom(rs.getString("nom"));
                    userGet.setPrenom(rs.getString("prenom"));
                    userGet.setEmail(rs.getString("email"));
                    userGet.setPwd(rs.getString("pwd"));
                }
            }
            //7 Fermer la connexion à la BDD
            //stmt.close();
            //connexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userGet;
    }
    public static List<User> getAllUser(){
        //instance d'une liste
        List<User> liste = new ArrayList<>();
        try {
            //1 Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //2 Requête SQL
            String sql = "SELECT id,nom, prenom, email, pwd FROM users";
            //3 préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //5 Exécution de la requête
            ResultSet rs = preparedStatement.executeQuery();
            //Vérification si on à des enregistrements
            while (rs.next()){
                //test si l'id existe
                if(rs.getString("id")!= null){
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setEmail(rs.getString("email"));
                    user.setPwd(rs.getString("pwd"));
                    //ajout du compte à la liste
                    liste.add(user);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //retourne la liste
        return liste;
    }
    public static User updateUser(User user, String nom, String prenom, String email, String pwd){
        User userUpdate = null;
        try{
            Statement stmt = connexion.createStatement();
            //2 Requête SQL
            String sql = "UPDATE users set nom = ?, prenom = ?, email = ?, pwd = ? WHERE id = ?";
            //3 préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //bind des paramètres
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, pwd);
            preparedStatement.setInt(5, user.getId());
            //5 Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //6 Test si la requête à été effectué
            if(addedRows>0){
                //assigner un user à userAdd
                userUpdate = new User(nom, prenom, email, pwd);
                userUpdate.setId(user.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userUpdate;
    }
}
