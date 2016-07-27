package controllers;

import controllers.secure.Secure;
import controllers.secure.Security;
import exceptions.InvalidArgumentException;
import exceptions.MetierException;
import model.Client;
import model.Produit;
import play.data.validation.Email;
import play.data.validation.Required;
import play.mvc.Controller;
import services.ClientService;
import services.ProduitService;
import services.CommandeService;
import services.PanierService;

import java.util.List;

public class Commande extends Controller {

    public static void creerCommande(){
        Client client = null;

        try {
            client = Security.connectedUser();

            model.Panier panier = PanierService.get().getPanier(client);
            model.Commande commande = CommandeService.get().creerDepuisPanier(panier);
            CommandeService.get().enregistrer(commande, panier);

        } catch (InvalidArgumentException e) {
            e.printStackTrace();

        }
        flash.success(String.format("Votre Panier a bien été commandé"));
        Application.index();


    }

}