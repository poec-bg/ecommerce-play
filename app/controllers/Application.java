package controllers;

import exceptions.InvalidArgumentException;
import model.Client;
import model.Panier;
import model.Produit;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import services.ClientService;
import services.PanierService;
import services.ProduitService;

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

}