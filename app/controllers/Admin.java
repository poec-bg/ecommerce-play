package controllers;

import exceptions.InvalidArgumentException;
import exceptions.MetierException;
import model.Client;
import model.Produit;
import play.data.validation.Email;
import play.data.validation.Error;
import play.data.validation.Min;
import play.data.validation.Required;
import play.mvc.Controller;
import services.ClientService;
import services.ProduitService;

import java.util.List;

public class Admin extends Controller {

    public static void index() {
        render();
    }

    public static void produits() {
        List<Produit> produits = ProduitService.get().lister();
        render(produits);
    }

    public static void creerNouveauProduit() {
        render();
    }

    public static void enregistrerNouveauProduit(@Required String nom, @Required String description, @Required @Min(0) Float prixUnitaire) {
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            creerNouveauProduit();
        }

        try {
            Produit produit = ProduitService.get().creer(nom, description, prixUnitaire);
            ProduitService.get().enregistrer(produit);
        } catch (InvalidArgumentException e) {
            error(e);
        }
        produits();
    }

    public static void clients() {
        List<Client> clients = ClientService.get().lister();
        render(clients);
    }

    public static void creerNouveauClient() {
        render();
    }

    public static void enregistrerNouveauClient(@Required String nom, @Required String prenom, @Required @Email String email) {

        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            creerNouveauClient();
        }

        try {
            Client client = ClientService.get().creer(email, email);
            ClientService.get().modifier(client, nom, prenom, null, null);
            ClientService.get().enregistrer(client);
        } catch (InvalidArgumentException |MetierException e) {
            error(e);
        }
        clients();
    }
}
