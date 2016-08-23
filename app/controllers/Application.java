package controllers;

import exceptions.InvalidArgumentException;
import exceptions.MetierException;
import model.Client;
import model.Produit;
import play.data.validation.Email;
import play.data.validation.Required;
import play.mvc.Controller;
import services.ClientService;
import services.ProduitService;

import java.util.List;

public class Application extends Controller {

    public static void index() {
        List<Produit> produits = ProduitService.get().lister();
        render(produits);
    }

    public static void detailProduit(String idProduit) {
        Produit produit = null;
        try {
            produit = ProduitService.get().getProduit(idProduit);
            notFoundIfNull(produit);
        } catch (InvalidArgumentException e) {
            error(e);
        }
        render(produit);
    }

    public static void nouveauClient() {
        render();
    }

    public static void enregistrerNouveauClient(@Required String nom, @Required String prenom, @Required @Email String email, @Required String motDePasse) throws Throwable {
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            nouveauClient();
        }

        try {
            Client client = ClientService.get().creer(email, motDePasse);
            ClientService.get().modifier(client, nom, prenom, null, null);
            ClientService.get().enregistrer(client);
        } catch (InvalidArgumentException | MetierException e) {
            error(e);
        }
        Application.index();
    }

    public static void presentation(){
        renderTemplate("Application/presentation-responsive.html");
    }

    public static void getAsyncPresentationMessage(String name, String signature){
        String videoCode = "1NKWop13q7I";
        renderTemplate("tags/presentation-message.html", name, signature);
    }

    public static void contact(){
        render();
    }

    public static void agenda(){
        render();
    }

}