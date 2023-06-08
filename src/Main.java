import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        //instancier un objet User
        User test = new User("Test4", "Test4", "test5@test.com", "1234");
        //Appel de la fonction getUserByMail
        User getUser = Request.getUserByMail(test);
        //test si un compte existe
        if (getUser !=null){
            System.out.println("Le compte : \n-"+getUser.getId()+" "+getUser.getEmail()+" existe déja en BDD");
        }
        //sinon pas de compte
        else{
            System.out.println("Le compte : "+Request.addUser(test).getNom()+" a été ajouté en BDD");
        }
        */
        //stocke la liste des User
        /*var allUser = Request.getAllUser();

        System.out.println("Liste des comptes utilisateur en BDD :");
        //parcours la liste
        for (User value : allUser) {
            //Affichage des comptes utilisateurs
            System.out.println("-" + value.getId() + " " + value.getNom() + " " + value.getPrenom() + " "
                    + value.getEmail() + " " + value.getPwd());
        }
        //penser à passer dans le premier paramètre un compte qui existe (qui à un id valide)
        User userUp = Request.updateUser(allUser.get(0), "Mithridate", "test", allUser.get(0).getEmail(), "14852");
        if (userUp != null)
            System.out.println("Le compte " + allUser.get(0).getNom() + " a été mis à jour");
        else
            System.out.println("Le compte n'existe pas");*/
        System.out.println("Choisir une option :\n-1: Ajouter un compte\n-2: Modifier un compte\n-3: Afficher tous les comptes");
        int choix = Integer.parseInt(scanner.next());
        switch (choix){
            case 1:
                createUserByScan(scanner);
                break;
            case 2:
                updateUserByScan(scanner);
                break;
            case 3:
                getAllUserByScan(scanner);
                break;
            default:
                System.out.println("Le choix n'est pas valide");
        }
    }
    //fonction pour ajouter un utilisateur avec la console
    private static void createUserByScan(Scanner scanner){
        System.out.println("Ajouter un utilisateur en BDD");
        String nom = lire(scanner, "veuillez saisir un nom :");
        String prenom = lire(scanner, "veuillez saisir un prénom :");
        String email = lire(scanner, "veuillez saisir un email :");
        String pwd = lire(scanner, "veuillez saisir un mot de passe :");
        //constuction d'un objet User
        User newUser = new User(nom,prenom,email,pwd);
        //test si le compte existe
        if(Request.getUserByMail(newUser)!=null){
            System.out.println("Le compte : "+newUser.getNom()+" existe déja en BDD");
        }
        //sinon ajoute le compte en BDD
        else{
            System.out.println("Le compte : "+Request.addUser(newUser).getEmail()+"a été ajouté en BDD");
        }
    }
    private static void updateUserByScan(Scanner scanner){
        System.out.println("Modifier un utilisateur en BDD");
        String email = lire(scanner, "Veuillez renseigner votre email");
        User recup = Request.getUserByMail(new User("","",email,"pwd"));
        //test si le compte existe
        if(recup!=null){
            //constuction d'un objet User
            String nom = lire(scanner, "Veuillez renseigner votre nom");
            String prenom = lire(scanner, "Veuillez renseigner votre prenom");
            String pwd = lire(scanner, "Veuillez renseigner votre password");
            System.out.println("Le compte : "+Request.updateUser(recup, nom, prenom, email, pwd).getNom()+" a été modifié");
        }
        else{
            System.out.println("Le compte : "+email+" n'existe pas");
        }
    }
    private static void getAllUserByScan(Scanner scanner){
        System.out.println("Liste des utilisateurs");
        List<User> liste = Request.getAllUser();
        if(liste!=null){
            for (User value:liste) {
                System.out.println("-id: "+value.getId()+" nom:"+value.getNom()+" prenom: "+value.getPrenom()+" email: "+value.getEmail());
            }
        }
        else{
            System.out.println("Il n'y à pas de compte dans la base de données");
        }
    }
    //fonction récupérer du contenu
    private static String lire(Scanner scanner,String message) {
        System.out.println(message);
        return scanner.next();
    }
}
