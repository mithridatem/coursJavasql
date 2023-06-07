public class Main {
    public static void main(String[] args) {
        //instancier un objet User
        User test = new User("Test4", "Test4", "test4@test.com", "1234");
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
    }
}
