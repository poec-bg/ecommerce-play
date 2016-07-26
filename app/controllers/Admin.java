package controllers;

import exceptions.InvalidArgumentException;
import model.Produit;
import play.mvc.Controller;
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

    public static void enregistrerNouveauProduit(String nom, String description, float prixUnitaire) {
        try {
            Produit produit = ProduitService.get().creer(nom, description, prixUnitaire);
            ProduitService.get().enregistrer(produit);
        } catch (InvalidArgumentException e) {
            error(e);
        }
        produits();
    }
}
