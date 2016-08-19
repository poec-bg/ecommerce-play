package controllers;

import controllers.secure.Check;
import controllers.secure.Secure;
import controllers.secure.Security;
import exceptions.InvalidArgumentException;
import model.Client;
import model.Commande;
import model.Produit;
import model.ProduitPanier;
import play.mvc.Controller;
import play.mvc.With;
import services.CommandeService;
import services.PanierService;
import services.ProduitService;

@With(Secure.class)
@Check({"ADMIN", "CLIENT"})
public class Panier extends Controller {

    public static void voirMonPanier() {
        Client client = null;
        model.Panier panier = null;
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

    public static void panierJson() {
        Client client = null;
        model.Panier panier = null;
        try {
            client = Security.connectedUser();
            notFoundIfNull(client);

            panier = PanierService.get().getPanier(client);
            notFoundIfNull(panier);
        } catch (InvalidArgumentException e) {
            error(e);
        }
        renderJSON(panier);
    }

    public static void ajouterAuPanier(String idProduit) {
        ajouterAuPanierEnNormalOuJson(idProduit);
        flash.success("Le produit a bien été ajouté à votre panier");
        Application.detailProduitTest(idProduit);
    }

    public static void ajouterAuPanierJson(String idProduit) {
        model.Panier panier = ajouterAuPanierEnNormalOuJson(idProduit);
        renderJSON(panier);
    }

    private static model.Panier ajouterAuPanierEnNormalOuJson(String idProduit) {
        Client client = null;
        model.Panier panier = null;
        Produit produit = null;
        try {
            produit = ProduitService.get().getProduit(idProduit);
            notFoundIfNull(produit);

            client = Security.connectedUser();
            notFoundIfNull(client);

            panier = PanierService.get().getPanier(client);
            notFoundIfNull(panier);

            panier = PanierService.get().ajouterProduit(panier, produit);

        } catch (InvalidArgumentException e) {
            error(e);
        }
        return panier;
    }

    public static void modifierQuantite(String idProduit, Integer quantite) {
        model.Panier panier = null;
        Client client = null;
        Produit produit = null;
        try {
            client = Security.connectedUser();
            notFoundIfNull(client);

            panier = PanierService.get().getPanier(client);
            notFoundIfNull(panier);

            produit = ProduitService.get().getProduit(idProduit);

            if(isProduitDansPanier(panier, produit)) {
                PanierService.get().modifierQuantite(panier, produit, quantite);
            } else {
                PanierService.get().ajouterProduit(panier, produit);
                PanierService.get().modifierQuantite(panier, produit, quantite);
            }
        } catch (InvalidArgumentException e) {
            error(e);
        }

        voirMonPanier();
    }

    private static boolean isProduitDansPanier(model.Panier panier, final Produit produit) {
        for (ProduitPanier produitPanier : panier.produits) {
            if(produitPanier.produit.id.equals(produit.id)) {
                return true;
            }
        }
        return false;
    }

    public static void retirer(String idProduit) {
        model.Panier panier = null;
        Client client = null;
        Produit produit = null;
        try {
            client = Security.connectedUser();
            notFoundIfNull(client);

            panier = PanierService.get().getPanier(client);
            notFoundIfNull(panier);

            produit = ProduitService.get().getProduit(idProduit);

            PanierService.get().retirerProduit(panier, produit);
        } catch (InvalidArgumentException e) {
            error(e);
        }

        voirMonPanier();
    }

    public static void passerCommande() {
        Client client = null;
        model.Panier panier = null;
        try {
            client = Security.connectedUser();
            notFoundIfNull(client);

            panier = PanierService.get().getPanier(client);
            notFoundIfNull(panier);

            Commande commande = CommandeService.get().creerDepuisPanier(panier);
            CommandeService.get().enregistrer(commande, panier);

        } catch (InvalidArgumentException e) {
            error(e);
        }

        flash.success(String.format("Votre commande est bien passée"));

        Application.index();
    }

}
