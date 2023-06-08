import java.util.List;
public class Main {
    public static void main(String[] args) {
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
        var allUser = Request.getAllUser();

        System.out.println("Liste des comptes utilisateur en BDD :");
        //parcours la liste
        for (User value:allUser) {
            //Affichage des comptes utilisateurs
            System.out.println("-"+value.getId()+" "+value.getNom()+" "+value.getPrenom()+" "
            +value.getEmail()+" "+value.getPwd());
        }
        //penser à passer dans le premier paramètre un compte qui existe (qui à un id valide)
        User userUp = Request.updateUser(allUser.get(0), "Mithridate", "test", allUser.get(0).getEmail(), "14852");
        if(userUp!=null)
            System.out.println("Le compte "+allUser.get(0).getNom()+" a été mis à jour");
        else
            System.out.println("Le compte n'existe pas");
    }
}
