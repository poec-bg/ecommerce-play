package controllers;

import controllers.secure.Secure;
import controllers.secure.Security;
import exceptions.InvalidArgumentException;
import model.Client;
import model.Produit;
import play.mvc.Controller;
import play.mvc.With;
import services.PanierService;
import services.ProduitService;

@With(Secure.class)
public class Panier extends Controller {

    public static void voirMonPanier() {
        model.Panier panier = null;
        Client client = null;
        try {
            client = Security.connectedUser();
            notFoundIfNull(client);

            panier = PanierService.get().getPanier(client);
            notFoundIfNull(panier);
        } catch (InvalidArgumentException e) {
            error(e);
        }
        render(panier);
    }

    public static void ajouterAuPanier(String idProduit) {
        Produit produit = null;
        model.Panier panier = null;
        Client client = null;
        try {
            produit = ProduitService.get().getProduit(idProduit);
            notFoundIfNull(produit);

            client = Security.connectedUser();
            notFoundIfNull(client);

            panier = PanierService.get().getPanier(client);
            notFoundIfNull(panier);

            PanierService.get().ajouterProduit(panier, produit);

        } catch (InvalidArgumentException e) {
            error(e);
        }

        flash.success(String.format("Le produit %s a bien été ajouté à votre panier", produit.nom));

        Application.detailProduit(idProduit);
    }

    public static void modifierQuantite(String idProduit, Integer quantite) {
        try {
            Client client =Security.connectedUser();
            Panier panier = PanierService.get().getPanier(client);
            Produit produit = ProduitService.get().getProduit(idProduit);
            PanierService.get().ajouterProduit(panier, produit);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

    }

}
